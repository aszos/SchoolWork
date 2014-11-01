/*
----------------------------------------
Smart Find
----------------------------------------
Let's remember the equivalence classes for nodes that we visited.

//O(log* n) -> O(log(log n))
int path_compress_find(int x)
{
	if(S[x] < 0)
		return x;
	else 	
		return (S[x] = path_compress_find(S[x]));
		//expressions evaluate to the left side, returns S[x])
}

----------------------------------------
Maze Generation
----------------------------------------
Definition of a Maze:
	- n x n grid
	- s = start
	- f = finish
	- some squares have walls, some don't
	- solvable: there exists a path (sequence of adjacent empty squares) from s -> f

The set S = all cells in the grid
Equivalence Class = are two cells connected?

Initially, every cell is "disconnected". 
	-> Every cell is in its own equivalence class

Knocking down a wall between i,j -> union(find(i), find(j))
Solvable? -> find(s) == find(f)


----------------------------------------
while(find(s) != find(f) && !all_cells_connected)
{
	pick a random wall between adjacent cells i,j
	
	if(!connected(i, j))
	{
		union(find(i), find(j))
	}
}
----------------------------------------
*/
