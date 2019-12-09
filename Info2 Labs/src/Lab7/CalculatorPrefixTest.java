package Lab7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorPrefixTest {

	@Test
	void extremeCalculations() throws Overflow, Underflow {
		ExtendedCalculator calc = new ExtendedCalculator();

		// 1+2*3 -> 7
		calc.engine.displayValue = "1+2*3";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result = Integer.parseInt(calc.engine.displayValue);

		assertEquals(7, result);

		// (1+2)*3 -> 9
		calc.engine.displayValue = "(1+2)*3";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result2 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(9, result2);

		// 2+2+2+3*3*2+3*2 -->30
		calc.engine.displayValue = "2+2+2+3*3*2+3*2";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result3 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(30, result3);

		// 2*2*2*3+3+2*3+2 --> 35
		calc.engine.displayValue = "2*2*2*3+3+2*3+2";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result4 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(35, result4);

		// 2*2+2*3+3*2+3*2 --> 22
		calc.engine.displayValue = "2*2+2*3+3*2+3*2";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result5 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(22, result5);

		// (3)+(4)*(5) --> 23
		calc.engine.displayValue = "(3)+(4)*(5)";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result6 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(23, result6);

		// (3)+(4)*(5) --> 23
		calc.engine.displayValue = "(3)+(4)*(5)";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result7 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(23, result7);

		// ((3)+(4))*(5) --> 35
		calc.engine.displayValue = "((3)+(4))*(5)";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result8 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(35, result8);

		// 6/3+4 --> 6
		calc.engine.displayValue = "6/3+4";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result10 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(6, result10);

		// 6^2+1 --> 37
		calc.engine.displayValue = "6^2+1";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result11 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(37, result11);

		// 6^(2+1) --> 216
		calc.engine.displayValue = "6^(2+1)";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result12 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(216, result12);

		// (3+2)^(2+1) --> 125
		calc.engine.displayValue = "(3+2)^(2+1)";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result13 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(125, result13);

		// 6+3/4 --> 7
		calc.engine.displayValue = "6+3/4";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result14 = Integer.parseInt(calc.engine.displayValue);

		
		
		assertEquals(7, result14);

		// 6+5/4 --> 7
		calc.engine.displayValue = "6+5/4";
		calc.engine.isLastOperator = false;
		calc.engine.operatorPressed('=');

		int result15 = Integer.parseInt(calc.engine.displayValue);

		assertEquals(7, result15);

	}

}
