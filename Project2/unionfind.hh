//----------------------------------------------------------------------------

#ifndef UNIONFIND_HH
#define UNIONFIND_HH

#include <cstdlib>
#include <iostream>
#include <vector>
#include <queue>
#include <cmath>
#include <fstream>
#include <string>

using namespace std;

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// a union-find class for disjoint sets

// S stores the "forest" of equivalence-class (EC) trees

// S[i] < 0 means that i is the root of an EC tree (for dumb unions this is just -1)
// S[i] >= 0 means that the value in S[i] is the index of i's parent

// when you are doing union-by-size you will store -size at S[i] if i is the root of an EC tree,
// where "size" is the number of nodes in i's tree

class UnionFind
{
public:

  UnionFind(int);
  void print_vertical();
  void print_horizontal();

  int find(int);
  void union_sets(int, int);           // "union" by itself is a C++ keyword so we can't use it
  void union_sets_by_size(int, int);   

  int num_elements;                    // how many members are in overall set
  int *S;                              // connectivity array 

};

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// given set element x, return name of equivalence class it belongs to

int UnionFind::find(int x)
{
  if(S[x] < 0)
    return x;
  else
    return find(S[x]);
}

//----------------------------------------------------------------------------

// given two equivalence classes s1 and s2, combine them into one such 
// that s1 remains the root and s2 becomes part of s1's EC tree.

// this is the "dumb" version -- do it first as an exercise, but use
// smart union in practice

void UnionFind::union_sets(int s1, int s2)
{
  S[s2] = s1;
}

//----------------------------------------------------------------------------

// given two equivalence classes s1 and s2, combine them into one.

// this is the "smart" version -- whichever of s1 and s2 has more nodes should
// remain the root and the smaller should become part of its EC tree

void UnionFind::union_sets_by_size(int s1, int s2)
{
  if(s1 < s2)
  {
    S[s1] += S[s2];
    S[s2] = s1;
  }
  else
  {
    S[s2] += S[s1];
    S[s1] = s2;
  }
}

//----------------------------------------------------------------------------

UnionFind::UnionFind(int n)
{
  num_elements = n;

  S = new int [num_elements];

  // every element initially in its own equivalence class

  for (int i = 0; i < num_elements; i++)
    S[i] = -1;
}

//----------------------------------------------------------------------------

// easier to read, but not good for lots of elements

void UnionFind::print_vertical()
{
  for (int i = 0; i < num_elements; i++)
    cout << i << ": " << S[i] << endl;
}

//----------------------------------------------------------------------------

// lots of elements on a few lines, but harder to read

void UnionFind::print_horizontal()
{
  int i;

  for (i = 0; i < num_elements - 1; i++)
    cout << i << ":" << S[i] << ", ";
  cout << i << ":" << S[i] << endl << endl;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#endif
