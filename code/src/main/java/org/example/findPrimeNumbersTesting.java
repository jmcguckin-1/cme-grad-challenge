package org.example;
import org.junit.*;

import static org.junit.Assert.*;

public class findPrimeNumbersTesting {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testFindPrimeNumbersHappy() throws Exception {
        String sequence = "12345";
        String expectedMessage = "Prime Numbers in sequence: 2,23,3,5";
        String realMessage = Main.findPrimeNumbers(sequence);
        assertEquals(expectedMessage, realMessage);
    }

    @Test
    public void testFindPrimeNumbersHappyTwo() throws Exception {
        String sequence = "56789";
        String expectedMessage = "Prime Numbers in sequence: 5,67,7,89";
        String realMessage = Main.findPrimeNumbers(sequence);
        assertEquals(expectedMessage, realMessage);
    }

    @Test
    public void testFindPrimeNumbersLetters() {
        try {
            String sequence = "abcd";
            String numbers = Main.findPrimeNumbers(sequence);

        } catch(Exception e) {
            assertEquals("Sequence should not contain letters", e.getMessage());

        }

    }

    @Test
    public void testFindPrimeNumbersBlankSpace() {
        try {
            String sequence = "1 2 3";
            String numbers = Main.findPrimeNumbers(sequence);

        } catch(Exception e) {
            assertEquals("Sequence should not contain blank spaces", e.getMessage());

        }

    }

    @Test
    public void testZeroisPrime() throws Exception {
        int num = 0;
        boolean prime = Main.isPrime(num);
        assertFalse(prime);
    }

    @Test
    public void testOneisPrime() throws Exception {
        int num = 0;
        boolean prime = Main.isPrime(num);
        assertFalse(prime);
    }

    @Test
    public void testTwoisPrime() throws Exception {
        int num = 2;
        boolean prime = Main.isPrime(num);
        assertTrue(prime);
    }


}
