#Scambio dei valori contenuti in due registri. Prima: $t0=5 e $t1=3 Dopo: $t0=3 e $t1=5
    .text
    .globl main
    
main:
    # Carica i valori iniziali nei registri $t0 e $t1
    #li (load Immediate): serve a caricare un valore costante in un registro
    li $t0, 5        # $t0 = 5
    li $t1, 3        # $t1 = 3
    
    # Scambia i valori nei registri $t0 e $t1
    move $t2, $t0    # Salva il valore di $t0 in un registro temporaneo $t2
    move $t0, $t1    # Copia il valore di $t1 in $t0
    move $t1, $t2    # Copia il valore salvato di $t0 (in $t2) in $t1
    
    # Uscita dal programma
    li $v0, 10       # Carica il codice di servizio 10 per l'uscita dal programma
    syscall          # Effettua la chiamata di sistema per l'uscita
