/**
 * @author Max Scheiber (scheiber), 14fa
 */
import java.util.Iterator;

public class DequeResizing<E> implements DequeI<E>, Iterable<E> {
	E[] elements;
	//note that size = backIndex - frontIndex
	private int frontIndex;
	private int backIndex;
	
    @SuppressWarnings("unchecked")
	public DequeResizing() {
    	//initialize initial array with size 2
        elements = (E[]) new Object[2];
        frontIndex = 0;
        backIndex = 0;
    }

    /**
     * @return true if the deque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        if ((backIndex - frontIndex) > 0) {
        	return true;
        }
        return false;
    }

    /**
     * @return the number of elements in the DequeI
     */
    @Override
    public int size() {
        return (backIndex - frontIndex);
    }

    /**
     * @param o
     * @return true if the deque contains o, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        for (int i = frontIndex; i <= backIndex; i++) {
        	if (elements[i] == o) {
        		return true;
        	}
        }
        return false;
    }

    /**
     * Adds the specified element at the front of the deque
     * 
     * @param elem
     */
    @Override
    public void offerFront(E elem) {
        // TODO: unimplemented
    }

    /**
     * Adds the specified element at the back of the deque
     * 
     * @param elem
     */
    @Override
    public void offerBack(E elem) {
        // TODO: unimplemented
    }

    /**
     * Removes and returns the first element of the deque, or returns null if
     * the deque is empty
     * 
     * @return the element removed
     */
    @Override
    public E pollFront() {
        // TODO: unimplemented
        return null;
    }

    /**
     * Removes and returns the last element of the deque, or returns null if the
     * deque is empty
     * 
     * @return the element removed
     */
    @Override
    public E pollBack() {
        // TODO: unimplemented
        return null;
    }

    /**
     * Returns, but does not remove, the first element, or returns null if the
     * deque is empty
     * 
     * @return the element at the front of the deque
     */
    @Override
    public E peekFront() {
        // TODO: unimplemented
        return null;
    }

    /**
     * Returns, but does not remove, the last element, or returns null if the
     * deque is empty
     * 
     * @return the element at the back of the deque
     */
    @Override
    public E peekBack() {
        // TODO: unimplemented
        return null;
    }

    /**
     * Returns an iterator that traverses the deque from front to back. The
     * presence of this method also allows you to state that Iterable<E> is
     * implemented.
     * 
     * @return an Iterator<E> that traverses the deque from front to back
     */
    @Override
    public Iterator<E> iterator() {
        // TODO: unimplemented
        return null;
    }

    /**
     * @return an Iterator<E> that traverses the deque from back to front
     */
    @Override
    public Iterator<E> iteratorRev() {
        // TODO: unimplemented
        return null;
    }
}
