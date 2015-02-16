package SummerWork;

public class ManyMethodsInstance {
	private String girlName; // View Notes #1
	public void SetName(String Name) {
		girlName = Name;		
	}	
	public String getName(){// View Note #2
		return girlName;
	}
	public void saying(){
		System.out.printf("Your first girlfriend's name was %s" , getName() + "."); //View Note #3
	}	
}
//Created by Alexander Szostek on August 11th, 2011\\
// Note #1: Notice this line has "private" instead of "public". Private means that the methods within this class can utilize these methods/classes.
// Note #2: Notice the location of the "String." This location is called the "Return type." Whatever your method does, this will return a string. Void means that the method will return nothing.
// Note #3: Notice it says printf instead of println. This is not different, but it allows methods to be implemented into the print.