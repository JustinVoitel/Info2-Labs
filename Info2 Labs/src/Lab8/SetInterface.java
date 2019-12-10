package Lab8;

public interface SetInterface<T> {
	//add an element to the set if it's not contained in the set
	public void addElement(T element);
	
	//adds all elements from the parameter set to the set and make sure the are unique
	public Set<T> addAll(Set<T> set);
	
	//removes an element from this set
	public void removeElement(T element);
	
	// removes all elements from the Set if they are containing in the parameter set
	public Set<T> removeAll(Set<T> set);
	
	//return the size of set
	public int size();
	
	//intersection of two sets (SCHNITTMENGE)
	public Set<T> intersection(Set<T> set);
	
	// check if the element is contained in the set
	public boolean contains(T element);
	
	// check if the set is empty
	public boolean isEmpty();
	
	//prints the set
	public void print();
}
