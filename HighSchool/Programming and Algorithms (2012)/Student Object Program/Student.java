
public class Student
{
private int studentID;

private HealthInfo healthObject;
private PersonalInfo personalObject;
private StudentEducationInfo educationObject;	
private String tempName;

	public Student(int studentID)
	{
		healthObject = new HealthInfo(studentID);
		tempName = healthObject.getStudentName();
		personalObject = new PersonalInfo(studentID,tempName);
		educationObject = new StudentEducationInfo(studentID,tempName);	
		this.studentID = studentID;
	}
	//when a Student object is instatiated, healthObjects, personalObjects, and educationObjects are instatiated
	//the student name is placed among all objects in order to reduce amount of prompts
	
	public int getStudentID()
	{
		return studentID;
	}
	
	public String getStudentName()
	{
		return tempName;
	}
	
	public HealthInfo getHealthInfo()
	{
		return healthObject;	
	}
	
	public PersonalInfo getPersonalInfo()
	{
		return personalObject;
	}
	
	public StudentEducationInfo getEducationInfo()
	{
		return educationObject;
	}

}