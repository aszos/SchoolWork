/*
-----------------------------
Minimum Spanning Trees (MSTs)
-----------------------------
Spanning Tree for which sum of weights is minimal

Function for creating a tree that connects all points of a graph together at a minimal cost 
	void kruskal()
	{
		tree = NULL;
		edges = E, sorted from minimum weight to maximum weight

		for(i = 1; i < |E| && |tree| < |v| -1; i++
		{
			if ei is a member of edges would not form a cycle
				add ei to tree
		}
	}

--------------------
Breadth-First Search
--------------------
breadthFirstSearch()
{
	for all verticies v	
		num(v) = 0
	
	edges = null  //spanning tree
	order = 1 
	
	while there is a vertex v && num(v) == 0
		num(v) = order++
		enqueue(v)

		while queue ! empty
			v = dequeue()

			for all verticies u adjacent to v for which num(u) == 0
				num(u) == order++
				enqueue(u)
				add edge(vu) to edges

	output edges
}

----------------------------------
Dijkstra's Shortest Path Algorithm
----------------------------------
dijkstra(vertex first)
{
	for all verticies v
		currDist(v) = infinite

	currentDist(first) = 0

	toBeChecked = V //all verticies to start
	
	while(toBeChecked is not empty)
		v = vertex in toBeChecked with MINIMUM currDist(v) // min priority queue with currDist(v) as key
		remove v from toBeChecked

		for all u adjacent to v which are in toBeChecked
			if currentDist(u) > currDist(v) + weight(edge(vu))		
				currDist(u) = currDist(v)+weight(edge(vu))
				predecessor(u) = v
}
*/
