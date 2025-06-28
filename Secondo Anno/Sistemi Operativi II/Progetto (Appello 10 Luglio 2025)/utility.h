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
    char *name; // nome del gate, es. 'X', 'Y', 'I'
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
void free_circuit(CircuitDef *circ);
int read_thread_input();
void print_state(ComplexNumber *state, int size);
void free_init_value(InitValue *iv);
void free_complex_matrix(ComplexNumber **matrix, int size);
void run_circuit(const InitValue *init, ComplexNumber **matrix, int size);
#endif //UTILITY_H
