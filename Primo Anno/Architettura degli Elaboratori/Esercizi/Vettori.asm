#Stampa di un vettore costituito da elementi di 16bit
.data
	array: .half 12,43,23,54,77
	nunelem: .word 5
	tabulato: .asciiz "\t

.text
.globl main

main:
	# STAMPA DI UN VETTORE
	lw $t1,nunelem 		# $t1 numero elementi del vettore
	li $t2,0 		# $t2 indice
loop:
	mul $t3,$t2,2 		# indice*dimensione (moltiplicare x 2 perché halfword)
	lh $t4,array($t3) 	# copia dell’i-esimo elemento nel registro $t4 cioè $t4=v[i]
	move $a0,$t4 		#sposto l’elemento per effettuarne la stampa
	li $v0,1 		#stampo i-esimo elemento
	syscall
	la $a0,tabulato 	# Stampo un tabulato (per una maggiore leggibilità)
	li $v0,4
	syscall
	add $t2,$t2,1 		# incremento indice
	blt $t2,$t1,loop	#confronto per determinare la fine del vettore
li $v0,10
syscall
