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
	public void testOfferFrontAndContains() {
		assertFalse(deqStack.contains("element"));
		assertTrue(deqStack.size() == 0);
		deqStack.offerFront("element Uno");
		assertTrue(deqStack.size() == 1);
		deqStack.offerFront("element Dos");
		assertTrue(deqStack.size() == 2);
		//element Dos should be at top of stack
		assertEquals("element Dos",deqStack.peekFront());
		assertTrue(deqStack.contains("element Dos"));
		assertFalse(deqStack.contains("element"));
	}
	
	@Test
	public void testOfferBackAndPeeking() {
		assertTrue(deqStack.size() == 0);
		deqStack.offerBack("element Uno");
		assertTrue(deqStack.size() == 1);
		deqStack.offerBack("element Dos");
		assertTrue(deqStack.size() == 2);
		//element Uno should be at top of stack
		assertEquals("element Uno", deqStack.peekFront());
		assertEquals("element Dos", deqStack.peekBack());
		deqStack.offerBack("element Tres");
		assertEquals("element Tres", deqStack.peekBack());
	}

}
