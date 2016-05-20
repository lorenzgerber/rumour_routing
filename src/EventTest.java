import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest
{
    @Test
    public void testConstructor() {
        assertNotNull(new Event(7, 7));
    }
}
