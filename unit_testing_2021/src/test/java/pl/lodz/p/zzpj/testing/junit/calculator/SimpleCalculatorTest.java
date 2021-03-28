package pl.lodz.p.zzpj.testing.junit.calculator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(JUnitParamsRunner.class)
public class SimpleCalculatorTest {

    private static final double DELTA = 0.000001;
    private SimpleCalculator sut;
    private Random rand;


    @BeforeClass
    public static void beforeClass() {

    }

    @Before
    public void setUp() {
        sut = new SimpleCalculator();
        rand = new Random();
    }


    @Test
    public void shouldAddTwoNumbers() {
        double a = rand.nextDouble();
        double b = rand.nextDouble();

        assertEquals(a + b, sut.add(a, b), DELTA);
    }

    @Test
    @Parameters({"3", "7", "73"})
    public void shouldCheckIfNumberIsPrime(int prime) {
        assertTrue(sut.isPrime(prime));
    }

    @Test
    @Parameters(method = "getTestSet")
    public void shouldCheckIfNumberIsPrimeOrNot(int prime, boolean resultFlag) {
        assertEquals(resultFlag, sut.isPrime(prime));
    }

    public Object[] getTestSet() {
        return $(
                $(3, true),
                $(5, true),
                $(21, false)
        );
    }

    @Test(expected = CannotDivideByZeroException.class)
    public void shouldThrowAnException() throws CannotDivideByZeroException {
        double numerator = rand.nextDouble();
        double denominator = 0;

        sut.divide(numerator, denominator);
    }

    @Test
    public void add() {
        double a = rand.nextDouble();
        double b = rand.nextDouble();

        assertEquals(a + b, sut.add(a, b), DELTA);

        assertEquals(sut.add(b, a), sut.add(a, b), DELTA) ;
    }

    @Test
    public void subtract() {
        double a = rand.nextDouble();
        double b = rand.nextDouble();

        assertEquals(a - b, sut.subtract(a, b), DELTA);

        assertEquals(b - a, sut.subtract(b, a), DELTA);

        assertNotEquals(a - b, sut.subtract(b, a), DELTA);
    }

    @Test
    public void divide() throws CannotDivideByZeroException {
        double numerator = rand.nextDouble();
        double denominator;
        do {
            denominator = rand.nextDouble();
        } while (denominator == 0);

        assertEquals(numerator / denominator, sut.divide(numerator, denominator), DELTA);

    }

    @Test(expected = CannotDivideByZeroException.class)
    public void divideException() throws CannotDivideByZeroException {
        double numerator = rand.nextDouble();
        double denominator = 0;

        sut.divide(numerator, denominator);
    }

    @Test
    public void calculateSquareRoot() throws CannotCalculateSquareRootOfNegativeNumber {
        double a = rand.nextDouble();
        assertEquals(Math.sqrt(a), sut.calculateSquareRoot(a), DELTA);
    }

    @Test(expected = CannotCalculateSquareRootOfNegativeNumber.class)
    public void calculateSquareRootException() throws CannotCalculateSquareRootOfNegativeNumber {
        double a;
        a = -(rand.nextDouble());
        sut.calculateSquareRoot(a);
    }

    @Test
    @Parameters(method = "moduloSetTest")
    public void modulo(int a, int b, int c) {
        assertEquals(c, sut.modulo(a, b));
    }

    private Object[] moduloSetTest() {
        return new Object[] {
                new Object[] { 154, 15, 4 },
                new Object[] { 132, 12, 0 },
                new Object[] { 12, 7, 5 }
        };
    }

    @Test
    @Parameters({ "97, 20" })
    public void isPrime(int a, int b) {
        assertTrue(sut.isPrime(a));
        assertFalse(sut.isPrime(b));
    }
}
