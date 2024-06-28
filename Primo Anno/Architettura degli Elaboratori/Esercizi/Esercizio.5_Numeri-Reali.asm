# La ridotta n-esima della serie armonica è definita come: Hn =1+ 1/2 + 1/3 +...+ 1/n Si scriva un programma in linguaggio assemblativo MIPS/MARS che ripeta i passi seguenti:
# legga da tastiera un numero intero n
# se il numero è minore o uguale a 0 termina l’esecuzione
# se il numero è maggiore di 0 stampa Hn (cioè la somma dei primi n termini della serie)

.data
    float_value: .float 0.0  # Spazio per memorizzare il float
    ins: .asciiz "Inserisci un numero: "
    neg: .asciiz "Il numero è negativo."
    ris: .asciiz "Il risultato è: "

.text
.globl main

main:
    li $t4, 0
    li $t2, 1         # Carica il valore 0 in $t2
    mtc1 $t2, $f4       # Copia il valore da $t2 a $f4

    # Prompt per l'utente
    li $v0, 4
    la $a0, ins
    syscall
    
    # Leggi l'input
    li $v0, 5
    syscall
    move $t0, $v0  # Memorizza l'input letto in $t0
    
    # Controllo se il numero è positivo o negativo
    blez $t0, negative  # Salta a negative se il numero è minore o uguale a 0
    
positive:
    li $t1, 1           # Carica il valore 1 in $t1
    mtc1 $t1, $f4       # Copia il valore da $t1 a $f4
    cvt.s.w $f4, $f4
    j for

for: 	
    blez $t0, fine_programma  # Se $t0 è 0, salta alla fine del programma
    
    mtc1 $t0, $f0     	# Metti il valore di $t0 nel registro floating-point
    cvt.s.w $f0, $f0
    div.s $f3, $f4, $f0   # Inverti l'ordine della divisione
    add.s $f5, $f5, $f3  # Aggiungi il termine corrente alla somma
    sub $t0, $t0, 1     # Decrementa $t0
    j for
    
fine_programma:
    li $v0, 4
    la $a0, ris
    syscall
    
    li $v0, 2          	# Stampa il risultato in virgola mobile
    mov.s $f12, $f5
    syscall

end:
    li $v0, 10
    syscall

negative:
    li $v0, 4
    la $a0, neg
    syscall
    
    j end
