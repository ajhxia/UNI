.text
.globl main
.macro FINEPROGRAMMA #definizione macro
	li $v0,10 #istruzione macro
	syscall #istruzione macro
.end_macro #chiusura macro
.eqv VAR1 $t0 #potrò utilizzare VAR1 come nome di variabile
.eqv VAR2 $t1
.eqv TOT $t2

main:
	lw VAR1, Joker
	lw VAR2, Goblin
	li TOT, 0
ciclo:
	bltz VAR2, fine
	add TOT, TOT, VAR1
	sub VAR2, VAR2, 1
	j ciclo
fine:
	FINEPROGRAMMA #chiamata macro (sostituzione codice)
	
.data
	Joker:.word 4
	Goblin:.word 6