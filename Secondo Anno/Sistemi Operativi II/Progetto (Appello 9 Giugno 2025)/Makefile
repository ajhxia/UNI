main: main.o file_reader.o
	gcc -o main main.o file_reader.o

main.o: main.c function.h
	gcc -c main.c

file_reader.o: file_reader.c function.h
	gcc -c file_reader.c

# Esecuzione
run: main
	./main

#pulisce
clean:
	rm -f *.o main