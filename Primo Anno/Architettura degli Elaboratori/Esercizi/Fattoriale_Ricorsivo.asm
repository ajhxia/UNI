# Calcolare il fattoriale per un valore intero n>=2

.text
.globl main 

main: 
	li $v0, 5
	syscall
	
	move $a0, $v0 # sposto il valore letto da $v0, in $a0 perchè il secondo è usato come registro per passare i parametri
	
	jal Fattoriale #richiamo la funzione Fattoriale
	
	move $a0, $v0 # $v0 è il valore di ritorno della funzione e lo sposto in $a0 per stamparlo
	
	li $v0,1
	syscall

	li $v0,10
	syscall
	
Fattoriale:
	li $t0, 1
	ble $a0, $t0, caso_base
	subu $sp, $sp, 8		# dedico nello stack lo spazio sufficiente per salvare due variabili word 
	sw $ra, 0($sp)			# carico $ra in 0($sp)
	sw $a0, 4($sp)		
	sub $a0, $a0, 1		
	jal Fattoriale 
			
	# viene eseguita quando richiamo il casobase e viene ciclata al contrario 			
	lw $ra, 0($sp)
	lw $a0, 4($sp)
	addi $sp, $sp, 8
	mul $v0, $v0, $a0
	jr $ra
	
caso_base: 
	li $v0, 1
	jr $ra 				# mi riporta sotto jal Fattoriale in Fattoriale