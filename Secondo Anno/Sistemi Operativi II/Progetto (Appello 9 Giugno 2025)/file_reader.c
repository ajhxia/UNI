//
// Created by cales on 12/05/2025.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "file_reader.h"

#include <errno.h>
#include "utility.h"

char *read_file_and_print_lines(char *filename) {
    char row[1024]; // buffer per leggere una riga del file
    size_t content_size = 1; // dimensione iniziale del contenuto
    char *content = malloc(content_size); // allocazione iniziale della variabile content
    if (content == NULL) {
        perror("Errore allocazione della memoria");
        return NULL;
    }

    content[0] = '\0';

    FILE *file = fopen(filename, "r"); // apro il file in modalità lettura
    if(file == NULL){
        perror("Errore apertura file");
        free(content);
        return NULL;
    }

    // leggo ogni riga del file
    while(fgets(row, sizeof(row), file) != NULL){
        size_t len_row = strlen(row); // lunghezza della riga letta
        content_size += len_row; // aumento la dimensione
        char *new_content = realloc(content, content_size); // riallocazione della memoria con una dimensione maggiore
        if (new_content == NULL) {
            perror("Errore riallocazione della memoria");
            free(content);
            fclose(file);
            return NULL;
        }
        content = new_content; // aggiorno il il puntatore al nuovo blocco di memoria
        strcat(content, row);  // aggiungo la riga al contenuto
    }

    fclose(file);
    return content;
}


// Libera la memoria allocata per InitValue
void free_init_value(InitValue *iv) {
    if (iv->value) free(iv->value);
    if (iv->qubits) free(iv->qubits);
}

// Estrae i valori iniziali e i qubit dalla stringa
InitValue split_function_init(char *var) {
    InitValue result = {
        .value = NULL,
        .qubits = NULL,
        .count_n = 0,
    }; // Struct da restituire

    char *input_copy = strdup(var); // Fa una copia modificabile dell'ingresso
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *line = strtok(input_copy, "\n"); // Divide l'input per righe
    while (line != NULL) {
        trim_leading_spaces(&line); // Salta spazi iniziali

        if (strncmp(line, "#qubits", 7) == 0) {
            char *q = line + 7;
            while (*q == ' ' || *q == '\t') q++;
            result.qubits = strdup(q); // Copio in nuova memoria per evitare dangling pointer
        }

        // Parsing della riga "#init"
        else if (strncmp(line, "#init", 5) == 0) {
            const char *start = strchr(line, '[');
            const char *end = strchr(line, ']');
            if (start && end && end > start) {
                char buffer[1024] = {0};
                strncpy(buffer, start + 1, end - start - 1); // Copia valori senza parentesi
                char *token = strtok(buffer, ","); // Estrae i singoli numeri complessi

                while (token) {
                    trim_leading_spaces(&token);

                    ComplexNumber c = {0, 0}; // Inizializza numero complesso
                    char *i_ptr = strchr(token, 'i'); // Controlla se contiene parte immaginaria
                    if (i_ptr) {
                        // Parsing del tipo a+bi o a-bi
                        char *plus = strrchr(token, '+');
                        char *minus = strrchr(token, '-');

                        char *sep = plus;
                        if (!sep || (minus && minus > plus)) sep = minus;

                        if (sep) {
                            char *endptr;
                            errno = 0;
                            c.re = strtod(token, &endptr);
                            if (errno != 0 || endptr == token) {
                                perror("Errore conversione double");
                                break;
                            }
                            errno = 0;
                            c.im = strtod(i_ptr + 1, &endptr);
                            if (errno != 0 || endptr == i_ptr + 1) {
                                perror("Errore conversione parte immaginaria");
                                break;
                            }
                            if (*sep == '-') c.im = -c.im;
                        } else {
                            perror("Errore: formato complesso non valido");
                            break;
                        }
                    } else {
                        char *endptr;
                        errno = 0;
                        c.re = strtod(token, &endptr);
                        if (errno != 0 || endptr == token) {
                            perror("Errore conversione double");
                            break;
                        }
                        c.im = 0.0;
                    }

                    ComplexNumber *temp = realloc(result.value, (result.count_n + 1) * sizeof(ComplexNumber));
                    if (temp == NULL) {
                        perror("Errore riallocazione della memoria");
                        free(result.value);
                        free(result.qubits);
                        free(input_copy);
                        result.value = NULL;
                        result.count_n = 0;
                        return result;
                    }

                    result.value = temp;
                    result.value[result.count_n++] = c;
                    token = strtok(NULL, ",");
                }
            }
        }

        line = strtok(NULL, "\n"); // Prossima riga
    }

    free(input_copy);
    return result;
}

// Parsing della sezione #define e #circ
CircuitDef split_function_define_circle(char *var) {
    CircuitDef result = { .gates = NULL, .count_n = 0, .circ_sequence = NULL, .circ_len = 0 };

    char *input_copy = strdup(var);
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *saveptr;
    char *line = strtok_r(input_copy, "\n", &saveptr);
    while (line != NULL) {
        trim_leading_spaces(&line);

        if (strncmp(line, "#define", 7) == 0) {
            Gate gate;
            gate.name = 0;
            gate.size = 0;
            gate.matrix = NULL;

            char *name_ptr = line + 7;
            trim_leading_spaces(&name_ptr);
            gate.name = *name_ptr;

            char *start = strchr(line, '[');
            char *end = strrchr(line, ']');
            if (start && end && end > start) {
                char buffer[4096] = {0};
                strncpy(buffer, start + 1, end - start - 1);
                buffer[end - start - 1] = '\0';

                // Conteggio righe
                int row_count = 0;
                for (char *p = buffer; *p; p++) {
                    if (*p == '(') row_count++;
                }
                gate.size = row_count;

                gate.matrix = malloc(gate.size * sizeof(ComplexNumber *));
                for (int i = 0; i < gate.size; i++) {
                    gate.matrix[i] = malloc(gate.size * sizeof(ComplexNumber));
                }

                char *ptr = buffer;
                int row = 0;

                while ((ptr = strchr(ptr, '(')) != NULL && row < gate.size) {
                    ptr++;
                    char *end_paren = strchr(ptr, ')');
                    if (!end_paren) break;

                    *end_paren = '\0';

                    int col = 0;
                    char *val_token = strtok(ptr, ",");
                    while (val_token && col < gate.size) {
                        trim_leading_spaces(&val_token);
                        printf("%s\n", val_token);
                        trim_trailing_spaces_and_parens(val_token);
                        gate.matrix[row][col++] = parse_complex(val_token);
                        val_token = strtok(NULL, ",");
                    }

                    row++;
                    ptr = end_paren + 1;
                }

                // Aggiunta alla lista dei gate
                Gate *tmp = realloc(result.gates, (result.count_n + 1) * sizeof(Gate));
                if (!tmp) {
                    perror("Errore realloc gates");
                    break;
                }
                result.gates = tmp;
                result.gates[result.count_n++] = gate;
            }
        }

        else if (strncmp(line, "#circ", 5) == 0) {
            char *p = line + 5;
            while (*p == ' ' || *p == '\t') p++;

            char *tmp = strdup(p), *q;
            int n = 0;
            q = strtok(tmp, " \t");
            while (q) { n++; q = strtok(NULL, " \t"); }
            free(tmp);

            result.circ_sequence = malloc(n + 1);
            result.circ_len = n;
            int idx = 0;
            q = strtok(p, " \t");
            while (q) {
                result.circ_sequence[idx++] = q[0];
                q = strtok(NULL, " \t");
            }
            result.circ_sequence[idx] = '\0';
        }

        line = strtok_r(NULL, "\n", &saveptr);
    }
    printf("Gates definiti:\n");
    for (int i = 0; i < result.count_n; i++) {
        Gate g = result.gates[i];
        printf("Gate %c (size: %dx%d):\n", g.name, g.size, g.size);
        for (int r = 0; r < g.size; r++) {
            for (int c = 0; c < g.size; c++) {
                ComplexNumber val = g.matrix[r][c];
                printf("%5.1f%+4.1fi ", val.re, val.im);
            }
            printf("\n");
        }
        printf("\n");
    }
    free(input_copy);
    return result;
}
