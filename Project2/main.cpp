//----------------------------------------------------------------------------
#include "maze.hh"
#include "unionfind.hh"
#include "string.h"
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

// global variables

Maze *M = NULL;
UnionFind *U = NULL;
bool verbose = false;
bool silent = false;
bool dumb = false;

//----------------------------------------------------------------------------
//Custom Utility Functions

//determines if the current maze is solvable

bool solvable()
{
  int start_block = M->rowcol_to_UF_index(M->start_row, M->start_col); 
    int finish_block =M->rowcol_to_UF_index(M->finish_row, M->finish_col);

  return (U->find(start_block) == U->find(finish_block));
}

//determines if each block is connected / in the same equivalence class

bool all_connected()
{
  bool connected_so_far = true;
  int block_num; 

  for(int i = 0; i < M->num_rows && connected_so_far; i++)    
  {
    for(int j = 0; j < M->num_cols && connected_so_far; j++)    
    {
      block_num = M->rowcol_to_UF_index(i,j);
      connected_so_far = (U->find(block_num) == U->find(block_num + 1));  
    }
  }  
}

//determines if the wall given shoud be destroyed or not
//does destroy the wall in the Union if it can

bool smart_union_break_wall(int dir, int i, int j)
{
  int block_num = M->rowcol_to_UF_index(i,j); 

  if(dir == 0)
  {
    int source_eq = U->find(block_num); 
    int east_eq = U->find(block_num + 1);
    if(source_eq != east_eq)
    {
      U->union_sets_by_size(source_eq, east_eq);
      return true;  
    }
    else
    {
      return false;
    }
  }  
  else
  {
    int source_eq = U->find(block_num); 
    int south_eq = U->find(block_num + M->num_cols);
    if(source_eq != south_eq)
    {
      U->union_sets_by_size(source_eq, south_eq);
      return true;  
    }
    else
    {
      return false;
    }
  }  
}

bool dumb_union_break_wall(int dir, int i, int j)
{
  int block_num = M->rowcol_to_UF_index(i,j); 

  if(dir == 0)
  {
    int source_eq = U->find(block_num); 
    int east_eq = U->find(block_num + 1);
    if(source_eq != east_eq)
    {
      U->union_sets(source_eq, east_eq);
      return true;  
    }
    else
    {
      return false;
    }
  }  
  else
  {
    int source_eq = U->find(block_num); 
    int south_eq = U->find(block_num + M->num_cols);
    if(source_eq != south_eq)
    {
      U->union_sets(source_eq, south_eq);
      return true;  
    }
    else
    {
      return false;
    }
  }  
}

//gets the height of the largest equivalence class 

int get_largest_eq()
{
  int largest = 0;

  for(int i = 0; i < U->num_elements; i++)
  {
    int current_largest = 0;
    int current = U->S[i];

    while(current > 0)
    {
      current_largest++;
      current = U->S[current];
    }

    if(current_largest > largest)
      largest = current_largest; 
  }
  return largest;
}

//prints out statistics only if the user wanted to see them 
void print_statistics(int dir, int i, int j)
{
  if(verbose)
  {
    M->print_wall_location(dir, i, j);
    M->print();
    U->print_horizontal();
  }
}

//prints the maze

void print_final_maze()
{
   cout << "---------- Final Maze ----------" << endl;
   
   if(!silent)
   { 
     M->print();
     U->print_horizontal();
   }
   
   cout << "Height of Largest Equivalence Class: " << get_largest_eq() << endl;
}


//----------------------------------------------------------------------------

// OPTION 1

// knock down random walls one at a time until they're all gone.
// ATM, this does nothing to associated union-find data structure

void knock_down_all_walls()
{
  int dir, i, j;
  
  while (M->pick_random_wall(dir, i, j)) 
  {
    M->knock_down_wall(dir, i, j);

    if(dumb)
      dumb_union_break_wall(dir, i, j);
    else
      smart_union_break_wall(dir, i, j);

    print_statistics(dir, i, j);    
  }
  print_final_maze();
}

//----------------------------------------------------------------------------

// OPTION 2

// knock down random walls one at a time until START and FINISH are connected.
// nothing else is considered

void knock_down_til_solvable()
{
  int dir, i, j;
 
  while (M->pick_random_wall(dir, i, j)) 
  {
    M->knock_down_wall(dir, i, j);
    
    if(dumb)
      dumb_union_break_wall(dir, i, j);
    else
      smart_union_break_wall(dir, i, j);

    print_statistics(dir, i, j);    

    if(solvable())  
      break;
  }
  print_final_maze();
}

//----------------------------------------------------------------------------

// OPTION 3

// knock down random walls one at a time until START and FINISH are connected.
// now we only knock down a wall if the cells on either side of it are NOT already
// connected

void knock_down_til_solvable_better()
{
  int dir, i, j;
  while (M->pick_random_wall(dir, i, j)) 
  {

    if(dumb)
    {
      if(dumb_union_break_wall(dir, i, j))
         M->knock_down_wall(dir, i, j);
    }
    else
    {
      if(smart_union_break_wall(dir, i, j))
         M->knock_down_wall(dir, i, j);
    }

    print_statistics(dir, i, j);    

   if(solvable())
     break;
  }
  print_final_maze();
}

//----------------------------------------------------------------------------

// OPTION 4

// same as option 3, but we only stop on the condition that the maze is solvable
// AND that every cell in it is connected to every other cell.  note that this 
// does not mean all the walls are down

void knock_down_til_all_connected()
{
  int dir, i, j;
  while (M->pick_random_wall(dir, i, j)) 
  {
    M->knock_down_wall(dir, i, j);

    if(dumb)
      dumb_union_break_wall(dir, i, j);
    else
      smart_union_break_wall(dir, i, j);

    print_statistics(dir, i, j);    

   if(solvable() && all_connected())
      break;
  }
  print_final_maze();
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------

void driver(int maze_height, int maze_length)
{
  // initialize maze

   M = new Maze(maze_height, maze_length);
   U = new UnionFind(M->num_rows * M->num_cols);

  // catch any excpetions
  if (!M || !U) {
    cout << "Must properly initialize maze and union-find data structure before continuing" << endl;
    exit(1);
  }

  // set start and finish locations 
  M->set_start_and_finish(0, 0, M->num_rows - 1, M->num_cols - 1);

  // show original maze
  
  if(!silent)
  {
    cout << "---------- Initial Maze ----------" << endl;
    M->print();
  }

  if(verbose) 
  {
    U->print_horizontal();
  } 

  cout << "Please choose one of the following:\n1 Knock down walls randomly until all gone\n2 Knock down walls until start and finish are connected\n3 Same as 2, but make sure cells on each side of wall are not already connected\n4 Same as 3, but keep going until ALL cells are connected to each other\nAnything else to exit" << endl;
}

//----------------------------------------------------------------------------

int main(int argc, char** argv)
{
  int height = 5;
  int length = 5;

  if(argc > 2)
  {
    height = atoi(argv[1]);
    length = atoi(argv[2]); 

    if(argv[3]) 
    {
      verbose = (strcmp(argv[3], "verbose") == 0);
      silent = (strcmp(argv[3], "silent") == 0);
    }
    if(argv[4]) 
    {
      dumb = (strcmp(argv[4], "dumb") == 0);
    }
  }
 
  driver(height, length);
  
  int input_val;
  cin >> input_val;

  switch (input_val) 
  {
    case 0: 
      exit(1);
    case 1: 
      knock_down_all_walls();
      exit(1);
    case 2:
      knock_down_til_solvable();
      exit(1);
    case 3:
      knock_down_til_solvable_better();
      exit(1);
    case 4:
      knock_down_til_all_connected();
      exit(1);
    }

  return 1;
}

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
