//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
#include "bst_fast_and_slow.hh"
#include <cstdlib>
#include <fstream> 
using namespace std;
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void insert_file_into_tree(BSTree_Fast<string> *n, char* file)
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

void driver(int argc, char** argv)
{
	//construct the trees
	BSTree_Fast<string> *fast = new BSTree_Fast<string>();
	BSTree_Slow<string> *slow = new BSTree_Slow<string>();


	for(int i = 1; i < argc; i++)
	{
		cout << argv[i] << endl;	
		insert_file_into_tree(fast, argv[i]);	
		fast->print();
	}

	//delete the trees
	delete fast;
	delete slow;
/*	delete bts_fast;
	delete ge_fast;
	delete doi_slow;
	delete bts_slow;
	delete ge_slow;
*/
}
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

int main(int argc, char** argv)
{
  
  //cout << "**Make sure the parameters when you call this program are bts.txt, doi.txt. and ge.txt.**" << endl;
  driver(argc, argv);
  return 1;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
