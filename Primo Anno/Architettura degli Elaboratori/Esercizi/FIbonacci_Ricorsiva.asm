.text
.globl main

.macro Push(%reg)
    sub $sp, $sp, 4
    sw %reg, ($sp)
.end_macro

.macro Pop(%reg)
    lw %reg, ($sp)
    add $sp, $sp, 4
.end_macro

main:
    li $v0, 5          # Chiede all'utente di inserire un numero
    syscall
    move $a0, $v0

    jal Func           # Chiama la funzione Func

    li $v0, 1          # Stampa il risultato (n-esimo numero di Fibonacci)
    move $a0, $v0
    syscall

    li $v0, 10         # Termina il programma
    syscall

Func:
    beq $a0, 0, caso_base   # Controllo del caso base f(0)
    beq $a0, 1, caso_base   # Controllo del caso base f(1)

    Push($a0)
    Push($ra)
    addi $a0, $a0, -1       # Calcola f(n-1)
    jal Func                # Chiama ricorsivamente la funzione
    move $s1, $v0           # Salva il risultato di f(n-1)

    Pop($a0)
    addi $a0, $a0, -2       # Calcola f(n-2)
    jal Func                # Chiama ricorsivamente la funzione
    move $s2, $v0           # Salva il risultato di f(n-2)

    add $v0, $s1, $s2       # Calcola f(n) = f(n-1) + f(n-2)

    Pop($a0)
    Pop($ra)
    jr $ra                  # Ritorna al chiamante

caso_base:
    li $v0, 1           # Carica il valore 1 in $v0
    jr $ra              # Ritorna al chiamante
