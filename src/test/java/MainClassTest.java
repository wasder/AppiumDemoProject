import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainClassTest {

    @Test
    void testGetLocalNumber() {
        int expected = 14;
        int actual = new MainClass().getLocalNumber();
        assertEquals(expected, actual ,"Method getLocalNumber() doesn't return " + expected);
    }
}
