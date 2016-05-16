import java.util.ArrayList;

/**
 * Messages are sent between nodes. Message is an
 * abstract class. A Message knows how many time steps/jumps
 * it has travelled. A Message knows it's TTL and it has
 * an array with id's of recent visited Nodes.
 */
public abstract class Message
{
    private int steps;
    private int maxSteps;
    private int TTL = 50;
    private ArrayList<Integer> recentNodes;

    public Message(int maxSteps, int steps)
    {
        this.steps = steps;
        this.maxSteps = maxSteps;

    }

    //For each call decrease Time to live with 1, if zero destroy message.
    public void checkTTL()
    {
        TTL = TTL -1;
        if(TTL == 0)
        {
            destructor();
        }
    }

    public void addRecentNodeId(int nodeId)
    {
        recentNodes.add(nodeId);
    }

    public void messageAction()
    {

    }

    public void destructor()
    {

    }


}
