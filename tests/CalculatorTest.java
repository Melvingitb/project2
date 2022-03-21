import org.junit.jupiter.api.Test;

import Application.Calculator;

import static org.junit.Assert.assertEquals;

public class CalculatorTest{
    //Calculator tester = new Calculator();
    @Test
    void testconvertToPostfix(){
        //String q = "a/b*(c+(d-e))";
        //String m = "a-b+c";
        String expected = "abca-/de+";
        String actual = Calculator.convertToPostfix("ab/(c-a)+de");
        assertEquals(expected, actual);

        String expected2 = "ab/cde-+*";
        String actual2 = Calculator.convertToPostfix("a/b*(c+(d-e))");
        assertEquals(expected2, actual2);

        String expected3 = "ab-c+";
        String actual3 = Calculator.convertToPostfix("a-b+c");
        assertEquals(expected3, actual3);

    }
    @Test
    void testevaluatePostfix(){
        int expected = 33;
        int actual = Calculator.evaluatePostfix("23*42-/56*+");
        assertEquals(expected, actual);

        int expected2 = 2;
        int actual2 = Calculator.evaluatePostfix("24+3/");
        assertEquals(expected2, actual2);

        int expected3 = 0;
        int actual3 = Calculator.evaluatePostfix("24/");
        assertEquals(expected3, actual3);
    }
}