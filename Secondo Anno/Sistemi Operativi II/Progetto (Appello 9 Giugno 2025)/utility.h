//
// Created by acassetta on 14/05/25.
//

#ifndef UTILITY_H
#define UTILITY_H
typedef struct {
    double re;
    double im;
} ComplexNumber;

typedef struct {
    ComplexNumber *value;
    char *qubits;
    int count_n;
} InitValue;

typedef struct {
    char name;
    int size; // dimensione della matrice NxN
    ComplexNumber **matrix;
} Gate;

typedef struct {
    Gate *gates;            // array dinamico di Gate
    int count_n;            // quanti gate ho
    char *circ_sequence;    // es. "Y X I"
    int circ_len;           // lunghezza di circ_sequence
} CircuitDef;

int check_normalization(ComplexNumber *state, int size, double epsilon);
void trim_leading_spaces(char **str);
void trim_trailing_spaces_and_parens(char *str);
int is_perfect_square(int n, int *root_out);
ComplexNumber parse_complex(const char *s);
#endif //UTILITY_H
