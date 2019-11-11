package Lab4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JuliaDateTest {
	
	public double getDaysTest(int day, int month, int year) {
		JuliaDate jd = new JuliaDate(day,month,year);
		return jd.getDays();
	}
	
	public double getFractionTest(int hour, int minute, int second) {
		JuliaDate jd = new JuliaDate(1,1,2000,hour,minute,second);
		System.out.println(jd.getTime());
		return jd.getTime();
	}

	@Test
	void testCalculateDays() {
		
		
		assertEquals(getDaysTest(17, 11,1858),2400000.5);
		assertEquals(getDaysTest(31,12,1899),2415019.5);
		assertEquals(getDaysTest(1, 1, 2000),2451544.5);
		assertEquals(getDaysTest(11,11,2019),2458798.5);

		
	}

	@Test
	void testCalculateFraction() {
		assertEquals(getFractionTest(0, 0, 0), 0);
		assertEquals(getFractionTest(24, 0, 0), 1);
		assertEquals(getFractionTest(12, 0, 0), 0.5);
		assertEquals(getFractionTest(20, 0, 0), 0.83333);
		
		assertEquals(getFractionTest(5, 6, 7), 0.21258);
		assertEquals(getFractionTest(17, 0, 7), 0.70841);
	}

//	@Test
//	void testGetFullDate() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void testGregorian(){
		JuliaDate greg = new JuliaDate(15,10,1582,0,0,0);
		greg.setGregorian(true);
		
		JuliaDate julia = new JuliaDate(4,10,1582,24,0,0);
		julia.setGregorian(false);
		
		assertEquals(greg.getFullDate(), julia.getFullDate());
	}

}
