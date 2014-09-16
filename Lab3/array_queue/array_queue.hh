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
  int front;
  int back;

};

//----------------------------------------------------------------------------

template<typename T>
MyQueue<T>::MyQueue() 
{ 
	front = 0;
	back = 0;
	A = new T[MAX_QUEUE_SIZE];   // make an empty queue
}    

//----------------------------------------------------------------------------

template<typename T>
bool MyQueue<T>::full() 
{
	return (front == 0 && back == MAX_QUEUE_SIZE - 1) || (front - 1 == back); 
}

//----------------------------------------------------------------------------

template<typename T>
bool MyQueue<T>::empty() 
{
	return front == back;	
}

//----------------------------------------------------------------------------

template<typename T>
void MyQueue<T>::enqueue(T theData)
{
	if(!full())
	{
		A[back] = theData;
		if(back == MAX_QUEUE_SIZE - 1)
			back = 0;
		else
			back++;
	}
}

//----------------------------------------------------------------------------

template<typename T>
T MyQueue<T>::dequeue()
{
	T storedValue = A[front];
	front++;
	return storedValue;
}

//----------------------------------------------------------------------------

template<typename T>
int MyQueue<T>::size() 
{
	return back - front + 1; 
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
