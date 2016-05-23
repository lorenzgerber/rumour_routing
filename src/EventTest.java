
import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest
{
    @Test
    public void testConstructor() {
        assertNotNull(new Event(7, 7));
    }

    @Test
    public void testIncreaseTime(){
        int time = 12;
        int time2 = 13;
        time++;
        assertEquals(time,time2);
    }
}

