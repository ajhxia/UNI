# Traccia 1. Scrivere in linguaggio assemblativo MIPS/MARS una funzione che riceve in ingresso due numeri interi a e b, 
# scrivere una funzione che restituisca il risultato di a elevato alla b; proporre quindi un adeguato main di prova 
# (i numeri possono essere definiti in memoria o - meglio - inseriti da tastiera). Il risultato deve essere mostrato su videoterminale.

.data 
	insA: .asciiz "Inserisci a: "
	insB: .asciiz "\n Inserisci b: "
.text 
.globl main

.eqv ris, $a2
.eqv ind, $t1


main:	
	li ind, 0
	li ris, 0
	
	li $v0, 4
	la $a0, insA
	syscall
	
	li $v0, 5
	syscall
	move $t4, $v0
	
	li $v0, 4
	la $a0, insB
	syscall
	
	li $v0, 5
	syscall
	move $t7, $v0
	
	jal elevamento
	
	li $v0, 1
	move $a0, ris
	syscall
	
	li $v0, 10
	syscall 
	
elevamento:
	beq ind, $t7, fine
	
	mul $t5, $t4, $t4
	add ris, ris, $t5
	
	add ind, ind, 1
	j elevamento
	
fine:
	jr $ra
	
	
	
