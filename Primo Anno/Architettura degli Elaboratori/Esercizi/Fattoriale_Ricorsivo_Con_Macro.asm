# Calcolare il fattoriale per un valore intero n>=2

.text
.globl main 

.macro Pop(%a)
	lw %a, ($sp)
	add $sp, $sp, 4
.end_macro

.macro Push(%b)
	sub $sp, $sp, 4
	sw %b, ($sp)
.end_macro

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
	Push($ra)		# carico $ra in 0($sp)
	Push($a0)		
	sub $a0, $a0, 1		
	jal Fattoriale 
			
	# viene eseguita quando richiamo il casobase e viene ciclata al contrario 	
	Pop($a0)		
	Pop($ra)
	mul $v0, $v0, $a0
	jr $ra
	
caso_base: 
	li $v0, 1
	jr $ra 				# mi riporta sotto jal Fattoriale in Fattoriale