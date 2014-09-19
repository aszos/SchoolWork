//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#include "dll_list.hh"

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

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

int main(int argc, char** argv)
{
  char_test();
  string_test();

  return 1;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
