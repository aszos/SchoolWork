/*
Alexander Szostek
Data Structures
December 19th, 2012
Dr. Brown

***Program Description***  
This program's purpose is to allow the user to manage a binary search tree. Features include adding values, inserting new nodes, deleting nodes, and displaying the tree in three (LVR,VLR,LRV) different methods. The menu loops indefinatley, or until the user gets bored and quits.

***Variable Dictionary***
Leaf leftLeaf - stores the left leaf pointer
Leaf rightLeaf - stores the right leaf pointer
int value - stores the value within this leaf
*/
public class Leaf
{
    Leaf leftLeaf, rightLeaf;
    int value;
    
    public Leaf(int value, Leaf leftLeaf, Leaf rightLeaf)
    {
        this.leftLeaf = leftLeaf;
        this.rightLeaf = rightLeaf;
        this.value = value;
    }
}