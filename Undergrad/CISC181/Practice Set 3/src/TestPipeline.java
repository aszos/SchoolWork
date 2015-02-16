import junit.framework.TestCase;

/*
 * Created by Alexander Szostek
 * Created on March 14th, 2014
 * Problem Set #3
 * */

public class TestPipeline extends TestCase {
    private static Pipeline create4() {
        Pipeline d = new Pipeline(2, 3, 1, null);
        Pipeline c = new Pipeline(1, 1, 3, d);
        Pipeline b = new Pipeline(3, 3, 1, c);
        Pipeline a = new Pipeline(4, 3, 2, b);
        return a;
    }
    
    // creates a Pipeline of length n made from n Pipeline instances
    private static Pipeline createN(int n) {
        Pipeline p = null;
        for (int i = 0; i < n; i++) {
            p = new Pipeline(n, n, n, p);
        }
        return p;
    }
    
    public void test_getTotalLength() {
        Pipeline p = create4();
        assertEquals(10, p.getTotalLength());
        assertEquals(6, p.getNext().getTotalLength());
        assertEquals(3, p.getNext().getNext().getTotalLength());
        
        int q = (int)(Math.random() * 1000);
        assertEquals(q*q, createN(q).getTotalLength());
    }
    
    public void test_hasLeak() {
        Pipeline p = create4();
        assertTrue(p.hasLeak());
        assertFalse(p.getNext().hasLeak());
        
        assertFalse(createN(1).hasLeak());

        int q = (int)(Math.random() * 1000);
        assertFalse(createN(q).hasLeak());
    }
    
    public void test_getMaxFlow() {
        Pipeline p = create4();
        assertEquals(1, p.getMaxFlow());
        assertEquals(1, createN(1).getMaxFlow());
        assertEquals(5, createN(5).getMaxFlow());
        
        Pipeline a = new Pipeline(1, 5, 5, new Pipeline(1, 10, 10, null));
        assertEquals(5, a.getMaxFlow());
        
        Pipeline b = new Pipeline(1, 10, 10, new Pipeline(1, 5, 5, null));
        assertEquals(5, b.getMaxFlow());
    }
    
    public void test_replaceEnd() {
        Pipeline p = create4();
        Pipeline a = new Pipeline(1, 5, 5);
        p.replaceEnd(a);
        assertSame(a, p.getNext().getNext().getNext());
    }
}
