/**
 * @author Max Scheiber (scheiber), 14fa
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeResizing<E> implements DequeI<E>, Iterable<E> {
	E[] elements;
	//note that the front can wrap around to the back of elements
	//in the case that we offerFront and there is no space at the 
	//front
	private int frontIndex;
	private int backIndex;
	private int dequeSize;
	
    @SuppressWarnings("unchecked")
	public DequeResizing() {
    	//initialize initial array with size 2
        elements = (E[]) new Object[2];
        frontIndex = -1;
        backIndex = -1;
        dequeSize = 0;
    }

    /**
     * @return true if the deque is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return dequeSize == 0;
    }

    /**
     * @return the number of elements in the DequeI
     */
    @Override
    public int size() {
    	return dequeSize;        
    }

    /**
     * @param o
     * @return true if the deque contains o, false otherwise
     */
    @Override
    public boolean contains(Object o) {
    	//check if deque is empty
    	if (dequeSize == 0) {
    		return false;
    	}
    	if (backIndex > frontIndex) {
    		//no wrapping has occurred
	        for (int i = frontIndex; i <= backIndex; i++) {
	        	if (elements[i].equals(o)) {
	        		return true;
	        	}
	        }
    	} else {
    		//wrapping has occurred. first check back of array then wrap around
    		for (int i = frontIndex; i < elements.length; i++) {
    			if (elements[i].equals(o)) {
	        		return true;
	        	}
    		}
    		//now go to front
    		for (int i = 0; i <= backIndex; i++) {
    			if (elements[i].equals(o)) {
	        		return true;
	        	}
    		}
    	}
    	//o was not found in elements
        return false;
    }

    /**
     * Adds the specified element at the front of the deque
     * 
     * @param elem
     */
    @Override
    public void offerFront(E elem) {
    	//check if there is an empty spot to the left of frontIndex
    	if (frontIndex > 0) {
    		frontIndex--;    		
    	} else {
    		//find rightmost index that's free
    		frontIndex = backIndex + (elements.length - dequeSize);
    	}
    	elements[frontIndex] = elem;
    	dequeSize++;
    	//check if we need to double the size of the array
    	if (dequeSize == elements.length) {
    		resize(elements.length*2);
    	}
    }

    /**
     * Adds the specified element at the back of the deque
     * 
     * @param elem
     */
    @Override
    public void offerBack(E elem) {
    	//check if there is an emtpty spot to the right of backIndex
    	if (backIndex < (elements.length - 1)) {
    		backIndex++;
    	} else {
    		//find the leftmost element that's free
    		backIndex = frontIndex - (elements.length - dequeSize);
    	}
    	elements[backIndex] = elem;
    	dequeSize++;
    	if (dequeSize == elements.length) {
    		resize(elements.length*2);
    	}
    }

    /**
     * Removes and returns the first element of the deque, or returns null if
     * the deque is empty
     * 
     * @return the element removed
     */
    @Override
    public E pollFront() {
        if (isEmpty()) {
        	return null;
        }
        E elemToReturn = elements[frontIndex];
        elements[frontIndex] = null;
        frontIndex++;
        //if we've gone over the rightside edge of the array, 
        //the frontIndex goes back to the left of the array
        if (frontIndex >= elements.length) {
        	frontIndex = 0;
        }
        dequeSize--;
        if (dequeSize == 0) {
        	//note that in this case we will keep the array's size at 2 despite
        	//having no elements
        	frontIndex = -1;
        	backIndex = -1;
        } else if (dequeSize*4 <= elements.length) {
        	resize(elements.length/2);
        }
        return elemToReturn;
    }

    /**
     * Removes and returns the last element of the deque, or returns null if the
     * deque is empty
     * 
     * @return the element removed
     */
    @Override
    public E pollBack() {
        if (isEmpty()) {
        	return null;
        }
        E elemToReturn = elements[backIndex];
        elements[backIndex] = null;
        backIndex--;
        //if we've gone past the left side of the array, the frontIndex goes
        //back to the rightmost part of the array
        if (backIndex < 0) {
        	backIndex = elements.length - 1;
        }
        dequeSize--;
        if (dequeSize == 0) {
        	//note that in this case we will keep the array's size at 2 despite
        	//having no elements
        	frontIndex = -1;
        	backIndex = -1;
        } else if (dequeSize*4 <= elements.length) {
        	resize(elements.length/2);
        }
        return elemToReturn;
    }

    /**
     * Returns, but does not remove, the first element, or returns null if the
     * deque is empty
     * 
     * @return the element at the front of the deque
     */
    @Override
    public E peekFront() {
    	if (isEmpty()) {
        	return null;
        }
        return elements[frontIndex];
    }

    /**
     * Returns, but does not remove, the last element, or returns null if the
     * deque is empty
     * 
     * @return the element at the back of the deque
     */
    @Override
    public E peekBack() {
    	if (isEmpty()) {
        	return null;
        }
        return elements[frontIndex];
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
        return new ForwardIterator();
    }
    
    private class ForwardIterator implements Iterator<E> {
    	private int curIndex = frontIndex;
		private int remainingElements = dequeSize;

		@Override
		public boolean hasNext() {
			return !(remainingElements == 0);
		}

		@Override
		public E next() {
			if (remainingElements == 0) {
				throw new NoSuchElementException();
			}
			else {
				E curItem = elements[curIndex];
				curIndex++;
				if (curIndex == elements.length) {
					curIndex = 0;
				}
				return curItem;
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
    }

    /**
     * @return an Iterator<E> that traverses the deque from back to front
     */
    @Override
    public Iterator<E> iteratorRev() {
        return new BackwardIterator();
    }
    
    private class BackwardIterator implements Iterator<E> {
    	private int curIndex = frontIndex;
		private int remainingElements = dequeSize;

		@Override
		public boolean hasNext() {
			return !(remainingElements == 0);
		}

		@Override
		public E next() {
			if (remainingElements == 0) {
				throw new NoSuchElementException();
			}
			else {
				E curItem = elements[curIndex];
				curIndex--;
				if (curIndex == -1) {
					curIndex = elements.length - 1;
				}
				return curItem;
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
    }
    
    private void resize(int newSize) {
    	//check to make sure that new array is still long enough to 
    	//fit the deque
    	if (newSize < dequeSize) {
    		throw new IllegalArgumentException();
    	}
    	@SuppressWarnings("unchecked")
		E[] newElementsArray = (E[]) new Object[newSize];
    	Iterator<E> iterator = this.iterator();
    	int indexInNewArray = 0;
    	while (iterator.hasNext()) {
    		E curItem = iterator.next();
    		newElementsArray[indexInNewArray] = curItem;
    		indexInNewArray++;
    	}
    	frontIndex = 0;
    	backIndex = indexInNewArray - 1;
    	elements = newElementsArray;   	
    }
}
