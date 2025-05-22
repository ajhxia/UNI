//
// Created by cales on 12/05/2025.
//

#ifndef LETTURA_FILE_H
#define LETTURA_FILE_H
#include "utility.h"

char *read_file_and_print_lines(char *filename);
InitValue split_function_init(char *var);
CircuitDef split_function_define_circle(char *var);
void free_init_value(InitValue *iv);
void free_circuit(CircuitDef *circ);
#endif //LETTURA_FILE_H
