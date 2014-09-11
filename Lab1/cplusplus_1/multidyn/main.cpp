
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

  int **p;

  int i, j, n;

  cout << "How many rows in your square array?\n";
  cin >> n;

  // array of "rows"

  p = new int *[n];

  // for each row, allocate array of column ints

  for (i = 0; i < n; i++) 
    p[i] = new int[n];

  // fill in random numbers...

  for (j = 0; j < n; j++) 
    for (i = 0; i < n; i++) 
      p[j][i] = rand() % 10;

  /// ...and print

  for (j = 0; j < n; j++) {
    for (i = 0; i < n; i++) 
    cout << p[j][i] << " ";
    cout << "\n";
  }

  //  de-allocate dynamically-created array

  for (j = 0; j < n; j++)
    delete [] p[j];

  delete [] p;

  return 0;
}


//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

