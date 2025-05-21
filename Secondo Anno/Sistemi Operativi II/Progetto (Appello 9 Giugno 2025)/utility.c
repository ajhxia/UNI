#include <string.h>
#include "utility.h"
//
// Created by acassetta on 14/05/25.
//
void trim_leading_spaces(char **str) {
    while (**str == ' ' || **str == '\t') (*str)++;
}

void trim_trailing_spaces_and_parens(char *str) {
    char *end = str + strlen(str) - 1;
    while (end > str && (*end == ' ' || *end == ')')) {
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

ComplexNumber parse_complex(const char *s) {
    ComplexNumber c = {0.0, 0.0};
    if (strcmp(s, "i") == 0) {
        c.im = 1.0;
    } else if (strcmp(s, "-i") == 0) {
        c.im = -1.0;
    } else if (strcmp(s, "1") == 0) {
        c.re = 1.0;
    } else if (strcmp(s, "-1") == 0) {
        c.re = -1.0;
    } else if (strcmp(s, "0") == 0) {
        c.re = 0.0;
    }
    return c;
}