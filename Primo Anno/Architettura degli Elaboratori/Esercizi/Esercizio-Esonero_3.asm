.data
	lunghezza: .word 10
	vet: .half 2,4,7,12,10,56,22,8,11,2346
	array: .space 20 # Spazio per 10 word
	tab: .asciiz "\t

.text 
.globl main

.eqv len, $t1
.eqv i, $t2
.eqv ind, $t3

main: 
	lw len, lunghezza		# carico la lunghezza nel registro $t1
	li i, 0				# carico 0 nel registro $t2
	mul  $t6, len, 2 		# moltiplico la lunghezza * 2 per ottenere il valore effettivo della lunghezza del vettore
	li $a1, 0
	jal Riposiziona 		# mi sposto nella funzione Riposiziona
		 
Riposiziona:
	beq  i, len, stampa			# verifico se l'indice e la lunghezza siano uguali e in caso termino il programma
	mul ind, i, 2				# moltiplico dimensione * indice
	lh $t8, vet(ind)			# carico l'elemtento nella posizione i
	
	sub $t6, $t6, 2
	lh $t7, vet($t6)
	
	div $t9, $t8, 2
	mfhi $t4
	
	div $a2, $t7, 2
	mfhi $t5
	
	beq $t4, 1, non_scambia			# controllo se è dispari
	beq $t5, 1, non_scambia	
	
	sh $t8, array($t6)			# scambio
	sh $t7, array(ind)			# scambio
	
	addi i, i, 1				# incremento il contatore
	
	j Riposiziona				# loop
	

non_scambia:

	sh $t8, array(ind) 			# carico l'elemento nel nuovo array
	sh $t7, array($t6)
	
	addi i, i, 1
	jal Riposiziona
	
fine:
	li $v0, 10
	syscall

stampa:
	beq  $a1, len, fine			# verifico se l'indice e la lunghezza siano uguali e in caso termino il programma
	mul ind, $a1, 2				# moltiplico dimensione * indice
	lh $t8, array(ind)			# carico l'elemtento nella posizione i
	
	li $v0, 1
	move $a0, $t8
	syscall
	
	li $v0, 4
	la $a0, tab
	syscall
	
	addi $a1, $a1, 1
	
	j stampa 
	
