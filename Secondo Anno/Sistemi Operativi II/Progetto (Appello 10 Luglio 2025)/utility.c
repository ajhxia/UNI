//
// Created by alebox on 20/06/25.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utility.h"
#include <errno.h>

#include "operation.h"

void trim_leading_spaces(char **str) {
    while (**str == ' ' || **str == '\t') (*str)++; // rimuove gli spazi iniziali
}

void trim_trailing_spaces_and_parens(char *str) {
    char *end = str + strlen(str) - 1;
    while (end > str && (*end == ' ' || *end == ')')) { // rimuove gli spazi finali e le parentesi chiuse
        *end = '\0';
        end--;
    }
}

void trim_trailing_whitespace(char *str) {
    if (!str) return;
    size_t len = strlen(str);
    while (len > 0 && (str[len - 1] == '\n' || str[len - 1] == '\r' || str[len - 1] == ' ' || str[len - 1] == '\t')) {
        str[--len] = '\0';
    }
}

void print_state(ComplexNumber *state, int size) {
    printf("[( ");
    for (int i = 0; i < size; ++i) {
        double re = state[i].re;
        double im = state[i].im;

        // stampa con formato (re + i*im) o (re - i*im)
        if (im < 0)
            printf("%5f-i%5f", re, -im);
        else
            printf("%5f+i%5f", re, im);

        if (i < size - 1)
            printf(",  ");
    }
    printf(" )]\n");
}

void free_complex_matrix(ComplexNumber **matrix, int size) {
    for (int i = 0; i < size; ++i) {
        free(matrix[i]);
    }
    free(matrix);
}

void free_circuit(CircuitDef *circ) {
    if (circ == NULL) return;

    // Libera ogni matrice di gate
    for (int i = 0; i < circ->count_n; i++) {
        Gate *g = &circ->gates[i];
        if (g->matrix) {
            for (int r = 0; r < g->size; r++) {
                free(g->matrix[r]);
            }
            free(g->matrix);
            g->matrix = NULL;
        }
    }

    // Libera l’array di gate
    free(circ->gates);
    circ->gates = NULL;

    // Libera la stringa circ_sequence
    free(circ->circ_sequence);
    circ->circ_sequence = NULL;

    circ->count_n = 0;
    circ->circ_len = 0;
}

// Libera la memoria allocata per InitValue
void free_init_value(InitValue *iv) {
    if (iv->value) free(iv->value);
    if (iv->qubits) free(iv->qubits);
}


// Funzione che controlla se un file esiste
int file_esiste(const char *path) {
    FILE *file = fopen(path, "r");
    if (file) {
        fclose(file);
        return 1;
    }
    return 0;
}

int read_thread_input(){
    char buffer[100];
    int numero;
    char *endptr;

    printf("Inserisci un numero intero: ");
    if (fgets(buffer, sizeof(buffer), stdin) != NULL) {
        // Rimuove newline
        size_t len = strlen(buffer);
        if (len > 0 && buffer[len - 1] == '\n') {
            buffer[len - 1] = '\0';
        }

        numero = (int) strtol(buffer, &endptr, 10);

        // controlla se tutto l'input è stato convertito
        if (endptr == buffer || *endptr != '\0') {
            printf("Input non valido.\n");
        } else {
            printf("Hai inserito: %d\n", numero);
        }
    } else {
        printf("Errore nella lettura dell'input.\n");
        return -1; // Indica errore nella lettura
    }
    return numero;
}

char *name_function() {
    const char *directory = "file_input/";
    char buffer[256];  // Dimensione fissa per input utente

    while (1) {
        printf("Inserisci il nome del file (presente in '%s'): ", directory);

        if (fgets(buffer, sizeof(buffer), stdin) == NULL) {
            perror("Errore durante la lettura dell'input");
            return NULL;
        }

        // Rimuove newline, se presente
        size_t len = strlen(buffer);
        if (len > 0 && buffer[len - 1] == '\n') {
            buffer[len - 1] = '\0';
        }

        // Costruisce il percorso completo
        size_t total_len = strlen(directory) + strlen(buffer) + 1;
        char *full_path = (char *)malloc(total_len);
        if (full_path == NULL) {
            perror("Errore allocazione memoria");
            return NULL;
        }

        strcpy(full_path, directory);
        strcat(full_path, buffer);

        // Controlla se il file esiste
        if (file_esiste(full_path)) {
            return full_path;  // restituisce il percorso valido
        } else {
            printf("Il file '%s' non esiste. Riprova.\n", full_path);
            free(full_path);
        }
    }
}

// TODO: refactor con fgetc
char *read_file(const char *filename) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        perror("Errore apertura file");
        return NULL;
    }

    char *content = malloc(1);
    if (!content) {
        fclose(file);
        return NULL;
    }
    content[0] = '\0';
    size_t content_size = 1;

    char *row = NULL;
    size_t row_capacity = 0;

    int inside_define_block = 0;

    while (getline(&row, &row_capacity, file) != -1) {
        row[strcspn(row, "\n")] = '\0';

        if ((strstr(row, "#define") && strchr(row, '[')) || (strstr(row, "#init") && strchr(row, '['))) {
            inside_define_block = 1;
        }

        if (inside_define_block) strcat(row, " ");
        else strcat(row, "\n");

        size_t len_row = strlen(row);
        content_size += len_row;

        char *new_content = realloc(content, content_size);
        if (!new_content) {
            perror("Errore realloc");
            free(content);
            free(row);
            fclose(file);
            return NULL;
        }

        content = new_content;
        strcat(content, row);

        if (inside_define_block && strchr(row, ']')) {
            inside_define_block = 0;
            strcat(content, "\n");
            content_size += 1;
        }
    }

    free(row);
    fclose(file);
    return content;
}


ComplexNumber parse_complex(const char* token) {
    ComplexNumber result = {0, 0}; // inizializza numero complesso
    if (!strchr(token, 'i')) {
        // Solo parte reale
        result.re = atof(token);
        result.im = 0;
        return result;
    }

    if (strcmp(token, "i") == 0) {
        result.re = 0;
        result.im = 1;
        return result;
    }

    if (strcmp(token, "-i") == 0) {
        result.re = 0;
        result.im = -1;
        return result;
    }

    char *i_ptr = strchr(token, 'i'); // controlla se contiene parte immaginaria
    if (i_ptr) {
        // Parsing del tipo a+bi o a-bi
        char *plus = strrchr(token, '+');
        char *minus = strrchr(token, '-');

        char *sep = plus;
        if (!sep || (minus && minus > plus)) sep = minus;

        if (sep) {
            char *endptr;
            errno = 0;
            result.re = strtod(token, &endptr);
            if (errno != 0 || endptr == token) {
                perror("Errore conversione double");
            }
            errno = 0;
            result.im = strtod(i_ptr + 1, &endptr);
            if (errno != 0 || endptr == i_ptr + 1) {
                perror("Errore conversione parte immaginaria");
            }
            if (*sep == '-') result.im = -result.im;
        } else {
            perror("Errore: formato complesso non valido");
        }
    } else {
        char *endptr;
        errno = 0;
        result.re = strtod(token, &endptr);
        if (errno != 0 || endptr == token) {
            perror("Errore conversione double");
        }
        result.im = 0.0;
    }
    return result;
}

void run_circuit(const InitValue *init, ComplexNumber **matrix, int size) {
    ComplexNumber *final_state = complex_matrix_vector_multiply(matrix, init->value, size);
    printf("\nStato finale:\n");
    print_state(final_state, size);
    free(final_state);
}
