CC = gcc
CFLAGS = -g -O0 -Wall

TARGET = main
OBJS = main.o utility.o file_reader.o

.DEFAULT_GOAL := all

all: $(TARGET)
	./$(TARGET)

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJS)

main.o: main.c utility.h file_reader.h
	$(CC) $(CFLAGS) -c main.c

file_reader.o: file_reader.c file_reader.h utility.h
	$(CC) $(CFLAGS) -c file_reader.c

utility.o: utility.c utility.h
	$(CC) $(CFLAGS) -c utility.c

.PHONY: clean

clean:
	rm -f $(OBJS) $(TARGET)
