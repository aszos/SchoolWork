/*
How do we delete something in a binary search tree?

Algorithm
1. If empty tree, do nothing
2. If the key is not in the tree, do nothing
3. If a key is found
	-> If it is a leaf
		-> Lop it off
	-> if the node has ONE child: Delete that node and make parent's child point to deleted node's child
	-> if the node with TWO children: Erase key in node
	
(Check online notes)

Complexity Analysis
	- We want to know time and space used by an algorithm -> How efficient is it?

Example: Enqueue(), using linear vs. circular arrays

We want to "count" the number of operations that an algorithm does
	- Arithmetic, comparison
	- Assignment	
	- Array Indexing/referencing (A[i])
	- Function call

-------------------------

We want to know the worst case performance!


What is the worst case?
Depends on the efficiency of functions called is worse.

	if (a < b)
		function_1();
	else
		function_2();


Definition of "big O" 
	A function f(n) if O(g(n)) if there exists positive numbers c,N such that 
		f(n) <= c * g(n) for n >= N


In the long run, g(n) grows faster than f(n).
g(n) is an upper bound on f(n).
"f is big O of g."

-------------------------
Big O conventions
1. Drop constant factors from g.
2. Drop lower-order terms from polynomial g.
3. Want the "tightest" upper bound, so we use the smallest g(n) function.

Examples
O(1/2 (n^2 + n)) --> O(n^2 + n) --> O(n^2)
2 + 2n is O(n^3)
       is O(n^2)
       is O(n), is the smallest

--------------------------------------------------
**********Big O Function Classes**********

O(k) - constant (not dependent on input)
O(log n) - logarithmic (base does not matter)
O(n) - linear
O(n log n) - linerithmic
O(n^2) -  quadratic
O(n^3) -  cubic
O(n^k) -  polynomial
O(2^n) - exponential (base does not matter)

linear enqueue() - O(n)
circular enqueue() - O(1)
--------------------------------------------------
Example of Complexitiy Analysis

contains() on a BST
	-> worst case = height of the tree ==> O(h)
Assuming the tree is full and perfect, O(log n)
Any traversals are O(n).

--------------------------------------------------
AVL Trees
	-> BSTs with a balance property added
	Definition of Balanced - For every node, (difference in height of left subtree) + (height of right subtree) <= 1 

inserts and deletes work the same, you just need to check it every time!
The check is gonna check the balance notation.

Tree rotations: BAsic transform that will be used to fix imbalances
	1. Changes (possibly) subtree height.
	2. Preserves BST ordering property.

A rotation happens...
	1. At a node
	2. In a direction

Promotion/Demotion - Push node down, bring child up
--------------------------------------------------
*/
