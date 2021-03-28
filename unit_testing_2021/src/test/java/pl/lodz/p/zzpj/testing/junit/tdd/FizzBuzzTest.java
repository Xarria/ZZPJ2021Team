package pl.lodz.p.zzpj.testing.junit.tdd;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeClass
    public static void beforeClass() {

    }

    @Before
    public void setUp() {
        fizzBuzz = new FizzBuzz();
    }


    @Test
    public void shouldReturnFizzBuzzString() {
        for (int i = 0; i < 100; i++) {
            if (i % 15 == 0) {
                assertEquals("FizzBuzz", fizzBuzz.getFizzBuzzNumber(i));
            } else if (i % 5 == 0) {
                assertEquals("Buzz", fizzBuzz.getFizzBuzzNumber(i));
            } else if (i % 3 == 0) {
                assertEquals("Fizz", fizzBuzz.getFizzBuzzNumber(i));
            } else {
                assertEquals(String.valueOf(i), fizzBuzz.getFizzBuzzNumber(i));
            }
        }
    }

}
