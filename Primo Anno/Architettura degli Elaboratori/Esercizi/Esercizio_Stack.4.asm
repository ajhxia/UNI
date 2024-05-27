# Si consideri la funzione f definita su interi: f(x,y) = 2∙f(x-2,y-5), f(0,y) = 1, f(x,0) = 2, f(0,0)=3
# Definiti due interi positivi x≥2 e y ≥2, calcola il corrispondente valore di f(x,y) in modo ricorsivo

.text
.globl main

.macro Pop(%a)
	lw %a, ($sp)
	add $sp, $sp, 4
.end_macro

.macro Push(%b)
	sub $sp, $sp, 4
	sw %b ($sp)
.end_macro

main:
	li $v0, 5
	syscall 
	move $a0, $v0
	
	li $v0, 5
	syscall 
	move $a1, $v0
	
	jal Func
	
	move $a0, $v0
	
	li $v0, 1
	syscall
	
	li $v0, 10
	syscall
	
caso_base1:
	beqz $a0, caso_base 
	li $v0, 2
	jr $ra
	
caso_base0:
	beqz $a1, caso_base 
	li $v0, 1
	jr $ra
	
caso_base:
	li $v0, 3
	jr $ra
	
Func:
	beqz $a1, caso_base1
	beqz $a0, caso_base0
	
	Push($ra)
		
	sub $a0, $a0, 2
	sub $a1, $a1, 5
	
	jal Func
	
	Pop($ra)
	
	mul $v0, $v0, 2
	
	jr $ra
	
	
	