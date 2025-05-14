//
// Created by cales on 12/05/2025.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "function.h"

char *read_file_and_print_lines(char *filename) {
    char row[1024];
    size_t content_size = 1;
    char *content = malloc(content_size);
    if (content == NULL) {
        perror("Errore allocazione della memoria");
        return NULL;
    }

    content[0] = '\0';

    FILE *file = fopen(filename, "r");
    if(file == NULL){
        perror("Errore apertura file");
        free(content);
        return NULL;
    }

    while(fgets(row, sizeof(row), file) != NULL){
        size_t len_row = strlen(row);
        content_size += len_row;
        char *new_content = realloc(content, content_size);
        if (new_content == NULL) {
            perror("Errore riallocazione della memoria");
            free(content);
            fclose(file);
            return NULL;
        }
        content = new_content;
        strcat(content, row);
    }

    fclose(file);
    return content;
}

InitValue split_function_init(char *var) {
    InitValue result;
    result.value = NULL;
    result.qubits = NULL;
    result.count_n = 0;

    char *input_copy = strdup(var);
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *line = strtok(input_copy, "\n");
    while (line != NULL) {
        while (*line == ' ' || *line == '\t') line++;

        if (strncmp(line, "#qubits", 7) == 0) {
            char *q = line + 7;
            while (*q == ' ' || *q == '\t') q++;
            result.qubits = strdup(q); // Copio in nuova memoria per evitare dangling pointer
        }

        else if (strncmp(line, "#init", 5) == 0) {
            const char *start = strchr(line, '[');
            const char *end = strchr(line, ']');
            if (start && end && end > start) {
                char buffer[1024] = {0};
                strncpy(buffer, start + 1, end - start - 1);
                char *token = strtok(buffer, ",");

                while (token) {
                    while (*token == ' ' || *token == '\t') token++;

                    ComplexNumber c = {0, 0};
                    char *i_ptr = strchr(token, 'i');
                    if (i_ptr) {
                        char *plus = strrchr(token, '+');
                        char *minus = strrchr(token, '-');

                        char *sep = plus;
                        if (!sep || (minus && minus > plus)) sep = minus;

                        if (sep) {
                            c.re = atof(token);
                            c.im = atof(i_ptr + 1);
                            if (*sep == '-') c.im = -c.im;
                        } else {
                            perror("Errore: formato complesso non valido");
                            break;
                        }
                    } else {
                        c.re = atof(token);
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

        line = strtok(NULL, "\n");
    }

    free(input_copy);
    return result;
}

void free_init_value(InitValue *iv) {
    if (iv->value) free(iv->value);
    if (iv->qubits) free(iv->qubits);
}

void split_function_define_circle(char *var) {
}
