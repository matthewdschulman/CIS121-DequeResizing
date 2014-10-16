import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class InfixToPostfixTest {

	private InfixToPostfix converter;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
    public void setup()
    {
		converter = new InfixToPostfix();
    }
	
	//note that we can assume that all input expressions
	//are syntactically correct
	
	@SuppressWarnings("static-access")
	@Test
	public void testCasesForSimpleShuntingYard() {		
		//no exception should be thrown if valid
		assertEquals("5 -3.14 * 1 -", converter.simpleShuntingYard("5 * -3.14 - 1"));
		assertEquals("3 4 2 / -", converter.simpleShuntingYard("3 - 4 / 2 "));
		assertEquals("3 4 5 * 2 / -", converter.simpleShuntingYard("3 - 4 * 5 / 2"));
		exception.expect(IllegalArgumentException.class);
		converter.simpleShuntingYard(null);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testCasesForMediumShuntingYard() throws UnmatchedParenthesesException {
		assertEquals("5 4 - 4 *", converter.mediumShuntingYard("( 5 - 4 ) * 4"));		
		assertEquals("3 4 - 2 5 3 - * /", converter.mediumShuntingYard("( 3 - 4 ) / ( 2 * ( 5 - 3 ) )"));	
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void unmatchedParentheses() throws UnmatchedParenthesesException {
		exception.expect(UnmatchedParenthesesException.class);
		converter.mediumShuntingYard("( 5 - 4 * 4");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testCasesForShuntingYard() throws UnmatchedParenthesesException {
		assertEquals("5 4 - 4 ^", converter.shuntingYard("( 5 - 4 ) ^ 4"));		
		assertEquals("4 5 4 - ^", converter.shuntingYard("4 ^ ( 5 - 4 )"));	
		assertEquals("3 5 max", converter.shuntingYard("max ( 3 , 5 )"));	
		assertEquals("3 4 - 6 4 ^ max 4 * 2 /", converter.shuntingYard("max ( 3 - 4 , 6 ^ 4 ) * 4 / 2"));
		assertEquals("2 sin", converter.shuntingYard("sin ( 2 )"));
		assertEquals("2 cos", converter.shuntingYard("cos ( 2 )"));		
		assertEquals("4 sin cos", converter.shuntingYard("cos ( sin ( 4 ) )"));
		assertEquals("3 4 2 sin / - 5 max", converter.shuntingYard("max ( 3 - 4 / sin ( 2 ) , 5 )"));
		assertEquals("3 6 2.5 4 ^ 5 ^ * + -3.4 0 max 3.3 cos * -", converter.shuntingYard("3 + 6 * 2.5 ^ 4 ^ 5 - max ( -3.4 , 0 ) * cos ( 3.3 )"));
	}

}
