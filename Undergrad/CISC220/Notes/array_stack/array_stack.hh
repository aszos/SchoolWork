//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#ifndef ARRAY_STACK_HH
#define ARRAY_STACK_HH

#include <cstdlib>
#include <iostream>

using namespace std;

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

const int MAX_STACK_SIZE = 100;

template<typename T>
class MyStack
{
public:
  
  MyStack();                 // make an empty stack

  bool empty();              // is there anything currently on the stack?

  void push(T);              // put item on the stack
  T pop();                   // take item off

  bool full();               // has capacity been reached?
  void error(string);        // print message and exit

private:

  T *A;
  int maxnum;
  int curnum;

};

//----------------------------------------------------------------------------

template<typename T>
MyStack<T>::MyStack()
{
  maxnum = MAX_STACK_SIZE; 
  curnum = 0; 

  A = new T[maxnum]; 
}

//----------------------------------------------------------------------------

template<typename T>
bool MyStack<T>::empty()
{ 
  return curnum == 0; 
}

//----------------------------------------------------------------------------

// item is new top of stack

template<typename T>
void MyStack<T>::push(T theData)
{
  if (!full()) 
    A[curnum++] = theData;
  else
    error("Tried to push on full stack");
}

//----------------------------------------------------------------------------

// return item currently on top of stack

template<typename T>
T MyStack<T>::pop()
{
  if (!empty())
    return A[--curnum];
  else
    error("Tried to pop from empty stack");
}

//----------------------------------------------------------------------------
  

template<typename T>
bool MyStack<T>::full() 
{ 
  return curnum == maxnum; 
}

//----------------------------------------------------------------------------

template<typename T>
void MyStack<T>::error(string s) 
{ 
  cout << "Error: " << s << endl; 
  exit(1); 
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#endif
