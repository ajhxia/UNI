#include <stdio.h>
#include <stdlib.h>
#include "function.h"

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

    CircDef circ = split_function_define_circle(file2);

    for (int i = 0; i < circ.count_n; i++) {
        printf("Gate %c:\n", circ.gates[i].name);
        for (int j = 0; j < circ.gates[i].count_n; j++) {
            printf("  (%.2f, %.2f)\n", circ.gates[i].value[j].re, circ.gates[i].value[j].im);
        }
        free(circ.gates[i].value); // libera ogni array di ComplexNumber
    }
    free(circ.gates); // libera array di Gate

    free(file1);

    return 0;
}
