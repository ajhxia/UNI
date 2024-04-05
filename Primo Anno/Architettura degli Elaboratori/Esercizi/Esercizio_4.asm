#Si scriva un programma in linguaggio assembly che definito un valore intero in memoria riporti in $t0 l’intero precedente, in $t1 il numero corrente e in $t2 il successivo

.text
.globl main

main: 
	li $t1, 10 #assegno $t1 = 10
	li $t3, -1 #assegno $t3 = -1
	li $t4, 1  #assegno $t4 = 1
	add $t0, $t1, $t3 # $t0 = $t1 + $t3
	add $t2, $t1, $t4 # $t2 = $t1 + $t4
