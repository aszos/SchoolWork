/*
----------------------------------------
Compression
----------------------------------------
Given a set of n "symbols"
	-> Alphabet: n = 26
	-> Binary: n = 2
	-> Morse Code (dots, dashes, pauses): n = 3
	-> ASCII: n = 256

ASCII Code is 8 bits per symbol (because 2^8 = 256) 
log(2,n) = # of bits per symbol, log(2, 256) = 8 bits 

----------------------------------------
Suppose we had a file with the contents:
----------------------------------------
Letters: {"A","E","I","S","T"," ", "\n"} : n = 7
Occurences: [10, 15, 12, 3, 4, 13, 1]

In our custom code, we can represent:
{"A","E","I","S","T"," ", "\n"}
[000, 001, 010, 011, 100, 101, 110]

In ASCII: 58 characters x 8 bits/character = 464 bits
In Custom Code: 58 characters x CEILING[log(2,7)] => 3 bits/character = 174 bits => 173 bits (with "/n")
--> 63% compression

-----------------------------------------
Trie Example
-----------------------------------------
We can represent the code as a tree (trie)
	-> Left child is 0
	-> RIght child is 1

            r
          /   \
         0     1
        / \   / \
       0   1 0   1
      / \       / 
     0   1     0   

(Some of this trie is missing, use your imagination)
{"A","E","I","S","T"," ", "\n"}
[000, 001, 010, 011, 100, 101, 110, 11]

----------------------------------------
How do we encode/decode?
----------------------------------------
Decode this: 010 011 11 000 101 100 010 001 11

[010], [011], [11], [000], [101], [100], [010], [001], [11]
  I      S     \n     A     " "     T      I      E     \n

----------------------------------------
Code "Cost"
----------------------------------------
The sum from i to n of (the depth of the tree) * (frequency of a symbol)

----------------------------------------
What's the big idea?
----------------------------------------
Use the shortest codes for the most frequent symbols and longer codes for rarer symbols.

Gives rise to: The Optimal Trie (the lowest cost overall)

              COST TABLE
----------------------------------------
letter      depth     frequency     cost
----------------------------------------
  I           2          12          24
 " "          2          13          26
  E           2          15          30
  A           3          10          30
  T           4           4          16
 \n           5           1           5
  S           5           3          15
                        
                        Total Cost: 146
----------------------------------------

*See UD Course Capture for Trie*

----------------------------------------
The Huffman Algorithm
----------------------------------------	
1. Start with forest of 1-node tries, one for each symbol
2. While there is > 1 trie:
	-> Select two *least frequent* tries, Ti and Tj (fi <= fj)
	-> Combine Ti and Tj into a new trie, with Ti as the left subtree and Tj as the right subtree: T*
		-> New frequency f* = fi + fj 
3. We now have a new trie, T*

----------------------------------------	
Example
----------------------------------------	
she_sells_seashells

{"s", "h", "e", "l", "a", " "} : n = 6
[6, 2, 4, 4, 1, 2] 

Use the Huffman Algorithm!
----------------------------------------	

*/
