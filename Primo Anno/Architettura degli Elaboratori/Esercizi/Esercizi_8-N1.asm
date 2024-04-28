#Scrivere in linguaggio assemblativo MIPS/MARS una funzione che riceve in ingresso due numeri interi a e b, scrivere una funzione che restituisca il risultato 
#di a elevato alla b; proporre quindi un adeguato main di prova (i numeri possono essere definiti in memoria o - meglio - inseriti da tastiera). 
#Il risultto deve essere mostrato su videoterminale.
.data
    stringa: .asciiz "Inserisci un numero: "
    fine: .asciiz "Risultato: "
.text
.globl main

.eqv a, $t0
.eqv c, $t1
.eqv result, $t2
.eqv i, $t3

main:
    # Input a
    li $v0, 4
    la $a0, stringa
    syscall

    li $v0, 5
    syscall
    move $a0, $v0
    move a, $a0  

    # Input c
    li $v0, 4
    la $a0, stringa
    syscall

    li $v0, 5
    syscall
    move $a0, $v0
    move c, $a0  

    # richiamo una funzione dove inizializzare i valori
    li result, 1      		# inizializzo result to 1
    li i, 0
    jal Func   

Inizialize:
          		# inizializzo i to 0

Func: 		
    mul result, result, a    	# result *= a
    addi i, i, 1         	# incremento i
    bne i, c, Func		# se i >= b, finisco il ciclo
    j End

End:
    li $v0, 4
    la $a0, fine
    syscall

    li $v0, 1
    move $a0, $t2 
    syscall

    # Exit
    li $v0, 10
    syscall
