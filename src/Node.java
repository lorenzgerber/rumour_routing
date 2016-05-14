import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;


/**
 * Node is the main component of the tested system. The
 * Node is placed in the environment and knows his coordinate
 * based position. During an initialization step, the Node
 * Gets to know his neighbour nodes in reach. The node sends
 * receives messages to/from his neighbour nodes and detects
 * events in the environment.
 */
public class Node
{

    public Position position;
    private double probAgent;
    public int periodQuery;
    private int nodeId;
    private ArrayList<Node> neighbourIds;
    private boolean busyState;
    private HashMap<Event, Integer> eventMap;
    private Queue<Message> messageQueue;

    public Node(int nodeId, Position position, double probAgent, int periodQuery)
    {

        this.neighbourIds = new ArrayList<Node>();
        this.eventMap = new HashMap<Event, Integer>();
        this.position = position;
        this.probAgent = probAgent;
        this.periodQuery = periodQuery;

    }




    /**
     * Constructor without periodQuery
     * @param position object of type Position that determines location of Node by x/y coordinates
     * @param probAgent probability value between 0 and 1. Probability that an agent is created after occurence
     *                  of an event
     */
    public Node(int nodeId, Position position, double probAgent)
    {
        this.nodeId = nodeId;
        this.neighbourIds = new ArrayList<Node>();
        this.eventMap = new HashMap<Event, Integer>();
        this.position = position;
        this.probAgent = probAgent;
    }


    public void setPeriodQuery(int period)
    {

    }

    public void detectNeighbour(Node node)
    {
        this.neighbourIds.add(node);
    }

    public void detectEvent(Event event)
    {

        // todo check if the deep cloning really works.
        try
        {
             this.eventMap.put((Event)event.clone(), this.nodeId);
        } catch ( java.lang.CloneNotSupportedException e)
        {
            // todo have to implement some serious exception handling here
            System.out.println("shit happend");
        }
        
    }

    public void makeMove()
    {

    }

    public void sendMessage()
    {

    }

    public void receiveMessage(Message message)
    {

    }

    public void messageAction(Message message)
    {

    }


}
