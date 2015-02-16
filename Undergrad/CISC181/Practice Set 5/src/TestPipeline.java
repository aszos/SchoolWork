package ps5;
import junit.framework.TestCase;


public class TestPipeline extends TestCase {
    private static Pipeline create4() {
        Pipeline p = new Pipeline();
        p.addAtEnd(new PipelineSegment(4, 3, 2));
        p.addAtEnd(new PipelineSegment(3, 3, 1));
        p.addAtEnd(new PipelineSegment(1, 1, 3));
        p.addAtEnd(new PipelineSegment(2, 3, 1));
        
        return p;
    }
    
    // creates a Pipeline of length n made from n Pipeline instances
    private static Pipeline createN(int n) {
        Pipeline p = new Pipeline();
        for (int i = 0; i < n; i++) {
            p.addAtEnd(new PipelineSegment(1, 1, 1));
        }
        return p;
    }
    
    public void test_getTotalLength() {
        Pipeline p = create4();
        assertEquals(10, p.getTotalLength());
        assertEquals(6, p.getSubPipeline(1).getTotalLength());
        assertEquals(3, p.getSubPipeline(2).getTotalLength());
        
        int q = (int)(Math.random() * 1000);
        assertEquals(q, createN(q).getTotalLength());
    }
    
    public void test_hasLeak() {
        Pipeline p = create4();
        assertTrue(p.hasLeak());
        assertFalse(p.getSubPipeline(1).hasLeak());
        
        assertFalse(createN(1).hasLeak());

        int q = (int)(Math.random() * 1000);
        assertFalse(createN(q).hasLeak());
    }
    
    public void test_getMaxFlow() {
        Pipeline p = create4();
        assertEquals(1, p.getMaxFlow());
        assertEquals(1, createN(1).getMaxFlow());
        assertEquals(1, createN(5).getMaxFlow());
        
        Pipeline a = new Pipeline();
        a.addAtEnd(new PipelineSegment(1, 5, 5));
        a.addAtEnd(new PipelineSegment(1, 10, 10));
        assertEquals(5, a.getMaxFlow());
        
        Pipeline b = new Pipeline();
        b.addAtEnd(new PipelineSegment(1, 10, 10));
        b.addAtEnd(new PipelineSegment(1, 5, 5));
        assertEquals(5, b.getMaxFlow());
    }
}
