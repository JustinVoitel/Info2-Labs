package Lab7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Lab6.Overflow;
import Lab6.Underflow;

class CalculatorPrefixTest {
	
	@Test
	void extremeCalculations() throws Overflow, Underflow{
		ExtendedCalculator calc= new ExtendedCalculator();
		
		//1+2*3 -> 7
		calc.engine.displayValue = "1+2*3";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');
		
		int result = Integer.parseInt(calc.engine.displayValue);
		
		assertEquals(7, result);
		
		//1+2*3 -> 7
				calc.engine.displayValue = "1+2*3";
				calc.engine.isLastOperator = false;
				calc.engine.operatorPressed('=');
				
				int result2 = Integer.parseInt(calc.engine.displayValue);
				
				assertEquals(7, result2);
		
		
	}

}
