import java.util.ArrayList;

/**
 * Messages are sent between nodes. Message is an
 * abstract class. A Message knows how many time steps/jumps
 * it has travelled. A Message knows it's TTL and it has
 * an array with id's of recent visited Nodes.
 */
public abstract class Message
{

    private int TTL;
    private ArrayList<Integer> recentNodes;

    public Message(int TTL)
    {
        this.TTL = TTL;

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

    //Sets alla references to null adn empty the list so garbage collector can take care.
    public void destructor()
    {
        recentNodes.clear();
        recentNodes.trimToSize();
    }


}
