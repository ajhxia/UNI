# Traccia 1. Definire una matrice 5x5 di halfword. Realizzare un programma in linguaggio assemblativo che consente ad un utente di inserire da tastiera il numero di riga ed 
# il numero di colonna e visualizzare su schermo l'elemento.
.data
	stringa: .asciiz "Inserisci colonna: "
	stringa2: .asciiz "Inserisci riga: "
	tabulato: .asciiz "\t"
	riga: .asciiz "\n"
	matrice: .half 6,3,8,3,4,6,35,20,2,1,0,22,133,67,42,32
	R: .word 5
	C: .word 5

.text
.globl main

main:
	# Stampa "Inserisci un numero: "
	li $v0, 4
	la $a0, stringa2
	syscall
	
	# Input per la riga
	li $v0, 5
	syscall
	move $t0, $v0 # Salva il numero di riga in $t0
	
	# Stampa "Inserisci un numero: "
	li $v0, 4
	la $a0, stringa
	syscall
	
	# Input per la colonna
	li $v0, 5
	syscall
	move $t1, $v0 # Salva il numero di colonna in $t1
	
	# Calcola l'indice dell'elemento nella matrice
	lw $t2, R # Carica il numero di righe
	lw $t3, C # Carica il numero di colonne
	sub $t0, $t0, 1 # Decrementa la riga (gli indici partono da 0)
	sub $t1, $t1, 1 # Decrementa la colonna (gli indici partono da 0)
	mul $t0, $t0, $t3 # Calcola l'offset della riga
	add $t0, $t0, $t1 # Aggiunge l'offset della colonna
	add $t0, $t0, $t0 # Ogni elemento è halfword (2 byte), quindi moltiplica per 2

	# Carica l'elemento dalla matrice
	lh $t4, matrice($t0) # Carica l'elemento in $t4

	# Stampa l'elemento
	move $a0, $t4 # Carica l'elemento in $a0 per la stampa
	li $v0, 1
	syscall

	# Uscita dal programma
	li $v0, 10
	syscall
