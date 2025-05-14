#include <stdio.h>
#include <stdlib.h>
#include "file_reader.h"

// Libera tutta la memoria
void free_circuit(CircuitDef *c) {
    for(int i=0;i<c->count_n;i++)
        free(c->gates[i].value);
    free(c->gates);
    free(c->circ_sequence);
}

int main(){
    char *filename1 = "init-ex.txt";
    char *file1 = read_file_and_print_lines(filename1);

    char *filename2 = "circ-ex.txt";
    char *file2 = read_file_and_print_lines(filename2);

    if (file1 == NULL) {
        return 1;
    }

    if (file2 == NULL) {
        return 1;
    }

    free(file1);

    CircuitDef circ = split_function_define_circle(file2);
    free_circuit(&circ);
    return 0;
}
