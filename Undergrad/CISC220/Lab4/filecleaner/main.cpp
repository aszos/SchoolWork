
#include <cstdlib>
#include <iostream>
#include <cmath>
#include <fstream>

using namespace std;

//----------------------------------------------------------------------------

int main(int argc, char **argv)
{
  ifstream inStream;
  ofstream outStream;
  char c;

  if (argc != 2) {
    cout << "please enter name of file to clean as 2nd argument" << endl;
    exit(1);
  }

  // try to open file to clean

  string inputFilename(argv[1]);

  inStream.open(inputFilename.c_str());

  if (inStream.fail()) {
    cout << "Failed to open file\n";
    exit(1);
  }

  // get ready to write
  
  string outputFilename("cleaned_" + inputFilename);
  outStream.open(outputFilename.c_str());

  // read input characters one by one

  while (!inStream.eof()) {

    inStream.get(c);

    // turn all punctuation which is not a single apostrophe to space;
    // turn capital letters into lower-case

    if (!isspace(c)) {
      if (ispunct(c) && c != '\'')
	c = ' ';
      else if (isalpha(c))
	c = tolower(c);
    }

    // write to file and echo to console
    
    outStream.put(c);
    cout.put(c);  

  }

  inStream.close();
  outStream.close();

  return 0;
}


//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

