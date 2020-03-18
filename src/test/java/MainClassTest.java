import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainClassTest {

    @Test
    void testGetLocalNumber() {
        int expected = 14;
        int actual = new MainClass().getLocalNumber();
        assertEquals(expected, actual ,"Method getLocalNumber() doesn't return " + expected);
    }

    @Test
    void testGetClassNumber() {
        int expected = 45;
        int actual = new MainClass().getClassNumber();
        assertTrue(actual > expected, "Method getClassNumber() returns " + actual + " which is less than " + expected);
    }
}
