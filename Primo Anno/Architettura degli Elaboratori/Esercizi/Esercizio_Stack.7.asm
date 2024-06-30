# Progettare e codificare un programma in assembly MIPS/MARS che determina se un numero è primo in modo ricorsivo, cioè (1 NON PRIMO, 0 PRIMO)


.text
.globl main

.macro Push(%x)
	sub $sp, $sp, 4
	sw %x, ($sp)
.end_macro

.macro Pop(%x)
	lw %x, ($sp)
	add $sp, $sp, 4
.end_macro

main:	
	li $t1, 2
	move $a1, $t1	# valore passato come parametro alla funzione (i)
	
	li $v0, 5
	syscall 
	
	move $a0, $v0	# valore passato alla funzione come parametro (n)
	
	li $v0,0	# carico in $v0 il valore di 0 perchè in caso in cui non è un numero primo deve stampare 0
	
	jal Func
	
	move $a0, $v0
	
	# stampo il risultato ottenuto dalla ricorsione 
	li $v0, 1
	syscall
	
	# fine del programma 
	li $v0, 10
	syscall

caso_base:
	li $v0, 1	# imposto $v0 con il numero 1 perchè è un numero primo
fine:
	jr $ra		# fine della ricorsione 
	
Func:
	rem $t3, $a0, $a1	# mi prendo il resto della divisione 
	ble $a0, $a1, fine 	# n<i
	beqz $t3, caso_base 	# (n%i) == 0
	
	Push($a1)
	Push($ra)
	
	add $a1, $a1, 1		# incremento il contatore i 
	
	jal Func
	
	# elimino i valori dalla memoria
	Pop($ra)
	Pop($a2)
	
	jr $ra