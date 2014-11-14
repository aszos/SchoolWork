#include <iostream>
#include <cstring>

using namespace std;
const int DEC_TO_INT = 97;
const int HASH_MOD = 11;

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

void print_hashed_array(char *a)
{
	int i;

	for(i = 0; i < strlen(a) - 1; i++)
	{
		cout << hash_char((a[i])) << " + ";
	}
 	
	cout << hash_char(a[i]) << " (mod 11) = " << hash_char_array(a) << endl; 

}

char * generate_hash_table(int len, char * argv[])
{
	int i; 
	int j;
	char * ht = new char[len]();

	for(i = 1; i < len; i++)
	{
		cout << "Char: " << argv[i] << " | "; 
		print_hashed_array(argv[i]);
	}

	return ht;
}

int main(int argc, char* argv[])
{
 	generate_hash_table(argc, argv);
	return 0;
}

