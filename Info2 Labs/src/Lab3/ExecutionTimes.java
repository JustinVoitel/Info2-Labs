package Lab3;

public class ExecutionTimes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutionTimes ex = new ExecutionTimes();
		ex.isPrime(17);
	}

public void isPrime(int num) {
	int i = 2;
	boolean isPrime = true;
	while (i <= num / 2) {
		System.out.println(num/2);
		if (num % i == 0) {
			isPrime = false;
			break;
		}
		++i;
	}
	if (isPrime)
		System.out.println(num + " is a prime number.");
	else
		System.out.println(num + " is not a prime number.");
}

}
