package Lab8;

import java.util.LinkedList;

public class Set<T> implements SetInterface<T> {

	private LinkedList<T> mySet;
	
	public Set() {
		super();
		mySet = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		// Test addElement
		Set<Integer> s = new Set<Integer>();
		s.addElement(10);
		s.addElement(101);
		s.print();
		// Test addAll
		Set<Integer> s2 = new Set<Integer>();

		s2.addElement(48);
		s2.addElement(10);
		
		Set<Integer> set3 = s.addAll(s2);
		
		s.print();
		s2.print();
		set3.print();
	}

	// add an element to the set if it's not contained in the set
	public void addElement(T element) {
		if(!mySet.contains(element)) {
			mySet.addLast(element);
		}
		
	}

	// adds all elements from the parameter set to the set and make sure the are
	// unique
	public Set<T> addAll(Set<T> set) {
		
		Set<T> newSet = new Set<>();
		for(T element:this.mySet) {
			newSet.addElement(element);
		}
		
		for(int i = 0;i<set.size();i++) {
			newSet.addElement(set.get(i));
		}
		return newSet;
		
	}

	private T get(int i) {
		// TODO Auto-generated method stub
		return this.mySet.get(i);
	}

	// removes an element from this set
	public void removeElement(T element) {

	}

	// removes all elements from the Set if they are containing in the parameter set
	public Set<T> removeAll(Set<T> set) {

	}

	// return the size of set
	public int size() {
		return this.mySet.size();
	}

	// check if the element is contained in the set
	public boolean contains(T element) {

	}

	// intersection of two sets (SCHNITTMENGE)
	public Set<T> intersection(Set<T> set) {

	}

	// check if the set is empty
	public boolean isEmpty() {

	}
	
	public void print() {
		String output = "";
		
		for(T element: this.mySet) {
			output += element +" ";
			
		}
		System.out.println(output);
	}
	

}
