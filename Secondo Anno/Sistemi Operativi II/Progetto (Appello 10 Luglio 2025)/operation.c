#include <stddef.h>
#include <stdlib.h>
#include <stdio.h>
#include "operation.h"

#include <string.h>
#include "utility.h"
//
// Created by alebox on 27/06/25.
//

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

ComplexNumber **build_circuit_matrix(const CircuitDef *circuit) {
    if (circuit->circ_len == 0 || circuit->count_n == 0) return NULL;

    int size = circuit->gates[0].size; // assumiamo dimensioni uniformi
    ComplexNumber **result_matrix = NULL;

    // processa i gate in ordine inverso
    for (int i = circuit->circ_len - 1; i >= 0; i--) {
        const char *gate_name = circuit->circ_sequence[i];
        Gate *gate = NULL;

        // trova il gate corrispondente
        for (int j = 0; j < circuit->count_n; j++) {
            if (strcmp(circuit->gates[j].name, gate_name) == 0) {
                gate = &circuit->gates[j];
                break;
            }
        }

        if (!gate) {
            fprintf(stderr, "Gate \"%s\" non trovato!\n", gate_name);
            exit(EXIT_FAILURE);
        }

        if (!result_matrix) {
            // copia iniziale
            result_matrix = allocate_and_copy_matrix(gate->matrix, size);
        } else {
            // moltiplicazione: result_matrix = result_matrix * gate->matrix
            ComplexNumber **new_result = allocate_empty_matrix(size);

            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    ComplexNumber sum = {0.0, 0.0};
                    for (int k = 0; k < size; k++) {
                        ComplexNumber prod = complex_multiply(result_matrix[r][k], gate->matrix[k][c]);
                        sum = complex_add(sum, prod);
                    }
                    new_result[r][c] = sum;
                }
            }

            // libera la matrice precedente
            for (int r = 0; r < size; r++) {
                free(result_matrix[r]);
            }
            free(result_matrix);

            result_matrix = new_result;
        }
    }

    return result_matrix;
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
