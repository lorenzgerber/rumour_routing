/**
 * Messages are sent between nodes. Message is an
 * abstract class. A Message knows how many time steps/jumps
 * it has travelled. A Message knows it's TTL and it has
 * an array with id's of recent visited Nodes.
 */
public class Message
{
    private int steps;
    private int maxSteps;
    private int[] recentNodes;

}
