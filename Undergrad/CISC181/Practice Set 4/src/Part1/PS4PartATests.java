package Part1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import junit.framework.TestCase;

/**
 * Note, some of these tests will look weird because
 * they are testing whether or not your class definitions are correct.
 * Because it is difficult to test things that are NOT supposed to happen,
 * I use the Java reflection package that will test whether certain things
 * FAIL when trying to create objects or make assignments.
 * 
 * You should NOT need to change any of these tests.
 * 
 * @author jatlas
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PS4PartATests extends TestCase {
    
    
    
    public void test_Fruit() {
        // Fruit has a constructor that takes a String and a double
        // but we should not be able to create a Fruit
        try {
            Class<Fruit> clazz = Fruit.class;
            Constructor<Fruit> ctor = clazz.getDeclaredConstructor(String.class, Double.TYPE);
            try {
                ctor.newInstance("Red", 5);
                fail("Should not be able to create an instance of Fruit");
            }
            catch (Exception e) {
                // should happen
            }
            
            Method m = clazz.getDeclaredMethod("getCalories", new Class[]{});
            assertEquals(Double.TYPE, m.getReturnType());
            assertTrue(Modifier.isAbstract(m.getModifiers()));
        }
        catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
   
    public void test_Orange() {
        Orange orange = new Orange(8);
        assertEquals(40.0, orange.getCalories(), 0.0001);
        assertTrue(orange instanceof Fruit);
        assertTrue(orange instanceof Edible);
    }
    
    public void test_Banana() {
        Banana banana = new Banana(5);
        Orange orange = new Orange(8);
        assertEquals(50.0, banana.getCalories(), 0.0001);
        assertTrue(banana instanceof Fruit);
        assertFalse(banana.getColor().equals(orange.getColor()));
        assertTrue(banana instanceof Edible);
    }

    public void test_NavalOrange() {
        NavalOrange navalOrange = new NavalOrange(8);
        assertEquals(44.0, navalOrange.getCalories(), 0.0001);
        assertTrue(navalOrange instanceof Orange);
        
        // testing Fruit equals method
        Orange orange = new Orange(8);
        NavalOrange navalOrange2 = new NavalOrange(8);
        assertEquals(orange, navalOrange);
        assertEquals(navalOrange2, navalOrange);
        assertEquals(orange, navalOrange2);
    }
    
    public void test_Edible() {
        try {
            // Edible should not have any constructor
            Class<Edible> clazz = Edible.class;
            Constructor<?>[] ctors = clazz.getConstructors();
            assertEquals(0, ctors.length);
            
            // but it should have a way to getCalories
            Method m = clazz.getMethod("getCalories", new Class[0]);
            assertNotNull(m);
            assertEquals(Double.TYPE, m.getReturnType());
        }
        catch (Exception e) {
            // not supposed to happen
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    public void test_IceCream() {
        IceCream ic = new IceCream();
        assertEquals(1000.0, ic.getCalories(), 0.0001);
        assertFalse(Fruit.class.isAssignableFrom(ic.getClass()));
        assertTrue(ic instanceof Edible);
    }
    
    public void test_Snowberry() {
        Snowberry sb = new Snowberry();
        assertEquals(0.117, sb.getWeight(), 0.0001);
        assertEquals("White", sb.getColor());
        assertFalse(sb instanceof Edible);
        assertTrue(sb instanceof Fruit);
    }
    
    public void test_CalorieComparator() {
        Orange orange = new Orange(8);
        Banana banana = new Banana(5);
        Orange reallyBigOrange = new Orange(200);
        IceCream ic = new IceCream();
        
        Comparator cc = new CalorieComparator();
        assertEquals(-1, cc.compare(orange, banana));
        assertEquals(1, cc.compare(banana, orange));
        assertEquals(0, cc.compare(reallyBigOrange, ic));
        
        assertTrue(Comparator.class.isAssignableFrom(CalorieComparator.class));
    }
    
    public void test_Restaurant() {
        Restaurant mcds = new Restaurant();
        mcds.addEdibleItem(new IceCream());
        mcds.addEdibleItem(new Orange(8));
        mcds.addEdibleItem(new NavalOrange(4));
        mcds.addEdibleItem(new Banana(6));
        mcds.addEdibleItem(new NavalOrange(100));
        
        // before ordering the menu by calories it should be in the same order as we added
        assertEquals("IceCream\nOrange\nNavalOrange\nBanana\nReallyBigNavalOrange\n", mcds.getMenu());
        
        mcds.orderByCalories();
        Edible previous = null;
        for (Edible ed : mcds.getItems()) {
            if (previous != null) {
                assertTrue(ed.getCalories() >= previous.getCalories());
            }
            previous = ed;
        }
        System.out.println(mcds.getMenu());
        assertEquals("NavalOrange\nOrange\nBanana\nReallyBigNavalOrange\nIceCream\n", mcds.getMenu());
    }
    

}
