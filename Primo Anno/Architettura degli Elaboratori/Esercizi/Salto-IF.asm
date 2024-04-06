#Definite due variabili intere , Batman e Robin, in memoria se Batman ha un valore maggiore di Robin 
#allora il valore di Robin diventa uguale al valore di Batman incrementato di due

.data
Batman: .word 3
Robin: .word 2

.text
.globl main

main:
	lw $t0, Batman
	lw $t1, Robin
	
	bgt $t0, $t1, incremento
	j end
incremento: 
 	add $t1, $t1, 2
end:
	sw $t1,Robin
	li $v0, 10
 	syscall
 	