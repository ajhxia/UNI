#Somma 5 volte il valore 15 e memorizza il risultato in una variabile totale
.data
totale: .word 0

.text
.globl main
main:
	li $t9,0 		# INIZIALIZZAZIONE INDICE i
	li $t0,15		# lettura del valore da sommare
	li $t1,0 		# Valore della somma che andrà nella variabile totale
FOR:
	blt $t9,5,BLOCCOFOR 	# CONTROLLO CONDIZIONE FOR (cioè i<5)
	j FINE 			# TERMINAZIONE FOR

BLOCCOFOR: 			# INIZIO BLOCCO FOR
	add $t1,$t1,$t0 	# ssommo 15 a 15
	addi $t9,$t9,1 		# INCREMENTO INDICE FOR
	j FOR 			# SALTO AL NUOVO CONTROLLO FOR
FINE:
	sw $t1,totale	 	# Salva risultato in totale
	li $v0,10
	syscall