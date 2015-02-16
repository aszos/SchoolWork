/*
----------------------------------------
Heap Properties
----------------------------------------
1. Completeness
2. Heap Order Property (HOP) 

----------------------------------------
Heaps for Priority Queues
----------------------------------------
insert(x)
deleteMin() / deleteMax()

----------------------------------------
Insert(x)
----------------------------------------
1. Put x into the first empty slot that satisfies completeness.
2. If HOP is violated: 
	upheap -> Restore by swapping keys between parent and child, until HOP is restored OR the root is reached

----------------------------------------
removeMax()
----------------------------------------
1. Save key in root node to return later
2. Move last key in heap to root (and delete at that location)
	->Almost definitely violates HOP
3. Restore HOP by swapping down tere until correct level reached <- down-heap/bubble down
4. Swap with biggest/smallest child if max/min heap 

----------------------------------------
Heapsort
----------------------------------------
1. insert() all keys from unordered set S (|S| = n) into Priority Queue (buildheap() = O(n))
2. removeMin() repeatedly, put removed value into the sorted list, until heap is empty (costs O(n log n))

O(n) + O(n log n) = O(n log n) for heapsort

----------------------------------------
Union/Find Algorithm for disjoint sets 
----------------------------------------
Equivalence Relations - a relation that has the following 3 properties:
1. a ~ a (reflexive) 
2. a ~ b iff b ~ a (symmetric)
3. a ~ b and b ~ c, then a ~ c (transitive)

Examples
	Given set S with elements a,b.
	Define a relation R on elements such that a R b is a memeber of {T,F}.
		For example R is "equality".
			If a R b, a == b is a member of {T,F}
		For example R is "<"
			If a R b, a < b is a member of {T,F}

An equivalence relation ~ "induces" equivalence classes of a set S.


1. For each i,j such that i =/= j, si intersection sj = empty set.
2. The sum of all equivalence classes  = S.
*/
