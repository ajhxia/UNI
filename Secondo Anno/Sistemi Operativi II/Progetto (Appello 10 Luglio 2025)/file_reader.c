//
// Created by alebox on 22/06/25.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include "file_reader.h"
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

    printf("Qubits definiti: %s\n", result.qubits ? result.qubits : "Nessuno");
    printf("Valori iniziali:\n");
    for (int i = 0; i < result.count_n; i++) {
        ComplexNumber c = result.value[i];
        printf("Valore %d: %.5f + %.5fi\n", i + 1, c.re, c.im);
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
            gate.name = 0;
            gate.size = 0;
            gate.matrix = NULL;

            char *name_ptr = line + 7;
            trim_leading_spaces(&name_ptr);
            gate.name = *name_ptr;

            char *start = strchr(line, '[');
            char *end = strrchr(line, ']');
            if (start && end && end > start) {
                size_t len = end - start - 1;
                char *buffer = malloc(len + 1);
                if (!buffer) {
                    perror("Errore allocazione buffer dinamico");
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

                // aggiungo il gate alla lista
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
            trim_leading_spaces(&p);

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

    printf("\nGates definiti:\n");
    for (int i = 0; i < result.count_n; i++) {
        Gate g = result.gates[i];
        printf("Gate %c (size: %dx%d):\n", g.name, g.size, g.size);
        for (int r = 0; r < g.size; r++) {
            for (int c = 0; c < g.size; c++) {
                ComplexNumber val = g.matrix[r][c];
                printf("%5f%+5fi ", val.re, val.im);
            }
            printf("\n");
        }
        printf("\n");
    }

    free(input_copy);
    return result;
}