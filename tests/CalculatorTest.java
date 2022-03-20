import org.junit.jupiter.api.Test;
//import org.junit.*;
import static org.junit.Assert.AssertEquals;

public class CalculatorTest{
    //Calculator tester = new Calculator();
    @Test
    void testconvertToPostfix(){
        assertEquals("ab*ca-/de*+", Calculator.evaluatePostfix("a*b/(c-a)+d*e"));
    }
}