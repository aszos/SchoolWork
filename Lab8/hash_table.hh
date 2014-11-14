#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;
const int DEC_TO_INT = 97;
const int HASH_MOD = 11;

class HashTable
{
	public:
		HashTable(){ }
		HashTable(int length, char** argv) 
		{
			copy(argv, argv + length, words);
			print_hashed_parameters();
			hash_table = generate_hash_table();
		}

		int hash_char(char a);
		int hash_char_array(char *a);
		int * generate_hash_table();
		void print_hashed_char_array(char *a);
		void print_hashed_parameters();
		void print_parameters();
		void print_hash_table();	

	private:
		int length;
		int load_factor;	
		char** words;
		int* hash_table;
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

void HashTable::print_hash_table()
{
	for(int i = 0 ; i < length; i++)
	{
		cout << " | " << hash_table[i];	
	}

	cout << " | " << endl;
}

void HashTable::print_parameters()
{
	for(int i = 1; i < length; i++)
	{
		cout << words[i] << " | "; 
	}
}

void HashTable::print_hashed_parameters()
{
	for(int i = 1; i < length; i++)
	{
		cout << "Char: " << words[i] << " | "; 
		print_hashed_char_array(words[i]);
	}
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

int * HashTable::generate_hash_table()
{
	int * ht = new int[length]();

	return ht;
}


