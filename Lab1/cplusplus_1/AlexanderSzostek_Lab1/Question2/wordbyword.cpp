#include <cstdlib>
#include <iostream>
#include <cmath>
#include <fstream>
#include <string.h>
#include <locale>

using namespace std;

//----------------------------------------------------------------------------
//replaces all punctuation (except apostrophes) with spaces 
string removePunct(string s)
{
	for(int i = 0; i != s.length(); i++)
	{
		if(ispunct(s[i]) && s[i] !=  '\'')
		{
			s.replace(i, 1, " ");
		}
	}
	return s;
}

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

    //remove any punctuation and write to file 
    if (!inStream.eof()) {
      s = removePunct(s); 
      outStream << s << " ";
    }
  }

  outStream.close();
  inStream.close();
  
  //open the file we just wrote to
  inStream.open("echo.txt");

  //read each word that was written to the file
  while (!inStream.eof()) {

    inStream >> s;

    //echo to console and count the number of words 
     if (!inStream.eof()) {
      
      cout << s << endl;
      num_words++;
    }

  }
  inStream.close();

  cout << num_words << " total words\n";

  return 0;
}
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
