package Lab4;

public interface DateADT {
	public void setDate(int day, int month, int year);
	public void setDay(int day);
	public void setMonth(int month);
	public void setYear(int year);
	
	public void setTime(int second, int minute, int hour);
	public void setSecond(int second);
	public void setMinute(int minute);
	public void setHour(int hour);
	
	public int getDay();
	public int getMonth();
	public int getYear();
	
	public int getSecond();
	public int getMinute();
	public int getHour();
	
	public double getDays();
	public double getTime();
	public double getFullDate();
	
	
	public void now();
	
	
	
}


