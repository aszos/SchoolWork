import java.util.Random;
import edu.udel.jatlas.gameframework.Position;

/*
 * Created by Alexander Szostek
 * Created on March 14th, 2014
 * Problem Set #3
 * */

public class Cat extends Pet {
    private Random randomGenerator;
    
    public Cat(Position position, Random random, boolean tagged) {
    	super(position, tagged);
    	this.randomGenerator = random;
    }
        
    public int getMovementPattern()
    {
    	return randomGenerator.nextInt(3) - 1;
    }
}
