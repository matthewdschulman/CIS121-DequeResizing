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
	
	public void testCasesForMediumShuntingYard() {
		
	}

}
