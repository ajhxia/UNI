//
// Created by cales on 12/05/2025.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "file_reader.h"
#include "utility.h"

char *read_file_and_print_lines(char *filename) {
    char row[1024]; // buffer per leggere una riga del file
    size_t content_size = 1; // dimensione iniziale del contenuto
    char *content = malloc(content_size); // allocazione iniziale della variabile content
    if (content == NULL) {
        perror("Errore allocazione della memoria");
        return NULL;
    }

    content[0] = '\0';

    FILE *file = fopen(filename, "r"); // apro il file in modalità lettura
    if(file == NULL){
        perror("Errore apertura file");
        free(content);
        return NULL;
    }

    // leggo ogni riga del file
    while(fgets(row, sizeof(row), file) != NULL){
        size_t len_row = strlen(row); // lunghezza della riga letta
        content_size += len_row; // aumento la dimensione
        char *new_content = realloc(content, content_size); // riallocazione della memoria con una dimensione maggiore
        if (new_content == NULL) {
            perror("Errore riallocazione della memoria");
            free(content);
            fclose(file);
            return NULL;
        }
        content = new_content; // aggiorno il il puntatore al nuovo blocco di memoria
        strcat(content, row);  // aggiungo la riga al contenuto
    }

    fclose(file);
    return content;
}

// Estrae i valori iniziali e i qubit dalla stringa
InitValue split_function_init(char *var) {
    InitValue result = {
        .value = NULL,
        .qubits = NULL,
        .count_n = 0,
    }; // Struct da restituire

    char *input_copy = strdup(var); // Fa una copia modificabile dell'input
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *line = strtok(input_copy, "\n"); // Divide l'input per righe
    while (line != NULL) {
        while (*line == ' ' || *line == '\t') line++; // Salta spazi iniziali

        if (strncmp(line, "#qubits", 7) == 0) {
            char *q = line + 7;
            while (*q == ' ' || *q == '\t') q++;
            result.qubits = strdup(q); // Copio in nuova memoria per evitare dangling pointer
        }

        // Parsing della riga "#init"
        else if (strncmp(line, "#init", 5) == 0) {
            const char *start = strchr(line, '[');
            const char *end = strchr(line, ']');
            if (start && end && end > start) {
                char buffer[1024] = {0};
                strncpy(buffer, start + 1, end - start - 1); // Copia valori senza parentesi
                char *token = strtok(buffer, ","); // Estrae i singoli numeri complessi

                while (token) {
                    while (*token == ' ' || *token == '\t') token++;

                    ComplexNumber c = {0, 0}; // Inizializza numero complesso
                    char *i_ptr = strchr(token, 'i'); // Controlla se contiene parte immaginaria
                    if (i_ptr) {
                        // Parsing del tipo a+bi o a-bi
                        char *plus = strrchr(token, '+');
                        char *minus = strrchr(token, '-');

                        char *sep = plus;
                        if (!sep || (minus && minus > plus)) sep = minus;

                        if (sep) {
                            c.re = atof(token);
                            c.im = atof(i_ptr + 1);
                            if (*sep == '-') c.im = -c.im;
                        } else {
                            perror("Errore: formato complesso non valido");
                            break;
                        }
                    } else {
                        c.re = atof(token);
                        c.im = 0.0;
                    }

                    ComplexNumber *temp = realloc(result.value, (result.count_n + 1) * sizeof(ComplexNumber));
                    if (temp == NULL) {
                        perror("Errore riallocazione della memoria");
                        free(result.value);
                        free(result.qubits);
                        free(input_copy);
                        result.value = NULL;
                        result.count_n = 0;
                        return result;
                    }

                    result.value = temp;
                    result.value[result.count_n++] = c;
                    token = strtok(NULL, ",");
                }
            }
        }

        line = strtok(NULL, "\n"); // Prossima riga
    }

    free(input_copy);
    return result;
}

// Libera la memoria allocata per InitValue
void free_init_value(InitValue *iv) {
    if (iv->value) free(iv->value);
    if (iv->qubits) free(iv->qubits);
}

// Parsing della sezione #define e #circ
CircuitDef split_function_define_circle(char *var) {
    CircuitDef result = { .gates = NULL, .count_n = 0, .circ_sequence = NULL, .circ_len = 0 };

    char *input_copy = strdup(var); // Copia modificabile dell'input
    if (!input_copy) {
        perror("Errore allocazione memoria");
        return result;
    }

    char *saveptr;
    char *line = strtok_r(input_copy, "\n", &saveptr); // Divisione per righe con contesto
    while (line != NULL) {
        trim_leading_spaces(&line); // Rimuove spazi iniziali

        if (strncmp(line, "#define", 7) == 0) {
            Gate gate;
            gate.count_n = 0;
            gate.value = NULL;

            // Estrai il nome del gate
            char *name_ptr = line + 7; // Salta "#define"
            trim_leading_spaces(&name_ptr);
            gate.name = *name_ptr; // Assume nome come singolo carattere

            // Trova i valori complessi tra le parentesi quadre
            const char *start = strchr(line, '[');
            const char *end = strchr(line, ']');

            if (start && end && end > start) {
                char buffer[1024] = {0};
                strncpy(buffer, start + 1, end - start - 1);

                // Ogni token ora è una stringa del tipo: (a, b)
                char *token = strtok(buffer, "(");
                while (token) {
                    // token sarà del tipo: a, b)
                    char *comma = strchr(token, ',');
                    if (!comma) break;
                    *comma = '\0';
                    char *re_str = token;
                    char *im_str = comma + 1;

                    trim_leading_spaces(&re_str);
                    trim_leading_spaces(&im_str);
                    trim_trailing_spaces_and_parens(im_str);

                    // Parsing numeri
                    ComplexNumber c;
                    if (strcmp(re_str, "i") == 0) c.re = 1.0;
                    else if (strcmp(re_str, "-i") == 0) c.re = -1.0;
                    else c.re = atof(re_str);

                    if (strcmp(im_str, "i") == 0) c.im = 1.0;
                    else if (strcmp(im_str, "-i") == 0) {
                        c.im = -1.0;
                    }
                    else c.im = atof(im_str);

                    ComplexNumber *temp_val = realloc(gate.value, (gate.count_n + 1) * sizeof(ComplexNumber));
                    if (!temp_val) {
                        perror("Errore realloc valore complesso");
                        free(gate.value);
                        gate.value = NULL;
                        gate.count_n = 0;
                        break;
                    }
                    gate.value = temp_val;
                    gate.value[gate.count_n++] = c;

                    token = strtok(NULL, "(");
                }

                // Aggiungi il gate a result
                Gate *temp_gate = realloc(result.gates, (result.count_n + 1) * sizeof(Gate));
                if (!temp_gate) {
                    perror("Errore realloc gates");
                    free(gate.value);
                    break;
                }
                result.gates = temp_gate;
                result.gates[result.count_n++] = gate;
            }
        }

        // Parsing della riga #circ
        else if (strncmp(line, "#circ", 5)==0) {
            // dopo "#circ " ci sono i nomi separati da spazi
            char *p = line + 5;
            while (*p==' '||*p=='\t') p++;
            // conta i token
            char *tmp = strdup(p), *q;
            int n = 0;
            q = strtok(tmp, " \t");
            while(q) { n++; q=strtok(NULL," \t"); }
            free(tmp);

            // alloco circ_sequence
            result.circ_sequence = malloc(n+1); // +1 per terminatore '\0'
            result.circ_len = n;
            int idx = 0;
            q = strtok(p, " \t");
            while(q) {
                result.circ_sequence[idx++] = q[0];
                q = strtok(NULL, " \t");
            }
            result.circ_sequence[idx] = '\0';
        }

        line = strtok_r(NULL, "\n", &saveptr);
    }

    return result;
}
