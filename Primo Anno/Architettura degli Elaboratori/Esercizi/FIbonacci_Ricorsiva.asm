#Realizzare la stringa di fibonacci f(n)=f(n-1)+f(n-2), f(1)=1, f(0)= 1
.macro Pop(%a)
	lw %a, ($sp)
	add $sp, $sp, 4
.end_macro

.macro Push(%b)
	sub $sp, $sp, 4
	sw %b ($sp)
.end_macro

.text
.globl main 

main:

	li $v0, 5
	syscall
	move $a0, $v0
	
	j Func
	
	move $a0, $v0
	
	li $v0, 1
	syscall
	
	li $v0, 10
	syscall
	
caso_base:
	li $v0, 1
	jr $ra

Func:

	beq $a0, 1, caso_base
	beqz $a0, caso_base
	
	sub $a0, $a0, 2
	add $a1, $a0, 1
	
	Push($ra)
	Push($a0)
	Push($a1)
	
	jal Func
	
	Pop($a2)
	Pop($a3)
	Pop($ra)

	add $v0, $a3, $a2
	
	jr $ra
