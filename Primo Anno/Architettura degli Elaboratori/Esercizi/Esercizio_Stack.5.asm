# Progettare e codificare un programma in assembly MIPS/MARS che calcola il quoziente
# della divisione tra interi in modo ricorsivo.	

.text
.globl main 

# scrivo il pop e il push dello stack, andando a rimuovere o aggiungere spazi per allocare delle variabili in memoria
.macro Pop(%a)
	lw %a, ($sp)
	add $sp, $sp, 4
.end_macro

.macro Push(%b)
	sub $sp, $sp, 4
	sw %b, ($sp)
.end_macro


main:	
	# mi prendo in input due numeri interi 
	li $v0, 5
	syscall 
	move $a0, $v0
	
	li $v0, 5
	syscall 
	move $a1, $v0
	
	jal Func # mi sposto nella funzione ricorsiva
	
	move $a0, $v0
	
	li $v0, 1
	syscall
	
	li $v0, 10
	syscall
	
caso_base:
	move $v0, $a0
	jr $ra
	
Func:
	blt $a0, $a1, caso_base
	
	Push($ra)
	Push($a0)
	
	sub $a0, $a0, $a1
	
	jal Func
	
	Pop($a0)
	Pop($ra)
	
	add $v0, $v0, 1
	
	jr $ra
	
