//
// Created by alebox on 02/07/25.
//
#include "threads_worker.h"
#include "utility.h"
#include "operation.h"
#include <stdlib.h>

void *matrix_multiply_thread(void *arg) {
    ThreadMatrixArgs *args = (ThreadMatrixArgs *)arg;
    int size = args->size;

    for (int r = 0; r < size; r++) {
        for (int c = 0; c < size; c++) {
            ComplexNumber sum = {0.0, 0.0};
            for (int k = 0; k < size; k++) {
                ComplexNumber prod = complex_multiply(args->A[r][k], args->B[k][c]); // moltiplica l'elemento della riga r di A per l'elemento della colonna c di B
                sum = complex_add(sum, prod); // somma il prodotto al totale per la cella (r, c)
            }
            args->result[r][c] = sum;
        }
    }

    return NULL;
}

ComplexNumber **multiply_matrices(ComplexNumber **A, ComplexNumber **B, int size) {
    ComplexNumber **result = allocate_empty_matrix(size); // alloca una matrice vuota per il risultato
    ThreadMatrixArgs args = {A, B, result, size}; // prepara gli argomenti per il thread
    matrix_multiply_thread(&args); // chiama la funzione di moltiplicazione in un thread
    return result;
}
