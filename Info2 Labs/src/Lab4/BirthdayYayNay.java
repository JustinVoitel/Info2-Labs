package Lab4;

import java.util.Scanner;

public class BirthdayYayNay {

	private final static String[] WEEKDAY = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};
	private int day,month,year;
	private JuliaDate julia;
	
	public BirthdayYayNay(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		
		julia = new JuliaDate(day,month,year);
		
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What´s the day of your birthday(number):");
		int day = scanner.nextInt();
		System.out.println("What´s the month of your birthday(number):");
		int month = scanner.nextInt();
		System.out.println("What´s the year of your birthday(number):");
		int year = scanner.nextInt();
		
		BirthdayYayNay birthday = new BirthdayYayNay(day, month,year);
		birthday.printDay();
		
		birthday.isToday();
		
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	 public void printDay() {
		 int days = (int) Math.round(julia.getFullDate());
		 int day = days % 7;
		 System.out.println("Your birthday was on a "+WEEKDAY[day]+"!");
	 }
	 
	 public void isToday() {
		 JuliaDate today = new JuliaDate();
		 int numToday = (int) (today.getDays() % 365.25);
		 int numBirthday = (int) (julia.getDays() % 365.25);
		 if(numToday == numBirthday) {
			 System.out.println("YAYYY, today is your birthday!!!!");
			 
		 }else{
			 System.out.println("Nay, no birthday for you!");
	
		 }
	 }

}
