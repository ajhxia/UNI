// Created by alebox on 20/06/25.

#include <stdio.h>      // per input/output standard (es. printf, perror)
#include <stdlib.h>     // per funzioni di allocazione e conversione (es. malloc, free, EXIT_FAILURE)
#include <string.h>     // per manipolazione di stringhe
#include "utility.h"    // header contenente utility varie (es. name_function, read_thread_input)
#include "operation.h"  // header per operazioni sul circuito (es. run_circuit, build_circuit_matrix)
#include "file_reader.h"// header per funzioni di lettura/parsing dei file (es. read_file, parse_function_init)

int main(){
    // Chiede all'utente il nome del file contenente le direttive #init e #qubits
    printf("\n ----> QUBITS: inserire il file contenente le direttive #init e #qubits \n ");

    // Legge il nome del file dei qubits da input utente
    char *file_qubits = name_function();
    if (file_qubits == NULL) {
        perror("Errore nella lettura del nome del file per i qubits");
        return EXIT_FAILURE;
    }

    // Chiede all'utente il nome del file contenente le direttive #define e #circ
    printf("\n ----> CIRCUITO: inserire il nome del file contenente le direttive #define e #circ \n ");

    // Legge il nome del file dei gates da input utente
    char *file_gates = name_function();
    if (file_gates == NULL) {
        perror("Errore nella lettura del nome del file per i gates");
        return EXIT_FAILURE;
    }

    // Chiede all'utente il numero di thread da utilizzare per l'elaborazione parallela
    printf("\n ----> Inserisci il numero di Threads da utilizzare:  \n");
    int n_threads = read_thread_input();

    // Legge il contenuto del file dei qubits
    char *qubits_content = read_file(file_qubits);
    if (qubits_content == NULL) {
        perror("Errore nell'apertura del file dei qubits");
        free(file_qubits);
        free(file_gates);
        return EXIT_FAILURE;
    }

    // Legge il contenuto del file dei gates
    char *gates_content = read_file(file_gates);
    if (gates_content == NULL) {
        perror("Errore nell'apertura del file dei gates");
        free(file_qubits);
        free(file_gates);
        return EXIT_FAILURE;
    }

    // Analizza le direttive #init e #qubits e crea la struttura InitValue
	InitValue init = parse_function_init(qubits_content);

    // Analizza le direttive #define e #circ e crea la struttura CircuitDef
    CircuitDef circuit = parse_function_define_circle(gates_content);

    // Costruisce la matrice complessa del circuito usando i gate definiti
    ComplexNumber **circuit_matrix = build_circuit_matrix(&circuit, n_threads);
    if (!circuit_matrix) {
        printf("Errore durante la costruzione della matrice del circuito\n");
        free_init_value(&init);
        free_circuit(&circuit);
        return 1;
    }

    // Esegue il circuito: applica la matrice ai valori iniziali
    run_circuit(&init, circuit_matrix, circuit.gates[0].size);

    // Libera la memoria allocata per la matrice del circuito
    free_complex_matrix(circuit_matrix, circuit.gates[0].size);

    // Libera la memoria allocata per i valori iniziali
    free_init_value(&init);

    // Libera la memoria allocata per la definizione del circuito
    free_circuit(&circuit);

    // Libera le stringhe lette dai file
    free (qubits_content);
    free (file_qubits);
    free (gates_content);
    free (file_gates);

    // Termina il programma con successo
    return 0;
}
