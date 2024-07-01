# Traccia 2. Si scriva un programma in linguaggio assemblativo MIPS/MARS che legge una sequenza di interi positivi da tastiera 
# (la sequenza termina quando viene inserito il valore -1), conta il numero complessivo dei numeri che sono multipli di 3, di 5 oppure di 7 compresi
# nella sequenza e stampa questo valore. Per esempio, nel caso la sequenza in ingresso è "4 8 12 15 14 8", il programma stampa il valore 3.

.data
    prompt: .asciiz "Inserisci un numero positivo (termina con -1): "
    result: .asciiz "I numeri multipli di 3, 5 o 7 sono: "
    count:  .word 0

.text
.globl main

.macro end
	li $v0, 10
	syscall
.end_macro

main:
	li $v0, 4
	la $a0, prompt
	syscall
	
	li $t0, 0	# contatore dei numeri divisibili per 3, 5, 7
	
for_loop:
	li $v0, 5
	syscall
	
	move $t2, $v0
	
	beq $t2, -1, end_loop
	
	li $t3, 3
	rem $t9 $t2, $t3
	beqz $t9, increment
	
	li $t5, 5
	rem $t9 $t2, $t5
	beqz $t9, increment
	
	li $t7, 7
	rem $t9 $t2, $t5
	beqz $t9, increment
	
	j for_loop
	
increment:
	add $t0, $t0, 1
	j for_loop
	
end_loop:
	li $v0, 4
	la $a0, result
	syscall
	
	li $v0, 1
	move $a0, $t0
	syscall
	
	end