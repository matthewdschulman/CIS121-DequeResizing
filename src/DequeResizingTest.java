import static org.junit.Assert.*;

import org.junit.Test;


public class DequeResizingTest {

	@Test
	public void testIsEmpty() {
		DequeI<String> deqStack = new DequeResizing<String>();
		assertTrue(deqStack.isEmpty());
		assertEquals(5,5);
	}

}
