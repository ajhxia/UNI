#Realizzare un programma che valuta il massimo fra due numeri x,y (di tipo byte) definiti in memoria. Riportare il risultato in memoria
.data 
	x: .word 6
	y: .word 4

.text
.globl main

main:
	lw $t0, x 
	lw $t1, y
	bgt $t1, $t0, salto #se t0 è maggiore di t1 salta in "salto" 
	
	move $t3, $t0 # questa operazione viene eseguita se t0<t1 
	j end #salta alla fine del programma. In questo modo non viene eseguito "salto"
salto:
	move $t3, $t1

end: