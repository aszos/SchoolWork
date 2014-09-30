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

*/

