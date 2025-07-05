CC = gcc
CFLAGS = -Wall -Wextra -std=c90 -g

TARGET = main
OBJS = main.o utility.o threads_worker.o file_reader.o operation.o

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

operation.o: operation.c operation.h utility.h threads_worker.h
	$(CC) $(CFLAGS) -c operation.c

threads_worker.o: threads_worker.c threads_worker.h
	$(CC) $(CFLAGS) -c threads_worker.c

.PHONY: all clean

clean:
	rm -f $(OBJS) $(TARGET)
