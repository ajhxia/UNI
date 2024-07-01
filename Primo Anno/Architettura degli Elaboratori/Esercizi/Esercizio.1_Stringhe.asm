# Traccia 1. Realizzare un programma in assembly MARS che, definite due stringhe in memoria di ugual lunghezza, 
# calcola la similitudine tra due stringhe. La similitudine è data dal numero dei ceratteri uguali alla stessa posizione diviso 
#la lughezza della stringa.

.data
	frase_1: .asciiz "ciao"
	frase_2: .asciiz "cano "
	final: .asciiz "Il numero di caratteri in comune e': "

.text
.globl main

main:
	li $t0, 0	# indice che scorre le stringhe
	
	li $t1, 0	# contatore delle lettere uguali
	
	li $v0, 4
	la $a0, frase_1
	syscall
	
	li $v0, 4
	la $a0, frase_2
	syscall
	
	j for_loop
	
inc:
	add $t1, $t1, 1
	add $t0, $t0, 1
	j for_loop
	
for_loop:
	lb $t9, frase_1($t0)
	lb $t8, frase_2($t0)
	
	beqz $t9, fine
	
	beq $t9, $t8, inc
	
	add $t0, $t0, 1
	
	j for_loop
	
fine:	
	li $v0, 4
	la $a0, final
	syscall
	
	li $v0, 1
	move $a0, $t1
	syscall
	
	li $v0, 10
	syscall
	