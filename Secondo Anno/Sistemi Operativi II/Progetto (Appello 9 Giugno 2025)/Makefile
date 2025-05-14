main: main.o lettura_file.o
	gcc -o main main.o lettura_file.o

main.o: main.c function.h
	gcc -c main.c

lettura_file.o: lettura_file.c function.h
	gcc -c lettura_file.c

# Esecuzione
run: main
	./main

#pulisce
clean:
	rm -f *.o main