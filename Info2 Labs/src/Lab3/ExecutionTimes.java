package Lab3;

import java.util.Random;

public class ExecutionTimes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutionTimes ex = new ExecutionTimes();
		int iterations = 100;
		
		long sum20 = 0;
		for(int i = 0;i<iterations;i++) {
			String binaryString = ex.generateRandomBinaryString(20);
			long binaryLong = ex.parseBinaryStringToNumber(binaryString);
			long sum = ex.isPrime(binaryLong);
			sum20 += sum;
			System.out.println(i);
		}
		System.out.println("20-bit: "+sum20+" operations, "+sum20/iterations+ " average");
		
		long sum40 = 0;
		for(int i = 0;i<iterations;i++) {
			String binaryString = ex.generateRandomBinaryString(40);
			long binaryLong = ex.parseBinaryStringToNumber(binaryString);
			long sum = ex.isPrime(binaryLong);
			sum40 += sum;
			System.out.println(i);
		}
		System.out.println("40-bit: "+sum40+" operations, "+sum40/iterations+ " average");
	}

	public long isPrime(long num) {
		long sum = 0;
		int i = 2;
		boolean isPrime = true;
		while (i <= num / 2) {
			sum += 1;
			if (num % i == 0) {
				isPrime = false;
				break;
			}
			++i;
		}
		if (isPrime) {
			System.out.println(num + " is a prime number.");
		}else {
			System.out.println(num + " is not a prime number.");			
		}
		System.out.println("sum: "+ sum);
		return sum;
	}
	
	public String generateRandomBinaryString(int length) {
		String binaryResult = "";
		Random rand = new Random();
		for(int i=0; i<length;i++) {
			binaryResult += rand.nextInt(2);
		}
		//System.out.println(binaryResult);
		return binaryResult;
	}
	
	public long parseBinaryStringToNumber(String binaryString) {
		long result = Long.parseLong(binaryString,2);
		//System.out.println(result);
		return result;
	}

}
