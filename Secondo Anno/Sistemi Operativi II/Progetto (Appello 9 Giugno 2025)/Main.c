//
// Created by Alessia Cassetta on 08/05/25.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    double re;
    double im;
} ComplexNumber;

char *read_file_and_print_lines(char *filename) {
    char row[1024]; // buffer per leggere le righe

    size_t content_size = 1; // dimensione iniziale del contenuto
    char *content = malloc(content_size); // *content è un puntatore a carattere, che punta ad un blocco allocato dinamicamente
    if (content == NULL) {
        perror("Errore allocazione della memoria\n");
        return NULL;
    }

    content[0] = '\0';

    // apro il file in modalità lettura
    FILE *file = fopen(filename, "r");
    if(file == NULL){
        perror("Errore apertura file");
        return NULL;
    }

    // leggo il file riga per riga
    while(fgets(row, sizeof(row), file) != NULL){
        size_t len_row = strlen(row);
        content_size += len_row;
        char *new_content = realloc(content, content_size);
        /* Realloc può spostare l'array in un'altra zona della memoria e restituisce un nuovo puntatore.
        Se va tutto bene, il nuovo puntatore viene copiato in content.*/

        if (new_content == NULL) {
            perror("Errore riallocazione della memoria\n");
            free(content);
            fclose(file); // chiudo il file
            return NULL;
        }
        content = new_content;
        strcat(content, row);
    }
    fclose(file);
    return content;
}

void split_function_init(char *var) {
    char *qubits = '\0';
    ComplexNumber *init_values = NULL;
    int init_count = 0;

    char *input_copy = strdup(var); // ritorna un duplicato di var, allocandolo nello stack
    if (!input_copy) {
        perror("Errore allocazione memoria\n");
        return;
    }

    char *line = strtok(input_copy, "\n"); // singola riga del file
    while (line != NULL) {
        while (*line == ' ' || *line == '\t') line++; // rimuovo eventuali tab iniziali o spazi vuoti

        // Gestione #qubits
        if (strncmp(line, "#qubits", 7) == 0) { // compara n caratteri (7) di entrambe le stringhe
            qubits = line + 7;
            while (*qubits == ' ' || *qubits == '\t') qubits++;
        }

        // Gestione #init [(re, im) ...]
        else if (strncmp(line, "#init", 5) == 0) { // compara n caratteri (5) di entrambe le stringhe
            const char *start = strchr(line, '[');
            const char *end = strchr(line, ']');
            if (start && end && end > start) {
                char buffer[1024] = {0};
                strncpy(buffer, start + 1, end - start - 1); // prendo solamente i numeri all'interno delle []
                char *token = strtok(buffer, ","); // prendo la prima parte della stringa fino a ','
                while (token) {
                    while (*token == ' ' || *token == '\t') token++;

                    ComplexNumber c = {0, 0};
                    char *i_ptr = strchr(token, 'i'); // cerca la prima occorrenza del carattere 'i'
                    if (i_ptr) {
                        // cerca il segno tra parte reale e immaginaria
                        char *plus = strrchr(token, '+'); // cerca l'ultima occorrenza del carattere '+'
                        char *minus = strrchr(token, '-');

                        char *sep = plus;
                        if (!sep || (minus && minus > plus)) sep = minus; // controllo quale operatore è utilizzato

                        if (sep) {
                            c.re = atof(token);
                            c.im = atof(i_ptr+1);
                            if (*sep == '-') c.im = -c.im; // se negativo aggiungo il -
                        } else {
                            perror("Errore: formato complesso non valido");
                            break;
                        }
                    } else {
                        c.re = atof(token);
                        c.im = 0.0;
                    }
                    ComplexNumber *temp = realloc(init_values, (init_count + 1) * sizeof(ComplexNumber));
                    if (temp == NULL) {
                        perror("Errore riallocazione della memoria\n");
                        free(init_values); // Libera la memoria già allocata
                        free(input_copy);  // Libera la copia della stringa
                        return;
                    }
                    init_values = temp;
                    init_values[init_count++] = c;

                    token = strtok(NULL, ",");
                }
            }
        }

        line = strtok(NULL, "\n");
    }

    free(init_values);
    free(input_copy);
}

void split_function_define_circle(char *var) {
    // TODO: da implementare
}

int main(){
    char *filename1 = "Secondo Anno/Sistemi Operativi II/Progetto (Appello 9 Giugno 2025)/init-ex.txt"; // nome del file da leggere
    // char *filename2 = "Secondo Anno/Sistemi Operativi II/Progetto (Appello 9 Giugno 2025)/circ-ex.txt";

    char *file1 = read_file_and_print_lines(filename1);

    if (file1) {
        split_function_init(file1);
        free(file1); // Libera la memoria allocata
    }

    return 0;
}


