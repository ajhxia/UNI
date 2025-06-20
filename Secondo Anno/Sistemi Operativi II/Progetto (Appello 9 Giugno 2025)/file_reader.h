//
// Created by cales on 12/05/2025.
//

#ifndef LETTURA_FILE_H
#define LETTURA_FILE_H
#include "utility.h"
/** * Legge un file e stampa le sue linee.
 * @param filename Nome del file da leggere.
 * @return Un puntatore alla stringa contenente il contenuto del file, o NULL in caso di errore.
 */
char *read_file_and_print_lines(char *filename);
/** * Inizializza una struttura InitValue a partire da una stringa.
 * @param var Stringa contenente lo stato iniziale.
 * @return Una struttura InitValue con i valori iniziali.
 */
InitValue split_function_init(char *var);
/** * Inizializza una struttura CircuitDef a partire da una stringa.
 * @param var Stringa contenente la definizione del circuito.
 * @return Una struttura CircuitDef con i gate e la sequenza del circuito.
 */
CircuitDef split_function_define_circle(char *var);
/** * Libera la memoria allocata per una struttura InitValue.
 * @param iv Puntatore alla struttura InitValue da liberare.
 */
void free_init_value(InitValue *iv);
/** * Libera la memoria allocata per una struttura CircuitDef.
 * @param circ Puntatore alla struttura CircuitDef da liberare.
 */
void free_circuit(CircuitDef *circ);
#endif //LETTURA_FILE_H
