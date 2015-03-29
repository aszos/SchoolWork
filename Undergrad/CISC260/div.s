# Created by Alexander Szostek
# Submitted March 19, 2015
# CISC260-010

# Key
# $a0 - Divisor
# $t0 - Quotient
# $t1 - Quotient at first, becomes Remainder
# $s0 - Counter
# $s1 - Counter Max
# $s2 - 2^31 in binary, used for checking msb of a 32-bit register

# Expected Output
# Quotient should be in $t0
# Remainder should be in $t1
# 19 = (6 * 3) + 1
# $t0 = 00000006
# $t1 = 00000001

.text
.globl main

main:
	li $a0, 3
	# Load Divisor into $a0
	
	li $t0, 19
	li $t1, 0
	#Load quotient into $t0 and remainder(dividend) into $t1 

	li $s0, 0
	li $s1, 32
	#Load loop counter into $s0 and number of loops in $s1

	li $s2, 2147483648
	#Load and constant into $s2
	
divloop:
	#Subtract the divisor register from the remainder register and place the result in the remainder register.
	sub $t1, $t1, $a0

	#if Remainder < 0
	blt $t1, $zero, ltzero
	
	#else if Remainder >= 0
	bge $t1, $zero, gtzero

	ltzero:
		# Restore the original value by adding the divisor register to the remainder register
		# and placing the sum in the remainder register. 
		add $t1, $t1, $a0
		j lshift

	gtzero:
		#Add 1 to the quotient register
		addi $t0, $t0, 1
		j lshift

	lshift:
		#Shift the remainder left
		sll $t1, $t1, 1
		#See if the msb of quotient is a 1
		and $s4, $s2, $t0
		#Branch if msb is a 1
		beq $s4, $s2, shift1
		j cont

	shift1:
		#we "shift" the msb from quotient to the lsb of remainder, only if msb = 1
		addi $t1, $t1, 1
		j cont
		
	cont:
	#Shift the quotient left one bit, msb was shifted into remainder register
	sll $t0, $t0, 1
	#Add 1 to the counter
	addi $s0, $s0, 1
	#Check if we've looped enough
	beq $s0, $s1, loopend	
	j divloop

loopend:
	jr $ra
