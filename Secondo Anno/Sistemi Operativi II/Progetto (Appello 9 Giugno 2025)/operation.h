//
// Created by alebox on 21/05/25.
//

#ifndef OPERATION_H
#define OPERATION_H
#include "file_reader.h"
ComplexNumber complex_multiply(ComplexNumber a, ComplexNumber b);
ComplexNumber complex_add(ComplexNumber a, ComplexNumber b);
void simulate_and_print(CircuitDef def, InitValue init);
ComplexNumber *apply_gate(ComplexNumber **matrix, ComplexNumber *vector, int size);
void print_state(ComplexNumber *state, int size);
ComplexNumber *complex_matrix_vector_multiply(ComplexNumber **matrix, ComplexNumber *vector, int size);
void free_complex_matrix(ComplexNumber **matrix, int size);
ComplexNumber **build_total_circuit_matrix(const CircuitDef *circuit);
#endif //OPERATION_H
