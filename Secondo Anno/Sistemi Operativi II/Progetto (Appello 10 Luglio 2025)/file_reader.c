//
// Created by alebox on 22/06/25.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include "file_reader.h"

#include <ctype.h>

#include "utility.h"

// Estrae i valori iniziali e i qubit dalla stringa
InitValue parse_function_init(char *var) {
    InitValue result = {
        .value = NULL,
        .qubits = NULL,
        .count_n = 0,
    }; // Struct da restituire

    char *input_copy = strdup(var);
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *line = strtok(input_copy, "\n"); // divide l'input per righe
    while (line != NULL) {
        trim_leading_spaces(&line); // salta spazi iniziali

        if (strncmp(line, "#qubits", 7) == 0) {
            char *q = line + 7;
            while (*q == ' ' || *q == '\t') q++;
            result.qubits = strdup(q); // copio in nuova memoria per evitare dangling pointer
        }

        // Parsing della riga "#init"
        else if (strncmp(line, "#init", 5) == 0) {
            const char *start = strchr(line, '[');
            const char *end = strrchr(line, ']'); // usa strrchr per l'ultima chiusura

            if (start && end && end > start) {
                size_t len = end - start - 1;
                char *buffer = malloc(len + 1);
                if (!buffer) {
                    perror("Errore allocazione memoria per buffer");
                    free(result.value);
                    free(result.qubits);
                    free(input_copy);
                    result.value = NULL;
                    result.count_n = 0;
                    return result;
                }

                strncpy(buffer, start + 1, len);
                buffer[len] = '\0'; // terminazione stringa

                char *token = strtok(buffer, ",");
                while (token) {
                    trim_leading_spaces(&token);

                    ComplexNumber c = parse_complex(token);

                    ComplexNumber *temp = realloc(result.value, (result.count_n + 1) * sizeof(ComplexNumber));
                    if (temp == NULL) {
                        perror("Errore riallocazione della memoria");
                        free(result.value);
                        free(result.qubits);
                        free(input_copy);
                        free(buffer);
                        result.value = NULL;
                        result.count_n = 0;
                        return result;
                    }

                    result.value = temp;
                    result.value[result.count_n++] = c;
                    token = strtok(NULL, ",");
                }

                free(buffer);
            }
        }

        line = strtok(NULL, "\n"); // Prossima riga
    }

    free(input_copy);
    return result;
}

CircuitDef parse_function_define_circle(char *var) {
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
            gate.name = NULL;
            gate.size = 0;
            gate.matrix = NULL;

            char *name_ptr = line + 7;
            trim_leading_spaces(&name_ptr);

            // Estrazione del nome del gate (può essere più lungo di un carattere)
            char gate_name_buf[256];
            int i = 0;
            while (*name_ptr && !isspace(*name_ptr) && *name_ptr != '[' && i < 255) {
                gate_name_buf[i++] = *name_ptr++;
            }
            gate_name_buf[i] = '\0';
            gate.name = strdup(gate_name_buf);
            if (!gate.name) {
                perror("Errore allocazione nome gate");
                free(input_copy);
                return result;
            }

            char *start = strchr(line, '[');
            char *end = strrchr(line, ']');
            if (start && end && end > start) {
                size_t len = end - start - 1;
                char *buffer = malloc(len + 1);
                if (!buffer) {
                    perror("Errore allocazione buffer dinamico");
                    free(gate.name);
                    free(input_copy);
                    return result;
                }
                strncpy(buffer, start + 1, len);
                buffer[len] = '\0';

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
                        trim_trailing_spaces_and_parens(val_token);
                        gate.matrix[row][col++] = parse_complex(val_token);
                        val_token = strtok(NULL, ",");
                    }

                    row++;
                    ptr = end_paren + 1;
                }

                free(buffer);

                Gate *tmp = realloc(result.gates, (result.count_n + 1) * sizeof(Gate));
                if (!tmp) {
                    perror("Errore realloc gates");
                    free(gate.name);
                    break;
                }
                result.gates = tmp;
                result.gates[result.count_n++] = gate;
            } else {
                free(gate.name); // se non c'è la matrice, liberiamo il nome
            }
        }

        else if (strncmp(line, "#circ", 5) == 0) {
            char *p = line + 5;
            trim_leading_spaces(&p);

            // Fai una prima passata per contare i token
            char *tmp = strdup(p), *q;
            int n = 0;
            q = strtok(tmp, " \t");
            while (q) {
                n++;
                q = strtok(NULL, " \t");
            }
            free(tmp);

            result.circ_len = n;

            // Ora ricostruisci la stringa concatenata con gli spazi
            result.circ_sequence = malloc(strlen(p) + 1); // sufficiente per contenere la sequenza
            result.circ_sequence[0] = '\0'; // inizializza come stringa vuota

            q = strtok(p, " \t");
            while (q) {
                strcat(result.circ_sequence, q);
                q = strtok(NULL, " \t");
                if (q) strcat(result.circ_sequence, " ");
            }
        }

        line = strtok_r(NULL, "\n", &saveptr);
    }
    free(input_copy);
    return result;
}