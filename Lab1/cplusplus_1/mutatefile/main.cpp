
#include <cstdlib>
#include <iostream>
#include <cmath>
#include <fstream>

using namespace std;

//----------------------------------------------------------------------------

void init_rand()
{
  time_t now;

  now = time(NULL);
  srand(now);
}

//----------------------------------------------------------------------------

// generate random number between low and high, inclusive

int rand_low_high(int low, int high)
{
  return low + rand() % (high + 1 - low);
}

//----------------------------------------------------------------------------

// generate random number in [0, 1]

float rand_probability()
{
  return static_cast<float>(rand()) /  static_cast<float>(RAND_MAX);
}

//----------------------------------------------------------------------------

// generate random lower-case letter

char rand_letter()
{
  return static_cast<char>(rand_low_high('a', 'z'));
}

//----------------------------------------------------------------------------

int main()
{
  init_rand();

  ifstream inStream;
  ofstream outStream;
  float mutation_rate;
  char c;

  // what percentage of characters should be messed up?

  while (1) {
    cout << "What mutation rate do you want in the range [0, 1]?\n";
    cin >> mutation_rate;
    if (mutation_rate >= 0 && mutation_rate <= 1)
      break;
  }

  // try to open file to mutate

  inStream.open("test.txt");

  if (inStream.fail()) {
    cout << "Failed to open file\n";
    exit(1);
  }

  // get ready to write

  outStream.open("mutated_test.txt");

  // read input characters one by one

  while (!inStream.eof()) {

    inStream.get(c);

    // mutate (don't touch spaces)?

    if (!isspace(c) && rand_probability() < mutation_rate)
      c = rand_letter();

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

