.data 
	str: .asciiz "CIAO A TUTTI SIETE INVITATI A PRENDERE UNA CILIEGIA"
	
.text
.globl main

.eqv A, $t0
.eqv E, $t1
.eqv I, $t2
.eqv O, $t3
.eqv U, $t4
.eqv count, $t5
.eqv char, $t7
.eqv i, $t8

main:
	li A, 65
	li E, 69
	li I, 73
	li O, 79
	li U, 85
	
	li i, 0
	
	li count, 0
	
	j for
	
for: 	
	lb char, str(i)
	beqz char, fine
	beq I, char, verifica
	add i, i, 1 
	j for
	
verifica: 
	add i, i, 1 
	lb char, str(i)
	beq char, A, incrementa
	beq char, E, incrementa
	beq char, I, incrementa
	beq char, O, incrementa
	beq char, U, incrementa
	j for
	
incrementa:
	add count, count, 1
	j for
	
fine:
	li $v0, 1
	move $a0, count
	syscall
	
	li $v0, 10
	syscall