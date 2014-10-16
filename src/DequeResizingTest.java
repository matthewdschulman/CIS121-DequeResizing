import static org.junit.Assert.*;

import java.util.Iterator;

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
		assertEquals(2, deqStack.size());
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
		assertEquals(3, deqStack.size());
	}
	
	@Test
	public void testPollFront() {
		deqStack.offerBack("element Uno");
		deqStack.offerBack("element Dos");
		deqStack.offerBack("element Tres");
		assertEquals("element Uno",deqStack.pollFront());
		assertEquals(2,deqStack.size());
		assertEquals("element Dos", deqStack.peekFront());
		deqStack.pollFront();
		deqStack.pollFront();
		assertEquals(0, deqStack.size());
	}
	
	@Test
	public void testPollBack() {
		deqStack.offerBack("element Uno");
		deqStack.offerBack("element Dos");
		deqStack.offerBack("element Tres");
		assertEquals("element Tres",deqStack.pollBack());
		assertEquals(2,deqStack.size());
		assertEquals("element Dos", deqStack.peekBack());
		deqStack.pollBack();
		deqStack.pollBack();
		assertEquals(0, deqStack.size());
	}
	
	@Test
	public void testIterators() {
		deqStack.offerBack("element Uno");
		deqStack.offerBack("element Dos");
		
		//first test forward Iterators
		Iterator<String> forwardIterator = deqStack.iterator();
		assertTrue(forwardIterator.hasNext());
		forwardIterator.next();
		assertTrue(forwardIterator.hasNext());
		forwardIterator.next();
		assertFalse(forwardIterator.hasNext());
		
		//next test Backward Iterators
		Iterator<String> backwardIterator = deqStack.iterator();
		assertTrue(backwardIterator.hasNext());
		backwardIterator.next();
		assertTrue(backwardIterator.hasNext());
		backwardIterator.next();
		assertFalse(backwardIterator.hasNext());
	}

}
