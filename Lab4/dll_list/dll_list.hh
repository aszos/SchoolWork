//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#ifndef DLL_LIST_HH
#define DLL_LIST_HH

#include <cstdlib>
#include <iostream>

using namespace std;

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// the Node class is what we're making a linked list out of

template<typename T>
class Node
{
public: 

  Node() { }
  Node(T theData, Node<T> *thePrev, Node<T> *theNext) { data = theData; prev = thePrev; next = theNext; }

  const T & getData() { return data; }
  void setData(T theData) { data = theData; }

  Node<T> *getPrev() { return prev; }
  void setPrev(Node<T> * thePrev) { prev = thePrev; }

  Node<T> *getNext() { return next; }
  void setNext(Node<T> * theNext) { next = theNext; }

  void print() { cout << data << endl; }

private:

  T data;
  Node<T> *prev;  // link back
  Node<T> *next;  // link forward

};

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// this is a list, implemented with a linked list of nodes

template<typename T>
class MyList
{
public:
  
  MyList();                              // make an empty list

  bool empty();                          // does list have no elements?
  int size();                            // how many element are in the list?

  void insert_ordered(T &);              // assuming < is defined for T and the list is already sorted
                                         // (from smallest to largest), put the item in its correct location in the list 

  void push_front(T &);                  // put item at front of list
  void push_back(T &);                   // put item at back of list
 
  void pop_front();                       // remove item from front
  void pop_back();                       // remove item from back

  const T & front();                           // first element (without removing it)
  const T & back();                            // last element (without removing it)

private:

  int curnum;

  Node<T> *first;                        // one end of the list; access rest through next
  Node<T> *last;                         // other end of the list; access rest through prev

};

//----------------------------------------------------------------------------

template<typename T>
MyList<T>::MyList()
{
  last = first = NULL;
  curnum = 0;
}

//----------------------------------------------------------------------------

template<typename T>
bool MyList<T>::empty()
{
  return curnum == 0; 
}

//----------------------------------------------------------------------------

template<typename T>
int MyList<T>::size()
{
  return curnum;
}

//----------------------------------------------------------------------------

// return element at FRONT of list without removing it

template<typename T>
const T & MyList<T>::front()
{
  if (!empty())
    return first->getData();
  else {
    cout << "Cannot access front of empty list!\n";
    exit(1);
  }
}

//----------------------------------------------------------------------------

// return element at BACK of list without removing it

template<typename T>
const T & MyList<T>::back()
{
  if (!empty())
    return last->getData();  
  else {
    cout << "Cannot access back of empty list!\n";
    exit(1);
  }
}

//----------------------------------------------------------------------------

// item is put in the correct place in list assuming it is ordered from smallest to largest by T

template<typename T>
void MyList<T>::insert_ordered(T & theData)
{
  // ???

  curnum++;
}

//----------------------------------------------------------------------------

// item is new FRONT of list  

template<typename T>
void MyList<T>::push_front(T & theData)
{
  // ???

  curnum++;
}

//----------------------------------------------------------------------------

// item is new BACK of list  

template<typename T>
void MyList<T>::push_back(T & theData)
{
  Node<T> *temp = new Node<T>(theData, last, NULL);

  // is there something in the list currently?

  if (last)
    last->setNext(temp);

  // if not, this item is also the new first

  else
    first = temp;

  last = temp;

  curnum++;
}

//----------------------------------------------------------------------------

// remove item currently at FRONT of list

template<typename T>
void MyList<T>::pop_front()
{
  if (empty()) {
    cout << "Cannot pop_front empty list!\n";
    exit(1);
  }

  Node<T> *temp = first->getNext();   // this will be the new first
  delete first;                       // get rid of old first
  first = temp;                       // update first
  if (first)                          // new first should have no prev
    first->setPrev(NULL);

  // done

  curnum--;

}

//----------------------------------------------------------------------------

// remove item currently at BACK of list

template<typename T>
void MyList<T>::pop_back()
{
  if (empty()) {
    cout << "Cannot pop_back empty list!\n";
    exit(1);
  }

  // ???

  // done

  curnum--;

}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#endif
