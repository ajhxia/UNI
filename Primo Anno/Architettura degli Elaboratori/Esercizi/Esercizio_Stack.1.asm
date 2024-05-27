# Si consideri la funzione f definita su interi: f(x) = f(x-2) – 2, f(1) = 14, f(0) = 10
# Si realizzi un programma in assembler MIPS che,definito un intero positivo x≥2, calcola il corrispondente valore di f(x) in modo ricorsivo 
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
		
	move $a0, $v0 # $v0 è il valore di ritorno della funzione e lo sposto in $a0 per stamparlo
		
	li $v0, 1
	syscall 
	
	li $v0, 10
	syscall
	
caso_base1:
	li $v0, 14
	jr $ra
	
caso_base0:
	li $v0, 10
	jr $ra
	
Func:
	beq $a0, 1, caso_base1
	beq $a0, 0, caso_base0
	
	Push($a0)
	Push($ra)
	
	sub $a0, $a0, 2
	
	subu $v0, $v0, 2
	
	jal Func
	
	Pop($ra)
	Pop($a1)
	
	sub $v0,$v0,2 
	
	jr $ra
