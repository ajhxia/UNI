.data
        matrice: .word 0:20
        str: .asciiz "Inserisci la colonna (max 5): "
        R: .word 4
        C: .word 5
        tabulato: .asciiz "\t"
        riga: .asciiz "\n"

.text
.globl main

main:
        li $t0, 1                    # indice RIGA
        li $t1, 1                    # indice COLONNA
        lw $t2, R
        lw $t3, C
        li $a1, 5 
        # mi sposto in genera_matrice

genera_matrice: 
        li $v0, 42
        syscall
        
        sub $t4, $t0, 1              # (r-1)
        mul $t5, $t4, $t3            # C(r-1)
        
        sub $t6, $t1, 1              # (c-1)
        add $t9, $t6, $t5            # C(r-1) + (c-1)
        
        mul $t8, $t9, 4              # moltiplico per 4 dato che la matrice è un semplice array di word
        
        sw $a0, matrice($t8)         # carico l'elemento all'interno della matrice
        
        # stampo l'elemento e il tabulato
        li $v0, 1 
        syscall 
        
        la $a0, tabulato             # stampa tabulato
        li $v0, 4
        syscall
        
        addi $t1, $t1, 1             # incremento l'indice della colonna
        
        ble $t1, $t3, genera_matrice # controllo se la colonna è arrivata alla fine
        
        la $a0,riga                  # stampa andata a capo (nuova riga)
        li $v0,4
        syscall
        
        addi $t0, $t0, 1             # incremento la riga
        
        ble $t0, $t2, reset_colonna
        
        # resetto contatore delle righe e delle colonne prima di spostarmi nell'user interaction
        li $t0, 0
        j user
        
reset_colonna:    
        li $t1, 1                    # imposto di nuovo il valore della colonna ad 1
        j genera_matrice
        
user:
        li $v0, 4
        la $a0, str
        syscall
        
        li $v0, 5
        syscall
        
        move $t9, $v0
        sub $t9, $t9, 1
        
        # Reset sum to 0
        li $t8, 0
        
        # Reset row index
        li $t0, 0
        
for:
        beq $t0, 4, fine
        
        mul $t6, $t0, $t3            # Calculate row offset (R * row_index)
        add $t7, $t6, $t9            # Calculate element index (R * row_index + column_index)
        
        mul $t7, $t7, 4              # Convert word index to byte index
        
        lw $t1, matrice($t7)         # Load matrix element
        
        add $t8, $t8, $t1            # Add element to sum
        
        addi $t0, $t0, 1             # Increment row index
        
        j for
        
fine:
        li $v0, 1
        move $a0, $t8
        syscall
        
        li $v0, 10
        syscall
        
