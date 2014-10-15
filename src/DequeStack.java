import java.util.EmptyStackException;

/**
 * Most of the Javadocs here were lifted from the Javadocs of the Stack class of
 * OpenJDK 6.
 *
 * @author Max Scheiber (scheiber), 14fa
 */

public class DequeStack<E> {
	private DequeI<E> deqStack;
	
    public DequeStack() {
        deqStack = new DequeResizing<E>();
    }

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param item
     *            the item to be pushed onto this stack
     * @return the item argument
     */
    public E push(E item) {
        deqStack.offerFront(item);
        return item;
    }

    /**
     * Removes the object at the top of this stack and returns that object as
     * the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException
     *             if this stack is empty
     */
    public E pop() {
    	if (empty()) {
    		throw new EmptyStackException();
    	}
    	return deqStack.pollFront();
    }

    /**
     * Looks at the object at the top of this stack without removing it from the
     * stack.
     *
     * @return the object at the top of this stack
     * @throws EmptyStackException
     *             if this stack is empty
     */
    public E peek() {
        return deqStack.peekFront();
    }

    /**
     * @return true if and only if the stack has no items, false otherwise
     */
    public boolean empty() {
        return deqStack.isEmpty();
    }
}