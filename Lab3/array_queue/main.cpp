//----------------------------------------------------------------------------
/*
Modify this class to be circular (Meaning that the areay queue has no end)

*/

//----------------------------------------------------------------------------

#include "array_queue.hh"

#define LINUX 

#ifdef LINUX
#include <unistd.h>
#endif

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void char_test()
{
  char next;

  MyQueue<char> s;

  cout << "Enter some text\n";

  // reads from terminal character by character until newline reached

  cin.get(next);
  while (next != '\n') {

    // put last character read into queue

    s.enqueue(next);
    cin.get(next);
  }

  cout << "Written forward that is:\n";

  while (!s.empty()) {
    cout << s.dequeue();

    // this adds a delay between chars

#ifdef LINUX
    cout.flush();
    usleep(100000);
#endif
  }

  cout << "\n";
}

//----------------------------------------------------------------------------

void string_test()
{
  string next;
  char c;

  MyQueue<string> s;

  cout << "Enter a sentence or two\n";

  // read a word

  while (cin >> next) {

    // put it into the queue

    s.enqueue(next);

    // was that the last word on the line?

    c = cin.get();

    if (c == '\n')
      break;
    else
      cin.putback(c);

  }

  cout << "Written forward that is:\n";
  
  while (!s.empty()) {
    cout << s.dequeue() << " ";

    // this adds a delay between words

#ifdef LINUX
    cout.flush();
    usleep(250000);
#endif
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
