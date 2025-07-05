#ifndef UTILITY_H
#define UTILITY_H

typedef struct {
    long double re; /* parte reale */
    long double im; /* parte immaginaria */
} ComplexNumber;

typedef struct {
    ComplexNumber *value; /* array di numeri complessi che rappresentano lo stato */
    char *qubits; /* stringa di qubit, es. "Y X I" */
    int count_n; /* numero di qubit */
} InitValue;

typedef struct {
    char *name; /* nome del gate, es. 'X', 'Y', 'I' */
    int size; /* dimensione della matrice NxN */
    ComplexNumber **matrix; /* matrice NxN di numeri complessi */
} Gate;

typedef struct {
    Gate *gates;
    int count_n;
    char **circ_sequence;
    int circ_len;
} CircuitDef;

char *name_function();
char *read_file(const char *filename);
void trim_leading_spaces(char **str);
void trim_trailing_whitespace(char *str);
void trim_trailing_spaces_and_parens(char *str);
ComplexNumber parse_complex(const char *s);
void free_circuit(CircuitDef *circ);
int read_thread_input();
void print_state(ComplexNumber *state, int size);
void free_init_value(InitValue *iv);
void free_complex_matrix(ComplexNumber **matrix, int size);
void run_circuit(const InitValue *init, ComplexNumber **matrix, int size);
#endif /*UTILITY_H */
