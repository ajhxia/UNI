#Traccia 5. Scrivere un programma in linguaggio assemblativo MARS che legge un valore intero da tastiera e scrive su videoterminale il numero di 1 che compongono il numero acquisito

.data
	val: .byte 10
	
.text
.globl main

main:
	lb $t0, val
	li $t1, 8
	li $t2, 0
	li $t9, 0
	j for
	
for:	
	beqz $t1, fine
	sll $t2, $t2, 1
	andi $t3, $t0, 1
	li $v0, 1
	move $a0, $t3
	syscall
	or $t2, $t2, $t3
	srl $t0, $t0, 1
	beq $t3, 1, inc
	subi $t1, $t1, 1
	j for
	
inc:
	add $t9, $t9, 1
	subi $t1, $t1, 1
	j for

fine:
	li $v0, 1
	move $a0, $t9
	syscall
	
	li $v0, 10
	syscall	
	
	
