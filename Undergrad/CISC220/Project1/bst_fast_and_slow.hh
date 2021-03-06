//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// two ways to implement a binary search tree...

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#ifndef BST_HH
#define BST_HH

#include <cstdlib>
#include <iostream>
#include <vector>

using namespace std;

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
// Print Formatting Functions 
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void print_seperator()
{
  cout << endl << "--------------------" << endl;
}

void print_large_seperator()
{
  cout << endl << endl << "--------------------------------------------------------------------------------" << endl << endl; 
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
// BSTNode 
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------


// the BSTNode class is what we're making the binary search tree out of

template<typename T>
class BSTNode
{
public: 

  BSTNode(T theKey) { key = theKey; left = NULL; right = NULL; number = 1; }
  BSTNode(T theKey, BSTNode<T> *theLeft, BSTNode<T> *theRight) { key = theKey; left = theLeft; right = theRight; number = 1; }

  const T & getKey() { return key; }
  void setKey(T theKey) { key = theKey; }

  BSTNode<T> *getLeft() { return left; }
  void setLeft(BSTNode<T> * theLeft) { left = theLeft; }

  BSTNode<T> *getRight() { return right; }
  void setRight(BSTNode<T> * theRight) { right = theRight; }

  void incrementNumber() { number++; }
  int getNumber() { return number; }
  void print() { cout << key << " (" << number << ")" << endl; }

private:

  T key;

  BSTNode<T> *left;   // link to left child
  BSTNode<T> *right;  // link to right child
  int number;         // how many occurrences of key there are

};

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
// BSTREE_FAST
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// a binary search tree, implemented as a linked structure

// this is only partial--there's no remove functionality, for example

template<typename T>
class BSTree_Fast
{
public:
  
  BSTree_Fast() { maxDepth = 0; root = NULL; }  // make an empty BST

  void insert(T &);                             // put a key into the BST 
  bool contains(T &);                           // does this BST contain a certain key?
  const T & findMax();                          // what is the smallest key in the BST?
  void print();

  void print_inorder(BSTNode<T> *); 
  int count_unique_inorder(BSTNode<T> *);
  int count_inorder(BSTNode<T> *);


  int getMaxDepth() { return maxDepth; }
  void setMaxDepth(int i) { maxDepth = i; }

private:

  int maxDepth;
  BSTNode<T> *root;                             // pointer to the node at the root of the tree

};

//----------------------------------------------------------------------------

template<typename T>
void BSTree_Fast<T>::insert(T & key)
{
  int currentMaxDepth = 1;
  if(!root)
  {
    root = new BSTNode<T>(key);  
  }  
  else 
  {
    BSTNode<T> *current = root;
    
    while(current)
    {
      if(key > current->getKey())
      {
        currentMaxDepth++;

        if(!current->getRight())
        {
          
          BSTNode<T> *n = new BSTNode<T>(key);
          current->setRight(n);  
          break;  
        }
        current = current->getRight();  
      }
      else if(key < current->getKey())
      {
        currentMaxDepth++;

        if(!current->getLeft())
        {
          BSTNode<T> *n = new BSTNode<T>(key);
          current->setLeft(n);  
          break;  
        }  
        current = current->getLeft();  
      }  
      else
      {
        current->incrementNumber();  
        break;
      }      
    }
  }  
  setMaxDepth(currentMaxDepth);
}

//----------------------------------------------------------------------------

template<typename T>
bool BSTree_Fast<T>::contains(T & key)
{
  if(!root)
  {
    cout << "Key not found, empty binary search tree. Exiting";
    exit(1);
  }
  else 
  {
    BSTNode<T> *current = root;  
    while(current != NULL)
    {
      if(key == current->getKey())
      {
        return true;  
      }
      else if(key > current->getKey())
      {
        current = current->getRight();
      }
      else 
      {
        current = current->getLeft();
      }
    }
    return false;  
  }
}

//----------------------------------------------------------------------------

// return largest key in BST
// print error and exit if BST is empty

template<typename T>
const T & BSTree_Fast<T>::findMax()
{
  if(!root)
  {
    cout << "Cannot find the maximum in an empty tree. Exiting." << endl;
    exit(1);
  }
  else
  {
    BSTNode<T> *current = root;
    
    while(current->getRight())
    {
      current = current->getRight(); 
    }

    return current->getKey();
  }
}

//----------------------------------------------------------------------------

template<typename T>
void BSTree_Fast<T>::print_inorder(BSTNode<T> *t)
{
  if(!t)
    return;
  
  print_inorder(t->getLeft());  
  t->print();
  print_inorder(t->getRight());  
}

//----------------------------------------------------------------------------

template<typename T>
int BSTree_Fast<T>::count_inorder(BSTNode<T> *t)
{
  if(!t)
    return 0;
  
  return count_inorder(t->getLeft()) + t->getNumber()  + count_inorder(t->getRight());
}

//----------------------------------------------------------------------------

template<typename T>
int BSTree_Fast<T>::count_unique_inorder(BSTNode<T> *t)
{
  if(!t)
    return 0;

  if(t->getNumber() == 1)  
    return count_unique_inorder(t->getLeft()) + 1 + count_unique_inorder(t->getRight());
  else
    return count_unique_inorder(t->getLeft()) + count_unique_inorder(t->getRight());
}

//----------------------------------------------------------------------------

template<typename T>
void BSTree_Fast<T>::print()
{
  //print in-order
  print_seperator();  
  print_inorder(root);
  print_seperator();  
  
  //count unique words (1 as number)
  cout << "Unique Words: " << count_unique_inorder(root) << endl;

  //count total words
  cout << "Total Words: " << count_inorder(root) << endl;

  //maximum depth
  cout << "Maximum Depth: " << getMaxDepth();

  print_large_seperator();  
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
// BSTREE_SLOW
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// a binary search tree, implemented as an unordered STL vector

// once again, this is only partial

template<typename T>
class BSTree_Slow
{
public:
  
  BSTree_Slow() { tree.clear(); }               // make an empty BST

  void insert(T &);                             // put a key into the BST 
  bool contains(T &);                           // does this BST contain a certain key?
  const T & findMax();                          // what is the smallest key in the BST?
  void print();

  void print_inorder(vector < BSTNode<T> * > tree);
  int count_unique(vector < BSTNode<T> * > tree);
  int count_inorder(vector < BSTNode<T> * > tree);

private:

  vector < BSTNode<T> * > tree;                 // vector of pointers to nodes in the "tree"

};

//----------------------------------------------------------------------------

template<typename T>
void BSTree_Slow<T>::insert(T & key)
{
  for(int i = 0; i < tree.size(); i++)
  {
    if(key == tree[i]->getKey())
    {
      tree[i]->incrementNumber();
      return;
    }
  }
  
  BSTNode<T> *n = new BSTNode<T>(key);
  tree.push_back(n);  
}

//----------------------------------------------------------------------------

template<typename T>
bool BSTree_Slow<T>::contains(T & key)
{
  // a simple loop through the vector 

  if(tree.size() == 0)
  {
    cout << "Key not found, empty binary search tree. Exiting";
    exit(1);
  }
  else
  {
    for(int i = 0; i < tree.size(); i++)
    {  
      if(tree[i]->getKey() == key)
        return true;  
    }
    return false;  
  }
}

//----------------------------------------------------------------------------

// return largest key in BST
// print error and exit if BST is empty

template<typename T>
const T & BSTree_Slow<T>::findMax()
{
  if(tree.size() == 0)
  {
    cout << "Key not found, empty binary search tree. Exiting";
    exit(1);
  }
  else
  {
    BSTNode<T> *currentMax = tree[0]; 

    for(int i = 0; i < tree.size(); i++)
    {  
      if(currentMax->getKey() < tree[i]->getKey())
        currentMax = tree[i];
    }
    
    return currentMax->getKey();
  }
}

//----------------------------------------------------------------------------

template<typename T>
void BSTree_Slow<T>::print_inorder(vector < BSTNode<T> * > tree)
{
  if(tree.size() == 0)
  {
    cout << "Key not found, empty binary search tree. Exiting";
    exit(1);
  }
  else
  {
    for(int i = 0; i < tree.size(); i++)
      tree[i]->print();
  }
}

//----------------------------------------------------------------------------

template<typename T>
int BSTree_Slow<T>::count_inorder(vector < BSTNode<T> * > tree)
{
  if(tree.size() == 0)
  {
    cout << "Key not found, empty binary search tree. Exiting";
    exit(1);
  }
  else
  {
    int sum = 0;

    for(int i = 0; i < tree.size(); i++)
    {
      sum = sum + tree[i]->getNumber(); 
    }
      
    return sum;
  }
}

//---------------------------------------------------------------------------- 

template<typename T>
int BSTree_Slow<T>::count_unique(vector < BSTNode<T> * > tree)
{
  int unique = 0;

  for(int i = 0; i < tree.size(); i++)
  {
    unique = (tree[i]->getNumber() == 1)? (unique + 1):(unique);    
  }
    
  return unique;
}

//----------------------------------------------------------------------------

template<typename T>
void BSTree_Slow<T>::print()
{
  //print in-order
  print_seperator();  
  print_inorder(tree);
  print_seperator();  
  
  //count unique words (1 as number)
  cout << "Unique Words: " << count_unique(tree) << endl;

  //count total words
  cout << "Total Words: " << count_inorder(tree) << endl;

  print_large_seperator();
}

#endif
