package Lab6;

public class Postfix {

	public Postfix() {
		super();
	}

	public static void main(String[] args) throws Underflow, Overflow {
		Postfix p = new Postfix();
		
		String s = p.infixToPostfix("1+2*3");
		System.out.println(s);
//		int f = p.evaluate("63*93/+");
//		System.out.println(f);
	}
	
	public int precedence(char operator) {
		switch (operator){
		    case '+':
		    case '-':
		        return 1;
		
		    case '*':
		    case '/':
		        return 2;
		
		    case '^':
		        return 3;
		    default:
		    	return -1;
		}
    }
	
	private boolean isOperand(char operand) { 
		return Character.isDigit(operand); 
	}
	
	public static Boolean isOperator(char op) {
		switch(op) {
		case '+':
			return true;
		case '-':
			return true;
		case '*':
			return true;
		case '/':
			return true;
		case '^':
			return true;
		default:
			return false;
		}
	}
    
    public String infixToPostfix(String infix) throws Overflow, Underflow{
        StackAsList<Character> stack = new StackAsList<Character>();
        char[] chars= infix.toCharArray();
        String result ="";
        
        for(char element : chars) {
        	if(element!=' ') {
        		if(isOperand(element)) {
        			result+=element;
        		}else if(element == '(') {
        			stack.push(element);
        		}else if(element == ')') {
                    while(!(stack.top() == '(')) {
                    	result+=stack.pop();
                    }
                    stack.pop();
                } else if(isOperator(element)) {
                    while(!stack.isEmpty() && precedence(stack.top()) >= precedence(element)) {
                        result+=stack.pop();
                    }
                    stack.push(element);
                }
        	}
        }
        while(!stack.isEmpty()) {
            result+=stack.pop();
        }
        return result;
	}

	public int evaluate(String pfx) throws Underflow, Overflow {

		StackAsList<Integer> stack = new StackAsList<Integer>();
		char[] chars = pfx.toCharArray();

		for (char element : chars) {
			if (Character.isDigit(element)) {
				stack.push(Character.getNumericValue(element));
			} else {

				int rhs = stack.pop();
				int lhs = stack.pop();

				switch (element) {
				case '+':
					stack.push(lhs + rhs);
					break;
				case '-':
					stack.push(lhs - rhs);
					break;
				case '*':
					stack.push(lhs * rhs);
					break;
				case '/':
					stack.push(lhs / rhs);
					break;
				case '^':
					stack.push(lhs ^ rhs);
					break;
				}
			}
		}
		return stack.pop();
	}
}
