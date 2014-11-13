/*
----------------------------------------
Collision Resolution for Hashing
----------------------------------------
h(k) = k mod m (13)

- Chaining
- Open Addressing / Probing : hi(k) = [h(k) + f(i)] mod m
	a. Linear Probing : f(i) = f * i 
	(Rehashing when LF > 0.5)

	----------------------------------------
	      41       18 44 59    22 
	0  1  2  3  4  5  6  7  8  9  10  11  12 

	k - 18, 41, 22, 44, 59, 32, 9, 73
	h(k) - 5, 2 9, 5, 7, 6, 9, 8
	----------------------------------------

	b. Quadratic Probing : f(i) = p * (+i^2), p * (-i^2)
	(Rehashing when LF > 0.75)

	c. Double hasing : f(i) = i * h2(k)
	(Rehashing when LF > 0.80, more expensive)

----------------------------------------
Deletions
----------------------------------------

      41       18 44       22 
0  1  2  3  4  5  6  7  8  9  10  11  12 

k - 18, 41, 22, 44, 59, 32, 9, 73
h(k) - 5, 2 9, 5, 7, 6, 9, 8

Suppose we call delete(18) after the first four insertions

      41          44       22 
0  1  2  3  4  5  6  7  8  9  10  11  12 

With open addressing, find() and delete() may have to probe as well!
What if we call delete(44)?
	-> It would return false, since we would visit index 5 and fail to find an index.

"Lazy" Deletions: "Mark" deleted elements without actually removing the value 

               v
      41       18 44       22 
0  1  2  3  4  5  6  7  8  9  10  11  12 

We mark where we plan on deleting values.
               v
      41          44       22 
0  1  2  3  4  5  6  7  8  9  10  11  12 

Keep the mark there after deletion.
On find() and delete(), treat marked slots as full.
On insert(), treat marked slots as empty.

----------------------------------------
Hashing Applications
----------------------------------------
1. Storage
2. Verifying file integrity / error correction
	-> h(fileF) -> bitstring Q (with q bits) 
	-> h(fileF2) -> bitstring Q2 (with q2 bits) 
	We expect F1 =/= F2 -> Q1 =/= Q2	

Common Hasing Functions
	- MD5 Hashing Function -> Converts files to 128-bitstring = 32 hex values
	- SHA1 Hashing Function -> Converts files to 160-bitstring = 40 hex values

3. Security / Password Storage
	- Don't store password -> store hash of my password
	- Login: h(what I type) ?=? stored hash of my password
	- Does h-1() exist?
	- How hard is it to find collisions?

4. Signatures

----------------------------------------
Cyptographic Hash Functions
----------------------------------------
1. Collision Resistance
	- Finding k1 and k2 such that h(k1) = h(k2), very hard
2. Pre-image resistance
	- Given b, hard to find a such that h(a) = b

*/
