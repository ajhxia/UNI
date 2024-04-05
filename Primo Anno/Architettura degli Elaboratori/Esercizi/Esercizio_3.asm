#Realizzare un programma che in linguaggio MIPS prenda un valore residente in memoria (di tipo byte) e metta 0 o 1 se il numero è rispettivamente pari o dispari.

.data 
	x: .byte 5 #in x assegno 5 (un byte) quindi, x = 00000101.Ma viene messo in una cella di memoria di 32 bit
.text
.globl main

main:
	lw $t0, x
	sll $t0, $t0, 31 #carico nel registro lo shift a sinistra di sette posizioni della variabile x (x non cambia) quindi, $t0 = 10000000 
	srl $t0, $t0, 31 #carico nel registro lo shift a destra di sette posizioni della variabile x (x non cambia) quindi, $t0 = 00000001

#abbiamo shiftato a sinistra per rimuovere tutti i valori precedenti all'ultimo, poi a destra per lasciare come ultimo valore o 0 o 1.