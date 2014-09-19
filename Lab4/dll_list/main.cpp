//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#include "dll_list.hh"
#include <cstdlib>
#include <fstream>
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void char_test()
{
  char next;

  MyList<char> L;

  cout << "Enter some text\n";

  // reads from terminal character by character until newline reached

  cin.get(next);
  while (next != '\n') {

    // put last character read into list

    L.push_back(next);
    cin.get(next);
  }

  cout << "Written forward that is:\n";

  while (!L.empty()) {
    cout << L.front();
    L.pop_front();
  }

  cout << "\n";
}

//----------------------------------------------------------------------------

void string_test()
{
  string next;
  char c;

  MyList<string> L;

  cout << "Enter a sentence or two\n";

  // read a word

  while (cin >> next) {

    // put it into the list

    L.push_back(next);

    // was that the last word on the line?

    c = cin.get();

    if (c == '\n')
      break;
    else
      cin.putback(c);

  }

  cout << "Written forward that is:\n";
  
  while (!L.empty()) {
    cout << L.front() << " ";
    L.pop_front();
  }
  cout << "\n";
}

void reverse_char_test()
{
  char next;

  MyList<char> L;

  cout << "Enter some text\n";

  // reads from terminal character by character until newline reached

  cin.get(next);
  while (next != '\n') {

    // put last character read into list

    L.push_back(next);
    cin.get(next);
  }

  cout << "Written backwards that is:\n";

  while (!L.empty()) {
    cout << L.front();
    L.pop_front();
  }

  cout << "\n";
}

void reverse_string_test()
{
  string next;
  char c;

  MyList<string> L;

  cout << "Enter a sentence or two\n";

  // read a word

  while (cin >> next) {

    // put it into the list

    L.push_back(next);

    // was that the last word on the line?

    c = cin.get();

    if (c == '\n')
      break;
    else
      cin.putback(c);

  }

  cout << "Written backwards that is:\n";
  
  while (!L.empty()) {
    cout << L.front() << " ";
    L.pop_front();
  }
  cout << "\n";
}

void insert_ordered(char** argv)
{
	ifstream inStream;
	string s;
  	MyList<string> L;
	
	inStream.open(argv[1]);
	
	if(inStream.fail())
	{
		cout << "Failed to open file." << endl;
		exit(1);
	}
	
	cout << "The file written from first from last:" << endl;
	
	while(!inStream.eof())
	{
		inStream >> s;
    		L.push_back(s);
	}

	while(!L.empty())
	{	
		cout << L.front()  << " ";
		L.pop_front();
	}
	
	cout << endl;
	inStream.close();
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

int main(int argc, char** argv)
{
	//  char_test();
	//  string_test();

	if(argc < 2)
	{
		cout << "Please specify filename on command line" << endl;
		exit(1);
	}

	insert_ordered(argv);
	reverse_char_test();
	reverse_string_test();

	return 1;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
