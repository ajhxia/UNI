//
// Created by cales on 12/05/2025.
//

#ifndef LETTURA_FILE_H
#define LETTURA_FILE_H

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
    ComplexNumber *value;
    int count_n;
} Gate;

typedef struct {
    Gate *gates;            // array dinamico di Gate
    int count_n;            // quanti gate ho
    char *circ_sequence;    // es. "Y X I"
    int circ_len;           // lunghezza di circ_sequence
} CircuitDef;

char *read_file_and_print_lines(char *filename);
InitValue split_function_init(char *var);
CircuitDef split_function_define_circle(char *var);
void free_init_value(InitValue *iv);


#endif //LETTURA_FILE_H
