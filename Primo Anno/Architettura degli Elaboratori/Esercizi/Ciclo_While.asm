#Si ripete l’incremento di Robin della quantità di Batman che va a diminuire di una unità ad ogni ciclo
#fino a quando il valore di Batman è maggiore di 0

.data
Batman: .word 5
Robin: .word 2

.text
.globl main

main:
	lw $t0, Batman
	lw $t1, Robin
	j while
	
while: 
	beqz $t0, incremento
	j end
	
incremento: 
	add $t1, $t1, $t0
	sub $t0, $t0, 1
	j while

end:
	sw $t0,Batman # Salva risultato in Batman
	sw $t1,Robin # Salva risultato in Robin
	li $v0,10
	syscall
