//
// Created by alebox on 20/06/25.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utility.h"

int main()
{
    // chiede all'utente il nome del file dei qubits e dei gates
    printf("Inserisci il nome del file dei qubits: ");
    char *file_qubits = name_function();
    if (file_qubits == NULL) {
        perror("Errore nella lettura del nome del file per i qubits");
        free(file_qubits);
        return EXIT_FAILURE;
    }
    printf("Inserisci il nome del file dei gates: ");
    char *file_gates = name_function();
    if (file_gates == NULL) {
        perror("Errore nella lettura del nome del file per i gates");
        free(file_gates);
        return EXIT_FAILURE;
    }

    // inserisce il contenuto dei file nella variabile content
    char *qubits_content = read_file(file_qubits);
    if (qubits_content == NULL) {
        perror("Errore nell'apertura del file dei qubits");
        free(qubits_content);
        free(file_qubits);
        free(file_gates);
        return EXIT_FAILURE;
    }
    char *gates_content = read_file(file_gates);
    if (gates_content == NULL) {
        perror("Errore nell'apertura del file dei gates");
        free(gates_content);
        free(file_qubits);
        free(file_gates);
        return EXIT_FAILURE;
    }
    printf("file_gates: %s\n", gates_content);

    free (file_qubits);
    free (file_gates);
    return 0;
}