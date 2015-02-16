/*
Alexander Szostek
Data Structures
December 19th, 2012
Dr. Brown

***Program Description***  
This program's purpose is to allow the user to manage a binary search tree. Features include adding values, inserting new nodes, deleting nodes, and displaying the tree in three (LVR,VLR,LRV) different methods. The menu loops indefinatley, or until the user gets bored and quits.

***Variable Dictionary***
boolean continueRunning - keeps the program running, exits if false
int counter - repetition tracker
int createThisManyLeaves - stores number of leaves that need to be made
Object[] displayMenuButtons - stores the menu options for displaying
int getMainMenuSelection - stores user's main menu selection
int getValue - stores value that user enters in for a leaf
boolean ifAdded - whether or not adding a leaf to the tree was a success
boolean ifFinished - stores if we're done with deletion
Object[] mainMenuButtons - stores main menu buttons
int modifyThis - gets the user's desired modifying target
int modifyToThis - stores what the user desires to modify target to
Leaf parentPointer - stores the parent of the rootPointer when deleting
Leaf root - stores the root of the tree
Leaf rootPointer - a pointer used within the tree, starts at the root
int searchForThisValue - when deleting, stores the value that we'll be deleting
Leaf treePointer - a pointer used for deletion, used to delete a child and relies on the parent pointer
*/

import javax.swing.JOptionPane;
public class MainTree
{
    public static void main(String[] args)
    {
       Leaf root = null; 
       menuOptions(root);
       //let's just make a root and run the menu
    }
    
    public static void menuOptions(Leaf root)
    {
        Object[] mainMenuButtons = {"New Pile of Leaves", "Delete Leaf", "Modify Leaf", "Display Tree", "Quit"};
        int getMainMenuSelection = JOptionPane.showOptionDialog(null, "Select an Option", "Required", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, mainMenuButtons, mainMenuButtons[0]);
        //ask the user what they want to do
        Leaf rootPointer = root;
        //make a pointer so we don't lose the root
        boolean continueRunning = true;
        //for now, let's keep the program running
        int getValue;
        //we're gonna use this later, promise
        switch(getMainMenuSelection)  
        //let's focus on what the user selected
        {
            case 0:
            //if the user wants to create a new bunch of leaves
            int counter = 0;
            //start a counter at 0, we've created 0 leaves so far
            int createThisManyLeaves = Integer.parseInt(JOptionPane.showInputDialog(null,"How many leaves to create?"));
            //well, how many leaves do you want user?
            if(root == null)
            //if this is the first leaf created
            {
                getValue = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter in a value."));
                //get the value
                root = createLeaf(getValue);
                //make the created leaf the root
                counter++;
                //we've made a leaf, gotta keep track of that
            }
            for(counter = counter; counter < createThisManyLeaves; counter++)
            //let's start from whatever counter equals (0 or 1), don't loop more than how many leaves we want to make, and we're gonna be making one leaf per repetition
            {
                getValue = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter in a value."));
                //get the value that's gonna be made
                rootPointer = createLeaf(getValue);
                //store the created leaf into a pointer
                root = addToTree(rootPointer, root);
                //add the created leaf into the tree, and it'll return a new head
            }
            break;
            
            case 1:
            //if we want to delete something
                int searchForThisValue = Integer.parseInt(JOptionPane.showInputDialog(null,"Which value should be deleted?"));
                //ask for what we want to delete
                root = deleteLeaf(root, searchForThisValue);
                //we run over to deleteLeaf, which will return the root of a tree after we've deleted that leaf
            break;
            
            case 2:
            //if we want to modify the leaf
                root = modifyLeaf(root);
                //just reference the method that modifies the leaf and it'll return the root of tree with the modified leaf
            break;
            
            case 3:
            //if we want to see how the tree looks like    
                Object[] displayMenuButtons = {"VLR (Pre Order)","LVR (In Order)","LRV (Post Order)","Quit"};
                //since this is a binary tree, there's a lot of ways to traverse it
                int getDisplayMenuSelection = JOptionPane.showOptionDialog(null, "Select an method of traversal.", "Required", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, displayMenuButtons, displayMenuButtons[0]);
                //find out how the user wants to see the tree
                switch(getDisplayMenuSelection)
                //let's focus on what the user wants to do
                {
                    case 0:
                    //if we want to VLR
                    System.out.println("VLR (Pre Order)");
                    traverseTreeVLR(root);
                    //do so
                    System.out.println("\n");
                    //with some nice formatting
                    break;
                    
                    case 1:
                    //if we want to LVR
                    System.out.println("LVR (In Order)");
                    traverseTreeLVR(root);
                    //do so
                    System.out.println("\n");
                    //with some beautiful formatting
                    break;
                    
                    case 2:
                    //if we want to LRV
                    System.out.println("LRV (Post Order)");
                    traverseTreeLRV(root);
                    //do it, baby!
                    System.out.println("\n");
                    //make it look nice!
                    break;
                    
                    case 3:
                    //Quit
                    break;
                }
            break;
            
            case 4:
            //if we want to quit
            continueRunning = false;
            //stop running the loop            
            break;
            
            default:
            //if something goes wrong
            continueRunning = false;
            //stop running the loop
            break; 
        }
        if(continueRunning == true)
        //if we want to continue running
        {
            menuOptions(root);
            //continue to run the same method
        }
    }
    public static Leaf createLeaf(int getValue)
    {
        Leaf createLeaf = new Leaf(getValue, null, null);
        return createLeaf;
        //creates a new leaf with the value give, then returns it 
    }
    
    public static Leaf addToTree(Leaf addMe, Leaf root)
    {
        Leaf rootPointer = root;
        //create a pointer so we don't lose the root
        boolean ifAdded = false;
        //this will keep track of whether or not we added the leaf successfully
        while(ifAdded == false)
        //while we haven't made a change
        {
            if(addMe.value > rootPointer.value)
            //if the leaf we want to add is greater than the current leaf
            {
                if(rootPointer.rightLeaf != null)
                //and while the right leaf exists
                {
                    rootPointer = rootPointer.rightLeaf;
                    //go to the right leaf
                }
                else
                //if it doesn't exist
                {
                    rootPointer.rightLeaf = addMe;
                    //make it the new right leaf
                    ifAdded = true;
                    //we made a change, so true
                }
            }
            else
            //if the leaf we want to add is less/equal than the current leaf
            {
                if(rootPointer.leftLeaf != null)
                //while the left leaf exists
                {
                    rootPointer = rootPointer.leftLeaf;
                    //go to the leat leaf
                }
                else
                //if it doesn't exist
                {
                    rootPointer.leftLeaf = addMe;
                    //make it the new left leaf
                    ifAdded = true;
                    //we made a change
                }
            }   
        } 
    return root;
    //once we're all done, we gotta return the root for more uses
    }
    
    public static void traverseTreeVLR(Leaf root)
    {
        Leaf rootPointer = root;
        if(rootPointer != null)
        {
            System.out.println(rootPointer.value);  
            traverseTreeVLR(rootPointer.leftLeaf);
            traverseTreeVLR(rootPointer.rightLeaf);
        }
        //standard VLR traversal
    }
    
    public static void traverseTreeLVR(Leaf root)
    {
        Leaf rootPointer = root;
        if(rootPointer != null)
        {
            traverseTreeLVR(rootPointer.leftLeaf);
            System.out.println(rootPointer.value);
            traverseTreeLVR(rootPointer.rightLeaf);
        }
        //standard LVR traversal
    }
    
    public static void traverseTreeLRV(Leaf root)
    {
        Leaf rootPointer = root;
        if(rootPointer != null)
        {
            traverseTreeLRV(rootPointer.leftLeaf);
            traverseTreeLRV(rootPointer.rightLeaf);
            System.out.println(rootPointer.value);
        }
        //standard LRV traversal
    } 
    
    public static Leaf deleteLeaf(Leaf root, int searchForThisValue)
    {
        boolean ifFinished = false;
        //let's keep track of whether or not we're done here
        Leaf parentPointer = root;
        Leaf treePointer = root;
        //some pointers to make this easier
        
        if(root == null)
        //if the root doesn't exist
        {
            System.out.println("You can't delete from an empty tree.");
            //pretty self explanatory
        }
        else if(searchForThisValue == root.value)
        //if we want to delete the root of the tree
        {
            if(root.leftLeaf == null && root.rightLeaf == null)
            //and if the root has no children
            {
                root = null;
                //delete the root, no problems
            }
            else if(root.leftLeaf == null && root.rightLeaf != null)
            //and if the root has a right child but no left child
            {
                root = root.rightLeaf;
                //the root becomes the right child, the previous root will be trash collected
            }
            else if(root.leftLeaf != null && root.rightLeaf == null)
            //and if the root has a left child but no right child
            {
                root = root.leftLeaf;
                //the root becomes the left child, the previous root will be trash collected
            }
            else
            //and if the root has two children
            {
                Leaf leftHolder = root.leftLeaf;
                Leaf rightHolder = root.rightLeaf;
                //store the children somewhere
                while(rightHolder.leftLeaf != null)
                {
                    rightHolder = rightHolder.leftLeaf;
                }
                //go all the way down the right leaf's left leaves
                rightHolder.leftLeaf = leftHolder;
                //cram the left leaf under the right leaf's leftmost leaf
                root = root.rightLeaf;
                //the root becomes the right leaf
            }
        }
        else
        //if we want to delete a leaf other than the root
        {
            while(ifFinished == false && treePointer != null)
            //continue looping while we're not finished and the pointer we're using isn't null
            {
                if(searchForThisValue > treePointer.value)
                //if the value we're looking for is greater than what we're at
                {
                    parentPointer = treePointer;
                    treePointer = treePointer.rightLeaf;
                    //go to the right    
                }
                
                else if(searchForThisValue < treePointer.value)
                //if the value we're looking for is less than what we're at
                {
                    parentPointer = treePointer;
                    treePointer = treePointer.leftLeaf;
                    //go to the left
                }
                else
                //if we've found the leaf
                {
                    if(treePointer.leftLeaf == null && treePointer.rightLeaf == null)
                    //and it has no children
                    {
                        if(parentPointer.leftLeaf == treePointer)
                        //check which side the tree pointer is on, if the it's the left child
                        {
                            parentPointer.leftLeaf = null;
                            //remove the left leaf
                        }
                        else
                        //it's on the right side
                        {
                            parentPointer.rightLeaf = null;
                            //remove the right leaf
                        }
                    }
                    else if(treePointer.leftLeaf == null && treePointer.rightLeaf != null)
                    //if the leaf has a right leaf but no left leaf
                    {
                        if(parentPointer.leftLeaf == treePointer)
                        //check which side the tree pointer is on, if the it's the left leaf
                        {
                            parentPointer.leftLeaf = treePointer.rightLeaf;
                            //remove the leaf by making the parent's left leaf point to the removed leaf's right leaf
                        }
                        else
                        //it's on the right side
                        {
                            parentPointer.rightLeaf = treePointer.rightLeaf;
                            //remove the leaf by making the parent's right leaf point to the removed leaf's right leaf
                        }
                    }
                    else if(treePointer.rightLeaf == null && treePointer.leftLeaf != null)
                    //if the leaf has a left leaf but no right leaf
                    {
                        if(parentPointer.leftLeaf == treePointer)
                        //check which side the tree pointer is on, if the it's the left leaf
                        {
                            parentPointer.leftLeaf = treePointer.leftLeaf;
                            //remove the leaf by making the parent's left leaf point to the removed leaf's left leaf
                        }
                        else
                        //it's on the right side
                        {
                            parentPointer.rightLeaf = treePointer.leftLeaf;
                            //remove the leaf by making the parent's right leaf point to the removed leaf's left leaf
                        }
                    }
                    else
                    //if the leaf has two children
                    {
                        if(parentPointer.leftLeaf == treePointer)
                        //check which side the tree pointer is on, if it's the left leaf
                        {
                            Leaf leftHolder = treePointer.leftLeaf;
                            Leaf rightHolder = treePointer.rightLeaf;
                            //hold onto the left and right leaf
                            parentPointer.leftLeaf = rightHolder;
                            //make the parent's left leaf point to the right leaf
                                while(rightHolder.leftLeaf != null)
                                {
                                    rightHolder = rightHolder.leftLeaf;
                                }
                                //go all the way down the right leaf's left leaves
                            rightHolder.leftLeaf = leftHolder;
                            //make the right leaf's left leaf point to the left leaf
                        }
                        else
                        //if it's the right leaf
                        {
                            Leaf leftHolder = treePointer.leftLeaf;
                            Leaf rightHolder = treePointer.rightLeaf;
                             //hold onto the left and right leaf
                            parentPointer.rightLeaf = rightHolder;
                            //make the parent's right leaf point to the right leaf
                                while(rightHolder.leftLeaf != null)
                                {
                                    rightHolder = rightHolder.leftLeaf;
                                }
                                //go all the way down the right leaf's left leaves
                            rightHolder.leftLeaf = leftHolder;
                            //make the right leaf's left leaf point to the left leaf
                        }
                        
                    }
                    ifFinished = true;
                    //we're done here, boss
                }
            }
        }
        return root;
        //gotta return the root when we're all done
    }
    
    public static Leaf modifyLeaf(Leaf root)
    {
        int modifyThis = Integer.parseInt(JOptionPane.showInputDialog(null,"Which number would you like to modify?"));
        int modifyToThis = Integer.parseInt(JOptionPane.showInputDialog(null,"Modify to...?"));
        //find out what the user wants to modify and what to modify it to
        root = deleteLeaf(root, modifyThis);
        //delete the leaf that we're going to modify
        Leaf insertNewLeaf = createLeaf(modifyToThis);
        //create a new leaf containing what we're suppoed to be modifying it to
        root = addToTree(insertNewLeaf, root);
        //shove that new leaf onto the tree
        return root;
        //return that root when we're all done 
    }
}
