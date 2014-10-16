import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DequeResizingTest {
	
	private DequeI<String> deqStack;
	
	@Before
    public void setup()
    {
		deqStack = new DequeResizing<String>();
    }

	@Test
	public void testIsEmpty() {
		assertTrue(deqStack.isEmpty());
		deqStack.offerFront("element");
		assertFalse(deqStack.isEmpty());
		deqStack.pollFront();
		assertTrue(deqStack.isEmpty());
	}
	
	@Test
	public void testSize() {
		assertTrue(deqStack.size() == 0);
		deqStack.offerFront("element");
		assertTrue(deqStack.size() == 1);
		deqStack.pollFront();
		assertTrue(deqStack.size() == 0);
	}
	
	@Test
	public void testOfferFront() {
		assertTrue(deqStack.size() == 0);
		deqStack.offerFront("element Uno");
		assertTrue(deqStack.size() == 1);
		deqStack.offerFront("element Dos");
		assertTrue(deqStack.size() == 2);
	}

}
