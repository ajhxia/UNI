#Realizzare un contatore da 0 a 100
.text
.globl main

main:
	
#realizzo una lable
loop: 
	add $t0, $t0, 1 #aggiungo 1 a $t0
	blt $t0, 100, loop # se 100>$t0 allora va a loop
	