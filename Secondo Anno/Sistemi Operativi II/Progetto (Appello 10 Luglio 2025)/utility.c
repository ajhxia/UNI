//
// Created by alebox on 20/06/25.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utility.h"

#include <errno.h>

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

ComplexNumber complex_multiply(ComplexNumber a, ComplexNumber b) {
    ComplexNumber result;
    result.re = a.re * b.re - a.im * b.im; // Parte reale: a.re * b.re - a.im * b.im
    result.im = a.re * b.im + a.im * b.re; // Parte immaginaria: a.re * b.im + a.im * b.re
    return result;
}

ComplexNumber complex_add(ComplexNumber a, ComplexNumber b) {
    ComplexNumber result;
    result.re = a.re + b.re; // Somma delle parti reali
    result.im = a.im + b.im; // Somma delle parti immaginarie
    return result;
}

char *name_function() {
    const char *directory = "file_input/";
    char *filename = NULL;
    size_t size = 0;
    const ssize_t len = getline(&filename, &size, stdin);

    if (len == -1){
        perror("Errore");
        return NULL;
    }
    // Rimuove newline alla fine
    if (filename[len - 1] == '\n') {
        filename[len - 1] = '\0';
    }

    const size_t total_len = strlen(directory) + strlen(filename) + 1;
    char *result = malloc(total_len);
    if (result == NULL) {
        perror("Errore allocazione della memoria");
        free(filename);
        return NULL;
    }

    strcpy(result, directory);
    strcat(result, filename);
    return result;
}

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