package Lab8;

import java.util.Stack;

/**
* The main part of the calculator doing the calculations.
* 
* @author  David J. Barnes and Michael Kolling 
* @version 2008.03.30 
* @edited by Kim & Le Ha 3.12.2019
*/
public class SetCalcEngine
{  
	// The current value to be shown in the display
    public String displayValue;  // for the display1  
    public Set<Integer> leftOperand;
    public Set<Integer> rightOperand;
    public Set<Integer> result;
    
    private char operator;
    /**
     * Create a CalcEngine.
     */
    public SetCalcEngine()
    {          
        
        leftOperand = new Set<>();
        rightOperand = new Set<>();
        result = new Set<>();
        clear();
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public String getDisplayValue()
    {
        return displayValue;
    } 
    
    /**
     *  Set the display value.
     * @param displayValue The string to be the display value/
     */
    public void setDisplayValue(String displayValue){
        this.displayValue = displayValue;
    }
    
   
    /**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear()
    {        
       displayValue = "";
       leftOperand.empty();; // delete all element in the leftOperand set
       rightOperand.empty();;// delete all element in the rightOperand set
       result.empty();;// delete all element in the result set
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java SET Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "Kim and Justin";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 1.0";
    }
    /**
     * Calculate union
     */
    protected void union(String input) {
    	// make two sets out of the input
    	setLeftOperand(input);
		setRightOperand(input);
    	
    	// result set
    	result = leftOperand.addAll(rightOperand);
    	
    	displayValue += this.result.print();
    	
    }
    
    /**
     * Calculate difference
     */
    protected void difference(String input) {
    	// make two sets out of the input
    	setLeftOperand(input);
		setRightOperand(input);
		
    	// result set
    	result = leftOperand.removeAll(rightOperand);
    	
    	displayValue += this.result.print();
    }
    
    /**
     * Calculate intersection
     */
    protected void intersection(String input) {
    	// make two sets out of the input
    	setLeftOperand(input);
		setRightOperand(input);
		
    	// result set
    	result = leftOperand.intersection(rightOperand);
    	
    	displayValue += this.result.print();
    }
    
    /**
     * Calculate size
     */
    protected void size() {
    	
    	Set<String> set = new Set<>();
    	String[] inputArray = this.displayValue.substring(1, this.displayValue.indexOf("}")).trim().split(",");
    	
		for (String i : inputArray) {
			if(!i.equals("")) {
				set.addElement(i);								
			}
			
		}

		displayValue = Integer.toString(set.size());
    }
    
    public boolean validateInput(String input) {
    	boolean isValid = true;
    	//check if brackets are correct
    	char[] chars = input.toCharArray();
    	Stack<Character> s = new Stack<>();
    	
    	try {
    		for(char c : chars) {
    			if(c == '{') {
    				s.push('{');
    			}else if(c == '}') {
    				s.pop();
    			}
    		}
    		
    		if(!s.isEmpty()) {
    			isValid = false;
    			this.keySequenceError("Curly Brackets are not valid.");
    		}
		} catch (Exception e) {
			isValid = false;
			this.keySequenceError("Curly Brackets are not valid.");
		}
    	
    	return isValid;
    }
    
    
    /**
     * Calculate the result
     * Take the inputs from two text fields, create two sets and
     * calculate those sets and add the result set to the displayValue string 
     * 
     */
    
    protected void calculateResult() 
    {    
    	String input = getDisplayValue();
    	
    	if(validateInput(input)) {
    		switch (getOperator()) {
    		case '+':
    			union(input);
    			break;
    		case '-':
    			difference(input);
    			break;
    		case 'X':
    			intersection(input);
    			break;
    		}
    	}
    }

	public Set<Integer> getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(String leftString) {
		String[] inputArray = leftString.substring(1, leftString.indexOf("}")).split(",");
		for (String i : inputArray) {
			if(!i.equals("")) {
				leftOperand.addElement(Integer.valueOf(i));				
			}
		}
	}

	public Set<Integer> getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(String rightString) {
		String[] rightArray = rightString.substring(rightString.indexOf(getOperator())+2, rightString.length()-2).split(",");
    	for (String j : rightArray) {
    		if(!j.equals("")) {
    			rightOperand.addElement(Integer.valueOf(j));    			
    		}
		}
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public Set<Integer> getResult() {
		return result;
	}

	public void setResult(Set<Integer> result) {
		this.result = result;
	}
	
	public void keySequenceError(String msg) {
		System.out.println("Error: "+msg);
	}

} 

