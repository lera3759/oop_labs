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

}
