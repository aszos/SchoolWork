#include <stdio.h>
#include "bool.h"
#include "queue.h"
#include "graph.h"

initialize_graph(graph *g, bool directed)
{
	int i;				/* counter */

	g -> nvertices = 0;
	g -> nedges = 0;
	g -> directed = directed;

	for (i=1; i<=MAXV; i++) g->degree[i] = 0;
	for (i=1; i<=MAXV; i++) g->edges[i] = NULL;
}

read_graph(graph *g, bool directed, int argc, char *argv[])
{
	int i;				/* counter */
	int m;				/* number of edges */
	int x, y;			/* vertices in edge (x,y) */

	initialize_graph(g, directed);
	
	scanf("%d %d",&(g->nvertices),&m);

	for (i=1; i<=m; i++) {
		scanf("%d %d",&x,&y);
		insert_edge(g,x,y,directed);
	}
}

insert_edge(graph *g, int x, int y, bool directed)
{
	edgenode *p;			/* temporary pointer */

	p = malloc(sizeof(edgenode));	/* allocate storage for edgenode */

	p->weight = NULL;
	p->y = y;
	p->next = g->edges[x];

	g->edges[x] = p;		/* insert at head of list */

	g->degree[x] ++;

	if (directed == FALSE)
		insert_edge(g,y,x,TRUE);
	else
		g->nedges ++;
}


delete_edge(graph *g, int x, int y, bool directed)
{
	int i;				/* counter */
	edgenode *p, *p_back;		/* temporary pointers */

	p = g->edges[x];
	p_back = NULL;

	while (p != NULL) 
		if (p->y == y) {
			g->degree[x] --;
			if (p_back != NULL) 
				p_back->next = p->next;
			else
				g->edges[x] = p->next;

			free(p);

			if (directed == FALSE)
				delete_edge(g,y,x,TRUE);
			else
				g->nedges --;

			return;
		}
		else
			p = p->next;

	printf("Warning: deletion(%d,%d) not found in g.\n",x,y);
}

print_graph(graph *g)
{
	int i;				/* counter */
	edgenode *p;			/* temporary pointer */

	for (i=1; i<=g->nvertices; i++) {
		printf("%d: ",i);
		p = g->edges[i];
		while (p != NULL) {
			printf(" %d",p->y);
			p = p->next;
		}
		printf("\n");
	}
}

