#Si scriva un programma in linguaggio assembly che letti quattro numeri interi da tastiera effettua la media dei primi due numeri
#e la media dei secondi due numeri e pone in $t9 il valore 1 se la prima media è maggiore della seconda altrimento $t9 è impostato a 0
.data
	leggi: .asciiz "Inserisci un numero:"
	due: .float 2.0
.text
.globl main

main:	
	# faccio le due chiamate con scritta output 
	la $a0, leggi
	li $v0, 4
	syscall 
	
	li $v0, 5
	syscall
	move $t0, $v0
	
	la $a0, leggi
	li $v0, 4
	syscall 
	
	li $v0, 5
	syscall
	move $t1, $v0
	
	add $t1, $t1, $t0 	#sommo i due numeri inseriti in output
	mtc1 $t1,$f0	  	#sposto il valore in $f0
	cvt.s.w	$f0, $f0	#converto il numero in single precision
	lwc1 $f1, due
	div.s $f2, $f0, $f1
	
	# faccio le due chiamate con scritta output 
	la $a0, leggi
	li $v0, 4
	syscall 
	
	li $v0, 5
	syscall
	move $t2, $v0
	
	la $a0, leggi
	li $v0, 4
	syscall 
	
	li $v0, 5
	syscall
	move $t3, $v0
	
	add $t2,$t2,$t3 	#sommo i valori
	mtc1 $t2,$f3		#sposto il valore in $f0 (senza convertirlo)
	cvt.s.w $f3,$f3 	#converto il valore in standard IEEE 754
	div.s $f4,$f3,$f1 	#media in $f1
	
	c.lt.s 3, $f2, $f4	#metto a 1/TRUE/SPUNTA il flag se la prima media è minore della prima
	li $t9,1

	bc1f 3, fine 		#salto se il flag 3 è settato a 0/FALSE/NOSPUNTA (non è flaggato) cioè $f2>$f4 ovvero la prima media e maggiore della seconda
	li $t9,0

fine:	
	# Fine del programma
    	li $v0, 10       # Carica il codice della syscall per terminare il programma ($v0 = 10)
    	syscall          # Esegui la syscall
	
