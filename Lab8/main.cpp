#include <iostream>
#include <cstring>
#include "hash_table.hh"

int main(int argc, char* argv[])
{
	HashTable *h = new HashTable(argc, argv);
	h->print_hash_table();
	return 0;
}

