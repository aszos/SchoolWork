
#include <cstdlib>
#include <iostream>
#include <cmath>

using namespace std;

//----------------------------------------------------------------------------

void init_rand()
{
  time_t now;

  now = time(NULL);
  srand(now);
}

//----------------------------------------------------------------------------

int main()
{
  init_rand();

  int *p;
  int i, n;

  // get legal size for our array and allocate it

  while (1) {
    cout << "How many entries in your 1-D array?\n";
    cin >> n;

    if (n < 1)
      cout << "Please give a positive number\n";
    else {
      cout << "One " << n << "-element array, coming right up\n"; 
      break;
    }
  }
 
  p = new int[n];

  // fill with random numbers

  for (i = 0; i < n; i++) 
    p[i] = rand() % 10;

  // print array

  for (i = 0; i < n; i++) 
    cout << p[i] << " ";
  cout << "\n";

  // print memory address of first array element
  //  cout << p << "\n";


  delete [] p;

  return 0;
}


//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

