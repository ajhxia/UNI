#Si scriva un programma in linguaggio assembly che definiti due numeri reali in memoria r1 e r2 riporta la media (fra r$f2)
.data
 	r1 : .float 5.0
        r2: .float 3.0
	due: .float 2.0

.text
.globl main

main:
	lwc1 $f0,r1 		#carico r1
        lwc1 $f1,r2 		#carico r2
	lwc1 $f3,due 		#carico il valore 2.0
	add.s $f2,$f0,$f1	#m=x+y
	div.s $f2,$f2,$f3	#media
	li $v0,10
	syscall
