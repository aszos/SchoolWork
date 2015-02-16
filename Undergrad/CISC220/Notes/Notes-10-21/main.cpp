/*
--------------------
Priority Queues
--------------------
Order of insertion does not determine order of removal
Key "priority" sets removal order

--------------------
Abstract Data Type 
--------------------
insert(el) -> enqueue() equivalent
removeMin() / removeMax() -> dequeue() equivalent
OPTIONAL: peakMin() / peakMax() - top() equivalent 

--------------------
Implementations
--------------------
Ordered Linked List
--> insert - O(n)
--> removeMin() - O(1)

Unordered Linked List
--> insert - O(1)
--> removeMin() - O(n)

Balanced BST (AVL)
--> insert - O(log n)
--> removeMin() - O(log n)

Binary "Heap"
--> insert - O(log n)
--> removeMin() - O(log n)

--------------------
Binary "Heap"
--------------------
Binary Trees with 2 Properties:
1. "Completely Balanced": all "levels" of the tree are filled from left to right
-> All "missing" nodes on the bottom level, far right

Completely Balanced 
  A
 /
B

Not Completely Balanced 
A
 \
  B
 
2. "Heap Order" Property 
Every node i's key <= keys of i's descendants -> "min heap"
-> Root of the min heap has the smallest key
-----OR----- 
Every node i's key >= keys of i's descendants -> "max heap"
-> Root of the max heap has the largest key

--------------------
Heap Representation as an Array
--------------------
Max Heap

      z
    /   \
   p     l
  / \   / 
 i   c d

Array of Max Heap

  z p l i c d
0 1 2 3 4 5 6 7

Suppose we insert(F)

By the completely balanced property, we can only place it:

      z
    /   \
   p     l
  / \   / \   
 i   c d   f


  z p l i c d f
0 1 2 3 4 5 6 7

The root is at index 1.
left(i) = 2i
right(i) = 2i + 1
parent(i) = floor[i/2]


insert(_)
-> Put _ in first "empty" slot according to the level numbering : O(1)
-> If heap order property (HOP) is violated
	-> swap wth the parent 
		-> until HOP is not violated OR root reached O(log n)

Complexity: O(log n)

*/
