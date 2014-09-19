//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#include "array_stack.hh"

//----------------------------------------------------------------------------

void char_test()
{
  char next;

  MyStack<char> s;

  cout << "Enter some text\n";

  // read characters in one by one until newline reached

  cin.get(next);
  while (next != '\n') {

    // push latest character

    s.push(next);
    cin.get(next);
  }

  cout << "Written backward that is:\n";

  // output all characters stored in stack

  while (!s.empty())
    cout << s.pop();
  cout << "\n";

}

//----------------------------------------------------------------------------

void string_test()
{
  string next;
  char c;

  MyStack<string> s;

  cout << "Enter a sentence or two\n";

  // read from terminal word by word

  while (cin >> next) {

    // put latest word into stack

    s.push(next);

    // was that the last word on the line?

    c = cin.get();

    if (c == '\n')
      break;
    else
      cin.putback(c);

  }

  cout << "Written backward that is:\n";
  
  while (!s.empty())
    cout << s.pop() << " ";
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
