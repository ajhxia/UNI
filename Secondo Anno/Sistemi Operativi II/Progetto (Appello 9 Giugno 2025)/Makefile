CC = gcc
CFLAGS = -Wall -Wextra -g

TARGET = main
OBJS = main.o utility.o file_reader.o operation.o

.DEFAULT_GOAL := all

all: $(TARGET)
	./$(TARGET)

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJS)

main.o: main.c file_reader.h operation.h utility.h
	$(CC) $(CFLAGS) -c main.c

file_reader.o: file_reader.c file_reader.h utility.h
	$(CC) $(CFLAGS) -c file_reader.c

utility.o: utility.c utility.h
	$(CC) $(CFLAGS) -c utility.c

operation.o: operation.c operation.h utility.h
	$(CC) $(CFLAGS) -c operation.c

.PHONY: clean

clean:
	rm -f $(OBJS) $(TARGET)
