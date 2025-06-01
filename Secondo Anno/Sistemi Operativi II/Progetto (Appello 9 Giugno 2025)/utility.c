#include <string.h>
#include "utility.h"

#include <stdio.h>
#include <stdlib.h>
//
// Created by acassetta on 14/05/25.
//
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

int is_perfect_square(int n, int *root_out) {
    for (int i = 1; i <= n / 2 + 1; ++i) {
        if (i * i == n) {
            *root_out = i;
            return 1;
        }
        if (i * i > n) break;
    }
    return 0;
}

ComplexNumber parse_complex(const char* str) {
    ComplexNumber result = {0, 0};
    char buffer[50];
    strncpy(buffer, str, sizeof(buffer));
    buffer[sizeof(buffer) - 1] = '\0'; // sicurezza

    // Trova posizione di 'i'
    char* i_ptr = strchr(buffer, 'i');
    if (!i_ptr) {
        // Se non c'è 'i', è un numero reale
        result.re = atof(buffer);
        result.im = 0;
        return result;
    }

    *i_ptr = '\0';  // spezza in due la stringa

    // Parte reale: tutto prima di "+i" o "-i"
    char* real_str = buffer;

    // Parte immaginaria: tutto dopo "+i" o "-i"
    char* imag_str = i_ptr + 1;

    // Trova il separatore '+' o '-' per la parte immaginaria
    char* sep = strpbrk(real_str + 1, "+-");  // +1 per evitare segno iniziale

    if (sep) {

        result.re = atoi(real_str);
        char sign = *sep;

        // Ricostruisce la parte immaginaria con il segno
        char imag_full[10];
        snprintf(imag_full, sizeof(imag_full), "%c%s", sign, imag_str);

        // Se "i" era da solo, intende "i1"
        result.im = atoi(imag_full);

    } else {
        // Nessun separatore trovato, può essere solo "i" o "iN"
        if (buffer[0] == '\0') {
            result.re = 0;
        } else {
            result.re = atoi(buffer);
        }
        result.im = 1;
    }
    return result;
}

double my_abs(double x) {
    return (x < 0) ? -x : x; // calcola il valore assoluto
}

int check_normalization(ComplexNumber *state, int size, double epsilon) {
    double norm_squared = 0.0;
    int i;

    for (i = 0; i < size; ++i) {
        norm_squared += state[i].re * state[i].re + state[i].im * state[i].im; // calcola il quadrato della norma
    }
    return (my_abs(norm_squared - 1.0) < epsilon) ? 1 : 0;
}

int check_gate_matrix_normalization(ComplexNumber **matrix, int size, double epsilon) {
    for (int col = 0; col < size; ++col) {
        double norm_squared = 0.0;
        for (int row = 0; row < size; ++row) {
            norm_squared += matrix[row][col].re * matrix[row][col].re + matrix[row][col].im * matrix[row][col].im;
        }
        if (my_abs(norm_squared - 1.0) >= epsilon) {
            return 0;
        }
    }
    return 1;
}