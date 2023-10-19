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
        assertEquals(150, calculator.Add("//h\n10h20h30h40h50"));
        assertEquals(6, calculator.Add("//7\n17273"));
    }

}
