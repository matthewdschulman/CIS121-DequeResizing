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
        if (!isValid(expr)) {
        	throw new IllegalArgumentException();
        }
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
        makeSureStackHasSolution(stack);
        return Float.parseFloat(stack.pop());
    }

	private static void makeSureStackHasSolution(DequeStack<String> stack) {
		String topNumber = stack.pop();
		if (!topNumber.matches("-?\\d+(\\.\\d+)?")) {
			throw new IllegalArgumentException();
		}
		if (!stack.empty()) {
			throw new IllegalArgumentException();
		}	
	}

	private static DequeStack<String> updateStack(DequeStack<String> stack) {
		if (stack.pop().equals("+")) {
			makeSureTopTwoElementsAreNumbers(stack);
			stack.push(String.valueOf(Float.parseFloat(stack.pop()) + Float.parseFloat(stack.pop())));
		} else if (stack.pop().equals("*")) {
			makeSureTopTwoElementsAreNumbers(stack);
			stack.push(String.valueOf(Float.parseFloat(stack.pop()) * Float.parseFloat(stack.pop())));
		} else if (stack.pop().equals("/")) {
			makeSureTopTwoElementsAreNumbers(stack);
			stack.push(String.valueOf(Float.parseFloat(stack.pop()) / Float.parseFloat(stack.pop())));
		} else if (stack.pop().equals("-")) {
			makeSureTopTwoElementsAreNumbers(stack);
			stack.push(String.valueOf(Float.parseFloat(stack.pop()) - Float.parseFloat(stack.pop())));
		} else if (stack.pop().equals("^")) {
			makeSureTopTwoElementsAreNumbers(stack);
			stack.push(String.valueOf(Math.pow(Float.parseFloat(stack.pop()),Float.parseFloat(stack.pop()))));
		} else if (stack.pop().equals("max")) {
			makeSureTopTwoElementsAreNumbers(stack);
			stack.push(String.valueOf(Math.max(Float.parseFloat(stack.pop()),Float.parseFloat(stack.pop()))));
		} else if (stack.pop().equals("sin")) {
			makeSureTopOneElementIsANumbers(stack);
			stack.push(String.valueOf(Math.sin(Float.parseFloat(stack.pop()))));
		} else if (stack.pop().equals("cos")) {
			makeSureTopOneElementIsANumbers(stack);
			stack.push(String.valueOf(Math.cos(Float.parseFloat(stack.pop()))));
		} else {
			throw new IllegalArgumentException();
		}
		return stack;
	}

	private static void makeSureTopOneElementIsANumbers(DequeStack<String> stack) {
		if (!stack.peek().matches("-?\\d+(\\.\\d+)?")) {
			throw new IllegalArgumentException();
		}
	}

	private static void makeSureTopTwoElementsAreNumbers(
			DequeStack<String> stack) {
		String topNumber = stack.pop();
		if (!topNumber.matches("-?\\d+(\\.\\d+)?") || !stack.peek().matches("-?\\d+(\\.\\d+)?")) {
			throw new IllegalArgumentException();
		}
		//put back on the top number to the top of the stack
		stack.push(topNumber);		
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
