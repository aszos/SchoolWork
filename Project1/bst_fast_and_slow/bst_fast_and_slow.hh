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

  int getMaxDepth() { return maxDepth; }

private:

  int maxDepth;

  BSTNode<T> *root;                             // pointer to the node at the root of the tree

};

//----------------------------------------------------------------------------

template<typename T>
void BSTree_Fast<T>::insert(T & key)
{
  // ???
}

//----------------------------------------------------------------------------

template<typename T>
bool BSTree_Fast<T>::contains(T & key)
{
  // ???
}

//----------------------------------------------------------------------------

// return largest key in BST
// print error and exit if BST is empty

template<typename T>
const T & BSTree_Fast<T>::findMax()
{
  // ???
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

private:

  vector < BSTNode<T> * > tree;                 // vector of pointers to nodes in the "tree"

};

//----------------------------------------------------------------------------

template<typename T>
void BSTree_Slow<T>::insert(T & key)
{
  // ???
}

//----------------------------------------------------------------------------

template<typename T>
bool BSTree_Slow<T>::contains(T & key)
{
  // ???
}

//----------------------------------------------------------------------------

// return largest key in BST
// print error and exit if BST is empty

template<typename T>
const T & BSTree_Slow<T>::findMax()
{
  // ???
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#endif
