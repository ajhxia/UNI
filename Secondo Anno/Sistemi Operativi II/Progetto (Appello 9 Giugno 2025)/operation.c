//
// Created by acassetta on 15/05/25.
//

#include <stdio.h>
#include "operation.h"
#include "utility.h"

ComplexNumber complex_multiply(ComplexNumber a, ComplexNumber b) {
    ComplexNumber result;
    result.re = a.re * b.re - a.im * b.im;
    result.im = a.re * b.im + a.im * b.re;
    return result;
}

ComplexNumber complex_add(ComplexNumber a, ComplexNumber b) {
    ComplexNumber result;
    result.re = a.re + b.re;
    result.im = a.im + b.im;
    return result;
}

void print_state(ComplexNumber *state, int size) {
    for (int i = 0; i < size; ++i) {
        printf("(%g, %g)\n", state[i].re, state[i].im);
    }
}
