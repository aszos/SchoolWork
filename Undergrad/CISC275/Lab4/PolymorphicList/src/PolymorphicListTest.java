import junit.framework.TestCase;


public class PolymorphicListTest extends TestCase {
	PolymorphicList<Integer> p1 = new PolymorphicList<Integer>();
	PolymorphicList<Integer> p2 = new PolymorphicList<Integer>();

	// note that if your add method does not work, it will be hard to test the other methods
	protected void setUp() throws Exception {
		p1.add(5);
		p1.add(6);
		p1.add(12);
		p1.add(3);
	}
	
	public void test_contains() {
		assertTrue(p1.contains(5));
		assertTrue(p1.contains(3));
		assertTrue(p1.contains(12));
		assertFalse(p1.contains(7));
		assertFalse(p2.contains(5));
	}
	
	public void test_size() {
		assertEquals(4, p1.size());
		assertEquals(0, p2.size());
	}
	
	public void test_foreach() {
		int sum_p1 = 0;
		for (Integer value : p1) {
			sum_p1 += value;
		}
		assertEquals(26, sum_p1);

		int sum_p2 = 0;
		for (Integer value : p2) {
			sum_p2 += value;
		}
		assertEquals(0, sum_p2);
	}
}
