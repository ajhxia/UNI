# Traccia 6. Realizzare un programma in assembly MARS che concatena due stringhe definite in memoria. La concatenazione 
# è l'unione di due stringhe. Es.: stringa1: "Gatto" stringa2: "matto" concatenazione="Gattomatto"

.data
	str: .asciiz "gatto"
	str2: .asciiz "matto"
	conc: .space 10
.text
.globl main
	
main:	
	la $t0, str
	la $t1, str2
	la $t9, conc
	
	j for1
	
for1:
	lb $t2, 0($t0)
	
	beqz $t2, for2
	
	sb $t2, 0($t9)
	
	add $t0, $t0, 1
	add $t9, $t9, 1
	
	j for1
	
for2:	
	lb $t3, 0($t1)
	
	beqz $t3, fine
	
	sb $t3, 0($t9)
	
	add $t1, $t1, 1
	add $t9, $t9, 1
	
	j for2

fine:
	li $v0, 4
	la $a0, conc
	syscall
	
	li $v0, 10
	syscall
	