package Lab11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.LinkedList;

public class Dictionary {
	private LinkedList<String>[] hashTable;
	int prime  = 43201;
	
	@SuppressWarnings("unchecked")
	public Dictionary(String filePath) {
		
		// prime number 5501
		this.hashTable = new LinkedList[prime]; 
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			
			while(line != null) {
				
				int keyValue = hash(line.trim());
				
				if(hashTable[keyValue] == null) {
					LinkedList<String> linkedList = new LinkedList<String>();
					linkedList.add(line);
					hashTable[keyValue] = linkedList;
					
				}else {
					hashTable[keyValue].add(line);
				}
				
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int hash(String word) {
		//TODO
		BigInteger charValue = new BigInteger("0");
		char [] characters = word.toCharArray();
		for(int i=0; i< characters.length; i++) {
			charValue = charValue.add(BigInteger.valueOf(characters[i]));
			charValue = charValue.multiply(new BigInteger("39"));
			//charValue = charValue.multiply(BigInteger.valueOf(characters[i]));
			//charValue += (int) characters[i];
		}
		//charValue = charValue%5501;
		charValue = charValue.mod(new BigInteger(String.valueOf(prime)));
		return charValue.intValue();
	}
	
	
	//TODO
	public String[] lookup(String word) {
		
	}
	
	//TODO
	public String normalize(String word) {
		
	}
	
	public void print() {
		int highestCollision = 0;
		for(LinkedList<String> list: hashTable ) {
			if(list != null) {
				System.out.println(list.size());
				if(list.size()>highestCollision) {
					highestCollision = list.size();
				}
			}else {
				System.out.println("0");
			}
		}
		
		System.out.println("highest Collision: "+highestCollision);
	}
	
	public static void main(String[] args) {
		Dictionary d = new Dictionary("./src/Lab11/list.txt");
		d.print();
	}
}


