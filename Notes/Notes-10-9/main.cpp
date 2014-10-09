/*
--------------------------------------------------
Tree Rotation Algorithm
1. At a node, this node will be demoted.
2. We need to specify a direction. Child on opposite side is promoted.
--------------------------------------------------
*****Examples*****
Suppose we have:

A
 \
  B
   \
    C


Suppose we rotate A to the left.

   B
 /   \
A     C

The tree is balanced!
--------------------------------------------------
Suppose we have: 

A
 \
  C
 /
B

Suppose we rotate A to the left.
We can't have two lefts!     
 
   C 
 /   
A     
 \
  B

We need to make it the right of A instead of the left of C.
--------------------------------------------------
Suppose we have:

   B
 /   \
A     D
     /  
    C

Suppose we did right(B).

A
 \
  B
   \
    D
   /
  C    

Suppose we did left(B) on the original tree.
 
      D
     /
   B
 /   \
A     C

--------------------------------------------------
Suppose we have:

A
 \
  C
 /
B

and we did right(c).

A
 \
  B
   \
    C

--------------------------------------------------
Suppose we had:

        C
      /
    B
  /
A

And we wanted to balance it.
Right(C) to get B as the root.

    B
  /   \
A       C
--------------------------------------------------
#########The Recipe########## 

Let P = lowest point of imbalance (+2 or -2) above the insertion or deletion.
Let C = P's child on *taller side*.
	-> If P's balance factor is +2, C is on the right.
	-> If P's balance factor is -2, C is on the left.

Rules
1. If P and C have the same sign of the balance factor or C is NULL,
	-> Single Rotation Case at P, away from the taller side.
		-> Left(P) if P is +2, 
		-> Right(P) if P is -2
			-> This promotes or "pulls up" the taller side.
2. If P and C have differing signs of the balance factor.
	-> Double Rotation Case 
		-> Single rotation at C away from its taller side.
		-> Single rotation at P away from c's side.
--------------------------------------------------
Example of a Single Rotaion:

A <-- P (+2)
 \
  B <-- C (+1)
   \
    C

If we do left(a), we get:

    B
  /   \
A       C

--------------------------------------------------
Example of a Double Rotaion:

A <-- P (+2)
 \
  C <-- C (-1)
 /
B

and we did right(c).

A <-- P (+2)
 \
  B <--- C (+1)
   \
    C

Then we do a left(A) .

    B
  /   \
A       C

--------------------------------------------------
*/
