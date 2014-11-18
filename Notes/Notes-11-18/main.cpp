/*
--------------------
Graphs - Definitions
--------------------
Graph G:
e - a   b  c  d
| x | \ |  | /
c - f   g  h

A graph G = a set of verticies V and edges E.
A tree is a graph with no "cycles" - loops of edges

G may be directed graph or undirected.
	-> Directed Graphs mean that the direction of the edge matters.

G's edges may be weighted or unweighted.
Verticies i, j which have an edge (i, j) are adjacent or neighbors.
Connectivity: i,j are connected if there's a sequence of edges between them
Degree(?) = number of connections at ?
	- In-degree
	- Out-degree
Degree(V) = 10

--------------
Implementation
--------------
Adjacency List
	1. Linked list of verticies, each with a list of pointers to neighbors
	2. STL vector of verticies, each with a vector of neighbors' indices (allows random access)

Adjacency Matrix - n x n where n = |V|
	 0 1 2 3 4 5 6 7 8 
 	 ___________________
	0|T| | | |T|T|T| |T|
	1| |T| | | | |T| | |
	2| | |T| | | | |T| |
	3| | | |T| | | | | |
	4| | | | |T| | | | |
	5| | | | | |T| | | |
	6| | | | | | |T| | |
	7| | | | | | | |T| |
	8| | | | | | | | |T|
	 -------------------
	 0 1 2 3 4 5 6 7 8 
	
	A[i][j] = adjacent(i,j)
	"look-up table" for adjacency
	=> O(1)
	O(n) to enumerate neighbors / calculating degree

------------------------
Graph Traversal / Search
------------------------
- Like tree traversals, but specify start vertex and mark visited verticies

- Depth-first Search on Graph G = (V, E)

	depthFirstSearch()
	{
		for (all verticies v)
			num(v) = 0; // non-zero means visited, positive number means order of visitation
		edges = null; // list of edges used
		i = 1;

		while(there is a vertex v && num(v) == 0)
			DFS(v)

		return edges;
	}


	DFS(v)
	{
		num(v) = i++;
		
		for(all verticies u adjacent to v)
			if(num(u) == 0)
				attach_edge(uv) to edges
				DFS(u)
	}
*/
