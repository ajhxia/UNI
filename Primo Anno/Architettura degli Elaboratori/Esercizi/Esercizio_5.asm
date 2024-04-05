#Si scriva un programma in linguaggio assembly che definiti due numeri in memoria riporta la loro media (fra interi) in $t2 Esempio: Batman=5 Robin=8 media=(5+8)/2=6
.data 
	batman: .word 5 
	robin: .word 8
	
.text
.globl main
main:
	lw $t0, batman 
	lw, $t1, robin
	add $t3, $t0, $t1
	div $t2, $t3, 2 #divisione tra $t2/$t3