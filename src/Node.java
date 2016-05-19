import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

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
    public int currentTime;
    private double probAgent;
    public int ttlAgent;
    public int periodQuery;
    public int ttlQuery;
    private int nodeId;
    private int numRecentNodes;
    private ArrayList<Node> neighbourIds;
    private boolean busyState;
    private HashMap<Event, Integer> eventMap;
    private Queue<Message> messageQueue;

    public Node(int nodeId,
                Position position,
                int currentTime,
                double probAgent,
                int ttlAgent,
                int ttlQuery,
                int periodQuery,
                int numRecentNodes)
    {

        this.neighbourIds = new ArrayList<Node>();
        this.eventMap = new HashMap<Event, Integer>();
        this.messageQueue = new LinkedList<Message>();
        this.position = position;
        this.probAgent = probAgent;
        this.ttlAgent = ttlAgent;
        this.periodQuery = periodQuery;
        this.ttlQuery = ttlQuery;
        this.numRecentNodes = numRecentNodes;

    }




    /**
     * Constructor without periodQuery
     * @param position object of type Position that determines location of Node by x/y coordinates
     * @param probAgent probability value between 0 and 1. Probability that an agent is created after occurence
     *                  of an event
     */
    public Node(int nodeId,
                Position position,
                int currentTime,
                double probAgent,
                int ttlAgent,
                int numRecentNodes)
    {
        this.nodeId = nodeId;
        this.neighbourIds = new ArrayList<Node>();
        this.eventMap = new HashMap<Event, Integer>();
        this.messageQueue = new LinkedList<Message>();
        this.position = position;
        this.probAgent = probAgent;
        this.ttlAgent = ttlAgent;
        this.numRecentNodes = numRecentNodes;
    }


    public void setPeriodQuery(int period)
    {
        this.periodQuery = period;
    }

    public void setTTLQuery(int ttlQuery){
        this.ttlQuery = ttlQuery;
    }

    public int getNodeId(){
        return (this.nodeId);
    }

    public int getPeriodQuery(){
        return this.periodQuery;
    }

    public boolean getBusyState(){
        return busyState;
    }

    public void resetBusyState() { this.busyState = false; }

    public ArrayList<Node> getNeighbourIds(){ return this.neighbourIds; }

    public Queue<Message> getMessageQueue(){ return this.messageQueue; }

    public HashMap getEventMap(){ return this.eventMap; }


    public void detectNeighbour(Node node)
    {
        this.neighbourIds.add(node);
    }

    public void detectEvent(Event event)
    {

        try
        {
             this.eventMap.put((Event)event.clone(), this.nodeId);
        } catch ( java.lang.CloneNotSupportedException e)
        {
            System.out.println("cloning error");
        }

        //random agent generation
        double randAgent = Math.random();
        if ( randAgent < this.probAgent){
            // add agent to message queue
            this.messageQueue.add(new Agent(this.ttlAgent, event, this.nodeId, this.numRecentNodes));
        }



    }

    public void makeMove()
    {
        // do we have a message in the queue?
        if(this.messageQueue.isEmpty())
            return;

        // do message action
        if(this.messageQueue.element() instanceof Agent){
            ((Agent) this.messageQueue.element()).messageAction(this.eventMap);
        }


        // check TTL of Message if zero, remove
        if(!this.messageQueue.element().checkTTL())
        {
            this.messageQueue.remove();
            return;
        }

        // get destination node
        Node nextNode = this.messageQueue.element().nextNode(this);
        if (!nextNode.equals(this)){
            this.sendMessage(nextNode);
        }



    }

    public void newQuery(int eventId){
        this.messageQueue.add(new Query(this.ttlQuery, eventId, this.nodeId, this.numRecentNodes));
    }

    public void sendMessage(Node destination)
    {
        this.messageQueue.element().onSendAction();
        destination.receiveMessage(this.messageQueue.remove());

    }



    public void receiveMessage(Message message) throws IllegalStateException
    {
        this.messageQueue.add(message);
        message.reduceTTL();
        message.addRecentNodeId(this.nodeId);
        this.busyState = true;
    }

}
