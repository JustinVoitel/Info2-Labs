package Lab4;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class JuliaDate implements DateADT {

	private int day = 1;
	private int month = 1;
	private int year = 2000;
	
	private int second = 0;
	private int minute = 0;
	private int hour = 12;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JuliaDate date = new JuliaDate();
		double days = date.getFullDate();
		System.out.println(days);
	}
	
	public JuliaDate() {
		super();	
		this.now();
		
	}
	
	public JuliaDate(int day, int month, int year) {
		super();
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
	}
	
	public JuliaDate(int day, int month, int year,int hour, int minute, int second) {
		super();
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
		this.setSecond(second);
		this.setMinute(minute);
		this.setHour(hour);
	}
	
	public double calculateDays() {
		
		double JD;
		int Y,M,D,B;
		if(this.month > 2) {
			Y = this.year;
			M = this.month;
		}else {
			Y = this.year-1;
			M = this.month+12;
		}
		
		D = this.day;
		
		B = 2 - (Y/100) + (Y/400);
		
		JD = (365.25*(Y+4716)) + (30.6001*(M+1)) + D + B - 1524.5;
		
		return Math.floor(JD);
	}
	
	public double calculateFraction() {
		double hour = this.hour;
		hour -= 12;
		
		double fraction = (hour/24) + ((double)this.minute/1440) + ((double)this.second/86400);
		DecimalFormat df =new DecimalFormat("0.00000");
		System.out.println(hour/24);
		return Double.parseDouble(df.format(fraction));
	}
	
	
	@Override
	public int getDay() {
		return day;
	}

	@Override
	public int getMonth() {
		return month;
	}
	
	@Override
	public int getYear() {
		return year;
	}
	
	@Override
	public void setDay(int day) {
		if(day >=1 && day <=31) {
			this.day = day;
		}else {
			throw new IllegalArgumentException("Day has to be between 1 and 31!");
		}
	}

	@Override
	public void setMonth(int month) {
		if(month >= 1 && month <=12) {
			this.month = month;			
		}else {
			throw new IllegalArgumentException("Month has to be between 1 and 12!");
		}
	}
	
	@Override
	public void setYear(int year) {
		if(year!=0) {
			this.year = year;
		}else {
			throw new IllegalArgumentException("There is no year 0 in the Julian system!");
		}
	}

	@Override
	public int getSecond() {
		return second;
	}
	
	@Override
	public int getMinute() {
		return minute;
	}
	
	@Override
	public int getHour() {
		return hour;
	}

	@Override
	public void setSecond(int second) {
		if(second >=0 && second <=60) {
			this.second = second;
		}else {
			throw new IllegalArgumentException("Socond has to be between 0 and 60!");
		}
	}

	@Override
	public void setMinute(int minute) {
		if(minute>=0 && minute <=60) {
			this.minute = minute;
		}else {
			throw new IllegalArgumentException("Minute has to be between 0 and 60!");
		}
	}

	@Override
	public void setHour(int hour) {
		if(hour >=0 && hour <=24) {
			this.hour = hour;
		}else {
			throw new IllegalArgumentException("Hour has to be between 0 and 24!");
		}
	}
	
	

	@Override
	public void setDate(int day, int month, int year) {
		this.setDay(day);
		this.setMonth(month);
		this.setYear(year);
		
	}

	@Override
	public void setTime(int second, int minute, int hour) {
		this.setSecond(second);
		this.setMinute(minute);
		this.setHour(hour);
	}

	@Override
	public void now() {
		LocalDate date = LocalDate.now();
		this.setDay(date.getDayOfMonth());
		this.setMonth(date.getMonthValue());
		this.setYear(date.getYear());
		
		LocalTime time = LocalTime.now();
		this.setSecond(time.getSecond());
		this.setMinute(time.getMinute());
		this.setHour(time.getHour());
	}

	@Override
	public double getDate() {
		double days = this.calculateDays();		
		return days;
	}

	@Override
	public double getTime() {
		double fraction = this.calculateFraction();
		return fraction;
	}

	@Override
	public double getFullDate() {
		double days = this.calculateDays();
		double fraction = this.calculateFraction();
		
		return days + fraction;
	}

}
