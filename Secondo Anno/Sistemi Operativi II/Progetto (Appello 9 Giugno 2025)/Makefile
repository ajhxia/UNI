main: main.o utility.o file_reader.o
	gcc -o main main.o utility.o file_reader.o

main.o: main.c file_reader.h
	gcc -c main.c

file_reader.o: file_reader.c file_reader.h
	gcc -c file_reader.c

utility.o: utility.c
	gcc -c utility.c
# Esecuzione
run: main
	./main

#pulisce
clean:
	rm -f *.o main