//
// Created by alebox on 20/06/25.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utility.h"

char *name_function() {
    const char *directory = "file_input/";
    char *filename = NULL;
    size_t size = 0;
    const ssize_t len = getline(&filename, &size, stdin);

    if (len == -1){
        perror("Errore");
        return NULL;
    }
    // Rimuove newline alla fine
    if (filename[len - 1] == '\n') {
        filename[len - 1] = '\0';
    }

    const size_t total_len = strlen(directory) + strlen(filename) + 1;
    char *result = malloc(total_len);
    if (result == NULL) {
        perror("Errore allocazione della memoria");
        free(filename);
        return NULL;
    }

    strcpy(result, directory);
    strcat(result, filename);
    return result;
}

char *read_file(const char *filename) {
    char row[2048];
    size_t content_size = 1;
    char *content = malloc(content_size);
    if (content == NULL) {
        perror("Errore allocazione della memoria");
        return NULL;
    }

    content[0] = '\0';

    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        perror("Errore apertura file");
        free(content);
        return NULL;
    }

    int inside_define_block = 0;

    while (fgets(row, sizeof(row), file) != NULL) {
        row[strcspn(row, "\n")] = '\0'; // rimuove il newline dalla riga letta

        if ((strstr(row, "#define") && strchr(row, '[')) || (strstr(row, "#init") && strchr(row, '['))) {
            inside_define_block = 1;
        }

        if (inside_define_block) {
            strcat(row, " ");
        } else {
            strcat(row, "\n");
        }

        size_t len_row = strlen(row);
        content_size += len_row;
        char *new_content = realloc(content, content_size);
        if (new_content == NULL) {
            perror("Errore allocazione della memoria");
            free(content);
            fclose(file);
            return NULL;
        }
        content = new_content;
        strcat(content, row);

        if (inside_define_block && strchr(row, ']')) {
            inside_define_block = 0;
            content_size += 2;
            content = realloc(content, content_size);
            if (content == NULL) {
                perror("Errore allocazione della memoria");
                fclose(file);
                return NULL;
            }
            strcat(content, "\n");
        }
    }

    fclose(file);
    return content;
}

