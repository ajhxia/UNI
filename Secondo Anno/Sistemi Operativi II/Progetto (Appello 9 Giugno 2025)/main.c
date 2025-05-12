#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lettura_file.h"

int main(){
    char *filename1 = "init-ex.txt";
    char *file1 = read_file_and_print_lines(filename1);

    if (file1 == NULL) {
        return 1;
    }

    InitValue init = split_function_init(file1);
    printf("Qubits: %s\n", init.qubits ? init.qubits : "Nessuno");
    printf("Valori iniziali:\n");
    for (int i = 0; i < init.count_n; i++) {
        printf("(%lf, %lf)\n", init.value[i].re, init.value[i].im);
    }

    free(file1);

    return 0;
}
