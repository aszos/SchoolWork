/*
Created by Alexander Szostek
Completed on November 4th, 2012
Data Structures 

Program Description:
This program is designed to manage four lists, all containing employee information, oriented relative to employee name(string), age(integer), 
and salary(double). First, the user is asked how many employees they are given, they then enter in information about each employee. From there, 
the program creates four lists oriented in chronological order, alphabetical order, numerical (age and salary) order. Once this is completed,
the user can add another employee to the lists, display either one of the lists, or exit the program.

Variable Dictionary:
Employee appendThisEmployee - stores the employee that needs to be appended
int counter - keeps track of loop repitition
int employeeAmount - stores the number of employees at program first run
int enteredEmployeeAge - stores the age when creating the employee node
String enteredEmployeeName - stores the name when creating the employee node
double enteredEmployeeSalary - stores the salary when creating the employee node
Employee EmployeeListHeadNode - stores the head of the list within the insertion and appending methods
Employee employeeNode - stores the current employee node
Employee employeeNodeByAge - stores the employee for insertion into the age list
Employee employeeNodeByName - stores the employee for insertion into the name list
Employee employeeNodeChronological - stores the employee for insertion into the chronological list
String finalPrintByNameList - contains all content (name, age, salary) of each employee of the name list
String finalPrintByAgeList - contains all content (name, age, salary) of each employee of the age list
String finalPrintBySalaryList - contains all content (name, age, salary) of each employee of the salary list
String finalPrintChronologicalList - contains all content (name, age, salary) of each employee of the chronological list
int getOptionNumber - stores the option selected for the add/display/exit menu
int getOptionTextButtonsPrint - stores the option selected for the print list menu
Employee getNewEmployee - stores a newly created employee for adding
Employee getNewEmployeeByAge - stores the age for the newly created employee for adding
Employee getNewEmployeeByName - stores the name for the newly created employee for adding
Employee getNewEmployeeBySalary - stores the salary for the newly created employee for adding
Employee getNewEmployeeChronological - stores the node that will be added to the chronological list
Employee headNodeEmployeeListBySalary - stores the head of the salary list
Employee insertThisEmployee - contains the object that needs to be inserted 
MasterEmployeeNode masterHead - contains all four head pointers for all lists
Object[] optionTextButtonsFirst - stores the options for the first menu
Object[] optionTextButtonsPrint - stores the options for the print menu
Employee tempHeadByAge - a handler pointer for the head of the age list
Employee tempHeadBySalary - a handler pointer for the head of the salary list 
Employee tempHeadByName - a handler pointer for the head of the name list 
Employee tempHeadChronological - a handler pointer for the head of the chronological list
Employee tempHeadHandle - a handler pointer for the head of a given list
Employee tempSecondHandle -  - a handler pointer for the second node of a given list
MasterEmployeeNode tempMasterHead - a handler pointer for the master head
*/
import javax.swing.JOptionPane;

public class EmployeeDatabaseMain
{
    public static void main(String[] args)
    {
    MasterEmployeeNode tempMasterHead = createEmployeesMasterHead();
    //create the masterhead in order to handle four lists under one instance
    menuOptions(tempMasterHead);
    //start the menu chain and send the masterhead for list references
    }
    
    public static Employee createSingleEmployee()
    //a method where we only want to create one single employee and return it
    {
        String enteredEmployeeName = JOptionPane.showInputDialog(null,"What is the employee's name?");
        int enteredEmployeeAge = Integer.parseInt((JOptionPane.showInputDialog(null,"How old is this employee?")));
        double enteredEmployeeSalary = Double.parseDouble((JOptionPane.showInputDialog(null,"How much does this employee make?")));
        //get data relating to an employee such as the name, age, and salary
        Employee employeeNode = new Employee(enteredEmployeeName,enteredEmployeeAge,enteredEmployeeSalary);
        //create the employee object 
        return employeeNode;
        //then return said object
    }
    
    public static MasterEmployeeNode createEmployeesMasterHead()
    //a method called at the start of the program, creates and returns a master head that contains pointers to four different lists
    {       
        Employee employeeNodeChronological = null;
        Employee employeeNodeByName = null;
        Employee employeeNodeByAge = null;
        Employee employeeNodeBySalary = null;
        //create four seperate nodes in order to place them each of them into four different lists
               
        Employee headNodeEmployeeListChronological = null;
        Employee headNodeEmployeeListByName = null;
        Employee headNodeEmployeeListByAge = null;
        Employee headNodeEmployeeListBySalary = null;
        //create the head nodes of each list (chronological, name, age, salary)
        
        int employeeAmount = Integer.parseInt((JOptionPane.showInputDialog(null,"How many employees do you have?")));
        //find out how long the lists will be and use that to find out how many times the following loop will repeat
        for(int counter = 0; counter < employeeAmount; counter++)
        {
            String enteredEmployeeName = JOptionPane.showInputDialog(null,"What is the employee's name?");
            int enteredEmployeeAge = Integer.parseInt((JOptionPane.showInputDialog(null,"How old is this employee?")));
            double enteredEmployeeSalary = Double.parseDouble((JOptionPane.showInputDialog(null,"How much does this employee make?")));
            //get information reguarding the employee being entered
            employeeNodeChronological = new Employee(enteredEmployeeName,enteredEmployeeAge,enteredEmployeeSalary);
            employeeNodeByName = new Employee(enteredEmployeeName,enteredEmployeeAge,enteredEmployeeSalary);
            employeeNodeByAge = new Employee(enteredEmployeeName,enteredEmployeeAge,enteredEmployeeSalary);
            employeeNodeBySalary = new Employee(enteredEmployeeName,enteredEmployeeAge,enteredEmployeeSalary);
            //create four objects, each containing the same information that was entereed
            headNodeEmployeeListChronological = appendEmployee(headNodeEmployeeListChronological, employeeNodeChronological);
            //append the newly created employee to the chronological list
            
            if(headNodeEmployeeListByName == null)
            //if the head is not pointing to anything
            {
                headNodeEmployeeListByName = appendEmployee(headNodeEmployeeListByName, employeeNodeByName);
                //append the node we want to insert
            }
            else
            //if we do have something in the list
            {
                headNodeEmployeeListByName = insertEmployeeByName(headNodeEmployeeListByName, employeeNodeByName);
                //insert the employee relative to the employee's name
            }

            if(headNodeEmployeeListByAge == null || headNodeEmployeeListByAge.listOfEmployeesPointer == null)
            //if the list is less than two nodes long
            { 
                headNodeEmployeeListByAge = appendEmployee(headNodeEmployeeListByAge, employeeNodeByAge);
                //append the node we want to insert
            }
            else
            //if we have a list of more than two nodes       
            {
                headNodeEmployeeListByAge = insertEmployeeByAge(headNodeEmployeeListByAge, employeeNodeByAge);
                //insert the employee relative to the employee's age
            }
           
            if(headNodeEmployeeListBySalary == null || headNodeEmployeeListBySalary.listOfEmployeesPointer == null)
             //if the list is less than two nodes long
            {
               headNodeEmployeeListBySalary = appendEmployee(headNodeEmployeeListBySalary, employeeNodeBySalary);
               //append the node we want to insert
            }
            else
            //if we have a list of more than two nodes 
            {
                headNodeEmployeeListBySalary = insertEmployeeBySalary(headNodeEmployeeListBySalary, employeeNodeBySalary);
                //insert the employee relative to the employee's salary
            }
            
        }
        MasterEmployeeNode masterHead = new MasterEmployeeNode(headNodeEmployeeListChronological, headNodeEmployeeListByName, headNodeEmployeeListByAge, headNodeEmployeeListBySalary);
        //after we've constructed and oriented the lists, create a master head containing the heads of all four lists then return it
        return masterHead;
    }
    
    
    public static void menuOptions(MasterEmployeeNode masterHead)
    {
        Object[] optionTextButtonsFirst = {"Add","Display","Exit"};
        int getOptionNumber = JOptionPane.showOptionDialog(null, "Select an action.", "Required Input", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionTextButtonsFirst, optionTextButtonsFirst[0]); 
        //simple menu with buttons, asks the user if they want to add a new employee to the lists, display the lists, or exit the program
        switch(getOptionNumber)
        {
            case 0:
            //if the user selects the "add" button
                 Employee getNewEmployee = createSingleEmployee();
                 //create a single new employee
                 Employee getNewEmployeeChronological = new Employee(getNewEmployee.returnEmployeeName(),getNewEmployee.returnEmployeeAge(),getNewEmployee.returnEmployeeSalary());
                 Employee getNewEmployeeByName = new Employee(getNewEmployee.returnEmployeeName(),getNewEmployee.returnEmployeeAge(),getNewEmployee.returnEmployeeSalary());
                 Employee getNewEmployeeByAge = new Employee(getNewEmployee.returnEmployeeName(),getNewEmployee.returnEmployeeAge(),getNewEmployee.returnEmployeeSalary());
                 Employee getNewEmployeeBySalary = new Employee(getNewEmployee.returnEmployeeName(),getNewEmployee.returnEmployeeAge(),getNewEmployee.returnEmployeeSalary());  
                 //for each list, we will create an object that contains the same content(name, age, salary) as getNewEmployee 
                 masterHead.masterPointerChronological = appendEmployee(masterHead.returnHeadNodeChronological(), getNewEmployeeChronological);

                if(masterHead.masterPointerByName == null && masterHead.masterPointerByAge == null && masterHead.masterPointerBySalary == null)
                //if we're given no nodes in all lists
                {
                    masterHead.masterPointerByName = appendEmployee(masterHead.returnHeadNodeByName(), getNewEmployeeByName);
                    masterHead.masterPointerByAge = appendEmployee(masterHead.returnHeadNodeByAge(), getNewEmployeeByAge);
                    masterHead.masterPointerBySalary = appendEmployee(masterHead.returnHeadNodeBySalary(), getNewEmployeeBySalary);
                    //append each employee onto the ends of the list
                }
                else
                //if we have any amount of nodes on each list
                {
                    masterHead.masterPointerByName = insertEmployeeByName(masterHead.returnHeadNodeByName(), getNewEmployeeByName);
                    masterHead.masterPointerByAge = insertEmployeeByAge(masterHead.returnHeadNodeByAge(), getNewEmployeeByAge);
                    masterHead.masterPointerBySalary = insertEmployeeBySalary(masterHead.returnHeadNodeBySalary(), getNewEmployeeBySalary);
                    //insert the employee relative to the list
                }
                //then insert each object that was created into their repsective lists and orient them relative to the list
                 menuOptions(masterHead);
                //reference the menu after we've finished adding the new employee 
            break;
           
            case 1:
                displayAllLists(masterHead);
                //reference the display method while passing the master head
            break;
         
            case 2:
            //exits, does nothing and breaks out of the loop
            break;
        }
    }
    
    public static Employee appendEmployee(Employee EmployeeListHeadNode, Employee appendThisEmployee)
    //this method appends a given employee given a list head, returns the head of the list
    {
        Employee tempHeadHandle = EmployeeListHeadNode;
        //create a handler pointer to prevent losing integrity of the list      
        if(tempHeadHandle == null)
        //if the head isn't pointing to anything
        {
            return appendThisEmployee; 
            //the head of the list will become what was supposed to be appended
        }
        else
        //if we have a list with any given number of nodes
        {
            while(tempHeadHandle.listOfEmployeesPointer != null)
            //continue operating as long as we're not one node away from the end of the list
            {
                tempHeadHandle = tempHeadHandle.listOfEmployeesPointer;
                //traverse down the list
            }
            //once we're at the last node of the list
            tempHeadHandle.listOfEmployeesPointer = appendThisEmployee;
            //make the last node point to what we want to append
            appendThisEmployee.listOfEmployeesPointer = null;
            //to prevent circular lists, make the appended employee point to nothing
        }    
        return EmployeeListHeadNode;  
        //return the head of the list
    }
    
    public static Employee insertEmployeeByName(Employee EmployeeListFrontNode, Employee insertThisEmployee)
    {
    //using the lock-step method, this method inserts an employee relative to the employee's name, the list is oriented from a-z
        Employee tempHeadHandle = EmployeeListFrontNode;
        Employee tempSecondHandle = null;
        //make a handler pointer to prevent losing the head of the list and a second node for insertion purposes
        if((tempHeadHandle.returnEmployeeName()).compareTo(insertThisEmployee.returnEmployeeName()) > 0)
        //if the head employee's name is further down in the alphabet than that of the employee we're inserting
        {
            insertThisEmployee.listOfEmployeesPointer = tempHeadHandle;
            //make the employee we want to insert point to the head
            EmployeeListFrontNode = insertThisEmployee;
            //make the new head of the the list the inserted employee
        }    
        else
        //if the employee's name is further down the alphabet than the current head
        {
        boolean ifStateChange = false;   
        //create a boolean that keeps track of changes made in the list
        do
            {
                if(((insertThisEmployee.returnEmployeeName()).compareTo(tempHeadHandle.returnEmployeeName()) < 0))
                //if the employee's name comes before the current node
                {
                    insertThisEmployee.listOfEmployeesPointer = tempHeadHandle;
                    //make the employee node point to the current node
                    tempSecondHandle.listOfEmployeesPointer = insertThisEmployee;
                    //make the previous node point to the newly inserted node
                    ifStateChange = true;
                    //we made a change in the list
                }
                else 
                //if the employee's name comes after the current node
                {
                    tempSecondHandle = tempHeadHandle;
                    tempHeadHandle = tempHeadHandle.returnListOfEmployees();  
                    //traverse down the list by one node
                }
            
            }while(tempHeadHandle != null && ifStateChange == false);
            //continue looping while we're not at the end of the list and there hasn't been any change in the list
        if(ifStateChange == false)
        //if we got to the end of the list and made no changes
            {
                tempSecondHandle.listOfEmployeesPointer = insertThisEmployee;
                //put the employee at the end of the list
            }
        }    
    return EmployeeListFrontNode;
    //return the head of the list  
    }
    
    public static Employee insertEmployeeByAge(Employee EmployeeListFrontNode, Employee insertThisEmployee)
    {
    //using the look-ahead method, this method inserts an employee relative to the employee's age, the list is oriented from 0 to ∞
        Employee tempHeadHandle = EmployeeListFrontNode;
        //make a handler pointer to prevent losing the head of the list
        boolean ifStateChange = false;
        //becomes true if we make a change in the list
        if(tempHeadHandle.returnEmployeeAge() >= insertThisEmployee.returnEmployeeAge())
        //if the head of the list is greater than what we're inserting
        {
            insertThisEmployee.listOfEmployeesPointer = tempHeadHandle;
            //make the employee we're inserting point at the head 
            EmployeeListFrontNode = insertThisEmployee;
            //make the new head what we're inserting
            ifStateChange = true;
            //we did change something in the list
        }
        else if(EmployeeListFrontNode.listOfEmployeesPointer == null)
        //if we're inserting into a list with only one node
        {
            appendEmployee(tempHeadHandle, insertThisEmployee);
            //append the employee we're inserting
        }
        else
        {
            do
            {
                if((insertThisEmployee.returnEmployeeAge() >= tempHeadHandle.returnEmployeeAge()) &&(insertThisEmployee.returnEmployeeAge() <= tempHeadHandle.listOfEmployeesPointer.returnEmployeeAge()))
                //if the inserting employee's age is greater than the current node
                {
                    insertThisEmployee.listOfEmployeesPointer = tempHeadHandle.returnListOfEmployees();
                    //the employee we're inserting will now point at the next node
                    tempHeadHandle.listOfEmployeesPointer = insertThisEmployee;
                    //the current node will now point at the inserted employee
                    ifStateChange = true;
                    //we did change something in the list
                }
                else
                {
                    tempHeadHandle = tempHeadHandle.returnListOfEmployees();
                    //traverse down the list
                }
            }
            while(tempHeadHandle == null && ifStateChange == false);
            //if we're not at the end of the list and we didn't change anything in the list, continue to loop
             
            if(ifStateChange == false)
            //if we're at the end of the list and we didn't make any changes to the list
            {
               tempHeadHandle.listOfEmployeesPointer = insertThisEmployee;
               //the node we're trying to insert must go at the end of the list
            }
        }
    return EmployeeListFrontNode;
    //return the head of the list
    }
    
    public static Employee insertEmployeeBySalary(Employee EmployeeListFrontNode, Employee insertThisEmployee)
        {
        //using the look-ahead method, this method inserts an employee relative to the employee's salary, the list is oriented from 0 to ∞
        Employee tempHeadHandle = EmployeeListFrontNode;
        //make a handler pointer to prevent losing the head of the list
        boolean ifStateChange = false;
       //becomes true if we make a change in the list

        if(tempHeadHandle.returnEmployeeSalary() >= insertThisEmployee.returnEmployeeSalary())
        //if the head of the list is greater than what we're inserting
        {
            insertThisEmployee.listOfEmployeesPointer = tempHeadHandle;
            //make the employee we're inserting point at the head 
            EmployeeListFrontNode = insertThisEmployee;
            //make the new head what we're inserting
            ifStateChange = true;
            //we did change something in the list
        }
        else if(EmployeeListFrontNode.listOfEmployeesPointer == null)
        //if we're inserting into a list with only one node
        {
            appendEmployee(tempHeadHandle, insertThisEmployee);
            //append the employee we're inserting
        }
        else
        {
            do
            {
                if((insertThisEmployee.returnEmployeeSalary() >= tempHeadHandle.returnEmployeeSalary()) &&(insertThisEmployee.returnEmployeeSalary() <= tempHeadHandle.listOfEmployeesPointer.returnEmployeeSalary()))
                //if the inserting employee's age is greater than the current node
                {
                    insertThisEmployee.listOfEmployeesPointer = tempHeadHandle.returnListOfEmployees();
                    //the employee we're inserting will now point at the next node
                    tempHeadHandle.listOfEmployeesPointer = insertThisEmployee;
                    //the current node will now point at the inserted employee
                    ifStateChange = true;
                    //we did change something in the list
                }
                else
                {
                    tempHeadHandle = tempHeadHandle.returnListOfEmployees();
                    //traverse down the list
                }
            }
            while(tempHeadHandle == null && ifStateChange == false);
            //if we're not at the end of the list and we didn't change anything in the list, continue to loop
             
            if(ifStateChange == false)
            //if we didn't make any changes to the list
            {
               tempHeadHandle.listOfEmployeesPointer = insertThisEmployee;
               //the node we're trying to insert must go at the end of the list
            }
        }
    return EmployeeListFrontNode;   
    //return the head of the list
    }   
    
    public static void displayAllLists(MasterEmployeeNode masterHead)
    {       
        String finalPrintChronologicalList = "";
        String finalPrintByNameList = "";
        String finalPrintByAgeList = "";
        String finalPrintBySalaryList = "";
        //create strings that will contain all the content of each list
        Object[] optionTextButtonsPrint = {"Chronological","By Name","By Age","By Salary","Exit"};
        int getOptionTextButtonsPrint = JOptionPane.showOptionDialog(null, "Which list would you like to see?", "Required Input", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionTextButtonsPrint, optionTextButtonsPrint[0]);
        //A menu that prompts the user for which list they want to see
        switch(getOptionTextButtonsPrint)
        {
            case 0:
            //if the user wants to see the chronological list
            for(Employee tempHeadChronological = masterHead.returnHeadNodeChronological(); tempHeadChronological != null; tempHeadChronological = tempHeadChronological.listOfEmployeesPointer)
            //create a handler pointer, keep looping until we're not at the end of the list, and after each repition traverse the list
            {
                finalPrintChronologicalList = finalPrintChronologicalList + tempHeadChronological.returnEmployeeName() + "\nAge: " + tempHeadChronological.returnEmployeeAge() + "\nSalary: " + tempHeadChronological.returnEmployeeSalary() + "\n\n";
                //form a string that has all data relating to the current node in that list
            }
            JOptionPane.showMessageDialog(null,"Chronological List: \n"+finalPrintChronologicalList);
            //print out the information
            menuOptions(masterHead);
            //go back to the menu
            break;
    
            case 1:
            //if the user wants to see the name list
            for(Employee tempHeadByName = masterHead.returnHeadNodeByName(); tempHeadByName != null; tempHeadByName = tempHeadByName.listOfEmployeesPointer)
            //create a handler pointer, keep looping until we're not at the end of the list, and after each repition traverse the list
            {
                finalPrintByNameList = finalPrintByNameList + tempHeadByName.returnEmployeeName() + "\nAge: " + tempHeadByName.returnEmployeeAge() + "\nSalary: " + tempHeadByName.returnEmployeeSalary() + "\n\n";
                //form a string that has all data relating to the current node in that list
            }
            JOptionPane.showMessageDialog(null,"List By Name\n" + finalPrintByNameList);
            //print out the information
            menuOptions(masterHead);
            //go back to the menu    
            break;
            
            case 2:
            //if the user wants to see the age list
            for(Employee tempHeadByAge = masterHead.returnHeadNodeByAge(); tempHeadByAge != null; tempHeadByAge = tempHeadByAge.listOfEmployeesPointer)
            //create a handler pointer, keep looping until we're not at the end of the list, and after each repition traverse the list
            {
                finalPrintByAgeList = finalPrintByAgeList + tempHeadByAge.returnEmployeeName() + "\nAge: " + tempHeadByAge.returnEmployeeAge() + "\nSalary: " + tempHeadByAge.returnEmployeeSalary() + "\n\n";
                //form a string that has all data relating to the current node in that list
            }
            JOptionPane.showMessageDialog(null,"List By Age\n" + finalPrintByAgeList);
            menuOptions(masterHead);
            break;
            
            case 3:
            //if the user wants to see the salary list
            for(Employee tempHeadBySalary = masterHead.returnHeadNodeBySalary(); tempHeadBySalary != null; tempHeadBySalary = tempHeadBySalary.listOfEmployeesPointer)
            //create a handler pointer, keep looping until we're not at the end of the list, and after each repition traverse the list
            {
                finalPrintBySalaryList = finalPrintBySalaryList + tempHeadBySalary.returnEmployeeName() + "\nAge: " + tempHeadBySalary.returnEmployeeAge() + "\nSalary: " + tempHeadBySalary.returnEmployeeSalary() + "\n\n";
                //form a string that has all data relating to the current node in that list
            }
            JOptionPane.showMessageDialog(null, "List by Salary\n" + finalPrintBySalaryList);
             //print out the information
            menuOptions(masterHead);
            //go back to the menu
            break;
            
            case 4:
            //exits, does nothing then breaks out of the loop
            break;
        }
    }
}
