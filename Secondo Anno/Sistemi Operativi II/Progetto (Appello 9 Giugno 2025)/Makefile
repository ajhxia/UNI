main: main.o utility.o file_reader.o operation.o
	gcc -o main main.o utility.o file_reader.o operation.o

main.o: main.c file_reader.h
	gcc -c main.c

file_reader.o: file_reader.c file_reader.h
	gcc -c file_reader.c

utility.o: utility.c
	gcc -c utility.c

operation.o: operation.c
	gcc -c operation.c
# Esecuzione
run: main
	./main

#pulisce
clean:
	rm -f *.o main