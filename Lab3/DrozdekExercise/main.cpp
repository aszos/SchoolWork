#include <iostream>
#include <cstdlib>
#include <fstream>
#include <stack>
using namespace std;

/*
Write a program that implements the algorithm delimiterMatching().
As in the algorithm, you should read from a file and handle parenthese, square brackets, curly braces, and C++ comments.
Skip all other characters that may be mixed in.
*/

int main(int argc, char **argv)
{
	ifstream inStream;
	char c;
		
	if(argc < 2)
	{
		cout << "Please specify filename on command line." << endl;
		exit(1);	
	}

	inStream.open(argv[1]);
	
	if(inStream.fail())
	{
		cout<< "Failed to open file." << endl;
		exit(1);	
	}

	while(!inStream.eof())
	{
		inStream >> c;
		cout << c << endl;			
		
		/*
		Structure algorith for delimiter checker
		*/
	}
	inStream.close();
	return 0;
}

