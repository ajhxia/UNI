# Traccia 5. Scrivere un programma in assembly che definsice un vettore di 10 elementi con numeri casuali compresi tra 0 e 999 e calcoli alcuni dati statistici:
#1) somma
#2) media
#3) min
#4) max
# PS: si consgilia di usare uan funzione per ogni analisi statistica

.macro Push(%a)
	sub $sp, $sp, 4
	sw %a, ($sp)
.end_macro

.macro Pop(%b)
	lw %b, ($sp)
	add $sp, $sp, 4
.end_macro

.data
	vet: .word 0:10
	len: .word 10
	tab: .asciiz "\t"
	somma: .asciiz "La somma dei numeri dell'array e': "
	somma_msg: .asciiz "La somma dei numeri dell'array e': "
	media_msg: .asciiz "La media dei numeri dell'array e': "
	n: .asciiz "\n"
	
.text
.globl main

main:
	la $t0, vet 	# carico l'array di 10 elementi inizializzati a 0
	li $t1, 0 	# indice per scorrere l'array
	li $a1, 10	
	lw $t2, len
	
	jal load_element
	
	jal Somma
	jal media
	
	li $v0, 10
	syscall
	
	
load_element:
	beq $t1, $t2, fine_ciclo
	
	# mi prendo il valore random compreso tra 0 e 10
	li $v0, 42
	syscall
	
	add $t1, $t1, 1
	
	Push($ra)
	Push($a0)
	
	jal load_element
	
	Pop($t3)
	Pop($ra)
	
	sw $t3, ($t0)
	
	add $t0, $t0, 4
	
	li $v0, 1
	move $a0, $t3
	syscall 
	
	li $v0, 4
	la $a0, tab
	syscall
	
fine_ciclo:	
	move $v0, $t0
	jr $ra 

Somma:
    	li $t0, 0
    	la $t1, vet
    	lw $t2, len
    	li $t3, 0
    
Somma_loop:
    	beq $t3, $t2, fine_somma
    	lw $t4, ($t1)
    	add $t0, $t0, $t4
    	add $t1, $t1, 4
    	add $t3, $t3, 1
    	j Somma_loop
    
fine_somma:

	li $v0, 4
	la $a0, n
	syscall
	
    	li $v0, 4
    	la $a0, somma_msg
    	syscall
    
    	li $v0, 1
   	move $a0, $t0
    	syscall
    
    	jr $ra	
    	
media:
	li $t0, 0
	la $t1, vet
	lw $t2, len
	li $t3, 0
	
media_loop:	
	beq $t3, $t2, fine_media
	lw $t4, ($t1)
	add $t0, $t0, $t4
	add $t1, $t1, 4
	add $t3, $t3, 1
	j media_loop
	
fine_media:
	div $t0, $t2
	mflo $t5
	
	li $v0, 4
	la $a0, n
	syscall
	
	li $v0, 4
	la $a0, media_msg
	syscall	
	
	li $v0, 1
	move $a0, $t5
	syscall 
	
	jr $ra
	
	
	
	
	

		
	
	
	
	
