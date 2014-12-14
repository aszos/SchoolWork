/*
--------------------
Hashing
--------------------
- Primary concern is key equality - not ordering
- Trying to get efficient insert() / delete() / find()
- Let h be a "hash" function which takes keys -> indices into a hash table (i.e array)
- Assuming h is O(1), insert() / delete() / find() CAN be O(1)
- "Perfect" hash function: k1 =/= b2 -> h(k1) =/= h(k2)
	-> When this is NOT true, we have a collision

- Let there be n items to hash and m slots in the hash table.
- The Load Factor(lambda) is (n/m)

--------------------
Designing the Hash Functions
--------------------
1. Must be able to transform key type into an integer
	- e.g Strings
		a. Use the length of the string
		b. convert characters to numbers -> sum them up
		c. Polynomial Scheme: Use place value
			-> h(K = k1, k2, k3 ... k(q-1))

2. Resulting integers should be uniformly spread out within the range [0, m-1]
- Assuming k is an integer, it's standard to take k mod m


----------------------------------------
1. "Chaining" / "Seperate Chaining"
- Maintain linked list at each hash table slot; add to it when there's a collision on insert()
- set max length on any chain
- When Load factor is too high, m bigger and rehash h'(k) = k mod m'

2.  "Open Addressing" / "Probing"
	-> Look for an empty slot in a prescribed fashion
- Linear Probing
	-> Move over p slots at a time
	- h(k) = [h(k) + f(i)] mod n, when f(i) = p * i

*/
