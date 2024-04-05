.data
prompt: .asciiz "Inserisci un numero intero: "  # Stringa di prompt per l'input
is_prime_msg: .asciiz "Il numero è primo."      # Stringa per il messaggio "Il numero è primo."
not_prime_msg: .asciiz "Il numero non è primo." # Stringa per il messaggio "Il numero non è primo."

.text
.globl main

#nelle syscall i numeri come 4,5,10 sono tutti dei servizi che usano le syscall
#4: stampa una stringa
#5: legge un intero da tastiera
#10: termina l'esecuzione del programma

main:
    # Stampare il prompt
    li $v0, 4            # Carica il codice di sistema per la stampa di una stringa
    la $a0, prompt       # Carica l'indirizzo della stringa prompt in $a0
    syscall              # Esegue la chiamata di sistema per stampare la stringa

    # Leggere l'input
    li $v0, 5            # Carica il codice di sistema per leggere un intero
    syscall              # Esegue la chiamata di sistema per leggere un intero
    move $t0, $v0        # Salva il valore letto in $t0

    # Chiamare la funzione is_prime
    move $a0, $t0        # Passa il valore letto come argomento per la funzione is_prime
    jal is_prime         # Salta e salva l'indirizzo di ritorno e chiama la funzione is_prime

    # Verificare il risultato e stampare il messaggio appropriato
    beq $v0, 1, prime    # Se $v0 è 1 (è primo), salta alla label prime
    li $v0, 4            # Carica il codice di sistema per la stampa di una stringa
    la $a0, not_prime_msg # Carica l'indirizzo della stringa not_prime_msg in $a0
    syscall              # Esegue la chiamata di sistema per stampare la stringa
    j end_program        # Salta alla fine del programma

prime:
    li $v0, 4            # Carica il codice di sistema per la stampa di una stringa
    la $a0, is_prime_msg # Carica l'indirizzo della stringa is_prime_msg in $a0
    syscall              # Esegue la chiamata di sistema per stampare la stringa

end_program:
    # Uscita dal programma
    li $v0, 10           # Carica il codice di sistema per uscire dal programma
    syscall              # Esegue la chiamata di sistema per uscire

is_prime:
    # $a0 contiene il numero da verificare
    # Ritorna 1 se è primo, altrimenti 0

    # Se il numero è minore o uguale a 1, non è primo
    blez $a0, not_prime  # Se $a0 <= 0, salta a not_prime

    # Se il numero è 2 o 3, è primo
    li $v0, 1            # Carica 1 in $v0 (indica che il numero è primo)
    beq $a0, 2, prime_done  # Se $a0 == 2, salta a prime_done
    beq $a0, 3, prime_done  # Se $a0 == 3, salta a prime_done

    # Verifica se il numero è divisibile per i numeri fino alla radice quadrata
    li $t0, 2            # Carica 2 in $t0 (inizia con il primo divisore)
    loop:
        mul $t1, $t0, $t0  # Calcola il quadrato del divisore
        bge $t1, $a0, prime_done  # Se il quadrato supera il numero, termina
        div $a0, $t0      # Divisione per il divisore
        mfhi $t2          # Resto
        beqz $t2, not_prime  # Se il resto è zero, non è primo
        addi $t0, $t0, 1  # Incrementa il divisore
        j loop

prime_done:
    li $v0, 1            # Carica 1 in $v0 (indica che il numero è primo)
    j is_prime_exit      # Salta a is_prime_exit

not_prime:
    li $v0, 0            # Carica 0 in $v0 (indica che il numero non è primo)

is_prime_exit:
    jr $ra               # Ritorna all'indirizzo di ritorno
