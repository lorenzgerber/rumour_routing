

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by loge on 11/05/16.
 */

public class PositionTest
{
    @Test
    public void testGetX()
    {
        Position pos = new Position(7,7);
        assertTrue(pos.getX() == 7);
    }

    @Test
    public void testGetY()
    {
        Position pos = new Position(7,7);
        assertTrue(pos.getY() == 7);
    }
}
