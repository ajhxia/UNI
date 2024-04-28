# Dato un numero di 8 bit, bidogna invertire il numero e poi convertirlo
# Esempio: 00001010 = 10, mentre 01010000 = 80

.data
	num_in: .byte 10
	nunelem: .word 8
	array_in: .byte 0,0,0,0,0,0,0,0
.text
.globl main

.eqv $t1, i
.eqv $t0, num
.eqv $t2, array

main:
	li i, 0
	lb num, num_in
	li array

	
	
	
