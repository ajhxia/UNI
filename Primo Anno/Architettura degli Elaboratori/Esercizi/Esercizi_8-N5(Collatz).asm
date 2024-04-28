# Considerate la regola di Collatz: dato un numero intero positivo n, se n è pari lo si divide per 2, se è dispari lo si moltiplica per 3 e si aggiunge 1 al risultato. Quando n è 1 ci si ferma.
# Ad esempio, la sequenza di Collatz di 7 è: 7 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1
# E' un noto problema aperto stabilire se ogni sequenza di Collatz termina (cioè, se arriva a 1). 
#Scrivere in linguaggio assemblativo MIPS/MARS una funzione che, dato un numero, dia il successivo in una sequenza di Collatz. Quindi, usare la funzione in un programma che chiede 
# all’utente un numero e mostra la sequenza di Collatz del numero e la lunghezza.

#Esempi di funzionamento:
#Numero: 7 - 7 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1, Lunghezza: 17
#Numero: 9 - 9 28 14 7 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1, Lunghezza: 20

.data
    inserisci: .asciiz "Inserisci un numero: "
    tab_in: .asciiz "\t"
    len: .asciiz "\n Lunghezza: "
    
.text
.globl main
	
#dichiaro i registri
.eqv num, $t0
.eqv resto, $t1
.eqv divisione, $t2
.eqv tab, $t3
.eqv count, $t8

main: 
    la tab, tab_in		#inserisco il valore di tab_in in tab ($t3)
    li count, 0
    
    # stampo la stringa inserisci
    li $v0, 4
    la $a0, inserisci
    syscall
    
    # prendo un valore intero da tastiera
    li $v0, 5
    syscall
    move num, $v0
    
    jal Collaz 			# mi sposto nella funzione Collaz
    
    # stampo la lunghezza della sequenza
    li $v0, 4
    la $a0, len
    syscall
    
    li $v0, 1
    move $a0, count
    syscall
    
    # termino il programma
    li $v0, 10
    syscall


Collaz: 
    beq num, 1, Fine		# verifico se il numero corrente è uguale a uno
    add count, count, 1
    div divisione, num, 2	# eseguo una divisione tra 2 e il numero inserito
    mfhi resto			# metto il resto nel registro resto
    bnez resto, Dispari		# se il resto è diverso da 0 allora mi sposto in Dispari
    beqz resto, Pari		# se il resto è zero allora mi sposto in Pari

Pari:
    div num, num, 2		# eseguo la divisione per 2
    # stampo il numero
    li $v0, 1
    move $a0, num
    syscall
    
    # stampo un tab per una visione migliore 
    li $v0, 4
    move $a0, tab
    syscall
 
    j Collaz  			# mi risposto in Collaz

Dispari: 
    mul num, num, 3		# eseguo una moltiplicazione per tre
    add num, num, 1		# aggiungo uno al numero 
    # stampo il numero
    li $v0, 1			
    move $a0, num
    syscall
    
    # stampo un tab per una visione migliore 
    li $v0, 4
    move $a0, tab
    syscall
    
    j Collaz
    
Fine:
    jr $ra			# ritorno all'indirizzo di ritorno
