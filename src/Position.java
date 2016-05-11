/**
 * Coordinate based position for nodes.
 * Used to calculate the node neighbours
 * based on distance.
 */
public class Position
{
    private final int x;
    private final int y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
