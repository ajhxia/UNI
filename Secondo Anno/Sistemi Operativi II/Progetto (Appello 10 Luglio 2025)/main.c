//
// Created by alebox on 20/06/25.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utility.h"
#include "operation.h"
#include "file_reader.h"

int main(){
    // chiede all'utente il nome del file dei qubits e dei gates
    printf("\n ----> QUBITS \n ");
    char *file_qubits = name_function();
    if (file_qubits == NULL) {
        perror("Errore nella lettura del nome del file per i qubits");
        return EXIT_FAILURE;
    }
    printf("\n ----> CIRCUITO \n ");
    char *file_gates = name_function();
    if (file_gates == NULL) {
        perror("Errore nella lettura del nome del file per i gates");
        return EXIT_FAILURE;
    }

    // printf("\n ----> Inserisci il numero di Threads da utilizzare:  \n");
    // read_thread_input();

    // inserisce il contenuto dei file nella variabile content
    char *qubits_content = read_file(file_qubits);
    if (qubits_content == NULL) {
        perror("Errore nell'apertura del file dei qubits");
        free(file_qubits);
        free(file_gates);
        return EXIT_FAILURE;
    }
    char *gates_content = read_file(file_gates);
    if (gates_content == NULL) {
        perror("Errore nell'apertura del file dei gates");
        free(file_qubits);
        free(file_gates);
        return EXIT_FAILURE;
    }

	InitValue init = parse_function_init(qubits_content); // analizza i qubits
    CircuitDef circuit = parse_function_define_circle(gates_content); // analizza i gates

    // costruzione della matrice del circuito
    ComplexNumber **circuit_matrix = build_circuit_matrix(&circuit);
    if (!circuit_matrix) {
        printf("Errore durante la costruzione della matrice del circuito\n");
        free_init_value(&init);
        free_circuit(&circuit);
        return 1;
    }

    run_circuit(&init, circuit_matrix, circuit.gates[0].size);

    // libero spazio dalla memoria allocata
    free_complex_matrix(circuit_matrix, circuit.gates[0].size);
    free_init_value(&init);
    free_circuit(&circuit);

    free (qubits_content);
    free (file_qubits);
    free (gates_content);
    free (file_gates);
    return 0;
}