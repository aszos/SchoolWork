import edu.udel.jatlas.gameframework.Position;

/*
 * Created by Alexander Szostek
 * Created on March 14th, 2014
 * Problem Set #3
 * */

public class Dog extends Pet {
    
    public Dog(Position position, boolean tagged) {
        super(position, tagged);
    }
    
    public int getMovementPattern()
    {
    	return 1;
    }  
}
