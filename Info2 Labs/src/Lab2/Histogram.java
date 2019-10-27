package Lab2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Histogram {
	private int[] frequencyUppercase;
	private int[] frequencyLowercase;
	private final int diffOfCharLower = 97;
	private final int diffOfCharUpper = 65;
	private LinkedList<Integer> mostFrequentLowercase;

	public Histogram() {
		super();
		this.frequencyUppercase = new int[26];
		this.frequencyLowercase = new int[26];
		this.mostFrequentLowercase = new LinkedList<>();
	}

	public static void main(String[] args) {
		Histogram histogram = new Histogram();
		Path readPath = Paths.get("src/Lab2/file.txt");
		Path writePath = Paths.get("src/Lab2/frequencyTable.txt");
		//histogram.writeFrequency(writePath);
		//histogram.logFrequencyTable();
		
		histogram.readLinesAndConvertChars(readPath);
		histogram.sortMostFrequent(5);
		histogram.writeFrequencyTable(writePath);
	}


	//main functions
	

	public void readLinesAndConvertChars(Path readPath) {
		try (Stream<String> stream = Files.lines(readPath, StandardCharsets.ISO_8859_1)) {
			stream.forEach(e -> {
				// regex expression to sort out everything exept a-z and A-Z
				char charArr[] = e.replaceAll("[^A-Za-z]+", "").toCharArray();
				for (int i = 0; i < charArr.length; i++) {
					int index = this.charToIndex(charArr[i]);
					if (index >= this.diffOfCharLower
							&& index <= this.diffOfCharLower + this.frequencyLowercase.length - 1) {
						this.frequencyLowercase[index - this.diffOfCharLower]++;
					} else if (index >= this.diffOfCharUpper
							&& index <= this.diffOfCharUpper + this.frequencyUppercase.length - 1) {
						this.frequencyUppercase[index - this.diffOfCharUpper]++;
					}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void sortMostFrequent(int range) {
		// for lower
		for (int i = 0; i < this.frequencyLowercase.length; i++) {
			innerloop: for (int j = 0; j < range; j++) {
				try {
					this.mostFrequentLowercase.get(j);
				} catch (Exception e) {
					this.mostFrequentLowercase.addLast(i);
					break innerloop;
				}
				if (frequencyLowercase[i] > frequencyLowercase[this.mostFrequentLowercase.get(j)]) {
					this.mostFrequentLowercase.add(j, i);
				
					break innerloop;
				}
			}
		}

		 //cutting unnecessary data at range
		while(this.mostFrequentLowercase.size() > range) {
			this.mostFrequentLowercase.removeLast();
		}
	}

	public String convertMostFrequentTable() {
		String mostFrequent = "";
		int count = this.getCount("lower");
		double partPercentage = 0;
		for (int i = 0; i < mostFrequentLowercase.size(); i++) {
			partPercentage += ((double) frequencyLowercase[this.mostFrequentLowercase.get(i)] / count) * 100;
		}

		for (int i = 0; i < mostFrequentLowercase.size(); i++) {
			char c = indexToChar(this.mostFrequentLowercase.get(i) + diffOfCharLower);
			int charCount = frequencyLowercase[this.mostFrequentLowercase.get(i)];
			String stars = "";
			int part = (int) ((charCount / ((partPercentage * count) / 100)) / 0.05);
			for (int j = 0; j <= part; j++) {
				stars += "*";
			}
			mostFrequent += c + " -> " + stars + "("+ charCount + ", " + (float) charCount / count * 100 + " %)" + "\n";
		}
		return mostFrequent;

	}

	public void writeFrequencyTable(Path writePath) {

		List<String> lines = new ArrayList<>();
		for (int i = 0; i < this.frequencyUppercase.length; i++) {
			lines.add(indexToChar(i + this.diffOfCharUpper) + " -> " + this.frequencyUppercase[i] + "\t\t\t\t"
					+ indexToChar(i + this.diffOfCharLower) + " -> " + this.frequencyLowercase[i]);
		}
		lines.add("count: " + this.getCount("all"));
		lines.add("most frequent lowercase characters:\n"+this.convertMostFrequentTable());

		try {
			Files.write(writePath, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//some helper functions

	public int getCount(String object) {
		int countLower = 0;
		int countUpper = 0;
		for (int i = 0; i < this.frequencyUppercase.length; i++) {
			countUpper += this.frequencyUppercase[i];
		}
		for (int i = 0; i < this.frequencyLowercase.length; i++) {
			countLower += this.frequencyLowercase[i];
		}
		if (object == "lower") {
			return countLower;
		} else if (object == "upper") {
			return countUpper;
		}
		return countUpper + countLower;
	}

	public int charToIndex(char character) {
		return (int) character;
	}

	public char indexToChar(int index) {
		return (char) index;
	}
	
	
	//logging functions

	public void logFrequencyTable() {
		System.out.println("-----UPPER-----");
		for (int i = 0; i < this.frequencyUppercase.length; i++) {
			System.out.println(indexToChar(i + this.diffOfCharUpper) + " -> " + this.frequencyUppercase[i]);
		}
		System.out.println("-----LOWER-----");
		for (int i = 0; i < this.frequencyLowercase.length; i++) {
			System.out.println(indexToChar(i + this.diffOfCharLower) + " -> " + this.frequencyLowercase[i]);
		}
	}
	
	public void logMostFrequent() {
		for (int i = 0; i < this.mostFrequentLowercase.size(); i++) {
			char c = indexToChar(this.mostFrequentLowercase.get(i) + diffOfCharLower);
			int charCount = frequencyLowercase[this.mostFrequentLowercase.get(i)];

			System.out.println(i + 1 + ". " + c + "(" + charCount + ")");
		}
	}

}
