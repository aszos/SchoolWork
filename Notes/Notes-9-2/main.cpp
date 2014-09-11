#include <cstdlib>
#include <iostream>
#include <cstring>

using namespace std;

const int MAX_STRLEN = 100;


class MyString
{
	//constructors are implicitly made
	public:
	//no argument constructor
	MyString();
	//single argument constructor
	MyString(const char *);
	//destructor
	~MyString();	
	void print(){cout << A << endl;}	
	void change_first_character(char c) { A[0] = c; }
	
	/*
	java like constructor, inline with class
	MyString()
	{
		cout << "zero argument constructor!" << endl;
	}
	*/

	private:
	//classes are private by default
	char *A; 
	//lets statically allocate 100 bytes for character array
};

//MyString function belongs to the MyString class, the constructor
MyString::MyString()
{
	cout << "zero argument constructor!" << endl;
	A = new char[MAX_STRLEN];
}

MyString::MyString(const char *s)
{	
	int n = strlen(s);
	A = new char[n + 1];
	cout << "one arguement constructor with string " << n << "-character string: " << s << endl;	

	for(int i = 0; i < MAX_STRLEN &&  i < n; i++)
	{
		A[i] = s[i];
		A[n] = '\0';
	}
	cout << A << endl;
	
}

//the destructor function
//this is where we would want to dynamically de-allocate memory
MyString::~MyString()
{
	cout << "BOOM BITCH!" << endl;
	//delete [] A;
}

int main()
{
	cout << "mystring test program" << endl;
	 
	MyString s1;
	//instantiating a zero argument object
	
	MyString * s2;
	//does NOT call the constructor, only makes memory space for a class object
	
	MyString *s3 = new MyString("hello");
	//DOES call the constructor AND make memory

	cout << sizeof(s1) << endl;
	//the program has set aside 1 byte for the mystring class, if nothing is in there
	//changes to 100 after character array

	//this invokes the "copy constructor", copies member variables one by one.
	//when copying, pointers will point to the same thing! statically allocated memory will copy into
	//different memory locations

	MyString t1("poopybutts");
	MyString t2(t1);
	
	t1.print();
	t2.print();

	//use delete whenever you use new!
	delete s3;	
	//if you try to delete something that's already deleted, you'll get a "core dumped" error
	return 1;
} 
// look up -> notation, how does it relate to dynamic allocation
// loop up making make files!

/*
A "shallow" copy copies everything regardless of the contents.
A "deep" copy copies everything and evaluates the pointers.
*/
