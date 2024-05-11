.data
	stringaIn: .asciiz "Inserisci un numero: "
	numZero: .asciiz "Il numero che hai inserito è zero, per tanto è impossibile"
	fineP: .asciiz "il logaritmo in base 2 dell'input è: "

.text
.globl main

.macro FINE
	li $v0, 10
	syscall 
.end_macro

main:	
	li $t1, 0
	li $t2, 2
	li $t3, 2
	
	li $v0, 4
	la $a0, stringaIn
	syscall
	
	li $v0, 5
	syscall
	move $t0, $v0
	
	beqz $t0, zero
	j for
	
zero:	
	li $v0, 4
	la $a0, numZero
	syscall
	
	FINE
	
for:	
	bgt $t3, $t0, fine_programma
	mul $t3, $t3, $t2
	add $t1, $t1, 1
	j for
	
fine_programma:
	
	li $v0, 4
	la $a0, fineP
	syscall
	
	li $v0, 1
	move $a0, $t1
	syscall
	
	FINE