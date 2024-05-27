# Traccia 4. Definire una matrice 10x10 di byte. Realizzare un programma in linguaggio assemblativo che inizializza la matrice con valori casuali 0 e 1.
# Permettere ad un utente di scegliere riga e colonna e decidere dopo cinque tentativi quanti elementi con valore 1 ha individuato. 
# Ogni volta che individua un 1 l'elemento è settato a 0.
.data
	matrice: .byte 0:100
	R: .word 10
	C: .word 10
	str:.asciiz "Numero di uno indovinati: "
	indovinato: .asciiz "Hai indovinato un 1\n"
	tabulato:.asciiz "\t"
	riga:.asciiz "\n"
	
.text
.globl main

main:	
	li $t0,1 #indice r
	li $t1,1 #indice c
	lw $t2,R #numero righe R
	lw $t3,C #numero colonne C
	li $a1, 2
	j GeneraMatrice
	
ResetColonna:
	li $t1,1		# imposto di nuovo il valore della colonna ad 1
	
GeneraMatrice: 
	li $v0, 42
	syscall
	 
	sub $t5, $t0, 1 # (r-1)
	mul $t6, $t5, $t3 # C(r-1)
	
	sub $t7, $t1, 1 # (c-1)
	add $t9, $t7, $t6 # C(r-1) + (c-1)
	
	sb $a0, matrice($t9)		# metto il valore generato casualmente dalla syscall 42
	
	lb $t8, matrice($t9) 		#prelievo elemento
	
	move $a0,$t8 			#stampa elemento
	li $v0,1 
	syscall 
	
	la $a0, tabulato 		#stampa tabulato
	li $v0, 4
	syscall
	
	addi $t1,$t1,1 			#incremento colonna
	
	ble $t1,$t3, GeneraMatrice 	
	
	addi $t0,$t0,1 			#incremento riga
	la $a0,riga			#stampa andata a capo (nuova riga)
	li $v0,4
	syscall
	
	ble $t0,$t2, ResetColonna 	# se le righe non sono finite allora resetto la colonna
	
	li $t0, 0
	j userInteraction
	
userInteraction:
	beq $t0, 5, fine
	
	li $v0, 5
	syscall
	move $t1, $v0
	
	li $v0, 5
	syscall
	move $t2, $v0
	
	sub $t1, $t1, 1	# decremento di uno i valori inseriti (gli indici partono da 0)
	sub $t2, $t2, 1
	
	mul $t1, $t1, 10
	add $t1, $t1, $t2 
	
	lb $t9, matrice($t1) # Carica l'elemento in $t9
	
	beq $t9, 1, incremento
	
	li $t0, 0
	j userInteraction
	
incremento:
	add $t8, $t8, 1
	add $t0, $t0, 1
	
	li $v0, 4
	la $a0, indovinato
	syscall

	j userInteraction
	
fine:	

	li $v0, 4
	la $a0, str
	syscall

	li $v0, 1
	move $a0, $t8
	syscall
	
	li $v0, 10
	syscall
	
