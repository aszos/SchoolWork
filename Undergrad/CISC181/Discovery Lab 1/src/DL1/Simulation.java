package DL1;

import java.util.Arrays;

import edu.udel.jatlas.gameframework.DirectionVector;
import edu.udel.jatlas.gameframework.Position;

public class Simulation 
{
    public static final double YEAR_SECONDS = 31557600;
    // constants to determine boundaries of universe to "draw" into String
    public static final double UNIVERSE_X = 400000000000.0;
    public static final double UNIVERSE_Y = 400000000000.0;

    
    
    public static void printSolarModel(SolarModel m)
	{
        // generates a "pretty" picture of the solar model
        // by creating a temporary grid of empty spaces
        // and setting the exact position of the sun and planet as
        // computed by their positions
        final int G = 20;
        char[][] grid = new char[G][G];
        for (char[] row : grid) 
        {
            Arrays.fill(row, ' ');
        }
        double midX = UNIVERSE_X/2.0;
        double midY = UNIVERSE_Y/2.0;
        grid[(int)(G*(m.getSun().getPosition().getY()+midY)/UNIVERSE_Y)]
                [(int)(G*(m.getSun().getPosition().getX()+midX)/UNIVERSE_X)] = 'S';
        grid[(int)(G*(m.getPlanet().getPosition().getY()+midY)/UNIVERSE_Y)]
                [(int)(G*(m.getPlanet().getPosition().getX()+midX)/UNIVERSE_X)] = 'P';
 
        if (m.getOtherPlanet() != null)
        {
            grid[(int)(G*(m.getOtherPlanet().getPosition().getY()+midY)/UNIVERSE_Y)]
                    [(int)(G*(m.getOtherPlanet().getPosition().getX()+midX)/UNIVERSE_X)] = 'P';
         }
        for (char[] row : grid) 
        {
            System.out.println(row);
        }
    }

    public static void main(String[] args) 
    {
        // one day per tick
        SolarModel sm = new SolarModel(60*60*24, new Sun(), 
            new Planet(5.97219E24, new Position(149600000000.0, 0), 
                new DirectionVector(0, 29786.80254518721)),
            new Planet(4.8678E24, new Position(1.082E11, 0), 
                new DirectionVector(0, 34810))); 
 
        for (long t = 0; t <= 365; t++) 
        {
            sm.onTick();
            System.out.println("SolarModel at tick " + t + ":");
            printSolarModel(sm);
            
            // forces the current Thread to wait
            // do not put this kind of code in any project logic
            // as it will cause bad things to happen in Android.
            // Keep it in its own simulation class (like this one)
            // as there will be a different way we call onTick
            // in Android.
            try 
            {
                Thread.sleep(25); // 1 / 40 of a second
            }
            catch (Exception e) 
            {}
        }
    }
}
