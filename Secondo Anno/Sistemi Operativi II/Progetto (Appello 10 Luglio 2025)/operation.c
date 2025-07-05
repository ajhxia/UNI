#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <string.h>
#include "operation.h"
#include "threads_worker.h"
#include "utility.h"

ComplexNumber complex_multiply(ComplexNumber a, ComplexNumber b) {
    ComplexNumber result;
    result.re = (a.re * b.re) - (a.im * b.im); /* Parte reale: a.re * b.re - a.im * b.im */
    result.im = (a.re * b.im) + (a.im * b.re); /* Parte immaginaria: a.re * b.im + a.im * b.re*/
    return result;
}

ComplexNumber complex_add(ComplexNumber a, ComplexNumber b) {
    ComplexNumber result;
    result.re = a.re + b.re; /* Somma delle parti reali*/
    result.im = a.im + b.im; /* Somma delle parti immaginarie*/
    return result;
}

ComplexNumber **allocate_and_copy_matrix(ComplexNumber **source, int size) {
    ComplexNumber **dest = malloc(size * sizeof(ComplexNumber *)); /* alloca un array di puntatori a numeri complessi*/
    int i;
    for (i = 0; i < size; i++) {
        dest[i] = malloc(size * sizeof(ComplexNumber));
        int j;
        for (j = 0; j < size; j++) {
            dest[i][j] = source[i][j];
        }
    }
    return dest;
}

ComplexNumber **allocate_empty_matrix(int size) {
    ComplexNumber **matrix = malloc(size * sizeof(ComplexNumber *));
    int i;
    for (i = 0; i < size; i++) {
        matrix[i] = malloc(size * sizeof(ComplexNumber));
    }
    return matrix;
}

ComplexNumber **build_circuit_matrix(const CircuitDef *circuit, int num_threads) {
    if (circuit->circ_len == 0 || circuit->count_n == 0) return NULL;

    int size = circuit->gates[0].size;

    /* 1. Crea un array di matrici da moltiplicare (da Gk-1 a G0)*/
    int k = circuit->circ_len;
    ComplexNumber ***matrices = malloc(k * sizeof(ComplexNumber **)); /* alloca un array di puntatori a matrici complesse*/
    int matrices_count = 0;

    int i;
    for (i = k - 1; i >= 0; i--) {
        const char *gate_name = circuit->circ_sequence[i]; /* nome del gate corrente*/
        Gate *gate = NULL;
        int j;
        for (j = 0; j < circuit->count_n; j++) {
            if (strcmp(circuit->gates[j].name, gate_name) == 0) {
                gate = &circuit->gates[j]; /* trova il gate corrispondente*/
                break;
            }
        }

        if (!gate) {
            fprintf(stderr, "Gate \"%s\" non trovato!\n", gate_name);
            exit(EXIT_FAILURE);
        }

        matrices[matrices_count++] = allocate_and_copy_matrix(gate->matrix, size); /* alloca e copia la matrice del gate corrente*/
    }

    /* 2. Moltiplicazioni parallele a coppie fino a una sola matrice*/
    while (matrices_count > 1) {
        int new_count = (matrices_count + 1) / 2;
        ComplexNumber ***new_matrices = malloc(new_count * sizeof(ComplexNumber **)); /* alloca un nuovo array di matrici per i risultati*/
        ThreadMatrixArgs *args = malloc((matrices_count / 2) * sizeof(ThreadMatrixArgs)); /* alloca un array di argomenti per i thread*/
        pthread_t *threads = malloc(num_threads * sizeof(pthread_t)); /* alloca un array di thread*/

        int thread_idx = 0;  /* thread lanciati nel blocco corrente*/
        int pair_idx = 0;    /* indice delle coppie da processare*/
        int new_matrix_idx = 0; /* indice per le nuove matrici*/

        while (pair_idx + 1 <= matrices_count - 1) {
            thread_idx = 0; /* Reset del contatore dei thread per il nuovo batch*/

            /* Lancia fino a num_threads thread*/
            for (; thread_idx < num_threads && pair_idx + 1 <= matrices_count - 1; thread_idx++, pair_idx += 2, new_matrix_idx++) {
                ComplexNumber **A = matrices[pair_idx]; /* matrice corrente*/
                ComplexNumber **B = matrices[pair_idx + 1]; /* matrice successiva*/
                ComplexNumber **res = allocate_empty_matrix(size); /* alloca una matrice vuota per il risultato*/
                new_matrices[new_matrix_idx] = res; /* salva la matrice risultante*/

                args[thread_idx] = (ThreadMatrixArgs){A, B, res, size}; /* prepara gli argomenti per il thread*/
                if (pthread_create(&threads[thread_idx], NULL, matrix_multiply_thread, &args[thread_idx]) != 0) {
                    perror("pthread_create");
                    exit(EXIT_FAILURE);
                }
            }

            /* Attendi tutti i thread di questo batch*/
            int t;
            for (t = 0; t < thread_idx; t++) {
                pthread_join(threads[t], NULL); /* attende il completamento di ogni thread*/
            }
        }

        /* Se dispari, ultima matrice passa direttamente*/
        if (matrices_count % 2 == 1) {
            new_matrices[new_count - 1] = matrices[matrices_count - 1]; /* copia l'ultima matrice se dispari*/
        }

        /* Libera vecchie matrici usate*/
        int i;
        for (i = 0; i + 1 < matrices_count; i += 2) {
            int r;
            for (r = 0; r < size; r++) {
                free(matrices[i][r]); /* libera le righe della matrice corrente*/
                free(matrices[i + 1][r]); /* libera le righe della matrice successiva*/
            }
            free(matrices[i]); /* libera la matrice corrente*/
            free(matrices[i + 1]); /* libera la matrice successiva*/
        }

        free(matrices); /* libera l'array di matrici*/
        free(threads); /* libera l'array di thread*/
        free(args); /* libera l'array di argomenti per i thread*/

        matrices = new_matrices;
        matrices_count = new_count;
    }

    ComplexNumber **final_matrix = matrices[0]; /* la matrice finale è l'unica rimasta*/
    free(matrices);

    return final_matrix;
}

ComplexNumber *complex_matrix_vector_multiply(ComplexNumber **matrix, ComplexNumber *vector, int size) {
    ComplexNumber *result = malloc(size * sizeof(ComplexNumber)); /* alloca un array di numeri complessi per il risultato*/
    if (!result) return NULL;

    int i;
    for (i = 0; i < size; ++i) {
        result[i].re = 0.0;
        result[i].im = 0.0;
        int j;
        for (j = 0; j < size; ++j) {
            ComplexNumber prod = complex_multiply(matrix[i][j], vector[j]); /* moltiplica l'elemento della matrice per l'elemento del vettore*/
            result[i] = complex_add(result[i], prod); /* somma il prodotto al risultato*/
        }
    }
    return result;
}
