/*
Created by Alexander Szostek
Completed on November 8th, 2012
Data Structures 

Program Description:
This program is designed to manage four lists, all containing employee information, oriented relative to employee name(string), age(integer), 
and salary(double). First, the user is asked how many employees they are given, they then enter in information about each employee. From there, 
the program creates four lists oriented in chronological order, alphabetical order, numerical (age and salary) order. Once this is completed,
the user can add another employee to the lists, display either one of the lists, or exit the program.

Variable Dictionary:
int age - stores the age of the employee 
Employee listOfEmployeesPointer - stores the location of the next node on the list
String name - stores the name of the employee
int modifyAgeToThis - stores the desired change for age
String modifyNameToThis - stores the desired change for name
double modifySalaryToThis - stores the desired change for salary
double salary - stores the salary of the employee
*/
public class Employee
{
    private String name;
    private int age;
    private double salary;
    Employee listOfEmployeesPointer;

    public Employee(String name, int age, double salary)
    {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    
    public String returnEmployeeName()
    {
        return name;
    }
    
    public int returnEmployeeAge()
    {
        return age;
    }
    
    public double returnEmployeeSalary()
    {
        return salary;
    }
    
    public void modifyEmployeeName(String modifyNameToThis)
    {
        name = modifyNameToThis;
    }

    public void modifyEmployeeAge(int modifyAgeToThis)
    {
        age = modifyAgeToThis;
    }
    
    public void modifyEmployeeSalary(double modifySalaryToThis)
    {
        salary = modifySalaryToThis;
    } 
}
