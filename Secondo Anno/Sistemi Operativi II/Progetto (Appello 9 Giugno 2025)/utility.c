#include <string.h>
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