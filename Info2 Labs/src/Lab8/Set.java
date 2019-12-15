package Lab8;
import java.util.*;

public class Set<T> implements SetInterface<T> {
	//private static final int new HashSet = 0;
	private LinkedList<T> mySet;
	
	public Set() {
		super();
		mySet = new LinkedList<>();
	}
	
	public static void main(String[] args) {
		
		System.out.println("Test add element, add 10, 101 to s1");
		Set<Integer> s1 = new Set<Integer>();
		s1.addElement(10);
		s1.addElement(101);
		System.out.print("s1: "); System.out.println(s1.print());
		System.out.println();
		
		System.out.println("Test addAll s1 and s2");
		Set<Integer> s2 = new Set<Integer>();

		s2.addElement(48);
		s2.addElement(10);
		
		Set<Integer> s3 = s1.addAll(s2);
		
		System.out.print("s1: ");System.out.println(s1.print());
		System.out.print("s2: ");System.out.println(s2.print());
		System.out.print("s1+s2 = s3 : ");System.out.println(s3.print());
		System.out.println();
		
		System.out.println("Test removeElement 10 from s2");
		s2.removeElement(10);
		System.out.print("s2: ");System.out.println(s2.print());
		System.out.println();
		
		System.out.println("Test removeAll elements of s1 from s2");
		s2.addElement(10);
		s2.addElement(2);
		s2.addElement(3);
		System.out.print("s2: ");System.out.println(s2.print());
		System.out.print("s1: ");System.out.println(s1.print());
		Set<Integer> s4 = s2.removeAll(s1);
		System.out.print("s1-s2 = s4 : ");System.out.println(s4.print());
		System.out.println();
		
		System.out.println("Test intersection of s1 and s3");
		Set<Integer> s5 = s1.intersection(s3);
		System.out.print("s1: ");System.out.println(s1.print());
		System.out.print("s3: ");System.out.println(s3.print());
		System.out.print("s1 X s3 = s5: ");System.out.println(s5.print());
	
		
	}
 
	/*
	 * Add an element to the set if it's not contained in the set and make sure 
	 * all elements in set are unique
	 */
	@Override
	public Set<T> addElement(T element) {
		if(!mySet.contains(element)) {
			mySet.addLast(element);
		}
		return this;
		
	}

	/*
	 *  Add all elements from the parameter set to the set 
	 */
	@Override
	public Set<T> addAll(Set<T> set) {
		
		Set<T> newSet = new Set<>(); // create a new set represent the result set
		
		for(T element:this.mySet) { // iterate itself
			newSet.addElement(element);
		}
		
		for(int i = 0;i<set.size();i++) { // iterate the parameter set
			newSet.addElement(set.get(i));
		}
		return newSet;
		
	}
	/*
	 *  Get element at index i
	 */
	
	private T get(int i) {
		
		return this.mySet.get(i);
	}

	/*
	 *  Removes an element from this set if the set contains the element
	 */
	@Override
	public void removeElement(T element) {
		if(mySet.contains(element)) {
			mySet.remove(element);
		}
	}

	/*
	 *  Removes all elements from the set if they are contained in the parameter set
	 */
	@Override
	public Set<T> removeAll(Set<T> set) {
		Set<T> newSet = new Set<>(); 
		for(T element:this.mySet) { // iterate itself
			newSet.addElement(element);
		}
		
		for(int i = 0;i < set.size();i++) { // iterate the parameter set
			newSet.removeElement(set.get(i));
		}
		return newSet;
		
	}
 
	/*
	 *  Return the size of set
	 */
	@Override
	public int size() {
		return this.mySet.size();
	}

	/*
	 * Check if an element is contained in the set
	 */
	@Override
	public boolean contains(T element) {
		return mySet.contains(element);
	}
	
	/*
	 * Find the intersection of two sets (SCHNITTMENGE)
	 */
	@Override
	public Set<T> intersection(Set<T> set) {
		Set<T> newSet = new Set<>(); 
		
			for(int i = 0;i<set.size();i++) {
				T object = set.get(i);
				if (mySet.contains(object)) {
					newSet.addElement(object);
				
			}
		}
		return newSet;
	}

	@Override
	public String print() {
		String output = "{";
		HashSet<String> newSet = new HashSet<>(); 
		for(T element: this.mySet) {
			newSet.add(element.toString());
		}
		 // Convert the Set of String to String 
        String string = String.join(",", newSet);
		
		output += string + "}";
		
		return output;
	}

	@Override
	public boolean isEmpty() {
		return this.mySet.size()==0;
	}

	@Override
	public void empty() {
		this.mySet.clear();
		
	}

}
