#Si scriva un programma in linguaggio assembly che definiti due numeri interi in memoria val1 e val2 riporta la media in memoria
.data
	val1: .word 3455
	val2: .word 4562
	due: .float 2.0
	media:.float 0.0

.text
.globl main
main:
	lw $t0,val1 		#carico val1
	lw $t1,val2 		#carico val2
	add $t0,$t1,$t0 	#sommo i valori
	mtc1 $t0,$f0 		#sposto il valore in $f0 (senza convertirlo)
	cvt.s.w $f0,$f0 	#converto il valore in standard IEEE 754
	lwc1 $f3,due 		#carico il valore 2.0
	div.s $f2,$f0,$f3 	#media
	swc1 $f2, media
	li $v0,10
	syscall
