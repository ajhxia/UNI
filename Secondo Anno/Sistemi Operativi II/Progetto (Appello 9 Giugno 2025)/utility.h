//
// Created by acassetta on 14/05/25.
//

#ifndef UTILITY_H
#define UTILITY_H

/** Rappresenta un numero complesso.
 * Contiene la parte reale e immaginaria del numero.
 */
typedef struct {
    double re; // parte reale
    double im; // parte immaginaria
} ComplexNumber;

/** Rappresenta un valore iniziale per lo stato quantistico.
 * Contiene un array di numeri complessi che rappresentano lo stato,
 * una stringa di qubit e il numero di qubit.
 */
typedef struct {
    ComplexNumber *value; // array di numeri complessi che rappresentano lo stato
    char *qubits; // stringa di qubit, es. "Y X I"
    int count_n; // numero di qubit
} InitValue;

/** Rappresenta un gate quantistico.
 * Contiene il nome del gate, la dimensione della matrice NxN e la matrice stessa.
 */
typedef struct {
    char name; // nome del gate, es. 'X', 'Y', 'I'
    int size; // dimensione della matrice NxN
    ComplexNumber **matrix; // matrice NxN di numeri complessi
} Gate;

/** Rappresenta una definizione di circuito quantistico.
 * Contiene un array dinamico di Gate, il numero di gate,
 * una sequenza di operazioni e la lunghezza della sequenza.
 */
typedef struct {
    Gate *gates;            // array dinamico di Gate
    int count_n;            // quanti gate ho
    char *circ_sequence;    // es. "Y X I"
    int circ_len;           // lunghezza di circ_sequence
} CircuitDef;

/** Calcola il valore assoluto di un numero reale.
 * @param x Numero reale.
 * @return Valore assoluto di x.
 */
int check_normalization(ComplexNumber *state, int size, double epsilon);
/** Calcola il valore assoluto di un numero reale.
 * @param x Numero reale.
 * @return Valore assoluto di x.
 */
void trim_leading_spaces(char **str);
/** Rimuove gli spazi e le parentesi finali da una stringa.
 * @param str Stringa da modificare.
 */
void trim_trailing_spaces_and_parens(char *str);
/** Controlla se un numero è un quadrato perfetto.
 * @param n Numero da controllare.
 * @param root_out Puntatore a una variabile che conterrà la radice quadrata se n è un quadrato perfetto.
 * @return 1 se n è un quadrato perfetto, 0 altrimenti.
 */
int is_perfect_square(int n, int *root_out);
/** Serve a fare parsing di numeri complessi.
 * @param s Stringa da analizzare.
 * @return Un numero complesso rappresentato dalla stringa.
 */
ComplexNumber parse_complex(const char *s);
#endif //UTILITY_H
