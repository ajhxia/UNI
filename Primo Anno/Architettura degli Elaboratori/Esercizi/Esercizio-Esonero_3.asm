.data
	lunghezza: .word 10
	vet: .half 2,4,7,12,10,56,22,8,11,2346
	array: .space 40 # Spazio per 10 word
	tab: .asciiz "\t

.text 
.globl main

.eqv len, $t1
.eqv i, $t2
.eqv ind, $t3
.eqv primo, $t4
.eqv ultimo, $t5

main: 
	lw len, lunghezza	# carico la lunghezza nel registro $t1
	li i, 0			# carico 0 nel registro $t2
	lh primo, vet($zero)	# carico il primo elemento dell'array
	mul  $t6, len, 2 	# moltiplico la lunghezza * 2 per ottenere il valore effettivo della lunghezza del vettore
	subi $t6, $t6, 2	# sottraggo 2 
	lh ultimo, vet($t6)	# recupero l'ultimo valore dell'array 
	jal Riposiziona 	# mi sposto nella funzione Riposiziona
		 
Riposiziona:
	beq  i, len, fine			# verifico se l'indice e la lunghezza siano uguali e in caso termino il programma
	mul ind, i, 2				# moltiplico dimensione * indice
	lh $t8, vet(ind)			# carico l'elemtento nella posizione i
	beq $t8, primo, inserisciPrimo		# verifico se il valore nella posizione i è il primo elemento 
	beq $t8, ultimo, inserisciUltimo	# verifico se il valore nella posizione i è l'ultimo elemento 
	sh $t8, array(ind) 			# carico l'elemento nel nuovo array
	
	#stampo l'elemento
	li $v0, 1
	move $a0, $t8
	syscall
	 
	#stampo un tab
	li $v0, 4
	la $a0, tab
	syscall
	 
	addi i, i, 1				# incremento il contatore
	
	j Riposiziona				# loop

inserisciPrimo: 
	sh ultimo, array(ind)			# carico l'ultimo elemento
	addi i, i, 1				# incremento il contatore
	
	li $v0, 1
	move $a0, ultimo
	syscall
	 
	li $v0, 4
	la $a0, tab
	syscall
	
	jal Riposiziona

inserisciUltimo:
	sh primo, array(ind)			# carico il primo elemento
	addi i, i, 1				# incremento il contatore
	
	li $v0, 1
	move $a0, primo
	syscall
	 
	li $v0, 4
	la $a0, tab 
	syscall
	
	jal Riposiziona
	
fine:	
	li $v0, 10
	syscall

	
	
	
	
