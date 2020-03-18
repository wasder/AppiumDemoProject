import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsStringIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testGetClassString() {
        String expected = "hello";
        String actual = new MainClass().getClassString();
        assertThat(actual, containsStringIgnoringCase(expected));
    }
}
