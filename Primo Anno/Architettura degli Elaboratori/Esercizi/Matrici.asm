#Stampa elementi di una matrice
.data
	matrice: .byte 2:20
	R: .word 4
	C: .word 5
	tabulato:.asciiz "\t"
	riga:.asciiz "\n"

.text
.globl main

main:
	li $t0,1 #indice r
	li $t1,1 #indice c
	lw $t2,R #numero righe R
	lw $t3,C #numero colonne C
	
analisi_riga:
	li $t1,1	

analisi_colonna:
	#calcolo elemento r,c
	sub $t6,$t0,1 			
	mul $t9,$t6,$t3 
	sub $t7,$t1,1 			#r,c= C(r-1)+(c-1)
	add $t9,$t9,$t7 
	lb $t8, matrice($t9) 		#prelievo elemento
	
	move $a0,$t8 			#stampa elemento
	li $v0,1 
	syscall 
	
	la $a0, tabulato 		#stampa tabulato
	li $v0, 4
	syscall
	
	addi $t1,$t1,1 			#incremento colonna
	ble $t1,$t3, analisi_colonna
	
	la $a0,riga			#stampa andata a capo (nuova riga)
	li $v0,4
	syscall
	
	addi $t0,$t0,1 			#incremento riga
	ble $t0,$t2, analisi_riga
	
	li $v0,10
	syscall
