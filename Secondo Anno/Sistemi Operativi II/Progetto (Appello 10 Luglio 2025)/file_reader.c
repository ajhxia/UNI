//
// Created by alebox on 22/06/25.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include "file_reader.h"

#include <ctype.h>

#include "utility.h"

// Estrae i valori iniziali e i qubit dalla stringa
InitValue parse_function_init(char *var) {
    // Struttura per i valori iniziali
    InitValue result = {
        .value = NULL,
        .qubits = NULL,
        .count_n = 0,
    };

    if (!var) return result; // Controlla se la stringa è nulla

    // Copia la stringa per evitare di modificare l'originale
    char *input_copy = strdup(var);
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *saveptr = NULL; // Variabile per strtok_r
    char *line = strtok_r(input_copy, "\n", &saveptr); // Inizializza la prima riga

    while (line != NULL) {
        trim_leading_spaces(&line); // Rimuove gli spazi iniziali

        // Parsing della direttiva #qubits
        if (strncmp(line, "#qubits", 7) == 0) {
            char *q = line + 7;
            trim_leading_spaces(&q);

            if (*q != '\0') {
                result.qubits = strdup(q);
            } else {
                // Cerca nella riga successiva
                char *next_line = strtok_r(NULL, "\n", &saveptr);
                while (next_line != NULL) {
                    trim_leading_spaces(&next_line);
                    if (*next_line != '\0') {
                        result.qubits = strdup(next_line);
                        break;
                    }
                    next_line = strtok_r(NULL, "\n", &saveptr);
                }
            }
        }

        // Parsing della direttiva #init
        else if (strncmp(line, "#init", 5) == 0) {
            const char *start = strchr(line, '['); // Trova il primo '['
            const char *end = strrchr(line, ']'); // Trova l'ultimo ']'

            // Se entrambi i caratteri sono trovati e sono nell'ordine corretto
            if (start && end && end > start) {
                size_t len = end - start - 1; // Calcola la lunghezza del contenuto tra le parentesi quadre
                char *buffer = malloc(len + 1); // +1 per il terminatore null
                if (!buffer) {
                    perror("Errore allocazione memoria per buffer");
                    free(result.value);
                    free(result.qubits);
                    free(input_copy);
                    return result;
                }

                // Copia il contenuto tra le parentesi quadre nel buffer
                strncpy(buffer, start + 1, len);
                buffer[len] = '\0';

                char *valptr = NULL;
                char *token = strtok_r(buffer, ",", &valptr); // Tokenizza il buffer usando la virgola come delimitatore
                while (token) {
                    trim_leading_spaces(&token); // Rimuove gli spazi iniziali

                    ComplexNumber c = parse_complex(token); // Converte il token in un numero complesso

                    ComplexNumber *temp = realloc(result.value, (result.count_n + 1) * sizeof(ComplexNumber)); // Rialloca la memoria per il vettore dei valori
                    if (!temp) {
                        perror("Errore riallocazione memoria");
                        free(result.value);
                        free(result.qubits);
                        free(input_copy);
                        free(buffer);
                        result.value = NULL;
                        result.count_n = 0;
                        return result;
                    }

                    result.value = temp; // Aggiorna il puntatore al vettore dei valori
                    result.value[result.count_n++] = c; // Aggiunge il nuovo valore al vettore

                    token = strtok_r(NULL, ",", &valptr); // Continua a tokenizzare il buffer
                }

                free(buffer); // Libera il buffer temporaneo
            }
        }

        line = strtok_r(NULL, "\n", &saveptr); // Passa alla riga successiva
    }
    free(input_copy);
    return result;
}

void parse_gate_block(CircuitDef *result, const char *block) {
    Gate gate;
    gate.name = NULL;
    gate.size = 0;
    gate.matrix = NULL;

    const char *name_ptr = strstr(block, "#define"); // Trova la direttiva #define
    if (!name_ptr) return;
    name_ptr += 7; // Avanza oltre "#define"
    while (isspace(*name_ptr)) name_ptr++; // Salta gli spazi bianchi

    const char *name_end = name_ptr; // Inizializza il puntatore alla fine del nome del gate
    while (*name_end && !isspace(*name_end) && *name_end != '[') name_end++; // Trova la fine del nome del gate

    gate.name = strndup(name_ptr, name_end - name_ptr); // Duplica il nome del gate
    if (!gate.name) {
        perror("Errore nome gate");
        return;
    }

    const char *start = strchr(block, '[');
    const char *end = strrchr(block, ']');
    if (!(start && end && end > start)) {
        free(gate.name);
        return;
    }

    size_t len = (size_t)(end - start - 1); // Calcola la lunghezza del contenuto tra le parentesi quadre
    char *matrix_content = strndup(start + 1, len); // Duplica il contenuto tra le parentesi quadre
    if (!matrix_content) {
        perror("Errore allocazione matrice");
        free(gate.name);
        return;
    }

    // Conta righe
    int row_count = 0;
    char *p = matrix_content; // Puntatore per contare le righe
    while (*p) {
        if (*p == '(') row_count++; // Incrementa il contatore quando trova un '('
        p++;
    }
    gate.size = row_count; // Imposta la dimensione del gate in base al numero di righe trovate

    gate.matrix = (ComplexNumber **)malloc(gate.size * sizeof(ComplexNumber *)); // Alloca memoria per le righe della matrice
    if (!gate.matrix) {
        perror("Errore allocazione matrice");
        free(gate.name);
        free(matrix_content);
        return;
    }

    for (int r = 0; r < gate.size; r++) {
        gate.matrix[r] = (ComplexNumber *)malloc(gate.size * sizeof(ComplexNumber)); // Alloca memoria per le colonne della matrice
    }

    char *cursor = matrix_content; // Puntatore per scorrere il contenuto della matrice
    int row = 0;

    while ((cursor = strchr(cursor, '(')) && row < gate.size) {
        cursor++; // dopo '('
        char *end_paren = strchr(cursor, ')');
        if (!end_paren) break;

        size_t seg_len = (size_t)(end_paren - cursor); // Calcola la lunghezza della stringa tra le parentesi
        char *row_str = strndup(cursor, seg_len); // Duplica la stringa della riga
        if (!row_str) break;

        int col = 0;
        char *token = strtok(row_str, ","); // Tokenizza la riga usando la virgola come delimitatore
        while (token && col < gate.size) {
            trim_leading_spaces(&token); // Rimuove gli spazi iniziali
            trim_trailing_spaces_and_parens(token); // Rimuove gli spazi finali e le parentesi
            gate.matrix[row][col++] = parse_complex(token); // Converte il token in un numero complesso e lo memorizza nella matrice
            token = strtok(NULL, ",");
        }

        free(row_str);
        row++;
        cursor = end_paren + 1; // Avanza oltre la parentesi chiusa
    }

    free(matrix_content);

    Gate *tmp = realloc(result->gates, (result->count_n + 1) * sizeof(Gate)); // Rialloca memoria per il vettore dei gate
    if (!tmp) {
        perror("Errore realloc gates");
        free(gate.name);
        return;
    }

    result->gates = tmp;
    result->gates[result->count_n++] = gate;
}

void parse_circ_block(CircuitDef *result, const char *block) {
    const char *p_start = strstr(block, "#circ"); // Trova la direttiva #circ
    if (!p_start) return;
    p_start += 5;
    while (isspace(*p_start)) p_start++;

    char *line = strdup(p_start);
    if (!line) {
        perror("strdup fallita");
        return;
    }

    int count = 0;
    for (char *p = line; *p;) {
        while (isspace(*p)) p++;
        if (*p) {
            count++;
            while (*p && !isspace(*p)) p++;
        }
    }

    result->circ_sequence = (char **)malloc(count * sizeof(char *)); // Alloca memoria per la sequenza circolare
    if (!result->circ_sequence) {
        perror("malloc fallita");
        free(line);
        return;
    }

    char *token = strtok(line, " \t\r\n"); // Tokenizza la stringa usando spazi, tabulazioni e ritorni a capo come delimitatori
    int idx = 0;
    while (token && idx < count) {
        result->circ_sequence[idx++] = strdup(token); // Duplica il token e lo memorizza nella sequenza circolare
        token = strtok(NULL, " \t\r\n");
    }

    result->circ_len = idx; // Imposta la lunghezza della sequenza circolare
    free(line);
}

CircuitDef parse_function_define_circle(char *var) {
    CircuitDef result;
    result.gates = NULL;
    result.count_n = 0;
    result.circ_sequence = NULL;
    result.circ_len = 0;

    char *input_copy = strdup(var); // Copia la stringa per evitare di modificare l'originale
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *cursor = input_copy; // Puntatore per scorrere la stringa
    while (cursor && *cursor)
    {
        // Salta spazi bianchi
        while (*cursor && isspace(*cursor)) cursor++;

        if (strncmp(cursor, "#define", 7) == 0) {
            char *next_define = strstr(cursor + 7, "#define");
            char *next_circ = strstr(cursor + 7, "#circ");

            char *next = NULL;
            if (next_define && next_circ) // Se entrambi i blocchi sono presenti
                next = (next_define < next_circ) ? next_define : next_circ; // Scegli il blocco più vicino
            else if (next_define) // Se solo il blocco #define è presente
                next = next_define; // Scegli il blocco #define
            else if (next_circ)
                next = next_circ; // Se solo il blocco #circ è presente

            size_t block_len = next ? (size_t)(next - cursor) : strlen(cursor); // Calcola la lunghezza del blocco corrente
            char *block = strndup(cursor, block_len);// Duplica il blocco corrente
            if (!block) {
                perror("Errore allocazione blocco gate");
                break;
            }

            parse_gate_block(&result, block); // Analizza il blocco dei gate
            free(block);
            cursor += block_len;// Avanza il cursore oltre il blocco corrente
        }
        else if (strncmp(cursor, "#circ", 5) == 0) {
            char *end = cursor + 5;
            while (*end && *end != '#') end++;
            size_t len = (size_t)(end - cursor);
            char *block = strndup(cursor, len);
            if (!block) {
                perror("Errore allocazione blocco circ");
                break;
            }

            parse_circ_block(&result, block); // Analizza il blocco circolare
            free(block); // Libera la memoria del blocco
            cursor += len;
        } else {
            cursor++; // avanza se non matcha né #define né #circ
        }
    }
    free(input_copy);
    return result;
}
