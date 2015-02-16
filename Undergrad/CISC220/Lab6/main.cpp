//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
#include "avl.hh"
#include "string.h" 
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void printTitle(string s, char * filename)
{
	cout << "-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-" << endl;
	cout << s << endl;
	cout << filename << endl;
	cout << "-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-#-" << endl;
}

void driver(char *filename, bool verbose)
{

  // Unbalanced Tree - BST // 
  printTitle("Unbalanced Tree - BST", filename);
  AVLTree<string> *my_bst = new AVLTree<string>();
  
  my_bst->insertFileWords(filename, false);
  
  if(verbose)
  my_bst->print_level_and_pretty();

  // Statistics //

  cout << "nodes = " << my_bst->size() << endl;
  cout << "keys = " << my_bst->getNumKeys() << endl;
  cout << "height = " << my_bst->computeHeight() << endl;
  cout << "worst balance factor = " << my_bst->worstBalanceFactor << endl;
  cout << "min key = " << my_bst->findMin() << endl;
  cout << "max key = " << my_bst->findMax() << endl << endl;;

  delete my_bst;
 
  // Balanced Tree - AVL // 

  printTitle("Balanced Tree - AVL", filename);
  AVLTree<string> *my_avl = new AVLTree<string>();

  my_avl->insertFileWords(filename, true);
  
  if(verbose)
  my_avl->print_level_and_pretty();

  // Statistics //

  cout << "nodes = " << my_avl->size() << endl;
  cout << "keys = " << my_avl->getNumKeys() << endl;
  cout << "height = " << my_avl->computeHeight() << endl;
  cout << "worst balance factor = " << my_avl->worstBalanceFactor << endl;
  cout << "min key = " << my_avl->findMin() << endl;
  cout << "max key = " << my_avl->findMax() << endl << endl;

  delete my_avl;
}
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
int main(int argc, char** argv)
{

  if (argc < 2) 
  {
    cout << "Please specify filename on command line\n";
    exit(1);
  }

  int verbose = -1;
 
  for(int i = 1; i < argc; i++)
  {
    if(strcmp(argv[i],"prettyplease") == 0) 
    {
      verbose = i;
    }
  }
  
  for(int i = 1; i < argc; i++)
  {
    if(i != verbose)
    driver(argv[i], (verbose != -1));
  }
  
  return 1;
}
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
