package Lab5;

import java.io.IOException;

public class CalculatorTest {

    static Lab5.ExtendedCalculator calc = new Lab5.ExtendedCalculator();
    static int testNumber = 0;

    public static void check(String s, String expected) {

        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) == '0')
                    || (s.charAt(i) == '1')
                    || (s.charAt(i) == '2')
                    || (s.charAt(i) == '3')
                    || (s.charAt(i) == '4')
                    || (s.charAt(i) == '5')
                    || (s.charAt(i) == '6')
                    || (s.charAt(i) == '7')
                    || (s.charAt(i) == '8')
                    || (s.charAt(i) == '9'))

                calc.numberPressed(Character.toString(s.charAt(i)));

            else if (s.charAt(i) == '+') calc.engine.plus();
            else if (s.charAt(i) == '-') calc.engine.minus();
            else if (s.charAt(i) == '*') calc.engine.multiply();
            else if (s.charAt(i) == '/') calc.engine.divide();
            else if (s.charAt(i) == 'C') calc.engine.clear();
            else if (s.charAt(i) == '=') calc.engine.equals();
        }
        calc.redisplay();

        testNumber += 1;

        if (("" + calc.engine.getDisplayValue()).equals(expected)==true)
            System.out.println("Test " + testNumber + " correct");
        else System.out.println("Test " + testNumber + " incorrect");

    }

    public static void main(String[] args) {
        calc.show();

        String test1 = "3*5+2=";
        check(test1,"17");

        System.out.println("Press enter to continue");
        try {
            System.in.read(); // press enter to continue
        } catch (IOException e) {
            e.printStackTrace();
        }

        String test2 = "4*6+2-1*3=";
        check(test2, "75");
    }
}

