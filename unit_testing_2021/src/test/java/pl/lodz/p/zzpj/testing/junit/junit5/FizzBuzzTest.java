package pl.lodz.p.zzpj.testing.junit.junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.lodz.p.zzpj.testing.junit.tdd.FizzBuzz;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("fizzBuzzClass")
@DisplayName("FizzBuzzJUnit5Tests")
public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeAll
    public static void beforeAll() {

    }

    @BeforeEach
    public void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Tag("slow")
    @Disabled
    @Test
    public void getFizzBuzzStringTestSlow() {
        Assumptions.assumeFalse(fizzBuzz == null);
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

    @Tag("fast")
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, 13, 15, 22, 30})
    public void getFizzBuzzStringTestFast(int number) {
        Assumptions.assumeTrue(fizzBuzz != null);
        if (number % 15 == 0) {
            assertEquals("FizzBuzz", fizzBuzz.getFizzBuzzNumber(number));
        } else if (number % 5 == 0) {
            assertEquals("Buzz", fizzBuzz.getFizzBuzzNumber(number));
        } else if (number % 3 == 0) {
            assertEquals("Fizz", fizzBuzz.getFizzBuzzNumber(number));
        } else {
            assertEquals(String.valueOf(number), fizzBuzz.getFizzBuzzNumber(number));
        }
    }

}

