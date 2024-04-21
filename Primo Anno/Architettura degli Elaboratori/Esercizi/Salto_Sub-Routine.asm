.data
	x:.word 45
	y:.word 100
	z:.word 77
.text
.globl main
main:
	lw $a0,x 		#lettura primo valore
	lw $a1,y 		#lettura secondo valore
	jal MASSIMO 		#salto a funzione
	move $a0,$v0		#recupero massimo dalla funzione
	lw $a1,z 		#lettura terzo valore
	jal MASSIMO 		#salto a funzione
	move $a0,$v0 		#recupero massimo dalla funzione
	move $t0,$a0 		#massimo in T0
	li $v0,10
	syscall

MASSIMO:
	# PARAMETRI INGRESSO: A0 e A1 valori interi
	# PARAMETRO USCITA: V0 massimo tra A0 e A1
	move $v0,$a0 		#Imposto A0 come massimo
	bgt $a0,$a1,fine 	#Se A0>A1 allora finisco
	move $v0,$a1 		#Imposto A1 come massimo
fine:
	jr $ra