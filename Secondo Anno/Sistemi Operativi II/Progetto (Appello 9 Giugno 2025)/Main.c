//
// Created by Alessia Cassetta on 08/05/25.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *read_file_and_print_lines(char *filename) {
    char row[256]; // buffer per leggere le righe

    size_t content_size = 1; // dimensione iniziale del contenuto
    char *content = malloc(content_size); // *content è un puntatore a carattere, che punta ad un blocco allocato dinamicamente
    if (content == NULL) {
        fprintf(stderr, "Errore allocazione della memoria\n");
        return NULL;
    }

    content[0] = '\0';

    // apro il file in modalità lettura
    FILE *file = fopen(filename, "r");
    if(file == NULL){
        perror("Errore apertura file");
        return 1;
    }

    // leggo il file riga per riga
    while(fgets(row, sizeof(row), file) != NULL){
        size_t len_row = strlen(row);
        content_size += len_row;
        char *new_content = realloc(content, content_size);
        /* realloc può spostare l'array in un'altra zona della memoria e restituisce un nuovo puntatore.
        Se va tutto bene, il nuovo puntatore viene copiato in content.*/

        if (new_content == NULL) {
            fprintf(stderr, "Errore riallocazione della memoria\n");
            fclose(file); // chiudo il file
            free(content);
            return NULL;
        }
        content = new_content;
        strcat(content, row);
    }
    fclose(file);
    return content;
}

int main(){
    char *filename1 = "Secondo Anno/Sistemi Operativi II/Progetto (Appello 9 Giugno 2025)/init-ex.txt"; // nome del file da leggere
    char *filename2 = "Secondo Anno/Sistemi Operativi II/Progetto (Appello 9 Giugno 2025)/circ-ex.txt";

    char *file1 = read_file_and_print_lines(filename1);
    char *file2 = read_file_and_print_lines(filename2);
    if(file1 == NULL) {
        printf("Errore apertura file\n");
        free(file1);
    }
    printf("%s", file1);
    if(file2 == NULL) {
        printf("Errore apertura file\n");
        free(file2);
    }
    printf("%s", file2);
    return 0;
}


