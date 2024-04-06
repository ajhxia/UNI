 #Svolgere il totale di valori (interi) immessi da tastiera con terminazione quando il valore è zero
.text
.globl main
main:
	li $t0,0 # inizializza il registro che ospiterà totale
	li $v0,5 # Servizio di lettura intero
	syscall # Chiamata del servizio
	move $t1,$v0 # spostamento del valore letto da tastiera
WHILE:
	beqz $t1,END_WHILE #Esce dal CICLO WHILE se condizione è falsa
	add $t0,$t0,$t1 # sommatoria
	li $v0,5 # Servizio di lettura intero
	syscall # Chiamata del servizio
	move $t1,$v0 # spostamento del valore letto da tastiera
	j WHILE # SALTO CICLO WHILE
END_WHILE:
	sw $t1,totale # Salva risultato in totale
	li $v0,10
	syscall

.data
totale: .word 0