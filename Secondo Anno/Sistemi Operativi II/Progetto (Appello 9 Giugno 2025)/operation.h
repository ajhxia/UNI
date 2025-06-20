//
// Created by alebox on 21/05/25.
//

#ifndef OPERATION_H
#define OPERATION_H
#include "file_reader.h"

/** Serve a rappresentare un numero complesso.
 * La struttura contiene due campi: re (parte reale) e im (parte immaginaria).
 */
ComplexNumber complex_multiply(ComplexNumber a, ComplexNumber b);

/** * Somma due numeri complessi.
 * @param a Primo numero complesso.
 * @param b Secondo numero complesso.
 * @return La somma dei due numeri complessi.
 */
ComplexNumber complex_add(ComplexNumber a, ComplexNumber b);

/** * Esegue la simulazione del circuito quantistico e stampa lo stato finale.
 * @param def Definizione del circuito.
 * @param init Valori iniziali dello stato quantistico.
 */
void simulate_and_print(CircuitDef def, InitValue init);

/** * Stampa lo stato quantistico.
 * @param state Stato quantistico da stampare.
 * @param size Dimensione dello stato quantistico.
 */
void print_state(ComplexNumber *state, int size);

/** * Moltiplica una matrice di numeri complessi per un vettore di numeri complessi.
 * @param matrix Matrice di numeri complessi (NxN).
 * @param vector Vettore di numeri complessi (Nx1).
 * @param size Dimensione della matrice e del vettore.
 * @return Il risultato della moltiplicazione (Nx1).
 */
ComplexNumber *complex_matrix_vector_multiply(ComplexNumber **matrix, ComplexNumber *vector, int size);

/** * Libera la memoria allocata per una matrice di numeri complessi.
 * @param matrix Matrice di numeri complessi da liberare.
 * @param size Dimensione della matrice (NxN).
 */
void free_complex_matrix(ComplexNumber **matrix, int size);

/** * Libera la memoria allocata per un circuito quantistico.
 * @param circuit Circuito da liberare.
 */
ComplexNumber **build_total_circuit_matrix(const CircuitDef *circuit);
#endif //OPERATION_H
