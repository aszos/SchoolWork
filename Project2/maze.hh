//----------------------------------------------------------------------------
// by Christopher Rasmussen, cer@cis.udel.edu
// created: October, 2014
// v. 1.00
//----------------------------------------------------------------------------

#ifndef MAZE_HH
#define MAZE_HH

#include <cstdlib>
#include <iostream>
#include <vector>
#include <queue>
#include <cmath>
#include <fstream>
#include <string>

using namespace std;

#define NUM_WALL_DIRECTIONS  2

#define EAST_WALL   0
#define SOUTH_WALL  1

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// initialize random number generator

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
//----------------------------------------------------------------------------

// a 2-D maze class

// consider the following 2 x 3 "maze" with numbered rows and columns:

//   -> EAST

//    0 1 2
//   #######    |
// 0 # | | #    V
//   #-+ +-#
// 1 # |   #  SOUTH
//   #######

// in between the cells there may be interior "walls", indicated by "|" or "-".  
// each cell "owns" the wall to the EAST and the wall to the SOUTH.  if there is a
// " " (space) where a wall could be, that wall has been "knocked down".

// "#" is an exterior wall, and "+" is a wall intersection.  these are always drawn
// and cannot be removed.  this is a completely empty (no walls) 2 x 3 maze:

// #######
// #     #
// # + + #
// #     #
// #######

// note that the cells in the last column have no east wall (those walls are exterior)
// and the cells in the last row have no south wall (also exterior)


class Maze
{
public:

  Maze(int, int);
  void print();
  void print_south_walls(int);
  bool pick_random_wall(int &, int &, int &);
  void knock_down_wall(int, int, int);
  void set_start_and_finish(int, int, int, int);
  void print_wall_location(int, int, int);

  // utility functions to convert to/from union-find array index

  int rowcol_to_UF_index(int, int);
  void UF_index_to_rowcol(int, int &, int &);

  int num_rows, num_cols;

  // num_rows x num_cols 2-D arrays

  int num_walls;     // keeps track of how many walls are still standing
  bool ***wall;      // true if wall of certain type (dimension 0) is standing at that location (dimensions 1 and 2)

  bool start_and_finish_are_set;
  int start_row, start_col;
  int finish_row, finish_col;

};

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// given row, column in maze, returns index that can be used in union-find data structure array.
// uses row-major order (http://en.wikipedia.org/wiki/Row-major_order)

int Maze::rowcol_to_UF_index(int row, int col)
{
  return row * num_cols + col;
}

//----------------------------------------------------------------------------

// given index in union-find data structure array, returns row, column in maze.
// uses row-major order (http://en.wikipedia.org/wiki/Row-major_order)

void Maze::UF_index_to_rowcol(int index, int & row, int & col)
{
  row = index / num_cols;
  col = index % num_cols;
}

//----------------------------------------------------------------------------

// indicate which coordinates the start and finish of the maze should be at

void Maze::set_start_and_finish(int srow, int scol, int frow, int fcol)
{
  if (srow < 0 || srow >= num_rows || scol < 0 || scol >= num_cols)
    return;

  start_row = srow;
  start_col = scol;

  finish_row = frow;
  finish_col = fcol;

  start_and_finish_are_set = true;
}

//----------------------------------------------------------------------------

// sets type (EAST_WALL or SOUTH_WALL) and coordinates (row, col) randomly from
// remaining standing walls

// returns false if there are no more standing walls

bool Maze::pick_random_wall(int & direction, int & row, int & col) 
{ 
  // if there are no more standing walls, return with failure because the loop below would never end

  if (num_walls == 0)
    return false;

  // keep rolling the dice until we find a standing wall (this is horribly inefficient)

  while (1) {

    // randomly decide whether this will be a SOUTH_WALL or an EAST_WALL

    if (num_rows == 1)
      direction = EAST_WALL;
    else if (num_cols == 1)
      direction = SOUTH_WALL;
    else
      direction = rand_low_high(0, NUM_WALL_DIRECTIONS - 1);
    
    int max_row = num_rows - 1;
    int max_col = num_cols - 1;

    if (direction == EAST_WALL)         // don't choose furthest east column if wall direction is EAST (because that would be exterior wall)
      max_col--;
    else if (direction == SOUTH_WALL)   // don't choose furthest south row if wall direction is SOUTH (same reason)
      max_row--;
    
    row = rand_low_high(0, max_row);
    col = rand_low_high(0, max_col);

    // if this is a standing wall, we're done

    if (wall[direction][row][col]) 
      break;
  }

  return true;
}

//----------------------------------------------------------------------------

// make a standing wall disappear
// must specify direction (EAST or SOUTH) and coordinates (row, col)

void Maze::knock_down_wall(int direction, int row, int col) 
{ 
  if (direction < 0 || direction >= NUM_WALL_DIRECTIONS || row < 0 || row >= num_rows || col < 0 || col >= num_cols)
    return;

  if (direction == SOUTH_WALL && row == num_rows - 1)
    return;

  if (direction == EAST_WALL && col == num_cols - 1)
    return;

  if (wall[direction][row][col]) {
    wall[direction][row][col] = false; 
    num_walls--;
  }
}

//----------------------------------------------------------------------------

// print type and coordinates of a wall section

void Maze::print_wall_location(int direction, int row, int col)
{
  if (direction == EAST_WALL)
    cout << "EAST (" << row << ", " << col << ")" << endl;
  else
    cout << "SOUTH (" << row << ", " << col << ")" << endl;
}

//----------------------------------------------------------------------------

// print a "run" of horizontal walls

// row = -1 means north exterior wall

void Maze::print_south_walls(int row)
{
  int j;

  // exterior wall

  if (row < 0 || row == num_rows - 1) {
    for (j = 0; j < 2 * num_cols + 1; j++) 
      cout << "#";
    cout << endl;
  }

  else {

    cout << "#";   // west exterior

    for (j = 0; j < num_cols; j++) {

      if (wall[SOUTH_WALL][row][j])
	cout << "-";
      else 
	cout << " ";

      if (j == num_cols - 1)
	cout << "#"; // east exterior
      else
	cout << "+";   // wall intersection

    }
    cout << endl;
  }
}

//----------------------------------------------------------------------------

// print maze as described in comments above class definition

void Maze::print()
{
  int i, j;

  if (num_rows == 0 || num_cols == 0) {
    cout << "Empty maze" << endl;
    return;
  }

  print_south_walls(-1);
  for (i = 0; i < num_rows; i++) {

    cout << "#";   // west exterior

    for (j = 0; j < num_cols; j++) {

      // print cell contents

      if (start_and_finish_are_set) {
	if (i == start_row && j == start_col)
	  cout << "S";
	else if (i == finish_row && j == finish_col)
	  cout << "F";
	else 
	  cout << " ";
      }
      else
	cout << " ";

      // print wall

      if (wall[EAST_WALL][i][j]) {
	if (j == num_cols - 1)
	  cout << "#";     // east exterior
	else  
	  cout << "|";     // interior
      }
      else
	cout << " ";
    }
    cout << endl;
    print_south_walls(i);
  }
  cout << endl;
}

//----------------------------------------------------------------------------

Maze::Maze(int rows, int cols)
{
  init_rand();

  num_rows = rows;
  num_cols = cols;

  if (rows < 1 || cols < 1) {
    num_rows = num_cols = 0;
    return;
  }

  start_and_finish_are_set = false;

  // allocate wall arrays

  num_walls = 
    (rows - 1) * (cols - 1) * NUM_WALL_DIRECTIONS + // upper-left portion that has both east and south interior walls
    rows - 1 +                                      // far east column that has just south walls, excluding bottom-right cell
    cols - 1;                                       // far south row that has just east walls, excluding bottom-right cell

  int d, i, j;

  wall = new bool **[NUM_WALL_DIRECTIONS];
  for (d = 0; d < NUM_WALL_DIRECTIONS; d++) {
    wall[d] = new bool *[num_rows];
    for (i = 0; i < num_rows; i++) {
      wall[d][i] = new bool[num_cols];
      for (j = 0; j < num_cols; j++) {
	wall[d][i][j] = true;
      }
    }
  }
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

#endif
