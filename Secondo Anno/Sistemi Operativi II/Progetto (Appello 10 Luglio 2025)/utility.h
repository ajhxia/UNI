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
void trim_leading_spaces(char **str);
void trim_trailing_spaces_and_parens(char *str);
ComplexNumber parse_complex(const char *s);
ComplexNumber complex_multiply(ComplexNumber a, ComplexNumber b);
ComplexNumber complex_add(ComplexNumber a, ComplexNumber b);
#endif //UTILITY_H
