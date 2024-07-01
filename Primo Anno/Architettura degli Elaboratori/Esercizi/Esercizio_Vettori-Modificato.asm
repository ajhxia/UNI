.data
    array: .half 12, 43, 23, 54, 77
    nunelem: .word 5
    tabulato: .asciiz "\t"

.text
.globl main

main:
    la $t2, array       # Carica l'indirizzo di array in $t2
    lw $t1, nunelem     # Carica il numero di elementi in $t1

    li $t3, 0           # Inizializza l'indice dell'array a 0

loop:
    bge $t3, $t1, end_loop  # Se l'indice è maggiore o uguale al numero di elementi, esci dal ciclo

    # Carica l'elemento dall'array in $t4
    lh $t4, ($t2)
    
    # Stampa l'elemento
    move $a0, $t4       # Carica il valore da stampare in $a0
    li $v0, 1           # Codice syscall per stampare intero
    syscall

    # Stampa la tabulazione
    la $a0, tabulato    # Carica l'indirizzo della stringa di tabulazione in $a0
    li $v0, 4           # Codice syscall per stampare stringa
    syscall

    # Aggiorna l'indice dell'array (2 byte per ogni elemento di 16 bit)
    addi $t2, $t2, 2

    # Incrementa l'indice
    addi $t3, $t3, 1

    # Ritorna all'inizio del ciclo
    j loop

end_loop:
    # Termina il programma
    li $v0, 10          # Codice syscall per uscire
    syscall
