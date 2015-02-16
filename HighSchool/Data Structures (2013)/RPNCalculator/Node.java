/*
Alexander Szostek
Dr. Brown
Data Structures
December 27th, 2012

***Program Description***
The purpose of this program is to simulate a Reverse Polish notation calculator by utilizing the stack (FILO) structure.
 
***Variable Dictionary***
Node pointer - stores the next node of the stack
double value - stores the value of the this node
*/

public class Node
{
    Node pointer;
    double value;
    public Node(Node pointer, double value)
    {
        this.pointer = pointer;
        this.value = value;
    }
}