#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utility.h"
#include <errno.h>
#define INITIAL_BUFFER_SIZE 128
#include "operation.h"

/* Rimuove gli spazi e i tab iniziali da una stringa modificando il puntatore */
void trim_leading_spaces(char **str) {
    while (**str == ' ' || **str == '\t') (*str)++; /* Avanza finché incontra spazi o tab */
}

/* Rimuove spazi finali e parentesi chiuse da una */
void trim_trailing_spaces_and_parens(char *str) {
    char *end = str + strlen(str) - 1; /* Punta all'ultimo carattere */
    while (end > str && (*end == ' ' || *end == ')')) { /* Finché incontra spazi o ')' */
        *end = '\0'; /* Termina la stringa */
        end--;
    }
}

/* Rimuove whitespace finale: spazi, tab, newline, carriage return */
void trim_trailing_whitespace(char *str) {
    if (!str) return; /* Se stringa nulla, esce */
    size_t len = strlen(str); /* Lunghezza della stringa */
    while (len > 0 && (str[len - 1] == '\n' || str[len - 1] == '\r' ||
                       str[len - 1] == ' ' || str[len - 1] == '\t')) {
        str[--len] = '\0'; /* Rimuove carattere finale */
        }
}

void print_state(ComplexNumber *state, int size) {
    printf("[( ");
    int i;
    for (i = 0; i < size; ++i) { /* Per ogni elemento nel vettore */
        double re = state[i].re; /* Parte reale */
        double im = state[i].im; /* Parte immaginaria */

        /* Stampa con segno corretto */
        if (im < 0)
            printf("%5f-i%5f", re, -im); /* Se parte immaginaria negativa */
        else
            printf("%5f+i%5f", re, im); /* Se positiva */

        if (i < size - 1)
            printf(",  "); /* Virgola tra elementi */
    }
    printf(" )]\n");
}

void free_complex_matrix(ComplexNumber **matrix, int size) {
    int i;
    for (i = 0; i < size; ++i) {
        free(matrix[i]);
    }
    free(matrix);
}

void free_circuit(CircuitDef *circ) {
    if (circ == NULL) return;

    /* Libera ogni matrice e nome del gate */
    int i;
    for (i = 0; i < circ->count_n; i++) {
        Gate *g = &circ->gates[i];

        free(g->name);  /* Libera il nome */
        g->name = NULL;

        if (g->matrix) {
            int r;
            for (r = 0; r < g->size; r++) {
                free(g->matrix[r]);
            }
            free(g->matrix);
            g->matrix = NULL;
        }
    }

    /* Libera l’array dei gate */
    free(circ->gates);
    circ->gates = NULL;

    /* Libera ogni stringa nella sequenza del circuito */
    if (circ->circ_sequence) {
        int j;
        for (j = 0; j < circ->circ_len; j++) {
            free(circ->circ_sequence[j]);
        }
        free(circ->circ_sequence);
        circ->circ_sequence = NULL;
    }

    circ->count_n = 0;
    circ->circ_len = 0;
}


/* Libera la memoria allocata per InitValue */
void free_init_value(InitValue *iv) {
    if (iv->value) free(iv->value);
    if (iv->qubits) free(iv->qubits);
}


/* Funzione che controlla se un file esiste */
int file_esiste(const char *path) {
    FILE *file = fopen(path, "r");
    if (file) {
        fclose(file); /* Se il file esiste, lo chiude */
        return 1;
    }
    return 0;
}

int read_thread_input(){
    char buffer[100]; /* Buffer per input da tastiera */
    int numero;
    char *endptr;

    printf("Inserisci un numero intero: ");
    if (fgets(buffer, sizeof(buffer), stdin) != NULL) {
        size_t len = strlen(buffer);
        if (len > 0 && buffer[len - 1] == '\n') {
            buffer[len - 1] = '\0'; /* Rimuove newline */
        }

        numero = (int) strtol(buffer, &endptr, 10); /* Converte in intero */

        if (endptr == buffer || *endptr != '\0') {
            printf("Input non valido.\n"); /* Controllo conversione */
        }
    } else {
        printf("Errore nella lettura dell'input.\n");
        return -1; /* Errore in lettura */
    }
    return numero;
}

/* Funzione per leggere una riga dinamicamente */
char *read_line(FILE *file) {
    int c;
    size_t size = INITIAL_BUFFER_SIZE;
    size_t len = 0;
    char *buffer = (char *)malloc(size);
    if (!buffer) return NULL;

    while ((c = fgetc(file)) != EOF && c != '\n') {
        if (len + 1 >= size) {
            size *= 2;
            char *new_buffer = (char *)realloc(buffer, size);
            if (!new_buffer) {
                free(buffer);
                return NULL;
            }
            buffer = new_buffer;
        }
        buffer[len++] = (char)c;
    }

    if (c == EOF && len == 0) {
        free(buffer);
        return NULL;
    }

    buffer[len] = '\0';
    return buffer;
}

/* Verifica se la riga inizia un blocco definito */
int starts_define_block(const char *line) {
    return (strstr(line, "#define") && strchr(line, '[')) ||
           (strstr(line, "#init") && strchr(line, '['));
}

/* Verifica se la riga termina un blocco definito */
int ends_define_block(const char *line) {
    return strchr(line, ']') != NULL;
}

/* Aggiunge una riga al contenuto */
char *append_line(char *content, const char *line, size_t *content_size) {
    size_t len_line = strlen(line);
    char *new_content = (char *)realloc(content, *content_size + len_line + 2); /* +1 for \n, +1 for \0 */
    if (!new_content) return NULL;

    strcat(new_content, line);
    strcat(new_content, "\n");

    *content_size += len_line + 1;
    return new_content;
}

/* Funzione principale */
char *read_file(const char *filename) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        perror("Errore apertura file");
        return NULL;
    }

    char *content = (char *)malloc(1);
    if (!content) {
        fclose(file);
        return NULL;
    }
    content[0] = '\0';
    size_t content_size = 1;

    int inside_define_block = 0;
    char *line = NULL;

    while ((line = read_line(file)) != NULL) {
        if (starts_define_block(line)) {
            inside_define_block = 1;
        }

        if (!inside_define_block) {
            content = append_line(content, line, &content_size);
        } else {
            char *temp = (char *)realloc(content, content_size + strlen(line) + 1);
            if (!temp) {
                free(content);
                free(line);
                fclose(file);
                return NULL;
            }
            content = temp;
            strcat(content, line);
            content_size += strlen(line);

            if (ends_define_block(line)) {
                strcat(content, "\n");
                content_size += 1;
                inside_define_block = 0;
            }
        }

        free(line);
    }

    fclose(file);
    return content;
}

ComplexNumber parse_complex(const char* token) {
    ComplexNumber result = {0, 0}; /* inizializza numero complesso */
    if (!strchr(token, 'i')) {
        /* Solo parte reale */
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

    char *i_ptr = strchr(token, 'i'); /* controlla se contiene parte immaginaria */
    if (i_ptr) {
        /* Parsing del tipo a+bi o a-bi */
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
