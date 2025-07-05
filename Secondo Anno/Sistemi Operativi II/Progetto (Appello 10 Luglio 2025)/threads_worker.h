#ifndef THREADS_WORKER_H
#define THREADS_WORKER_H
#include "utility.h"  /* definizione ComplexNumber */

typedef struct {
    ComplexNumber **A;
    ComplexNumber **B;
    ComplexNumber **result;
    int size;
} ThreadMatrixArgs;

/* Funzione da eseguire nel thread */
void *matrix_multiply_thread(void *arg);

/* Allocazione e moltiplicazione diretta (helper) */
ComplexNumber **multiply_matrices(ComplexNumber **A, ComplexNumber **B, int size);
#endif /*THREADS_WORKER_H */
