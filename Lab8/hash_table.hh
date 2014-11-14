#include <iostream>
#include <cstring>

using namespace std;
const int DEC_TO_INT = 97;
const int HASH_MOD = 11;

class HashTable
{
	public:
		HashTable(){ }
		HashTable(int length, char** words) 
		{
			print_hashed_parameters(length, words);
			hash_table = generate_hash_table(length, *words);
		}

		int hash_char(char a);
		int hash_char_array(char *a);
		int * generate_hash_table(int argc, char* argv);
		void print_hashed_char_array(char *a);
		void print_hashed_parameters(int len, char * argv[]);
		void print_hash_table();	
	private:
		int length;
		int load_factor;	
		int *hash_table;
};

//------------------------//
//Printing Functions
//------------------------//
void HashTable::print_hashed_char_array(char *a)
{
	int i;

	for(i = 0; i < strlen(a) - 1; i++)
	{
		cout << hash_char((a[i])) << " + ";
	}

	cout << hash_char(a[i]) << " (mod 11) = " << hash_char_array(a) << endl; 
}

void HashTable::print_hashed_parameters(int len, char * argv[])
{
	for(int i = 1; i < len; i++)
	{
		cout << "Char: " << argv[i] << " | "; 
		print_hashed_char_array(argv[i]);
	}
}

void HashTable::print_hash_table()
{
	for(int i = 0 ; i < length; i++)
	{
		cout << " | " << hash_table[i];	
	}

	cout << " | " << endl;
}

//------------------------//
//Hashing Functions
//------------------------//
int HashTable::hash_char(char a)
{
	return (((int) a) - DEC_TO_INT);	
}

int HashTable::hash_char_array(char *a)
{
	int hash = 0;

	for(int i = 0; i < strlen(a); i++)
	{
		hash += hash_char(a[i]);
	}

	return (hash % HASH_MOD);
}

int * HashTable::generate_hash_table(int argc, char* argv)
{
	int * ht = new int[argc]();

	return ht;
}


