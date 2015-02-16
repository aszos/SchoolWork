#include <cstdlib>
#include <iostream>
#include <cstring>
#include <string>

using namespace std;

//----------------------------------------------------------------------------

template <typename T>
T my_min(T a, T b)
{
	if(a < b)
		return a;
	else
		return b;
}

int my_min(int a, int b)
{
	if(a < b)
		return a;
	else
		return b;
}

int main()
{
	cout << my_min(10, 4) << endl;

	cout << my_min(10.1, 4.9) << endl;
	//casts these floats into ints
	cout << my_min("dsjahda","asdhasudhushb") << endl;;
	cout << my_min("alsjahda","asdhasudhushb") << endl;;
	return 1;
}
//----------------------------------------------------------------------------
