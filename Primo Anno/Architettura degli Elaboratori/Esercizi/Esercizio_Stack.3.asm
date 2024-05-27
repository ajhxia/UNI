# Scrivere il codice di una funzione ricorsiva int f(int n) che restituisce quante coppie di cifre uguali in posizioni adiacenti ci sono nel numero n, 
# nel caso n sia negativo restituisce 0.


.text
.globl main

.macro Push(%a)
	sub $sp, $sp, 4
	sw %a, ($sp)
.end_macro

.macro Pop(%b)
	lw %b, ($sp)
	add $sp, $sp, 4
.end_macro

main:
	li $v0, 5
	syscall 
	
	move $a0, $v0
	
	jal Func
	
	move $a0, $v0
	
	li $v0, 1
	syscall 
	
	li $v0, 10
	syscall
	
caso_base:
	li $v0, 0
	jr $ra	
	
Func:	
	blez $a0, caso_base # se $a0 è 0 allora va al caso base
	
	rem $t1, $a0, 10 # tengo il contenuto della divisione $a0, 10
	div $a0, $a0, 10	# decremento $a0
	
	Push($t1) # pusho i valori che mi servono nello stack, ossia il numero estratto dal rem e il registro ra
	Push($ra)
	
	jal Func
	
	# mi recupero i due valori consegutivi dallo stack
	Pop($ra)
	Pop($t2)
	
	Pop($ra)
	Pop($t3)
	
	beq $t2, $t3, somma # se sono uguali incremento il contatore in somma
	j skip # altrimenti rimetto nello stack l'ultimo valore estratto per controllarlo nell'esecuzione successiva
	
somma:
	add $v0, $v0, 1

skip:
	Push($t3)
	Push($ra)
	jr $ra

	
	