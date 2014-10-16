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
	public void testInvalidExpressionTooManyNumbersForUnary() {		
		exception.expect(IllegalArgumentException.class);
		calculator.calculate("4 4 cos");
	}

}
