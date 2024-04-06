#Definite due variabili intere, Batman e Robin, in memoria. Se Batman ha un valore maggiore di zero
#allora il valore di Batman si incrementa di due e il valore di Robin si moltiplica per dieci
.data
Batman: .word 0
Robin: .word 2

.text
.globl main

main:
	lw $t0, Batman
	lw $t1, Robin
	
	bgtz $t0, incremento
	j end
incremento: 
 	add $t0, $t0, 2
 	mul $t1, $t1, 10
end:
	sw $t0,Batman
	sw $t1,Robin
	li $v0, 10
 	syscall
 	