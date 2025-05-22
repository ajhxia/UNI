#include <stdio.h>
#include <stdlib.h>
#include "file_reader.h"
#include "operation.h"
#include "utility.h"
int load_and_parse_data(char *filename1, char *filename2, InitValue *init, CircuitDef *circuit) {
    char *file1 = read_file_and_print_lines(filename1);
    char *file2 = read_file_and_print_lines(filename2);

    if (!file1 || !file2) {
        free(file1);
        free(file2);
        return 0;
    }

    *init = split_function_init(file1);
    *circuit = split_function_define_circle(file2);

    free(file1);
    free(file2);

    if (init->count_n == 0 || circuit->count_n == 0 || circuit->circ_len == 0) {
        return 0;
    }

    return 1;
}

void run_circuit(const InitValue *init, ComplexNumber **matrix, int size) {
    ComplexNumber *final_state = complex_matrix_vector_multiply(matrix, init->value, size);
    printf("Stato finale:\n");
    print_state(final_state, size);
    free(final_state);
}

int main() {
    InitValue init;
    CircuitDef circuit;

    if (!load_and_parse_data("init-ex.txt", "circ-ex.txt", &init, &circuit)) {
        printf("Errore nei dati di input\n");
        return 1;
    }

    ComplexNumber **circuit_matrix = build_total_circuit_matrix(&circuit);
    if (!circuit_matrix) {
        printf("Errore durante la costruzione della matrice del circuito\n");
        free_init_value(&init);
        free_circuit(&circuit);
        return 1;
    }

    run_circuit(&init, circuit_matrix, circuit.gates[0].size);

    free_complex_matrix(circuit_matrix, circuit.gates[0].size);
    free_init_value(&init);
    free_circuit(&circuit);

    return 0;
}
