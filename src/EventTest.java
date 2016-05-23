
import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest
{
    @Test
    public void testConstructor() {
        assertNotNull(new Event(7, 7));
    }

    @Test
    public void testGetEventId(){
        Event event = new Event(3,5);
        assertEquals(event.getEventId(),3);
    }

    @Test
    public void testGetDistance(){
        Event event = new Event(3,5);
        event.increaseDistance();
        event.increaseDistance();
        assertEquals(event.getDistance(),2);
    }

    @Test
    public void testGetZeroTime(){
        Event event = new Event(3,5);
        assertEquals(event.getZeroTime(),5);
    }

}

