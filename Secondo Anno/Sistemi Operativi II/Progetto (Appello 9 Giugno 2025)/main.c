#include <stdio.h>
#include <stdlib.h>
#include "file_reader.h"
#include "operation.h"
#include "utility.h"

int main(){
    char *filename1 = "init-ex.txt";
    char *file1 = read_file_and_print_lines(filename1);

    char *filename2 = "circ-ex.txt";
    char *file2 = read_file_and_print_lines(filename2);

    if (file1 == NULL) {
        return 1;
    }

    if (file2 == NULL) {
        return 1;
    }

    CircuitDef circ = split_function_define_circle(file2);
    InitValue init = split_function_init(file1);

    return 0;
}
