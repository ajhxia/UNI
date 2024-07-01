# Traccia 5. Realizzare un programma in assembly MARS che rimuove il carattere spazio, " ",
# da una stringa definita in memoria senza utilizzare una nuova stringa di supporto.
.data
	str: .asciiz "prova a a a a a a prova ccc c c c cc ccccccccc "
.text
.globl main
	
main:
	li $t0, 32 	# carattere dello spazio
	la $t1, str	# carico la stringa dalla memoria
	
	j for_loop
	
for_loop:	
	lb $t4, ($t1)	# prendo l'elemento dalla stringa in posizione $t1. Se fosse stato un array di half: 2($t1)
	beqz $t4, fine	# se il carattere è zero il ciclo finisce	
	
	beq $t4, $t0, shift	# se il carattere è spazio allora shift
	
	sb $t4, ($t1)		# carico il carattere in memoria
	
	add $t1, $t1, 1		# incremento l'indice del registro dove ho caricato la stringa
		
	j for_loop
	
shift:
	add $t1, $t1, 1		# incremento l'indice
	
	lb $t5, ($t1)		# prendo l'elemento successivo allo spazio
		
	subu $t1, $t1, 1	
		
	sb $t5, ($t1)
	
	add $t1, $t1, 1
	
	j for_loop
	
fine:
	li $v0, 4
	la $a0, str
	syscall
	
	li $v0, 10
	syscall
	
	