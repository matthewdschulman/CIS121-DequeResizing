import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class PostfixCalculatorTest {
	
	private PostfixCalculator calculator;
	
	@Before
    public void setup()
    {
		calculator = new PostfixCalculator();
    }
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	//note that we can assume that all input expressions
	//are syntactically correct
	
	@SuppressWarnings("static-access")
	@Test
	public void testValidExpressionWithAllOperators() {		
		//no exception should be thrown if valid
		calculator.calculate("4 4 + 4 - 12.234234 * 234.234 / 2 ^ 234 max sin cos");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInvalidExpressionTooManyNumbers() {		
		exception.expect(IllegalArgumentException.class);
		calculator.calculate("4 4 4 +");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInvalidExpressionTooFewNumbers() {		
		exception.expect(IllegalArgumentException.class);
		calculator.calculate("4 +");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInvalidExpressionTooFewNumbers2() {		
		exception.expect(IllegalArgumentException.class);
		calculator.calculate("4 4 - *");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInvalidExpressionTooManyNumbersForUnary() {		
		exception.expect(IllegalArgumentException.class);
		calculator.calculate("4 4 cos");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testInvalidExpressionInternally() {		
		exception.expect(IllegalArgumentException.class);
		calculator.calculate("4 4 + 4 - 12.234234 * 234.234 / 2 4 ^ 234 max sin cos");
	}
	
	@SuppressWarnings({ "static-access" })
	@Test
	public void testSimpleOperations() {		
		assertEquals(5.0, calculator.calculate("4 1 +"), 0.001);
		assertEquals(5.0, calculator.calculate("6 1 -"), 0.001);
		assertEquals(6.0, calculator.calculate("4.0 1.5 *"), 0.001);
		assertEquals(2.0, calculator.calculate("4 2 /"), 0.001);
		assertEquals(64.0, calculator.calculate("4 3 ^"), 0.001);
		assertEquals(Math.sin(4), calculator.calculate("4 sin"), 0.001);
		assertEquals(Math.cos(4), calculator.calculate("4 cos"), 0.001);
	}
	
	@SuppressWarnings({ "static-access" })
	@Test
	public void testComplexOperation() {		
		assertEquals(Math.sin(2.5), calculator.calculate("4 1 + 2.5 - sin"), 0.001);
	}
}