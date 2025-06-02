//
// Created by acassetta on 15/05/25.
//

#include <stdio.h>
#include "operation.h"
#include <stdlib.h>
#include "utility.h"

ComplexNumber complex_multiply(ComplexNumber a, ComplexNumber b) {
    ComplexNumber result;
    result.re = a.re * b.re - a.im * b.im; // Parte reale: a.re * b.re - a.im * b.im
    result.im = a.re * b.im + a.im * b.re; // Parte immaginaria: a.re * b.im + a.im * b.re
    return result;
}

ComplexNumber complex_add(ComplexNumber a, ComplexNumber b) {
    ComplexNumber result;
    result.re = a.re + b.re; // Somma delle parti reali
    result.im = a.im + b.im; // Somma delle parti immaginarie
    return result;
}

void print_state(ComplexNumber *state, int size) {
    for (int i = 0; i < size; ++i) {
        double re = state[i].re;
        double im = state[i].im;

        if (im < 0)
            printf("(%g - %gi)", re, -im);
        else
            printf("(%g + %gi)", re, im);
    }
    printf("\n");
}

ComplexNumber *complex_matrix_vector_multiply(ComplexNumber **matrix, ComplexNumber *vector, int size) {
    ComplexNumber *result = malloc(size * sizeof(ComplexNumber)); // alloca un array di numeri complessi per il risultato
    if (!result) return NULL;

    for (int i = 0; i < size; ++i) {
        result[i].re = 0.0;
        result[i].im = 0.0;
        for (int j = 0; j < size; ++j) {
            ComplexNumber prod = complex_multiply(matrix[i][j], vector[j]); // moltiplica l'elemento della matrice per l'elemento del vettore
            result[i] = complex_add(result[i], prod);
        }
    }
    return result;
}

ComplexNumber **allocate_and_copy_matrix(ComplexNumber **source, int size) {
    ComplexNumber **dest = malloc(size * sizeof(ComplexNumber *)); // alloca un array di puntatori a numeri complessi
    for (int i = 0; i < size; i++) {
        dest[i] = malloc(size * sizeof(ComplexNumber));
        for (int j = 0; j < size; j++) {
            dest[i][j] = source[i][j];
        }
    }
    return dest;
}

ComplexNumber **allocate_empty_matrix(int size) {
    ComplexNumber **matrix = malloc(size * sizeof(ComplexNumber *));
    for (int i = 0; i < size; i++) {
        matrix[i] = malloc(size * sizeof(ComplexNumber));
    }
    return matrix;
}

ComplexNumber **build_total_circuit_matrix(const CircuitDef *circuit) {
    int size = circuit->gates[0].size; // assumo che tutti i gate abbiano la stessa dimensione
    ComplexNumber **result_matrix = NULL;

    for (int i = circuit->circ_len - 1; i >= 0; i--) {
        char gate_name = circuit->circ_sequence[i]; // ottiene il nome del gate dalla sequenza del circuito
        Gate *gate = NULL;

        // trova il gate corrispondente nella definizione del circuito
        for (int j = 0; j < circuit->count_n; j++) {
            if (circuit->gates[j].name == gate_name) {
                gate = &circuit->gates[j];
                break;
            }
        }

        if (!gate) {
            printf("Gate %c non trovato!\n", gate_name);
            continue;
        }

        // se result_matrix è NULL, alloca e copia la matrice del gate corrente
        if (!result_matrix) {
            result_matrix = allocate_and_copy_matrix(gate->matrix, size);
        } else {
            ComplexNumber **new_result = allocate_empty_matrix(size);

            // moltiplicazione: new_result = result_matrix * gate->matrix
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    ComplexNumber sum = {0.0, 0.0};
                    for (int k = 0; k < size; k++) {
                        // moltiplica l'elemento della matrice result_matrix per l'elemento della matrice del gate
                        ComplexNumber prod = complex_multiply(result_matrix[r][k], gate->matrix[k][c]);
                        sum = complex_add(sum, prod);
                    }
                    new_result[r][c] = sum;
                }
            }

            // deallocazione sicura della matrice precedente
            for (int r = 0; r < size; r++) {
                free(result_matrix[r]);
            }
            free(result_matrix);
            result_matrix = NULL; // evitare dangling pointer


            result_matrix = new_result;
        }
    }

    return result_matrix;
}

void free_complex_matrix(ComplexNumber **matrix, int size) {
    for (int i = 0; i < size; ++i) {
        free(matrix[i]);
    }
    free(matrix);
}

void free_circuit(CircuitDef *circ) {
    if (circ == NULL) return;

    // Libera ogni matrice di gate
    for (int i = 0; i < circ->count_n; i++) {
        Gate *g = &circ->gates[i];
        if (g->matrix) {
            for (int r = 0; r < g->size; r++) {
                free(g->matrix[r]);
            }
            free(g->matrix);
            g->matrix = NULL;
        }
    }

    // Libera l’array di gate
    free(circ->gates);
    circ->gates = NULL;

    // Libera la stringa circ_sequence
    free(circ->circ_sequence);
    circ->circ_sequence = NULL;

    circ->count_n = 0;
    circ->circ_len = 0;
}
