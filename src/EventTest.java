<<<<<<< HEAD
import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest
{
    @Test
    public void testConstructor() {
        assertNotNull(new Event(0, 7, 7));
    }

    @Test
    public void testIncreaseTime(){
        int time = 12;
        int time2 = 13;
        time++;
        assertEquals(time,time2);
    }
}
=======
import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest
{
    @Test
    public void testConstructor() {
        assertNotNull(new Event(7, 7));
    }
}
>>>>>>> caa89496228d5330379f6ce751704cb58f1006c6
