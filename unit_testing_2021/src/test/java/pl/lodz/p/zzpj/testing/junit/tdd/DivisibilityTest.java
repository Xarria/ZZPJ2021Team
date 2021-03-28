package pl.lodz.p.zzpj.testing.junit.tdd;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DivisibilityTest {

    private Divisibility divisibility;
    private Random rand;

    @BeforeClass
    public static void beforeClass() {

    }

    @Before
    public void setUp() {
        divisibility = new Divisibility();
        rand = new Random();
    }

    @Test(expected = CantByZeroException.class)
    public void checkIfIsDivisibleException() throws CantByZeroException {
        int numerator = rand.nextInt();
        int denominator = 0;

        divisibility.isDivisible(numerator, denominator);
    }

    @Test
    public void checkIfIsDivisible() throws CantByZeroException {
        int numerator1 = 6;
        int numerator2 = 5;
        int denominator = 3;

        assertTrue(divisibility.isDivisible(numerator1, denominator));
        assertFalse(divisibility.isDivisible(numerator2, denominator));
    }

}