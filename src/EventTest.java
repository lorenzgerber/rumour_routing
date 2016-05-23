
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * EventTest
 *
 * Tests for Event
 */
public class EventTest
{
    /**
     * Test to see if the constructor is null
     */
    @Test
    public void testConstructor() {
        assertNotNull(new Event(7, 7));
    }

    /**
     * Test to see if getEventId can find the event ID
     */
    @Test
    public void testGetEventId(){
        Event event = new Event(3,5);
        assertEquals(event.getEventId(),3);
    }

    /**
     * Test to see if getDistance works after increasing distance
     */
    @Test
    public void testGetDistance(){
        Event event = new Event(3,5);
        event.increaseDistance();
        event.increaseDistance();
        assertEquals(event.getDistance(),2);
    }

    /**
     * Test to see if getZeroTime can figure out the event start time
     */
    @Test
    public void testGetZeroTime(){
        Event event = new Event(3,5);
        assertEquals(event.getZeroTime(),5);
    }

}

