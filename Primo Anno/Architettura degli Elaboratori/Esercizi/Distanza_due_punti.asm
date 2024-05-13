# Traccia 3. Scrivere un programma in linguaggio assembaltivo MIPS/MARS che Calcola la distanza tra due punti (x1,y1) (x2,y2).
# NB:i punti x1,y1,x2,y2 sono interi definiti in memoria; la distanza si ottine con la radice quadrata di (x1-x2)^2+(y1-y2)^2
# ES: (1,1) (2,2) d=1.41421

# dichiaro le etichette per maggiore semplicità
.eqv y1, $t0
.eqv y2, $t1
.eqv x1, $t2
.eqv x2, $t3

.text 
.globl main

main:	
	# carico i valori nei rispettivi registri
	li x1, 1 
	li y1, 1
	li x2, 2
	li x2, 2
	
	# faccio la sottrazione dei due punti x1-x2 e y1-y2
	sub $t4, x1, x2
	sub $t5, y1, y2
	
	# faccio l'elevazione al quadrato dei due punti (x1-x2)*(x1-x2) e (y1-y2)*(y1-y2)
	mul $t4, $t4, $t4
	mul $t5, $t5, $t5
	
	# sommo le due elevazioni a potenza 
	add $t6, $t5, $t4
	
	mtc1 $t6, $f0		# sposto il valore di $t6 nel coprocessore 
	cvt.s.w $f0, $f0	# converto $f0 in un numero in virgola mobile single precision 
	
	sqrt.s $f1, $f0		# eseguo la radice quadrata
	
	li $v0, 2		# stampo il valore di $f1
	mov.s $f12, $f1
	syscall
	
	li $v0, 10        
    	syscall  
		