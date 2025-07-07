#include <stdio.h> /* per input/output standard (es. printf, perror) */
#include <stdlib.h> /* per funzioni di allocazione e conversione (es. malloc, free, EXIT_FAILURE) */
#include <string.h> /* per manipolazione di stringhe */
#include "utility.h" /* header contenente utility varie (es. name_function, read_thread_input) */
#include "operation.h" /* header per operazioni sul circuito (es. run_circuit, build_circuit_matrix) */
#include "file_reader.h" /* header per funzioni di lettura/parsing dei file (es. read_file, parse_function_init) */

int main(int argc, char *argv[]) {
    char *file_qubits = argv[1];
    if (file_qubits == NULL) {
        perror("Errore nella lettura del nome del file per i qubits");
        return EXIT_FAILURE;
    }

    char *file_gates = argv[2];
    if (file_gates == NULL) {
        perror("Errore nella lettura del nome del file per i gates");
        return EXIT_FAILURE;
    }

    int n_threads = atoi(argv[3]);

    if (argc != 4) {
        printf("Numero di argomenti non valido. Utilizza: ./main <-q> <-c> <-t>\n");
        return EXIT_FAILURE;
    }

    /* Legge il contenuto del file dei qubits */
    char *qubits_content = read_file(file_qubits);
    if (qubits_content == NULL) {
        perror("Errore nell'apertura del file dei qubits");
        free(file_qubits);
        free(file_gates);
        return EXIT_FAILURE;
    }

    /* Legge il contenuto del file dei gates */
    char *gates_content = read_file(file_gates);
    if (gates_content == NULL) {
        perror("Errore nell'apertura del file dei gates");
        free(file_qubits);
        free(file_gates);
        return EXIT_FAILURE;
    }

    /* Analizza le direttive #init e #qubits e crea la struttura InitValue */
	InitValue init = parse_function_init(qubits_content);

    /* Analizza le direttive #define e #circ e crea la struttura CircuitDef */
    CircuitDef circuit = parse_function_define_circle(gates_content);

    /* Costruisce la matrice complessa del circuito usando i gate definiti */
    ComplexNumber **circuit_matrix = build_circuit_matrix(&circuit, n_threads);
    if (!circuit_matrix) {
        printf("Errore durante la costruzione della matrice del circuito\n");
        free_init_value(&init);
        free_circuit(&circuit);
        return 1;
    }

    /* Esegue il circuito: applica la matrice ai valori iniziali */
    run_circuit(&init, circuit_matrix, circuit.gates[0].size);

    /* Libera la memoria allocata per la matrice del circuito */
    free_complex_matrix(circuit_matrix, circuit.gates[0].size);

    /* Libera la memoria allocata per i valori iniziali */
    free_init_value(&init);

    /* Libera la memoria allocata per la definizione del circuito */
    free_circuit(&circuit);

    /* Libera le stringhe lette dai file */
    free (qubits_content);
    free (gates_content);
    /* Termina il programma con successo */
    return 0;
}
