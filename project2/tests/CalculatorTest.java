import org.junit.jupiter.api.Test;

public class CalculatorTest{
    @Test
    void testconvertToPostfix(){
        assertEquals("ab*ca-/de*+", Calculator.evaluatePostfix("a*b/(c-a)+d*e"));
    }
}