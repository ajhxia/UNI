//
// Created by alebox on 20/06/25.
//

#ifndef UTILITY_H
#define UTILITY_H

typedef struct {
    double re; // parte reale
    double im; // parte immaginaria
} ComplexNumber;

typedef struct {
    ComplexNumber *value; // array di numeri complessi che rappresentano lo stato
    char *qubits; // stringa di qubit, es. "Y X I"
    int count_n; // numero di qubit
} InitValue;

typedef struct {
    char name; // nome del gate, es. 'X', 'Y', 'I'
    int size; // dimensione della matrice NxN
    ComplexNumber **matrix; // matrice NxN di numeri complessi
} Gate;

typedef struct {
    Gate *gates;            // array dinamico di Gate
    int count_n;            // quanti gate ho
    char *circ_sequence;    // es. "Y X I"
    int circ_len;           // lunghezza di circ_sequence
} CircuitDef;

char *name_function();
char *read_file(const char *filename);
#endif //UTILITY_H
