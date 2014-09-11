
#include <cstdlib>
#include <iostream>
#include <cmath>
#include <fstream>
#include <string>

using namespace std;

//----------------------------------------------------------------------------

int main(int argc, char **argv)
{
  ifstream inStream;
  ofstream outStream;
  string s;
  int num_words;

  if (argc < 2) {
    cout << "Please specify filename on command line\n";
    exit(1);
  }

  // try to open file to echo

  inStream.open(argv[1]);

  if (inStream.fail()) {
    cout << "Failed to open file\n";
    exit(1);
  }

  // get ready to write

  outStream.open("echo.txt");

  // read input WORDS one by one

  num_words = 0;

  while (!inStream.eof()) {

    inStream >> s;

    // write to file and echo to console
    
    if (!inStream.eof()) {

      outStream << s << " ";
      cout << s << endl;
      
      num_words++;
    }

  }

  inStream.close();
  outStream.close();

  cout << num_words << " total words\n";

  return 0;
}


//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

