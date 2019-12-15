package Lab8;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.jupiter.api.*;

class SetCalculatorTest {
	static SetCalculator calc;

	@BeforeEach
	void setUp() {
		reset();
	}

	@Test
	void testButtons() {
		String input, assumedResult;
		
		input = "{1,2,3,4,5}+{6,7,8,9,0}";
		assumedResult = "{0,1,2,3,4,5,6,7,8,9}";
		checkResult(input, assumedResult);
		
		reset();
		
		input =  "{2,9,3,7,1}+{5,0,4,8,6}";
		assumedResult = "{0,1,2,3,4,5,6,7,8,9}";
		checkResult(input, assumedResult);
		
	}
	
	@Test
	void testUnion() {
		String input, assumedOutput;
		
		input =  "{5,6}+{1,8}";
		assumedOutput = "{1,5,6,8}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{2,9,3}+{4,8,6,2,1}";
		assumedOutput = "{1,2,3,4,6,8,9}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{1,2}+{1,3,4}";
		assumedOutput = "{1,2,3,4}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{1,2,3,4}+{2,5,6}";
		assumedOutput = "{1,2,3,4,5,6}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{1,2,6,7}+{1,2,6,7}";
		assumedOutput = "{1,2,6,7}";
		checkResult(input, assumedOutput);
	}

	@Test
	void testDifference() {
		String input, assumedOutput;
		
		input =  "{5,6}-{1,8}";
		assumedOutput = "{5,6}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{2,9,3}-{4,8,6,2,1}";
		assumedOutput = "{3,9}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{1,2,4}-{1,2}";
		assumedOutput = "{4}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{1,2,3,4}-{2,5,6}";
		assumedOutput = "{1,3,4}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{1,2,6,7}-{1,2,6,7}";
		assumedOutput = "{}";
		checkResult(input, assumedOutput);
	}

	@Test
	void testIntersection() {
		String input, assumedOutput;
		
		input =  "{5,6}X{1,8}";
		assumedOutput = "{}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{2,9,3}X{4,8,6,2,1}";
		assumedOutput = "{2}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{1,2,4}X{1,2}";
		assumedOutput = "{1,2}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{1,2,3,4}X{2,4,6}";
		assumedOutput = "{2,4}";
		checkResult(input, assumedOutput);
		
		reset();
		
		input =  "{1,2,6,7}X{1,2,6,7}";
		assumedOutput = "{1,2,6,7}";
		checkResult(input, assumedOutput);
	}
	
	@Test
	void testSize() {
		String input, assumedOutput;
		
		input =  "{2,9,3,4,8,6,2,1}";
		assumedOutput = "7";
		clickButtons(input);
		clickSingleButton("size");
		assertEquals(assumedOutput,calc.engine.displayValue);
		
		reset();
		
		input =  "{2,1}";
		assumedOutput = "2";
		clickButtons(input);
		clickSingleButton("size");
		assertEquals(assumedOutput,calc.engine.displayValue);
		
		reset();
		
		input =  "{}";
		assumedOutput = "0";
		clickButtons(input);
		clickSingleButton("size");
		assertEquals(assumedOutput,calc.engine.displayValue);
	}
	
	@Test 
	void testClear(){
		String input, assumedOutput;
		
		input =  "{2,9,3,4,8,6,2,1}";
		assumedOutput = "";
		
		clickButtons(input);
		assertNotEquals(calc.engine.displayValue, assumedOutput);
		clickSingleButton("Clear");
		assertEquals(calc.engine.displayValue, assumedOutput);
		
	}
	
	public void clickSingleButton(Object button) {
		calc.sui.actionPerformed(new ActionEvent(new JButton(), 1,String.valueOf(button)));
	}
	
	public void clickButtons(String input) {
		for(char c:input.toCharArray()) {
			clickSingleButton(String.valueOf(c));
		}
	}
	
	public void reset() {
		calc = new SetCalculator();
	}
	
	public void checkResult(String input, String assumedOutput) {
		clickButtons(input);
		
		clickSingleButton("=");
		
		assertEquals(input+"="+assumedOutput,calc.engine.displayValue);
	}
	
	

}
