//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#ifndef ARRAY_QUEUE_HH
#define ARRAY_QUEUE_HH

#include <cstdlib>
#include <iostream>

using namespace std;

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

const int MAX_QUEUE_SIZE = 100;

template<typename T>
class MyQueue
{
public:
  
  MyQueue();          // make an empty queue

  void enqueue(T);    // put item on the queue
  T dequeue();        // take item off

  bool empty();       // is the queue empty?
  bool full();        // is the queue full (aka max capacity reached)?
  int size();         // how many items are in the queue?
  void error(string); // print and exit

private:

  T *A;
  int maxnum;
  int curnum;

};

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

template<typename T>
MyQueue<T>::MyQueue() 
{ 
  maxnum = MAX_QUEUE_SIZE; 
  curnum = 0; 
  A = new T[maxnum];   // make an empty queue
}    

//----------------------------------------------------------------------------

template<typename T>
bool MyQueue<T>::empty() 
{ 
  return curnum == 0; 
}
  
//----------------------------------------------------------------------------

// item 0 is new BACK of queue

template<typename T>
void MyQueue<T>::enqueue(T theData)
{
  if (!full()) {

    // move over all items in queue to make room for new one

    for (int i = curnum; i > 0; i--)
      A[i] = A[i-1];

    // actually insert the new data

    A[0] = theData;

    // update how many items are in queue

    curnum++;
  }
  else
    error("Tried to enqueue on full queue");
}

//----------------------------------------------------------------------------

// return item currently at FRONT of queue (aka which is index curnum - 1)

template<typename T>
T MyQueue<T>::dequeue()
{
  // take item from front, update number of items in queue

  if (!empty())
    return A[--curnum];
  else
    error("Tried to dequeue from empty queue");
}

//----------------------------------------------------------------------------

template<typename T>
bool MyQueue<T>::full() 
{ 
  return curnum == maxnum; 
}
  
//----------------------------------------------------------------------------

template<typename T>
int MyQueue<T>::size() 
{ 
  return curnum; 
}  

//----------------------------------------------------------------------------

template<typename T>
void MyQueue<T>::error(string s) 
{ 
  cout << "Error: " << s << endl; 
  exit(1); 
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#endif
