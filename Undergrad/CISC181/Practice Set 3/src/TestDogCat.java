import java.util.Random;
import edu.udel.jatlas.gameframework.Position;
import junit.framework.TestCase;

/*
 * Created by Alexander Szostek
 * Created on March 14th, 2014
 * Problem Set #3
 * */

public class TestDogCat extends TestCase {
    public void test_DogCat() {
        Random r = new Random(0); // set seed so this generates same sequence every test
        Dog dog = new Dog(new Position(3, 4), true);
        Cat cat = new Cat(new Position(0, 0), r, false);
        cat.setFriend(dog);
        dog.setFriend(cat);
        
        // dog chases cat
        assertEquals(7, dog.getPosition().blockDistance(cat.getPosition()));
        dog.move(); // dog moves towards cat
        assertEquals(5, dog.getPosition().blockDistance(cat.getPosition()));
        dog.move(); // dog moves towards cat
        assertEquals(3, dog.getPosition().blockDistance(cat.getPosition()));
        dog.move(); // dog moves towards cat
        assertEquals(1, dog.getPosition().blockDistance(cat.getPosition()));
        dog.move(); // dog moves to same space as cat
        assertEquals(0, dog.getPosition().blockDistance(cat.getPosition()));
        assertFalse(dog.isTagged());
        assertTrue(cat.isTagged());
        
        // dog runs away
        dog.move();
        dog.move();
        dog.move();
        
        // cat's turn to chase dog
        assertEquals(6, dog.getPosition().blockDistance(cat.getPosition()));
        cat.move(); // cat moves towards dog
        assertEquals(4, dog.getPosition().blockDistance(cat.getPosition()));
        cat.move(); // cat moves towards dog
        assertEquals(2, dog.getPosition().blockDistance(cat.getPosition()));
        cat.move(); // cat moves to same space as dog
        assertEquals(0, dog.getPosition().blockDistance(cat.getPosition()));
        assertFalse(cat.isTagged());
        assertTrue(dog.isTagged());
        
        // cat runs away with "random movement"
        cat.move();
        cat.move();
        cat.move();
        cat.move();
        cat.move();
        cat.move();
        
        // dog chases cat again
        assertEquals(4, dog.getPosition().blockDistance(cat.getPosition()));
        dog.move(); // dog moves towards cat
        assertEquals(2, dog.getPosition().blockDistance(cat.getPosition()));
        dog.move(); // dog moves towards cat
        assertEquals(1, dog.getPosition().blockDistance(cat.getPosition()));
        dog.move(); // dog moves to same space as cat
        assertEquals(0, dog.getPosition().blockDistance(cat.getPosition()));
        assertFalse(dog.isTagged());
        assertTrue(cat.isTagged());
    }
}
