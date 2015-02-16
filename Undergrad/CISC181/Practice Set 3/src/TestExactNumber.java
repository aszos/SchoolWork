import junit.framework.TestCase;

/*
 * Created by Alexander Szostek
 * Created on March 14th, 2014
 * Problem Set #3
 * */

public class TestExactNumber extends TestCase {
    ExactNumber threesevenfive = new ExactNumber(3, 7500000000000000L);
    ExactNumber threesevenfive_andalittlebit = new ExactNumber(3, 7500000000000001L);
    ExactNumber threesevenfive_dupe = new ExactNumber(3, 7500000000000000L);
    ExactNumber ten = new ExactNumber(10, 0);
    ExactNumber thirteensevenfive = new ExactNumber(13, 7500000000000000L);
    ExactNumber sevenfifty = new ExactNumber(7, 5000000000000000L);
    

    
    public void test_equals() {
        assertFalse(threesevenfive.equals(threesevenfive_andalittlebit));
        assertEquals(threesevenfive, threesevenfive_dupe);
    }
    
    
    public void test_compareTo() {
        assertEquals(threesevenfive.compareTo(threesevenfive), 0);
        assertEquals(threesevenfive.compareTo(threesevenfive_dupe), 0);
        assertEquals(threesevenfive.compareTo(threesevenfive_andalittlebit), -1);
        assertEquals(threesevenfive_andalittlebit.compareTo(threesevenfive), 1);
        assertEquals(threesevenfive.compareTo(ten), -1);
        assertEquals(ten.compareTo(threesevenfive), 1);
    }
    
    
    public void test_doubleValue() {
        assertEquals(3.75, threesevenfive.doubleValue(), 0.00000001);
    }
    
    
    public void test_add() {
        assertEquals(threesevenfive.add(ten), thirteensevenfive);
        assertEquals(threesevenfive.add(threesevenfive), sevenfifty);
    }
    
    
    public void test_toString() {
        assertEquals(threesevenfive.toString(), Double.toString(3.75));
        assertEquals(ten.toString(), Double.toString(10.0));
        assertEquals(sevenfifty.toString(), Double.toString(7.50));

    }
}
