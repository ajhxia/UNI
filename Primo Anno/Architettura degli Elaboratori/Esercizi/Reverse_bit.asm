# Dato un numero di 8 bit, bidogna invertire il numero e poi convertirlo
# Esempio: 00001010 = 10, mentre 01010000 = 80

.data
	num_in: .byte 10
.text
.globl main

main:
	lb $t0, num_in
	li $t1, 0
	li $t3, 8
	j for
	
for: 	
	beqz $t3, fine		
	sll $t1, $t1, 1		# Shift a sinistra di $t1 (per fare spazio al prossimo bit)
	andi $t4, $t0, 1	# Ottiene il bit meno significativo di $t0
	li $v0, 1           
    	move $a0, $t4       
    	syscall
	
	or $t1, $t1, $t4	# Aggiunge il bit a $t1 (costruendo il numero invertito)
	srl $t0, $t0, 1		# Shift a destra di $t0 (per processare il prossimo bit)
	subi $t3, $t3, 1	# Decrementa il contatore del ciclo
	j for			# Cicla finché non sono stati processati tutti e 8 i bit
	
fine:	
	li $v0, 1           
    	move $a0, $t1       
    	syscall
	
	
	
	
