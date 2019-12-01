package Lab6;

public class StackAsList<T> implements ADTStack<T> {

	private Node top = null;
	
	public StackAsList() {
		
	}
	
	public static void main(String[] args) throws Overflow, Underflow {
		StackAsList<Integer> s = new StackAsList<Integer>();
		s.push(Integer.valueOf(1));
		s.push(Integer.valueOf(3));
		s.push(Integer.valueOf(5));
		
		System.out.println(s.top());
		s.print();
		
		System.out.println(s.pop());
		
		System.out.println(s.top());
		s.print();
		
		
	}
	
	@Override
	public void push(T value) throws Overflow {
		//Overflow will never happen
		
		Node node = new Node(value);
		
		if(this.isEmpty()) {
			top = node;
		}else {
			node.next = this.top;
			this.top = node;
		}
	}

	@Override
	public T pop() throws Underflow {
		if(top == null) {
			throw new Underflow();
		}else {
			T value = this.top.value;
			top = top.next;
			
			return value;
		}
	}

	@Override
	public T top() throws Underflow {
		if(top == null) {
			throw new Underflow();
		}else {
			return this.top.value;			
		}
	}

	@Override
	public boolean isEmpty() {
		return this.top == null;
	}
	
	public void print() {
		if(this.isEmpty()) {
			System.out.println("The Stack is empty");
		}else {
			Node node = this.top;
			String output = "top: ";
			while(node != null) {
				output += node.value.toString() + "|";
				node = node.next;
			}
			output += "]";
			System.out.println(output);			
		}
		
	}
	
	
	private class Node{
		T value;
		Node next;
		
		public Node(T value) {
			this.value = value;
		}
	}
	
	
}
