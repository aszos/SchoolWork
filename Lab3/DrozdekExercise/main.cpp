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
	stack <char> delimiters;
	bool isCommented = false;

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
		
		if(isCommented)
		{
	
		}
		else
		{
			if(c == '/')
			{
				isCommented = (inStream.peek() == '*');	
			}
			else if(c == '(' ||  c == '[' ||  c == '{')
			{
				delimiters.push(c);	
			}
			else if(c == ')' || c == ']' || c == '}')
			{
				if(c == ')' && delimiters.top() == '(')
				{
					delimiters.pop();	
				}
				else if(c == ']' && delimiters.top() == '[')
				{
					delimiters.pop();	
				}
				else if(c == '}' && delimiters.top() == '{')
				{
					delimiters.pop();	
				}
				else
				{
					cout << "Unmatched delimiter detected." << endl;
					exit(1);
				}
			}
		}
	}

	inStream.close();
	
	if(delimiters.size() != 0)
		cout << "There are unmatched delimiters." << endl;
	else
		cout << "No unmatched delimiters found, captain." << endl;

	return 0;
}
