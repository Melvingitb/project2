import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest{
    @Test
    public void testconvertToPostfix(){
        assertEquals("ab*ca-/de*+", convertToPostfix(y));
    }
}