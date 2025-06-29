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
    };

    if (!var) return result;

    char *input_copy = strdup(var);
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *saveptr = NULL;
    char *line = strtok_r(input_copy, "\n", &saveptr);

    while (line != NULL) {
        trim_leading_spaces(&line);

        // Parsing della direttiva #qubits
        if (strncmp(line, "#qubits", 7) == 0) {
            char *q = line + 7;
            trim_leading_spaces(&q);

            if (*q != '\0') {
                result.qubits = strdup(q);
            } else {
                // Cerca nella riga successiva
                char *next_line = strtok_r(NULL, "\n", &saveptr);
                while (next_line != NULL) {
                    trim_leading_spaces(&next_line);
                    if (*next_line != '\0') {
                        result.qubits = strdup(next_line);
                        break;
                    }
                    next_line = strtok_r(NULL, "\n", &saveptr);
                }
            }
        }

        // Parsing della direttiva #init
        else if (strncmp(line, "#init", 5) == 0) {
            const char *start = strchr(line, '[');
            const char *end = strrchr(line, ']');

            if (start && end && end > start) {
                size_t len = end - start - 1;
                char *buffer = malloc(len + 1);
                if (!buffer) {
                    perror("Errore allocazione memoria per buffer");
                    free(result.value);
                    free(result.qubits);
                    free(input_copy);
                    return result;
                }

                strncpy(buffer, start + 1, len);
                buffer[len] = '\0';

                char *valptr = NULL;
                char *token = strtok_r(buffer, ",", &valptr);
                while (token) {
                    trim_leading_spaces(&token);

                    ComplexNumber c = parse_complex(token);

                    ComplexNumber *temp = realloc(result.value, (result.count_n + 1) * sizeof(ComplexNumber));
                    if (!temp) {
                        perror("Errore riallocazione memoria");
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

                    token = strtok_r(NULL, ",", &valptr);
                }

                free(buffer);
            }
        }

        line = strtok_r(NULL, "\n", &saveptr);
    }
    free(input_copy);
    return result;
}

void parse_gate_block(CircuitDef *result, const char *block) {
    Gate gate;
    gate.name = NULL;
    gate.size = 0;
    gate.matrix = NULL;

    const char *name_ptr = strstr(block, "#define");
    if (!name_ptr) return;
    name_ptr += 7;
    while (isspace(*name_ptr)) name_ptr++;

    const char *name_end = name_ptr;
    while (*name_end && !isspace(*name_end) && *name_end != '[') name_end++;

    gate.name = strndup(name_ptr, name_end - name_ptr);
    if (!gate.name) {
        perror("Errore nome gate");
        return;
    }

    const char *start = strchr(block, '[');
    const char *end = strrchr(block, ']');
    if (!(start && end && end > start)) {
        free(gate.name);
        return;
    }

    size_t len = (size_t)(end - start - 1);
    char *matrix_content = strndup(start + 1, len);
    if (!matrix_content) {
        perror("Errore allocazione matrice");
        free(gate.name);
        return;
    }

    // Conta righe
    int row_count = 0;
    char *p = matrix_content;
    while (*p) {
        if (*p == '(') row_count++;
        p++;
    }
    gate.size = row_count;

    gate.matrix = (ComplexNumber **)malloc(gate.size * sizeof(ComplexNumber *));
    if (!gate.matrix) {
        perror("Errore allocazione matrice");
        free(gate.name);
        free(matrix_content);
        return;
    }

    for (int r = 0; r < gate.size; r++) {
        gate.matrix[r] = (ComplexNumber *)malloc(gate.size * sizeof(ComplexNumber));
    }

    char *cursor = matrix_content;
    int row = 0;

    while ((cursor = strchr(cursor, '(')) && row < gate.size) {
        cursor++; // dopo '('
        char *end_paren = strchr(cursor, ')');
        if (!end_paren) break;

        size_t seg_len = (size_t)(end_paren - cursor);
        char *row_str = strndup(cursor, seg_len);
        if (!row_str) break;

        int col = 0;
        char *token = strtok(row_str, ",");
        while (token && col < gate.size) {
            trim_leading_spaces(&token);
            trim_trailing_spaces_and_parens(token);
            gate.matrix[row][col++] = parse_complex(token);
            token = strtok(NULL, ",");
        }

        free(row_str);
        row++;
        cursor = end_paren + 1;
    }

    free(matrix_content);

    Gate *tmp = realloc(result->gates, (result->count_n + 1) * sizeof(Gate));
    if (!tmp) {
        perror("Errore realloc gates");
        free(gate.name);
        return;
    }

    result->gates = tmp;
    result->gates[result->count_n++] = gate;
}

void parse_circ_block(CircuitDef *result, const char *block) {
    const char *p_start = strstr(block, "#circ");
    if (!p_start) return;
    p_start += 5;
    while (isspace(*p_start)) p_start++;

    char *line = strdup(p_start);
    if (!line) {
        perror("strdup fallita");
        return;
    }

    int count = 0;
    for (char *p = line; *p;) {
        while (isspace(*p)) p++;
        if (*p) {
            count++;
            while (*p && !isspace(*p)) p++;
        }
    }

    result->circ_sequence = (char **)malloc(count * sizeof(char *));
    if (!result->circ_sequence) {
        perror("malloc fallita");
        free(line);
        return;
    }

    char *token = strtok(line, " \t\r\n");
    int idx = 0;
    while (token && idx < count) {
        result->circ_sequence[idx++] = strdup(token);
        token = strtok(NULL, " \t\r\n");
    }

    result->circ_len = idx;
    free(line);
}

CircuitDef parse_function_define_circle(char *var) {
    CircuitDef result;
    result.gates = NULL;
    result.count_n = 0;
    result.circ_sequence = NULL;
    result.circ_len = 0;

    char *input_copy = strdup(var);
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *cursor = input_copy;
    while (cursor && *cursor)
    {
        // Salta spazi bianchi
        while (*cursor && isspace(*cursor)) cursor++;

        if (strncmp(cursor, "#define", 7) == 0) {
            char *next_define = strstr(cursor + 7, "#define");
            char *next_circ = strstr(cursor + 7, "#circ");

            char *next = NULL;
            if (next_define && next_circ)
                next = (next_define < next_circ) ? next_define : next_circ;
            else if (next_define)
                next = next_define;
            else if (next_circ)
                next = next_circ;

            size_t block_len = next ? (size_t)(next - cursor) : strlen(cursor);
            char *block = strndup(cursor, block_len);
            if (!block) {
                perror("Errore allocazione blocco gate");
                break;
            }

            parse_gate_block(&result, block);
            free(block);
            cursor += block_len;
        }
        else if (strncmp(cursor, "#circ", 5) == 0) {
            char *end = cursor + 5;
            while (*end && *end != '#') end++;
            size_t len = (size_t)(end - cursor);
            char *block = strndup(cursor, len);
            if (!block) {
                perror("Errore allocazione blocco circ");
                break;
            }

            parse_circ_block(&result, block);
            free(block);
            cursor += len;
        } else {
            cursor++; // avanza se non matcha né #define né #circ
        }
    }
    free(input_copy);
    return result;
}
