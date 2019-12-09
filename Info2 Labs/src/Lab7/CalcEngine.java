package Lab7;


public class CalcEngine {
	
	protected CalculatorMode mode;


	protected Boolean isLastOperator = true;
	protected Boolean isCalculated = false;
	protected String displayValue = "";
	protected Postfix p = new Postfix();

	/**
	 * Create a CalcEngine.
	 */
	public CalcEngine() {
		clear();
	}

	/**
	 * @return The value that should currently be displayed on the calculator
	 *         display.
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	/**
	 * A number button was pressed. Either start a new operand, or incorporate this
	 * number as the least significant digit of an existing one.
	 * 
	 * @param number The number pressed on the calculator.
	 */
	public void numberPressed(int number) {
		// concatenates last pressed int to string displayValue, as long as no 2 digit
		// numbers come in
		if (isCalculated) {
			this.clear();
			this.isCalculated = false;
		}

		if (isLastOperator) {
			displayValue = displayValue.concat(String.valueOf(number));
			this.isLastOperator = false;
		} else {
			keySequenceError();
		}

	}

	public void operatorPressed(char operator) throws Overflow, Underflow {
//		int lastDispChar = displayValue.length() - 1;
//		//if p.isOperator(displayValue.charAt(lastDispChar))
//		

		if (!this.isLastOperator) {
			if (operator != '=') {
				this.displayValue = displayValue.concat(String.valueOf(operator));
				this.isLastOperator = true;

			} else {
				System.out.println(this.displayValue.toString());
				String postFix = p.infixToPostfix(displayValue);
				this.displayValue = String.valueOf(p.evaluate(postFix));
				System.out.println("post eval:" +this.displayValue);
				this.isLastOperator = true;
				this.isCalculated = true;
			}
		} else {
			keySequenceError();
		}
	}

	public void bracketPressed(char bracket) {

		if (isCalculated) {
			this.clear();
			this.isCalculated = false;
		}
		
		if (this.isLastOperator && bracket == '(') {
			this.displayValue = displayValue.concat(String.valueOf(bracket));

		} else if (!this.isLastOperator && bracket == ')') {
			this.displayValue = displayValue.concat(String.valueOf(bracket));

		} else {
			this.keySequenceError();
		}
	}

	/**
	 * The 'C' (clear) button was pressed. Reset everything to a starting state.
	 */
	public void clear() {
		displayValue = "";
		this.isLastOperator = true;
	}

	/**
	 * @return The title of this calculation engine.
	 */
	public String getTitle() {
		return "Java Calculator";
	}

	/**
	 * @return The author of this engine.
	 */
	public String getAuthor() {
		return "David J. Barnes and Michael Kolling";
	}

	/**
	 * @return The version number of this engine.
	 */
	public String getVersion() {
		return "Version 1.0";
	}

	/**
	 * Report an error in the sequence of keys that was pressed.
	 */
	protected void keySequenceError() {
		System.out.println("A key sequence error has occurred.");
		// Reset everything.
		clear();
	}
}
