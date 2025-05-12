main: main.o lettura_file.o
	gcc main.o lettura_file.o -o main

lettura_file.o: lettura_file.c lettura_file.h
	gcc -c lettura_file.c -o lettura_file.o

main.o: main.c lettura_file.h
	gcc -c main.c -o main.o

clean:
	rm -f *.o main