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
    ComplexNumber *value;
    int count_n;
}CircDef;

char *read_file_and_print_lines(char *filename);
InitValue split_function_init(char *var);
void split_function_define_circle(char *var);
void free_init_value(InitValue *iv);

#endif //LETTURA_FILE_H
