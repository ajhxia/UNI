//
// Created by alebox on 27/06/25.
//

#ifndef OPERATION_H
#define OPERATION_H
#include "utility.h"
ComplexNumber **build_circuit_matrix(const CircuitDef *circuit);
ComplexNumber *complex_matrix_vector_multiply(ComplexNumber **matrix, ComplexNumber *vector, int size);
ComplexNumber complex_multiply(ComplexNumber a, ComplexNumber b);
ComplexNumber complex_add(ComplexNumber a, ComplexNumber b);
#endif //OPERATION_H
