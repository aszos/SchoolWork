#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;
const int DEC_TO_INT = 97;
const int HASH_MOD = 23;

int hash_char(char a)
{
	return (((int) a) - DEC_TO_INT);	
}

int hash_char_array(char *a)
{
	int hash = 0;

	for(int i = 0; i < strlen(a); i++)
	{
		hash += hash_char(a[i]);
	}

	return (hash % HASH_MOD);
}

void print_hashed_char_array(char *a)
{
	int i;

	for(i = 0; i < strlen(a) - 1; i++)
	{
		cout << hash_char((a[i])) << " + ";
	}

	cout << hash_char(a[i]) << " (mod " << HASH_MOD << ") = " << hash_char_array(a) << endl; 
}

void print_hashed_parameters(int length, char * argv[])
{
	for(int i = 1; i < length; i++)
	{
		cout << "Char: " << argv[i] << " | "; 
		print_hashed_char_array(argv[i]);
	}
}

int main(int argc, char * argv[])
{
	print_hashed_parameters(argc, argv);
}
