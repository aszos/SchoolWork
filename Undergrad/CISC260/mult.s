# Created by Alexander Szostek
# Submitted March 19, 2015
# CISC260-010

# Expected Output
# 10 * 5 = 50 
# $s0 = 00000032 (50 in base 16)

.text
.globl main

main:
	li $a0, 10
	li $a1, 5
	#Load multiples into $a0 and $a1

	li $t0, 1
	li $t1, 0
	#Load 1 and 0 as constants for checking lsb

	li $s0, 0
	#Load sum into $s0

multloop:
	beq $a1, $zero, loopend
	#If the multiple is 0, we are done
	and $t1, $a1, $t0
	#See if lsb of the multiple is 0
	beq $t1, $t0, doadd
	#If lsb = 1, we add
	beq $t1, $zero, doshift
	#If lsb = 0, we shift

	doadd:	
		addu $s0, $s0, $a0
		#Add to current sum	

	doshift:
		sll $a0, $a0, 1
		srl $a1, $a1, 1
		#We shift top multiple left and bottom multiple right one bit

	j multloop
	#Repeat the loop

loopend:
	jr $ra
