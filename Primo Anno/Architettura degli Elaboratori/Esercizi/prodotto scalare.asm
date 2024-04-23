#Definire due vettori di 5 elementi x di valore -2^16<xi<2^15 -1 e eseguire il prodotto scalare (⊥)
#NB: v1=(3,5,8,10,1) e v2=(1,2,3,0,13) QUINDI v1⊥v2= (3*1)+(5*2)+(8*3)+(10*0)+(1*13)=50

.data
	v1: .half 3,5,8,10,1
	v2: .half 1,2,3,0,13
	num: .word 5

#assegno a dei registri un nome per rendere più esplicito il codice
.eqv cont $t0
.eqv i $t6
.eqv somma $t1
.eqv prodscal $t2

.text
.globl main

main:
	lw $t3, num		# carico in $t0 il valore di num = 5
	li cont, 0		# inizializzo il contatore
	li i, 0			# inizializzo l'indice
	li somma, 0		# inizializzo la somma 
	

for:	
	mul i, cont, 2		# spiazzamento all’i-esimo elemento indice*dimensione
	lh $t5, v1(i)		# v1[i]
	lh $t7, v2(i)		# v2[i]
	mul prodscal, $t5, $t7	# eseguo la moltiplicazione tra i due numeri in indice i
	add somma, somma, prodscal	# aggiungo alla somma il valore della moltiplicazione
	add cont, cont, 1	# incremento il contatore
	bne cont, $t3, for	# controllo se il contatore è pari al numero di elementi nel vettore più lungo e in caso esco o rieseguo il ciclo
	
	li $v0, 1		# stampo il numero finale 
	move $a0, somma
	syscall
