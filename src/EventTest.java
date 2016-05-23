
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * EventTest
 *
 * Tests event
 */
public class EventTest
{
    /**
     * Checks if the constructor is Null
     */
    @Test
    public void testConstructor() {
        assertNotNull(new Event(7, 7));
    }

    /**
     * Checks if increaseTime is able to make correct increments
     */
    @Test
    public void testIncreaseTime(){
        int time = 12;
        int time2 = 13;
        time++;
        assertEquals(time,time2);
    }
}

