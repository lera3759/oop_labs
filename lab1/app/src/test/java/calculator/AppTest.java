package calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AppTest {
    StringCalculator calculator = new StringCalculator();

    @Test
    public void emptyString() {
        assertEquals(0, calculator.Add(""));
    }

    @Test
    public void singleNumber() {
        assertEquals(1, calculator.Add("1"));
    }

    @Test
    public void twoNumbers() {
        assertEquals(3, calculator.Add("1,2"));
    }

    @Test
    public void multipleNumbers() {
        assertEquals(20, calculator.Add("1,2,3,4,10"));
    }

    @Test
    public void differentDelimeter() {
        assertEquals(3, calculator.Add("//;\n1;2"));
        assertEquals(3, calculator.Add("//*\n1*2"));
        assertEquals(150, calculator.Add("//h\n10,20\n30h40h50"));
        assertEquals(6, calculator.Add("//7\n17273"));
    }

    @Test
    public void ignoreLargeNumbers() {
        assertEquals(1010, calculator.Add("10,1001\n1000"));
        assertEquals(1999, calculator.Add("//;\n1000;999\n1001"));
    }

    @Test
    public void delimeterOfAnyLength() {
        assertEquals(6, calculator.Add("//[***]\n1***2***3"));
        assertEquals(6, calculator.Add("//[123]\n112321233"));
    }

    @Test
    public void multipleDelimeters() {
        assertEquals(6, calculator.Add("//[*][;]\n1*2;3"));
        assertEquals(15, calculator.Add("//[;][&]\n1&2\n3,4;5"));
    }

    @Test
    public void multipleDelimetersOfAnyLength() {
        assertEquals(6, calculator.Add("//[**][***]\n1**2***3"));
        assertEquals(15, calculator.Add("//[;][&&&]\n1;2&&&3,4\n5"));
    }

}
