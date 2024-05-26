# Si scriva la routine assembler MIPS che implementa la funzione ricorsiva definita come segue:
# f(x,y) = 1 se uno (almeno) tra x,y vale 0
# f(x,y) = x * f(y,x-1) altrimenti
# Si assuma che x, y siano immessi da input sempre maggiori o uguali a 0

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
	# leggo in input il valore
	li $v0, 5
	syscall
	move $a0, $v0 # Salva il numero di riga in $t0
	
	# leggo in input il valore
	li $v0, 5
	syscall
	move $a1, $v0
	
	jal Func	#richiamo la funzione 
	
	move $a0, $v0	# viene eseguita dopo il ciclo inverso del caso base
	
	li $v0,1
	syscall

	li $v0,10
	syscall
	
Func:	
	
	li $t1, 1
	
	# verifica se in $a0 o $a1 c'è 0
	beq $a0, $t1, caso_base
	beq $a1, $t1, caso_base 
	
	Push($ra)
	Push($a0)
	
	sub $a0, $a0, 1
	
	move $a2, $a0
	move $a0, $a1
	move $a1, $a2
	
	jal Func
	
	Pop($a3)
	mul $v0, $v0, $a3
	
	Pop($ra)
	
	jr $ra

caso_base:
	li $v0, 1
	jr $ra
	
	
	
	