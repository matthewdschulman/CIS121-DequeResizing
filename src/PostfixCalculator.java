import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class PostfixCalculator {

    /**
     * Takes a postfix expression to be evaluated and outputs the calculated
     * value.
     * 
     * @param expr
     *            Postfix expression to be evaluated
     * @return Evaluation of the postfix expressions
     * @throws IllegalArgumentException
     *             if the postfix expression is invalid or null, or if the
     *             postfix expression is empty
     */
    public static float calculate(String expr) throws IllegalArgumentException {
        //create stack of postfix expression
        Tokenizer tokenizer = new Tokenizer(expr);
        DequeStack<String> stack = new DequeStack<String>();
        Iterator<String> iterator = tokenizer.getIterator();
        while (iterator.hasNext()) {
        	stack.push(iterator.next().toString());
        	if (!isValid(stack.peek())) {
        		throw new IllegalArgumentException();
        	}
        	if (!stack.peek().matches("-?\\d+(\\.\\d+)?")) {
        		stack = updateStack(stack);
        	}
        }
        //at this point, stack should have size 1 and that element should be a number
        return makeSureStackHasSolution(stack);
    }

	private static float makeSureStackHasSolution(DequeStack<String> stack) {
		String topNumber = stack.pop();
		if (!topNumber.matches("-?\\d+(\\.\\d+)?")) {
			throw new IllegalArgumentException();
		}
		if (!stack.empty()) {
			throw new IllegalArgumentException();
		}	
		return Float.parseFloat(topNumber);
	}

	private static DequeStack<String> updateStack(DequeStack<String> stack) {
		if (stack.peek().equals("+")) {
			stack = makeSureTopTwoElementsAreNumbers(stack);
			stack.push(String.valueOf(Float.parseFloat(stack.pop()) + Float.parseFloat(stack.pop())));
		} else if (stack.peek().equals("*")) {
			stack = makeSureTopTwoElementsAreNumbers(stack);
			stack.push(String.valueOf(Float.parseFloat(stack.pop()) * Float.parseFloat(stack.pop())));
		} else if (stack.peek().equals("/")) {
			stack = makeSureTopTwoElementsAreNumbers(stack);
			String first = stack.pop();
			String second = stack.pop();
			stack.push(String.valueOf(Float.parseFloat(second) / Float.parseFloat(first)));
		} else if (stack.peek().equals("-")) {
			stack = makeSureTopTwoElementsAreNumbers(stack);
			String first = stack.pop();
			String second = stack.pop();
			stack.push(String.valueOf(Float.parseFloat(second) - Float.parseFloat(first)));
		} else if (stack.peek().equals("^")) {
			stack = makeSureTopTwoElementsAreNumbers(stack);
			String first = stack.pop();
			String second = stack.pop();
			stack.push(String.valueOf(Math.pow(Float.parseFloat(second),Float.parseFloat(first))));
		} else if (stack.peek().equals("max")) {
			stack = makeSureTopTwoElementsAreNumbers(stack);
			stack.push(String.valueOf(Math.max(Float.parseFloat(stack.pop()),Float.parseFloat(stack.pop()))));
		} else if (stack.peek().equals("sin")) {
			stack = makeSureTopOneElementIsANumbers(stack);
			stack.push(String.valueOf(Math.sin(Float.parseFloat(stack.pop()))));
		} else if (stack.peek().equals("cos")) {
			stack = makeSureTopOneElementIsANumbers(stack);
			stack.push(String.valueOf(Math.cos(Float.parseFloat(stack.pop()))));
		} else {
			throw new IllegalArgumentException();
		}
		return stack;
	}

	private static DequeStack<String> makeSureTopOneElementIsANumbers(DequeStack<String> stack) {
		if (stack.empty()) {
			throw new IllegalArgumentException();
		}
		stack.pop();
		if (stack.empty()) {
			throw new IllegalArgumentException();
		}
		if (!stack.peek().matches("-?\\d+(\\.\\d+)?")) {
			throw new IllegalArgumentException();
		}
		return stack;
	}

	private static DequeStack<String> makeSureTopTwoElementsAreNumbers(
			DequeStack<String> stack) {
		//get rid of operator
		
		if (stack.empty()) {
			throw new IllegalArgumentException();
		}
		stack.pop();
		String topOfStack = stack.pop();
		if (stack.empty()) {
			throw new IllegalArgumentException();
		}
		String secondOnStack = stack.pop();
		if (!topOfStack.matches("-?\\d+(\\.\\d+)?") || !secondOnStack.matches("-?\\d+(\\.\\d+)?")) {
			throw new IllegalArgumentException();
		}
		//put back on the top number and 2nd number to the top of the stack
		stack.push(secondOnStack);		
		stack.push(topOfStack);	
		return stack;
	}

	private static boolean isValid(String expr) {
		Set<String> validOperators = new HashSet<String>();
		validOperators.add("+");
		validOperators.add("*");
		validOperators.add("/");
		validOperators.add("-");
		validOperators.add("^");
		validOperators.add("max");
		validOperators.add("sin");
		validOperators.add("cos");
		return (expr.matches("-?\\d+(\\.\\d+)?") || validOperators.contains(expr));
	}
}
