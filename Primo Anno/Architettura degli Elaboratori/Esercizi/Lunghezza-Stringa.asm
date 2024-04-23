#Calcolare il numero di caratteri di una strianga (str_len function)
#"Dedicato a chi crede che la colonna sonora quando ci sbatti contro… suoni", OUTPUT = 75
.data
	stringa: .asciiz "Dedicato a chi non ha mai letto Non Do Maho, la storia di un ricco avaro giapponese"

.text
.globl main

main:	
	xor $t2,$t2,$t2 # contatore delle lettere 'a'
	li $t8, 97	# inizializzo il registro col valore numerico di 'a' in ASCII	
	la $t0, stringa # inizializzo la stringa nel registro
	
for:
	lb $t9,($t0)    # lettura dell'i-esimo carattere 
	bne $t9, $t8, non_trovata 
	add $t2, $t2, 1

non_trovata:
	add $t0, $t0, 1 
	bnez $t9, for 
	
	move $a0,$t2
	li $v0,1 
	syscall