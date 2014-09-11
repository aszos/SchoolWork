
#include <cstdlib>
#include <iostream>
#include <cstring>

using namespace std;

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

class MyString
{
public:
  MyString();
  MyString(char *);
  MyString(const MyString &);
  ~MyString();
  void print() { cout << A << endl; }
  friend ostream & operator <<(ostream &, const MyString &);
  friend MyString operator +(const MyString &, const MyString &);

  void print_size() { cout << sizeof(*this) << endl; }
  void change_first_letter(char c) { if (A) { A[0] = c; } }
  MyString & operator=(const MyString &);

private:
  char *A;
};


//----------------------------------------------------------------------------

// concatenation -- based on copy constructor below

MyString operator +(const MyString & s1, const MyString & s2)
{
  MyString s3;
  int i;
  
  int n1 = strlen(s1.A);
  int n2 = strlen(s2.A);

  s3.A = new char[n1 + n2 + 1];

  for (i = 0; i < n1; i++)
    s3.A[i] = s1.A[i];
  for (i = 0; i < n2; i++)
    s3.A[n1 + i] = s2.A[i];
  
  s3.A[n1 + n2] = '\0';

  return s3;
}

//----------------------------------------------------------------------------

// output operator for printing -- note that this is NOT a member of MyString--just a friend

ostream & operator <<(ostream &outputStream, const MyString & s)
{
  cout << s.A;
  
  return outputStream;
}

//----------------------------------------------------------------------------

// override default constructor because we can

MyString::MyString() 
{ 
  A = NULL;
  cout << "zero arg constructor!" << endl; 
}

//----------------------------------------------------------------------------

// copy string passed in into dynamically-allocated char array

MyString::MyString(char *s) 
{ 	
  int n = strlen(s);

  A = new char[n+1];

  cout << "construct from " << n << "-char string \"" << s << "\"" << endl; 

  for (int i = 0; i < n; i++)
    A[i] = s[i];
  A[n] = '\0';
}

//----------------------------------------------------------------------------

// assignment operator

MyString & MyString::operator=(const MyString & rhs)
{
  cout << "assignment!" << endl;

  // check for (and avoid) self-assignment

  if (this != &rhs) {

    // free old array

    if (A != NULL)
      delete [] A;
    
    // allocate new array
    
    if (rhs.A == NULL) {
      cout << "rhs is empty" << endl;
      return *this;
    }
    
    int n = strlen(rhs.A);
    
    A = new char[n+1];
    
    // copy array over

    for (int i = 0; i < n; i++)
      A[i] = rhs.A[i];
    A[n] = '\0';
  }

  return *this;
}

//----------------------------------------------------------------------------

// copy constructor

MyString::MyString(const MyString & src)
{
  cout << "copy constructor!" << endl;

  // allocate and copy over new array

  A = NULL;

  if (src.A == NULL) {
    cout << "src is empty" << endl;
    return;
  }

  int n = strlen(src.A);

  A = new char[n+1];

  for (int i = 0; i < n; i++)
    A[i] = src.A[i];
  A[n] = '\0';

}

//----------------------------------------------------------------------------

// override default destructor to clean up dynamically-allocated char array

MyString::~MyString() 
{ 
  if (A)
    delete [] A;

  cout << "destructor!" << endl; 
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

int main()
{
  cout << "mystring program" << endl;

  MyString s1("hello");
  MyString s2(s1);

  cout << "s1 = " << s1 << endl;
  cout << "s2 = " << s2 << endl;

  s1.change_first_letter('j');

  cout << "s1 = " << s1 << endl;
  cout << "s2 = " << s2 << endl;

  MyString s3;

  s3 = s1 + s2;
  cout << "s3 = " << s3 << endl;

  
  return 1;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------



