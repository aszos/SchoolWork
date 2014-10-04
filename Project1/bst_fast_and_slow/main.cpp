//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
#include "bst_fast_and_slow.hh"
#include <cstdlib>
#include <fstream> 
#include <sys/time.h>
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

template<class T>
void tree_speed_test(T *t, char* file)
{
	timeval t1,t2; 
	double elapsedTime;
	string a = "love";
	string b = "death";
	string c = "tyranny";

//----------------------------------------------------------------------------
	gettimeofday(&t1, NULL);
	insert_file_into_tree(t, file);	
	gettimeofday(&t2, NULL);
	
	elapsedTime = (t2.tv_sec - t1.tv_sec) * 1000.0;
	elapsedTime += (t2.tv_usec - t1.tv_usec) / 1000.0;

	cout << "Inserting Speed: " << elapsedTime << " ms" << endl;

//----------------------------------------------------------------------------
	gettimeofday(&t1, NULL);
	t->contains(a);
	gettimeofday(&t2, NULL);
	
	elapsedTime = (t2.tv_sec - t1.tv_sec) * 1000.0;
	elapsedTime += (t2.tv_usec - t1.tv_usec) / 1000.0;

	cout << "Searching 'love' speed: " << elapsedTime << " ms" << endl;

//----------------------------------------------------------------------------

	gettimeofday(&t1, NULL);
	t->contains(b);
	gettimeofday(&t2, NULL);
	
	elapsedTime = (t2.tv_sec - t1.tv_sec) * 1000.0;
	elapsedTime += (t2.tv_usec - t1.tv_usec) / 1000.0;

	cout << "Searching 'death' speed: " << elapsedTime << " ms" << endl;

//----------------------------------------------------------------------------
	gettimeofday(&t1, NULL);
	t->contains(c);
	gettimeofday(&t2, NULL);
	
	elapsedTime = (t2.tv_sec - t1.tv_sec) * 1000.0;
	elapsedTime += (t2.tv_usec - t1.tv_usec) / 1000.0;

	cout << "Searching 'tyranny' speed: " << elapsedTime << " ms" << endl;


//----------------------------------------------------------------------------
	gettimeofday(&t1, NULL);
	t->findMax();
	gettimeofday(&t2, NULL);
	
	elapsedTime = (t2.tv_sec - t1.tv_sec) * 1000.0;
	elapsedTime += (t2.tv_usec - t1.tv_usec) / 1000.0;

	cout << "Searching maximum speed: " << elapsedTime << " ms"<< endl;

//----------------------------------------------------------------------------
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
		tree_speed_test(fast, argv[i]);
		//fast->print();
		delete fast;
	}
 
	//BSTree_Slow Operations
	for(int i = 1; i < argc; i++)
	{
		slow = new BSTree_Slow<string>();
		cout << "File: " << argv[i] << endl;	
		tree_speed_test(slow, argv[i]);
		//slow->print();
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
