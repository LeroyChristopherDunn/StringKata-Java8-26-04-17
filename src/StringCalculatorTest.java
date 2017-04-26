import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public void givenEmptyStringShouldReturn0() throws Exception {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void givenSingleNumberShouldReturnNumber() throws Exception {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    public void givenTwoNumbersShouldReturnSum() throws Exception {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    public void givenMultipleNumbersShouldReturnSum() throws Exception {
        assertEquals(6, StringCalculator.add("1,2,3"));
    }

    @Test
    public void givenCommaOrNewlineDelimiterShouldReturnSum() throws Exception {
        assertEquals(6, StringCalculator.add("1,2\n3"));
    }

    @Test
    public void givenCustomDelimiterShouldReturnSum() throws Exception {
        assertEquals(6, StringCalculator.add("//;\n1;2;3"));
    }

    @Test
    public void givenNegativeNumbersShouldThrowExceptionWithNegativesInExceptionMessages(){
        try{
            StringCalculator.add("1,-2,-3");
            fail();
        }catch (Exception e){
            assertEquals("Negatives not allowed: [-2, -3]", e.getMessage());
        }
    }

    @Test
    public void givenNumbersShouldReturnSumOfNumbersLessThan1000() throws Exception {
        assertEquals(6, StringCalculator.add("1,2000,2,4888,3"));
    }

    @Test
    public void givenMultiCharCustomDelimiterShouldReturnSum() throws Exception {
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void givenMultiSingleCustomDelimiterShouldReturnSum() throws Exception {
        assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void givenMultipleMultiCustomDelimiterShouldReturnSum() throws Exception {
        assertEquals(6, StringCalculator.add("//[***][%%%]\n1***2%%%3"));
    }

}