/**
 * @author Shaanan Cohney, Pulak Mittal, Grace Wang, Max Scheiber
 */
import java.util.Iterator;

public interface DequeI<E> {

    /**
     * @return true if the DequeI is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * @return the number of elements in the DequeI
     */
    public int size();

    /**
     * Check if it contains a given element
     * 
     * @param o
     * @return true if the DequeI contains o, false otherwise
     */
    public boolean contains(Object o);

    /**
     * Adds the specified element at the front of the deque
     * 
     * @param elem
     */
    public void offerFront(E elem);

    /**
     * Adds the specified element at the back of the deque
     * 
     * @param elem
     */
    public void offerBack(E elem);

    /**
     * Removes and returns the first element of the deque, or returns null if
     * the deque is empty
     * 
     * @return the element removed
     */
    public E pollFront();

    /**
     * Removes and returns the last element of the deque, or returns null if the
     * deque is empty
     * 
     * @return the element removed
     */
    public E pollBack();

    /**
     * Returns, but does not remove, the first element, or returns null if the
     * deque is empty
     * 
     * @return the element at the front of the deque
     */
    public E peekFront();

    /**
     * Returns, but does not remove, the last element, or returns null if the
     * deque is empty
     * 
     * @return the element at the back of the deque
     */
    public E peekBack();

    /**
     * Returns an iterator that traverses the deque from front to back The
     * presence of this method also allows you to state that Iterable is
     * implemented
     * 
     * @return Iterator<E> that traverses the deque from front to back
     */
    public Iterator<E> iterator();

    /**
     * Returns an iterator that traverses the deque from back to front
     * 
     * @return Iterator<E> that traverses the deque from back to front
     */
    public Iterator<E> iteratorRev();
}
