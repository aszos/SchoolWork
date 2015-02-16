/*
Binary Trees
	- Trees with 0, 1, or 2 children per node
	-> If 0 or 2 children per node, the tree is "proper"


*/

class BTNode
{
	T data;
	BTNode<T> *left;
	BTNode<T> *right;
}

//prefix notation
void preorder(BTNode<T> *t)
{	
	if(!t)
		return;

	visit(t);
	preorder(t->left);
	preorder(t->right);
}

//postfix notation
void postorder(BTNode<T> *t)
{
	if(!t)
		return;
	
	visit(t);
	preorder(t->left);
	preorder(t->right);
}

/*inorder notation

left 
visit 
right

*/

/*
Binary Expression Trees
	- Operands = leaves
	- Operators = interior nodes
	- The tree must be proper!
*/
