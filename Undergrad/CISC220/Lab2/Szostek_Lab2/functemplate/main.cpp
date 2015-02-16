//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// http://www.cs.bu.edu/teaching/cs113/spring-2000/template/

#include <cstdlib>
#include <iostream>

using namespace std;

//----------------------------------------------------------------------------

/*
void myswap(int & a, int & b)
{
  int temp;

  temp = a;
  a = b;
  b = temp;
}


//----------------------------------------------------------------------------

void myswap(float & a, float & b)
{
  float temp;

  temp = a;
  a = b;
  b = temp;
}
*/


//----------------------------------------------------------------------------

// there's a predefined function called swap(), so don't use that

template <typename T>
void myswap(T & a, T & b)
{
  T temp;

  temp = a;
  a = b;
  b = temp;
}

//----------------------------------------------------------------------------

template <typename T>
T findsmallest(T A[], int size)
{
  int i;
  T smallest_so_far;

  smallest_so_far = A[0];

  for (i = 1; i < size; i++)
    if (A[i] < smallest_so_far)
      smallest_so_far = A[i];

  return smallest_so_far;
}

template <typename T>
T calculatesum(T A[], int size)
{
  int i;
  T sum_so_far;

  sum_so_far = A[0];

  for (i = 1; i < size; i++)
      sum_so_far = sum_so_far + A[i];

  return sum_so_far;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

class IntCell
{
public:

  IntCell(int initialValue = 0) { storedValue = initialValue; }

  // "accessor" function

  int read() const  
  { return storedValue; }

  // "mutator" function

  int write(int x) 
  { storedValue = x; }

  // overloaded insertion/extraction operators (friend means they're not member functions, but
  // they have the same privileges vis-a-vis accessing private variables)

  friend ostream & operator <<(ostream &, IntCell);
  friend istream & operator >>(istream &, IntCell &);

  friend bool operator <(IntCell &, IntCell &);
  friend IntCell operator +(IntCell &, IntCell &);

private:

  int storedValue;

};

//----------------------------------------------------------------------------

IntCell operator +(IntCell & a, IntCell & b)
{
  return (a.storedValue + b.storedValue);
}

bool operator <(IntCell & a, IntCell & b)
{
  return (a.storedValue < b.storedValue);
}

//----------------------------------------------------------------------------

ostream & operator <<(ostream &outputStream, IntCell a)
{
  cout << a.storedValue;
  
  return outputStream;
}

//----------------------------------------------------------------------------

istream & operator >>(istream &inputStream, IntCell & a)
{
  cin >> a.storedValue;
  
  return inputStream;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void example1()
{
  // integers

  int x = 1;
  int y = 5;
  
  cout << "before: " << x << " " << y << endl;
  myswap(x, y);
  cout << "after: " << x << " " << y << endl << "------------------------------\n";

  // chars

  char u = '!';
  char v = '?';

  cout << "before: " << u << " " << v << endl;
  myswap(u, v);
  cout << "after: " << u << " " << v << endl << "------------------------------\n";

  // strings (classes!)

  string s = "hoo";
  string t = "ray";

  cout << "before: " << s << " " << t << endl;
  myswap(s, t);
  cout << "after: " << s << " " << t << endl;
}

//----------------------------------------------------------------------------

void example2()
{
  int A[5] = { 7, 9, 2, 10, 23 };


  string S[6] = { "umbrella",
		  "stingray",
		  "bicycle",
		  "aardvark",
		  "AARDVARK",
		  "rainbow" };

  cout << calculatesum(A, 5) << endl << "------------------------------\n";
  cout << calculatesum(S, 6) << endl;
}

//----------------------------------------------------------------------------

void example3()
{
  /*
  IntCell a(5);
  IntCell b(10);

  cout << "before: " << a << " " << b << endl;
  myswap(a, b);
  cout << "after: " << a << " " << b << endl << "------------------------------\n";
  */

  int i;
  IntCell A[5];

  A[0] = IntCell(7);
  A[1] = IntCell(9);
  A[2] = IntCell(2);
  A[3] = IntCell(10);
  A[4] = IntCell(23);

  for (i = 0; i < 5; i++)
    cout << i << " " << A[i] << endl;

  IntCell small = calculatesum(A, 5);
  cout << "sum = " << small << endl;
}

//----------------------------------------------------------------------------

int main(int argc, char** argv)
{
  //example1();
  example2();
  example3();
  return 1;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
