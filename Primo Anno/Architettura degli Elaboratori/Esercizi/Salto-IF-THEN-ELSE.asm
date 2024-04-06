.data
Batman: .word 5
Robin: .word 0

.text
.globl main
main:
	lw $t0,Batman 			# carica Batman
	lw $t1,Robin 			# carica Robin
	bgtz $t0, RAMO_THEN 		# salto se condizione vera al BLOCCO IF
	j RAMO_ELSE 			# salto incondizionato in caso condione falsa al BLOCCO ELSE
RAMO_THEN: 				# INIZIO BLOCCO IF
	add $t0,$t0,1 			# Batman=Batman+1
	add $t1,$t0,2 			# Robin=Batman+2
	j FINE_IF			# FINE BLOCCO IF
RAMO_ELSE:				# INIZIO BLOCCO ELSE
	sub $t0,$t0,1 			#Batman=Batman-1
	sub $t1,$t0,2 			# Robin=Batman-2
FINE_IF: 				# FINE BLOCCO IF
	sw $t0,Batman 			# salva $t1 in Batman
	sw $t1,Robin 			# salva $t1 in Robin
	li $v0,10
	syscall
