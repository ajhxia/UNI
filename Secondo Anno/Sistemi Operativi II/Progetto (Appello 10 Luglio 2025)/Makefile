CC = gcc
CFLAGS = -Wall -Wextra -g

TARGET = main
OBJS = main.o utility.o

.DEFAULT_GOAL := all

all: $(TARGET)
	./$(TARGET)

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJS)

main.o: main.c utility.h
	$(CC) $(CFLAGS) -c main.c

utility.o: utility.c utility.h
	$(CC) $(CFLAGS) -c utility.c

.PHONY: clean

clean:
	rm -f $(OBJS) $(TARGET)
