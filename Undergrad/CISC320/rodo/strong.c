#include <stdio.h>
#include "stack.c"
#include "bfs-dfs.c"

#ifndef queue
#else
#include "queue.h"
#endif

extern bool processed[];	/* which vertices have been processed */
extern bool discovered[];	/* which vertices have been found */
extern int parent[];		/* discovery relation */

extern int entry_time[];	/* time of vertex entry */
extern int exit_time[];		/* time of vertex exit */

int low[MAXV+1];		/* oldest vertex surely in component of v */
int scc[MAXV+1];		/* strong component number for each vertex */

stack active;			/* active vertices of unassigned component */
int components_found;		/* number of strong components identified */

process_vertex_early(int v)
{
	/*printf("entered vertex %d at time %d\n",v, entry_time[v]);*/

	push(&active,v);
}

process_vertex_late(int v)
{
        /*printf("exit vertex %d at time %d\n",v, exit_time[v]);*/

	if (low[v] == v) { 		/* edge (parent[v],v) cuts off scc */
/*printf("strong commonent started backtracking from %d\n",v);*/
		pop_component(v);
	}

	if (entry_time[low[v]] < entry_time[low[parent[v]]])
		low[parent[v]] = low[v];
}

pop_component(int v)
{
        int t;                  /* vertex placeholder */

	components_found = components_found + 1;
	printf("%d is in component %d \n",v,components_found);

	scc[ v ] = components_found;
	while ((t = pop(&active)) != v) {
		scc[ t ] = components_found;
		printf("%d is in component %d with %d \n",t,components_found,v);
	}
}

process_edge(int x, int y)
{
	int class;		/* edge class */

	class = edge_classification(x,y);
/*printf("(%d,%d) class %d\n", x,y,class);*/

	if (class == BACK) {
		if (entry_time[y] < entry_time[ low[x] ] )
			low[x] = y;
/*printf("process BACK (%d,%d) low[%d]=%d  low[%d]=%d\n",x,y,x,low[x],y,low[y]);*/
	}

	if (class == CROSS) {
		if (scc[y] == -1)	/* component not yet assigned */
			if (entry_time[y] < entry_time[ low[x] ] )
                        	low[x] = y;
/*printf("process CROSS (%d,%d) low[%d]=%d  low[%d]=%d\n",x,y,x,low[x],y,low[y]);*/
	}
}

strong_components(graph *g)
{
	int i;				/* counter */

	for (i=1; i<=(g->nvertices); i++) {
		low[i] = i;
		scc[i] = -1;
	}

	components_found = 0;

	init_stack(&active);
	initialize_search(&g);

	for (i=1; i<=(g->nvertices); i++)
		if (discovered[i] == FALSE) {
			dfs(g,i);
			/*pop_component(i);*/
		}
}

main(int argc, char *argv[])
{
	graph g;
	int i;

	read_graph(&g,TRUE, argc, argv);
	print_graph(&g);

	strong_components(&g);
}

