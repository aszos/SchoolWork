//----------------------------------------------------------------------------
#ifndef AVL_HH
#define AVL_HH

#include <cstdlib>
#include <iostream>
#include <vector>
#include <queue>
#include <cmath>
#include <fstream>
#include <string>

using namespace std;

//----------------------------------------------------------------------------

// the AVLNode class is what we're making the binary search tree out of

template<typename T>
class AVLNode
{
public: 

  AVLNode(T theKey) 
  { key = theKey; parent = NULL; left = NULL; right = NULL; number = 1; balanceFactor = currentHeight = 0; }

  AVLNode(T theKey, AVLNode<T> *theLeft, AVLNode<T> *theRight) 
  { key = theKey; parent = NULL; left = theLeft; right = theRight; number = 1; balanceFactor = currentHeight = 0; }

  AVLNode(T theKey, AVLNode<T> *theParent, AVLNode<T> *theLeft, AVLNode<T> *theRight) 
  { key = theKey; parent = theParent; left = theLeft; right = theRight; number = 1; balanceFactor = currentHeight = 0; }

  const T & getKey() { return key; }
  void setKey(T & theKey) { key = theKey; }

  AVLNode<T> * & getParent() { return parent; }
  void setParent(AVLNode<T> * theParent) { parent = theParent; }

  AVLNode<T> * & getLeft() { return left; }
  void setLeft(AVLNode<T> * theLeft) { left = theLeft; }

  AVLNode<T> * & getRight() { return right; }
  void setRight(AVLNode<T> * theRight) { right = theRight; }

  int getNumber() { return number; }
  void setNumber(int num) { number = num; }

  void print() { cout << key << " (" << number << ")" << endl; }

  int print_column;    // used by pretty print
  
  int balanceFactor;
  int currentHeight;   // used to speed up computation of ancestor node heights
 
private:

  T key;

  AVLNode<T> *parent; // link to parent

  AVLNode<T> *left;   // link to left child
  AVLNode<T> *right;  // link to right child

  int number;         // how many occurrences of key there are
  
};

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
// AVL tree
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// a binary search tree, implemented as a linked structure, with AVL balancing

// this is only partial--there's no remove functionality, for example

template<typename T>
class AVLTree
{
public:
  
  AVLTree() { root = NULL; node_just_inserted = NULL; numNodes = 0; numKeys = 0; worstBalanceFactor = 0; }   // make an empty AVL tree

  void insert(T &, bool);                             // put a key into the AVL tree 
  AVLNode<T> *insert(T &, AVLNode<T> *, AVLNode<T> *); 

  bool contains(T &);                           // does this AVL tree contain a certain key?
  bool contains(T &, AVLNode<T> *);             

  const T & findMax();                          // what is the biggest key in the AVL tree?
  const T & findMax(AVLNode<T> *);  

  const T & findMin();                          // what is the smallest key in the AVL tree?
  const T & findMin(AVLNode<T> *);  

  void rotate_right(AVLNode<T> * &, bool = false);
  void rotate_left(AVLNode<T> * &, bool = false);

  void print_inorder();
  void print_inorder(AVLNode<T> *);
  void print_levelorder();
  void print_pretty();
  void print_level_and_pretty();

  void setAllBalanceFactors();
  void setAllBalanceFactors(AVLNode<T> *);

  void setAllCurrentHeights();
  int setAllCurrentHeights(AVLNode<T> *);

  int computeHeight();                          // needed for balance factor computation 
  int computeHeight(AVLNode<T> *);

  int computeBalance(AVLNode<T> *);
  int computeLevel(AVLNode<T> *);

  int size() { return numNodes; }
  int getNumKeys() { return numKeys; }

  void insertFileWords(char *, bool);

  void updateBalanceFactors();

  AVLNode<T> *root;                             // pointer to the node at the root of the tree

  AVLNode<T> *node_just_inserted;               // pointer to the node last inserted

  int worstBalanceFactor;

private:

  int numKeys;
  int numNodes;

};

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// update balance factors and do rotations necessary to rebalance tree
template<typename T>
void AVLTree<T>::updateBalanceFactors()
{
  // NULL here means that key inserted was already in tree, so no need to rebalance

  if (!node_just_inserted)
    return;

//----------------------------------------------------------------------------
// (1) Update balance factors for insertion that just happened. 
//----------------------------------------------------------------------------
  AVLNode<T> * q = node_just_inserted;
  AVLNode<T> * p = node_just_inserted->getParent();

  if(p == NULL)
  { return; }

  if(p->getLeft() && p->getLeft()->getKey() == q->getKey())
  {
    p->balanceFactor--;
  }
  else if(p->getRight() && p->getRight()->getKey() == q->getKey())
  {
    p->balanceFactor++;
  }  
  
  while(p != root && !(p->balanceFactor > 1 || p->balanceFactor < -1))
  {
    q = p;
    p = p->getParent();
    
    if(q->balanceFactor == 0)
    {
      return;
    }
      
    if(p->getLeft() && p->getLeft()->getKey() == q->getKey())
    {
      p->balanceFactor--;
    }
    else if(p->getRight() && p->getRight()->getKey() == q->getKey())
    {
      p->balanceFactor++;
    }  
  }
   
//----------------------------------------------------------------------------
// (2) Rebalance via rotations
//----------------------------------------------------------------------------
  if(p->balanceFactor > 1)
  {
    if(q->balanceFactor > 0)
    {
      rotate_left(p, false);
    }
    else if(q->balanceFactor < 0)
    {
      rotate_right(q, false);
      rotate_left(p, false);
    }
  }
  else if(p->balanceFactor < -1)
  {
    if(q->balanceFactor > 0)
    {
      rotate_left(q, false);
      rotate_right(p, false);
    }
    else if(q->balanceFactor < 0)
    {
      rotate_right(p, false);
    }
  }  

//---------------------------------------------------------------------------
// (3) Update balance factors for rotation(s) that just happened
//---------------------------------------------------------------------------
  setAllBalanceFactors();
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// read through file word by word and insert them in order

template<typename T>
void AVLTree<T>::insertFileWords(char *filename, bool updateTree)
{
  ifstream inStream;
  ofstream outStream;
  string s;

  inStream.open(filename);

  if (inStream.fail()) {
    cout << "Failed to open file\n";
    exit(1);
  }

  // read input WORDS one by one

  while (!inStream.eof()) {

    inStream >> s;

    if (!inStream.eof()) {
      insert(s, updateTree);
    }
  }
}

//----------------------------------------------------------------------------

// UPDATED VERSION: CHANGED SINCE INITIAL ASSIGNMENT POSTING!

// RIGHT tree rotation at node pointed to by t
// reference to t because it is changed here -- it points to promoted node when we are done

// need to update balance factors

template<typename T>
void AVLTree<T>::rotate_right(AVLNode<T> * & t, bool do_print)
{
  // if empty tree, done

  if (!t) {
    if (do_print)
      cout << "rotate_right(): null rotation because empty tree" << endl;
    return;
  }

  // if no left child, done

  AVLNode<T> *left = t->getLeft();
  if (!left) {
    if (do_print)
      cout << "rotate_right() at key = " << t->getKey() << ": null rotation because no left child" << endl;
    return;
  }

  if (do_print)
    cout << "rotate_right() at key = " << t->getKey() << endl;

  // PROMOTION: t's left child becomes t's parent, and takes t's previous parent as its own

  AVLNode<T> *parent = t->getParent();
  t->setParent(left);
  left->setParent(parent);

  // THIS WAS ADDED AFTER THE INITIAL ASSIGNMENT POSTING!

  if (!parent)
    root = left;
  else {
    if (t == parent->getRight())
      parent->setRight(left);
    else
      parent->setLeft(left);
  }

  // DEMOTION: t becomes the newly-promoted node's right child.  promoted node's existing right child, 
  // if any, becomes t's left child

  AVLNode<T> *left_right = left->getRight();
  left->setRight(t);
  t->setLeft(left_right);
  if (left_right)
    left_right->setParent(t);

  t = t->getParent();
}

//----------------------------------------------------------------------------

// UPDATED VERSION: CHANGED SINCE INITIAL ASSIGNMENT POSTING!

// LEFT tree rotation at node pointed to by t
// reference to t because it is changed here -- it points to promoted node when we are done

// need to update balance factors

template<typename T>
void AVLTree<T>::rotate_left(AVLNode<T> * & t, bool do_print)
{
  // if empty tree, done

  if (!t) {
    if (do_print)
      cout << "rotate_left(): null rotation because empty tree" << endl;
    return;
  }

  // if no right child, done

  AVLNode<T> *right = t->getRight();
  if (!right) {
    if (do_print)
      cout << "rotate_left() at key = " << t->getKey() << ": null rotation because no right child" << endl;
    return;
  }

  if (do_print)
    cout << "rotate_left() at key = " << t->getKey() << endl;

  // PROMOTION: t's right child becomes t's parent, and takes t's previous parent as its own

  AVLNode<T> *parent = t->getParent();
  t->setParent(right);
  right->setParent(parent);

  // THIS WAS ADDED AFTER THE INITIAL ASSIGNMENT POSTING!

  if (!parent)
    root = right;
  else {
    if (t == parent->getLeft())
      parent->setLeft(right);
    else
      parent->setRight(right);
  }

  // DEMOTION: t becomes the newly-promoted node's left child.  promoted node's existing left child, 
  // if any, becomes t's right child

  AVLNode<T> *right_left = right->getLeft();
  right->setLeft(t);
  t->setRight(right_left);
  if (right_left)
    right_left->setParent(t);

  t = t->getParent();
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// computes and writes currentHeight in every node of the tree

template<typename T>
void AVLTree<T>::setAllCurrentHeights()
{
  setAllCurrentHeights(root);
}

//----------------------------------------------------------------------------

template<typename T>
int AVLTree<T>::setAllCurrentHeights(AVLNode<T> * t)
{
  if (!t)
    return 0;
  else {
    t->currentHeight = 1 + max(setAllCurrentHeights(t->getLeft()), setAllCurrentHeights(t->getRight()));
    return t->currentHeight;
  }
}

//----------------------------------------------------------------------------

// computes and writes balanceFactor in every node of the tree
// also notes the worst one seen

template<typename T>
void AVLTree<T>::setAllBalanceFactors()
{
  setAllCurrentHeights();

  worstBalanceFactor = 0;
  setAllBalanceFactors(root);
}

//----------------------------------------------------------------------------

// assuming that currentHeight is already written to every node

template<typename T>
void AVLTree<T>::setAllBalanceFactors(AVLNode<T> * t)
{
  if (!t)
    return;
  else {

    int height_left = 0;
    int height_right = 0;

    if (t->getLeft() != NULL)
      height_left = t->getLeft()->currentHeight;
    if (t->getRight() != NULL)
      height_right = t->getRight()->currentHeight;

    t->balanceFactor = height_right - height_left;

    if (abs(t->balanceFactor) > abs(worstBalanceFactor))
      worstBalanceFactor = t->balanceFactor;

    setAllBalanceFactors(t->getLeft());
    setAllBalanceFactors(t->getRight());
  }
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// level 1 = root
// level 2 = root's children
// etc.

template<typename T>
int AVLTree<T>::computeLevel(AVLNode<T> * t)
{
  if (!t) 
    return 0;
  else 
    return 1 + computeLevel(t->getParent());
}

//----------------------------------------------------------------------------

// computes height of entire tree.  does NOT write currentHeight 

template<typename T>
int AVLTree<T>::computeHeight()
{
  return computeHeight(root);
}

//----------------------------------------------------------------------------

// computes height of subtree with t as its root.  does NOT write currentHeight 

// empty tree has height 0
// leaf node (no children) has height 1
// node with at least one child but no grandchildren has height 2
// etc.

template<typename T>
int AVLTree<T>::computeHeight(AVLNode<T> * t)
{
  if (!t)
    return 0;
  else
    return 1 + max(computeHeight(t->getLeft()), computeHeight(t->getRight()));

}

//----------------------------------------------------------------------------

// height of right subtree - height of left

// this is done "fresh" and not written to balanceFactor

template<typename T>
int AVLTree<T>::computeBalance(AVLNode<T> * t)
{
  if (!t)
    return 0;
  else
    return computeHeight(t->getRight()) - computeHeight(t->getLeft());
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// first the root, then the root's children, then the root's grandchildren, and so on

// balance factors also printed

template<typename T>
void AVLTree<T>::print_levelorder()
{
  // don't do anything if empty tree

  if (!root)
    return;

  queue< AVLNode<T> * > Q;

  int last_level; 

  for (Q.push(root), last_level = 0; !Q.empty(); Q.pop()) {

    AVLNode<T> *current = Q.front();
    int level = computeLevel(current);

    if (level != last_level)
      cout << "\n" << level << ": ";

    last_level = level;

    cout << current->getKey() << " [b1 " << computeBalance(current) << "][b2 " << current->balanceFactor << "][h " << current->currentHeight << "] ";

    AVLNode<T> *left, *right;
    left = current->getLeft();
    right = current->getRight();
    if (left)
      Q.push(left);
    if (right)
      Q.push(right);

  }

  cout << endl;
}

//----------------------------------------------------------------------------

// try to print the tree out more-or-less like it would be drawn

// NOTE:
// assumes T = string
// only prints first character of each string

template<typename T>
void AVLTree<T>::print_pretty()
{
  // don't do anything if empty tree

  if (!root)
    return;

  queue< AVLNode<T> * > Q;

  int last_level, last_column; 

  int height = computeHeight();

  if (height > 7) {
    cout << "tree height = " << height << " is too tall to print properly" << endl;
    return;
  }

  int width = pow(2, height) - 1;

  // root column should be right in the middle of its width

  root->print_column = (width - 1) / 2;

  // basically level-order printing with additional formatting

  for (Q.push(root), last_level = 0; !Q.empty(); Q.pop()) {

    AVLNode<T> *current = Q.front();
    int level = computeLevel(current);

    if (level != last_level) {
      last_column = 0;
      cout << "\n" << level << ": ";
    }

    last_level = level;

    for (int i = last_column; i < current->print_column; i++)
      cout << " ";
    cout << current->getKey()[0];  // ONLY WORKS if T = string
    last_column = current->print_column + 1;

    AVLNode<T> *left, *right;
    int offset = pow(2, height - level - 1);
    left = current->getLeft();
    right = current->getRight();
    if (left) {
      left->print_column = current->print_column - offset;
      Q.push(left);
    }
    if (right) {
      right->print_column = current->print_column + offset;
      Q.push(right);
    }
  }

  cout << endl;
}

//----------------------------------------------------------------------------

// level-order print followed by pretty print

template<typename T>
void AVLTree<T>::print_level_and_pretty()
{
  print_levelorder();
  cout << "--------------------------------------------" << endl;
  print_pretty();
  cout << endl << "--------------------------------------------" << endl;
}

//----------------------------------------------------------------------------

// in-order traversal of entire tree

template<typename T>
void AVLTree<T>::print_inorder()
{
  print_inorder(root);
}

//----------------------------------------------------------------------------

// first left, then t (this node), then right

template<typename T>
void AVLTree<T>::print_inorder(AVLNode<T> * t)
{
  if (!t)
    return;

  print_inorder(t->getLeft());
  cout << t->getKey() << " (" << t->getNumber() << ")" << " [" << computeHeight(t) << ", " << computeBalance(t) << "]" << endl;
  print_inorder(t->getRight());
}

//----------------------------------------------------------------------------

// insert into the whole tree

template<typename T>
void AVLTree<T>::insert(T & key, bool updateTree)
{
  // normal BST insert

  root = insert(key, root, NULL);

  // this is where the AVL magic happens

  if(updateTree)
  updateBalanceFactors();
}

//----------------------------------------------------------------------------

// t points to the root of the subtree into which key should be inserted
// parent is the parent node of t

template<typename T>
AVLNode<T> *AVLTree<T>::insert(T & key, AVLNode<T> * t, AVLNode<T> * parent)
{
  if (!t) {
    numKeys++;
    numNodes++;
    node_just_inserted = new AVLNode<T>(key, parent, NULL, NULL);
    return node_just_inserted;  // t should now point to the new node
  }
  else if (key < t->getKey())
    t->setLeft(insert(key, t->getLeft(), t));
  else if (key > t->getKey())
    t->setRight(insert(key, t->getRight(), t));

  // else we can infer that key == t->getKey()

  else {
    numKeys++;
    t->setNumber(t->getNumber() + 1);
    node_just_inserted = NULL;
    return t;
  }
  
}

//----------------------------------------------------------------------------

// is key in the entire tree?

template<typename T>
bool AVLTree<T>::contains(T & key)
{
  return contains(key, root);
}

//----------------------------------------------------------------------------

// is key in the subtree with t as its root?

template<typename T>
bool AVLTree<T>::contains(T & key, AVLNode<T> * t)
{
  if (!t)
    return false;
  else if (key < t->getKey())
    return contains(key, t->getLeft());
  else if (key > t->getKey())
    return contains(key, t->getRight());
  else
    return true;
}

//----------------------------------------------------------------------------

// return BIGGEST key in AVL
// print error and exit if AVL is empty

template<typename T>
const T & AVLTree<T>::findMax()
{
  return findMax(root);
}

//----------------------------------------------------------------------------

template<typename T>
const T & AVLTree<T>::findMax(AVLNode<T> * t)
{
  if (!t) {
    cout << "findMax() error: tree is empty" << endl;
    exit(1);
  }

  // follow RIGHT child links as far as possible

  AVLNode<T> *current;
 
  for (current = t; current->getRight(); current = current->getRight());

  return current->getKey();
}

//----------------------------------------------------------------------------

// return SMALLEST key in AVL
// print error and exit if AVL is empty

template<typename T>
const T & AVLTree<T>::findMin()
{
  return findMin(root);
}

//----------------------------------------------------------------------------

template<typename T>
const T & AVLTree<T>::findMin(AVLNode<T> * t)
{
  if (!t) {
    cout << "findMin() error: tree is empty" << endl;
    exit(1);
  }

  // follow LEFT child links as far as possible

  AVLNode<T> *current;
 
  for (current = t; current->getLeft(); current = current->getLeft());

  return current->getKey();
}

//----------------------------------------------------------------------------
#endif
