import java.util.ArrayList;
import java.util.HashMap;
import java.util.Deque;
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
    public int queryResendWait;
    private int nodeId;
    private int numRecentNodes;
    private ArrayList<Node> neighbourIds;
    private boolean busyState;
    private HashMap<Event, Integer> eventMap;
    private Deque<Message> messageQueue;
    private HashMap<Integer, Query> queryResendMap;

    /**
     * Constructor without periodQuery
     * @param position object of type Position that determines location of Node by x/y coordinates
     * @param probAgent probability value between 0 and 1. Probability that an agent is created after occurrence
     *                  of an event
     */
    public Node(int nodeId,
                Position position,
                int currentTime,
                double probAgent,
                int ttlAgent,
                int numRecentNodes)
    {

        // Exception Handling

        if (nodeId < 0)
            throw new IllegalArgumentException("nodeId can't be negative");

        if (currentTime < 0)
            throw new IllegalArgumentException("currentTime can't be negative");

        if (probAgent > 1 || probAgent < 0)
            throw new IllegalArgumentException("probabilities must be a number between 0 and 1");

        if (ttlAgent < 0)
            throw new IllegalArgumentException("TTL's can't be negative");

        if (numRecentNodes < 0)
            throw new IllegalArgumentException("numRecentNodes can't be negative");


        this.nodeId = nodeId;
        this.neighbourIds = new ArrayList<Node>();
        this.eventMap = new HashMap<Event, Integer>();
        this.messageQueue = new LinkedList<Message>();
        this.queryResendMap = new HashMap<Integer, Query>();
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

    public void setQueryResendWait(int time){
        this.queryResendWait = time;
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
    public int getX(){
        return this.position.getX();
    }

    public int getY(){
        return this.position.getY();
    }

    public void resetBusyState() { this.busyState = false; }

    public ArrayList<Node> getNeighbourIds(){ return this.neighbourIds; }

    public Deque<Message> getMessageQueue(){ return this.messageQueue; }

    public HashMap<Event, Integer> getEventMap(){ return this.eventMap; }

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

        // Check if a due Query is in the resendQuery Map. If so resend the Query
        // todo In the current implementation just one resend is requested. This could be
        // todo implemented more generic in the future to have number of resends as parameter
        if(this.queryResendMap.containsKey(this.currentTime)){
            Integer eventId = this.queryResendMap.remove(this.currentTime).getQueryEventId();
            this.messageQueue.add(new Query(this.ttlQuery, eventId, this, this.numRecentNodes));
        }


        // do message action
        this.messageQueue.element().messageAction(this);

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

        this.currentTime++;


    }

    public void newQuery(int eventId){
        this.messageQueue.add(new Query(this.ttlQuery, eventId, this, this.numRecentNodes));
        this.queryResendMap.put(this.currentTime + this.queryResendWait, (Query) this.messageQueue.getLast());
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

    public void removeQueryFromResend(Query removeQuery){
        this.queryResendMap.values().remove(removeQuery);
    }

}
