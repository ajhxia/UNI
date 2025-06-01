#include <stdio.h>
#include <stdlib.h>
#include "file_reader.h"
#include "operation.h"
#include "utility.h"

/** * Carica e analizza i dati dai file di input.
 * @param filename1 Nome del file contenente lo stato iniziale.
 * @param filename2 Nome del file contenente la definizione del circuito.
 * @param init Puntatore alla struttura InitValue da riempire.
 * @param circuit Puntatore alla struttura CircuitDef da riempire.
 * @return 1 se il caricamento e l'analisi sono riusciti, 0 altrimenti.
 */
int load_and_parse_data(char *filename1, char *filename2, InitValue *init, CircuitDef *circuit) {
    char *file1 = read_file_and_print_lines(filename1); // legge il file di stato iniziale
    char *file2 = read_file_and_print_lines(filename2); // legge il file di definizione del circuito

    // controlla se i file sono stati letti correttamente
    if (!file1 || !file2) {
        free(file1);
        free(file2);
        return 0;
    }

    *init = split_function_init(file1);
    *circuit = split_function_define_circle(file2);

    free(file1);
    free(file2);

    // controlla se le strutture sono state riempite correttamente
    if (init->count_n == 0 || circuit->count_n == 0 || circuit->circ_len == 0) {
        return 0;
    }

    return 1;
}

/** Esegue il circuito quantistico dato lo stato iniziale e la matrice del circuito.
 * @param init Puntatore alla struttura InitValue contenente lo stato iniziale.
 * @param matrix Matrice del circuito quantistico.
 * @param size Dimensione della matrice (NxN).
 */
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
    if (!check_normalization(init.value, init.count_n, 0.000001)) {
        printf("Errore: lo stato iniziale non è normalizzato.\n");
        free_init_value(&init);
        free_circuit(&circuit);
        return 1;
    }

    //TODO:  Fare la normalizzazione dello stato iniziale delle matrici

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
