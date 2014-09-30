//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
#include "bst_fast_and_slow.hh"
#include <cstdlib>
#include <fstream> 
using namespace std;
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

template<class T>
void insert_file_into_tree(T *n, char* file)
{
	ifstream inStream;
	string s;

	inStream.open(file);

	if(inStream.fail())
	{
		cout << "Failed to open " << file << "." << endl;	
		exit(1);
	}	
		
	while(true)
	{
		inStream >> s;
		if(inStream.eof())
			break;
			
		n->insert(s);	
	}
}

//----------------------------------------------------------------------------

void driver(int argc, char** argv)
{
	//make pointers for the trees
	BSTree_Fast<string> *fast; 
	BSTree_Slow<string> *slow;
	
	//BSTree_Fast Operations
	for(int i = 1; i < argc; i++)
	{
		fast = new BSTree_Fast<string>();
		cout << "File: " << argv[i] << endl;	
		insert_file_into_tree(fast, argv[i]);	
		fast->print();
		delete fast;
	}
 
	//BSTree_Slow Operations
	for(int i = 1; i < argc; i++)
	{
		slow = new BSTree_Slow<string>();
		cout << "File: " << argv[i] << endl;	
		insert_file_into_tree(slow, argv[i]);	
		delete slow;
	}

}
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

int main(int argc, char** argv)
{
  
  driver(argc, argv);
  return 1;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
