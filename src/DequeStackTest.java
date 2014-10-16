import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DequeStackTest {
	
	private DequeStack<String> deqStack;
	
	@Before
    public void setup()
    {
		deqStack = new DequeStack<String>();
    }

	@Test
	public void testDequeStack() {
		assertNotNull(deqStack);
	}

	@Test
	public void testPush() {
		deqStack.push("item uno");
		assertEquals("item uno", deqStack.peek());
		assertFalse(deqStack.empty());
	}

	@Test
	public void testPopAndPeek() {
		deqStack.push("item uno");
		deqStack.push("item dos");
		assertEquals("item dos", deqStack.peek());
		assertEquals("item dos", deqStack.pop());
		assertFalse(deqStack.empty());
		assertEquals("item uno", deqStack.peek());
		assertEquals("item uno", deqStack.pop());
		assertTrue(deqStack.empty());
	}

}
