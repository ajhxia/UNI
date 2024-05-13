# Realizzare un programma in assembly MARS che, definita una stringa in memoria 
# e letti due caratteri da tastiera search e replace, sostituisce il carattere seach con replace.
.data
	string: .asciiz "Ciao mi chiamo Alessia e ho 20 anni"
	scS: .asciiz "Inserisci il carattere da sostituire: "
	rpS: .asciiz "Inserire il carattere con cui vuoi sostituire: "
	
	
.eqv ind, $t0
.eqv search, $t1
.eqv replace, $t2
.eqv str, $t3
.eqv ch, $t4

.text 
.globl main

main:
	li ind, 0
	
	li $v0, 4
	la $a0, scS
	syscall
	
	li $v0, 12
	syscall
	move search, $v0 
	
	
	li $v0, 4
	la $a0, rpS
	syscall
	
	li $v0, 12
	syscall
	move replace, $v0 
	

for:	
	lb ch, string(ind)
	beqz ch, fine
	
	beq ch, search, replace_letter
	
	add ind, ind, 1
	j for 
	
replace_letter:
	sb replace, string(ind)
	add ind, ind, 1
	
	j for

fine:
	li $v0, 4
	la $a0, string
	syscall
	
	li $v0, 10
	syscall	
	

	
	
