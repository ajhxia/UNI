Main: Main.o lettura_file.o
	gcc -o Main Main.o lettura_file.o

Main.o: Main.c lettura_file.h
	gcc -c Main.c

lettura_file.o: lettura_file.c lettura_file.h
	gcc -c lettura_file.c

clean:
	rm -f *.o Main