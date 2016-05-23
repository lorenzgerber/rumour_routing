

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * PositionTest
 *
 * Tests for Position
 */

public class PositionTest
{
    /**
     * Checks if getX is able to get values
     */
    @Test
    public void testGetX()
    {
        Position pos = new Position(7,7);
        assertTrue(pos.getX() == 7);
    }

    /**
     * Checks if getY is able to get values
     */
    @Test
    public void testGetY()
    {
        Position pos = new Position(7,7);
        assertTrue(pos.getY() == 7);
    }
}
