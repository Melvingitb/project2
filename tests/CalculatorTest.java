import org.junit.jupiter.api.Test;

import Application.Calculator;

import static org.junit.Assert.assertEquals;

public class CalculatorTest{
    //Calculator tester = new Calculator();
    @Test
    void testconvertToPostfix(){
        String expected = "abca-/de+";
        String actual = Calculator.convertToPostfix("ab/(c-a)+de");
        assertEquals(expected, actual);


    }
    @Test
    void testevaluatePostfix(){
        int expected = 33;
        int actual = Calculator.evaluatePostfix("2342-/56+");
        assertEquals(expected, actual);
    }
}