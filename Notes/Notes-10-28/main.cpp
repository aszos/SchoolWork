/*
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

----------------------------------------
Equivalence Classes - Data Structures
----------------------------------------
- Given a set S with n members, assume all elemwents in their own equivalence classes.

Two Operations
1. Given an element a of S, what equivalence class is it in?
	-> returns <- find(a)

2. Merge Equivalance Classes together: union(sa, sh)

Idea:
	Represent each equivalence classes as a tree where nodes are members of S
		-> Root element = name of equivalence class
	Implementation of this "forest" is one array of integers which are indicies
		-> -1 = root, otherwise index of parent node

-----------------------------------------
Equivalence Classes - Functions on Data Structures 
----------------------------------------
// O(n)

int find(int x)
{
	if(S[x] == -1) // if x is the root node
		return x;
	else //otherwise
		return find(S[x]); //return the index of the parent
}

// by convention, a will become the root
//b's root element point to a's root
// O(1)

void union(int s_a, int s_b)
{
	S[s_b] = s_a;	
}

----------------------------------------
Smart Union
----------------------------------------
-> Put the "smaller" tree into the "larger" tree
	-> Union by SIZE
	-> Union by HEIGHT

write -size instead of -1 at an array slot to indicate
	-> root
	-> size of tree

We need to change find and union
//O(log n)
int find(int x)
{
	if(S[x] < 0) // if x is the root node
		return x;
	else //otherwise
		return find(S[x]); //return the index of the parent
}

-> Union by SIZE
//O(1)
void union(int s_a, int s_b)
{
	if(s_a < s_b) //s_a has more elements
	{
		S[s_a] += S[s_b]; //the s_a tree is increasing in size by the size of s_b	
		S[s_b] = s_a; //we tack on s_b onto s_a	
	}
	else //s_b has more elements
	{
		S[s_b] += S[s_a];	
		S[s_a] = s_b;	
	}
}

-> Union by HEIGHT
void union(int s_a, int s_b)
{
	if(s_a < s_b) //s_a has more elements
		S[s_b] = s_a;	
	else
		S[s_a] = s_b;	
}


----------------------------------------
*/
