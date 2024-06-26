.data
	vet: .half 0,0,0,0,0,0
	len: .byte 6
	
.text
.globl main

main:
	lb $t0, len
	mul $t3, $t0, 2
	li $t1, 3
	li $t2, 0
	li $t9, 6
	sh $t1, vet($t9) 
	j for
	
for:	
	beq $t0, $t2, fine
	mul $t4, $t2, 2
	li $v0, 1
	lh $a0, vet($t4)
	syscall
	add $t2, $t2, 1
	j for
	
fine:
	li $v0, 10
	syscall
