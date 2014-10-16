import java.util.Iterator;

/**
 * @author Max Scheiber (scheiber), 14fa
 * 
 *         An InfixToPostfix converter should take in equations in infix form
 *         and convert them to postfix form.
 */
public class InfixToPostfix {

    /**
     * This function should convert infix expressions to postfix expressions,
     * implementing the standard Shunting Yard algorithm. It should convert any
     * valid expression with the following operators: + - * / any floating point
     * numbers
     * 
     * @return space-delimited postfix expression
     * @throws IllegalArgumentException
     *             if the input is null
     */
    public static String simpleShuntingYard(String input) {
    	Tokenizer tokenizer = new Tokenizer(input);
        DequeStack<String> stack = new DequeStack<String>();
        Iterator<String> iterator = tokenizer.getIterator();
        String output = "";
        while (iterator.hasNext()) {
        	String current = iterator.next();
        	if (current.matches("-?\\d+(\\.\\d+)?")) {
        		output += current + " ";
        	} else if (isSimpleOperator(current)){
        		while (!stack.empty() && precedenceIsLessOrEqual(current, stack.peek())) { //WILL THIS GO INTO THIS SHIT HERE ON THE RIGHT?
        			output += stack.pop() + " ";
        		}
        		stack.push(current);
        	}
        }
        while(!stack.empty()) {
        	output += stack.pop() + " ";
        }
        System.out.println(output.trim());
        return output.trim();
    }

	/**
     * This function converts infix expressions to postfix expressions,
     * implementing the standard Shunting Yard algorithm. It should convert any
     * valid expression with the following operators: + - * / any floating point
     * numbers and any set of matching parentheses
     * 
     * @return space-delimited postfix expression
     * @throws UnmatchedParenthesesException
     * @throws IllegalArgumentException
     *             if the input is null
     */
    public static String mediumShuntingYard(String input)
            throws UnmatchedParenthesesException {
        // TODO: unimplemented
        return null;
    }

    /**
     * This function converts infix expressions to postfix expressions,
     * implementing the standard Shunting Yard algorithm. It should convert any
     * valid expression with the following operators: + - * / ^ the following
     * functions: max(a,b) sin(a) cos(a) any floating point numbers and any set
     * of matching parentheses
     * 
     * @return space-delimited postfix expression
     * @throws UnmatchedParenthesesException
     * @throws IllegalArgumentException
     *             if the input is null
     */
    public static String shuntingYard(String input)
            throws UnmatchedParenthesesException {
        // TODO: unimplemented
        return null;
    }
    
  //states if "current" has lower or equal precedence to "peek"
    private static boolean precedenceIsLessOrEqual(String current,
			String peek) {
		if (current.equals("+") || current.equals("-")) {
			return true;
		}
		if (current.equals("*") || current.equals("/")) {    		
            if(peek.equals("+")|| peek.equals("-")){
            	return false;
			} else { return true; }
    	}
		if (current.equals("^")) {
			if (peek.equals("*")|| peek.equals("/") || peek.equals("+") || peek.equals("-")) {
				return false;
			} else { return true; }
		}
		if (current.equals(")") || current.equals("]")) {
			if (peek.equals("^") || peek.equals("*")|| peek.equals("/") || peek.equals("+") || peek.equals("-")) {
				return false;
			} else { return true; }
		}
		return false;
	}

	private static boolean isSimpleOperator(String current) {
		if ((current.equals("+") || current.equals("-"))
				||(current.equals("*") || current.equals("/"))) {
			return true;
		}
		return false;
	}
}
