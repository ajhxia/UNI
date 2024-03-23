#Definiti due valori in memoria, Diabolik e Ginko, scambiare la loro posizione
.data
diabolik: .word 4
ginko: .word 8
    
	.text
	.globl main

main:
	#carico il contenuto di Diabolik e di Ginko in memoria
	lw $t1, diabolik #$t1 = Diabolik
	lw $t0, ginko #$t0 = Ginko 
	
	sb $t0, diabolik #ora $t1 ha Ginko mentre $t0 Diabolik
	sb $t1, ginko 
#Chiedere al professore perchè se scrivo ad esempio a diabolik assegno qualunque tipo di variabile che non sono di 32bit allora sw da errore. 