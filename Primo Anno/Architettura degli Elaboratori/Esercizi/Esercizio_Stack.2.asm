# Scrivere il codice di una funzione ricorsiva che inverta le cifre di un numero intero N
.text
.globl main

.macro Push(%a)
	sub $sp, $sp, 4
	sw %a, ($sp)
.end_macro

.macro Pop(%b)
	lw %b, ($sp)
	add $sp, $sp, 4
.end_macro

main:
	li $v0, 5
	syscall 
	
	move $a0, $v0
	
	jal Func
	
	move $a0, $v0
	
	li $v0, 1
	syscall 
	
	li $v0, 10
	syscall
	
caso_base:
	move $v0,  $a0
	li $a3, 1
	jr $ra
	
Func:	
	blt $a0, 10, caso_base
	rem $t1,$a0,10			# rem serve a ricordare che $t1 contiene la divisione di $a0/10
	
	Push($ra)
	Push($t1)

	mul $t0, $t0, 10
	div $a0, $a0, 10

	jal Func
	
	Pop($t1)
	Pop($ra)

	mul $a3,$a3,10
	mul $t1,$t1,$a3
	add $v0,$v0,$t1 
	
	jr $ra
	
	
	
	