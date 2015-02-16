/*
Trees
 - A hierarchical data structure (as opposed to linear)

Terminology
- Nodes: 
- Links / Edges / Branches / Arcs
- Up/Down is vertical, Left/Right is Lateral
- Leaf: childless node
- Internal Node: has children
- Path: A sequence of nodes
- Path Length: # of nodes - 1 
- Node Depth: Path Length to root
- Node Height: Length of a path to most distant leaf
- Tree Height: Height of the root

Recursivce Definition
- A tree is ...
	- Empty
	- A root node R and a set of subtrees connect to R by exactly 1 edge 

Family Relationships
- Parent/Child Nodes
- Generations

Applications 
- "Family" Tree
- Organizational Charts
- Kinematic Trees for Robotics/Computer Graphics/Anatomy
- Directory and File Structure

Psuedocode
class TreeNode
{
	TreeNode<T> *firstChild;
	TreeNode<T> *next;
	TreeNode<T> *parent;
	<T> data;
}


How to print the directory structure?
1. Visiting the node of the tree systematically is called a traversal.

void printAllNames(TreeNode<T> * t)
{
	if(!t)
	{
		return;
	}
	else
	{
		cout << t->data << endl;
		printAllNames(t->firstChild);
		printAllNames(t->nextSibling);
	}
}

void printAllNames(TreeNode<T> * t)
{
	if(!t)
	{
		return;
	}
	else
	{
		//for each child c of t
			printAllNames(c);
	}
*/
