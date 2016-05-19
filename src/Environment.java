import java.util.ArrayList;
import java.util.Random;

/**
 * The environment simulates the real world in which
 * the experiment takes place. The environment knows the time
 * and contains the nodes. The environment advances the time
 * and knows all the events.
 */
public class Environment
{
    private int time;
    private ArrayList<Node> nodeList;
    private ArrayList<Event> eventList;
    private double newEventProb;
    private int eventCounter = 0;

    /**
     * Constructor for Environment
     *
     * @param newEventProb Probability of creating a new
     *                     event per time step and node.
     *                     Probability from 0 to 1.
     * @throws IllegalArgumentException
     */
    public Environment(double newEventProb) throws IllegalArgumentException
    {
        if (newEventProb > 1)
            throw new IllegalArgumentException();

        this.nodeList = new ArrayList<Node>();
        this.eventList = new ArrayList<Event>();
        this.newEventProb = newEventProb;
        this.time = 0;

    }

    /**
     * Returns number of Nodes in Environment
     *
     * @return int number of nodes
     */
    public int numberOfNodes(){
        return this.nodeList.size();
    }

    /**
     * set Query Periodicity of Node
     *
     * By default Nodes have query Periodicity  with number noNode is set to be a Query node.
     * Every n:th time step a query event is started
     * where n is the periodicity argument.
     *
     * @param noNodes int number of Query node in Environment
     * @param periodicity period for creating query nodes
     */
    public void setPeriodQuery(int noNodes, int periodicity){
        this.nodeList.get(noNodes).setPeriodQuery(periodicity);
    }

    /**
     * Set Query Time To Live (TTL) for Node with number
     * 'noNode' in the environment.
     *
     * @param noNodes
     * @param ttlQuery
     */
    public void setTTLQuery(int noNodes, int ttlQuery){
        this.nodeList.get(noNodes).setTTLQuery(ttlQuery);
    }


    /**
     * getter for current Time
     * @return int current time.
     */
    public int getTime(){
        return this.time;
    }


    /**
     * Method to populate the neighbour lists of
     * all nodes in the environment. The method
     * calls the detectNeighbour method of a node
     * to register a neighbour.
     * @param maxDistance int send/receive distance of nodes
     */
    public void neighbourInit(double maxDistance)
    {
        for ( int fromNode = 0; fromNode < this.numberOfNodes(); fromNode++ ){
            for( int toNode = 0; toNode < this.numberOfNodes(); toNode++ ){

                if(fromNode != toNode){
                    int x1 = this.nodeList.get(fromNode).position.getX();
                    int x2 = this.nodeList.get(toNode).position.getX();
                    int y1 = this.nodeList.get(fromNode).position.getY();
                    int y2 = this.nodeList.get(toNode).position.getY();

                    double distance = Math.sqrt(Math.pow(Math.abs(x2 - x1),2) + Math.pow(Math.abs(y2 - y1),2));
                    if (distance <= maxDistance){
                        this.nodeList.get(fromNode).detectNeighbour(this.nodeList.get(toNode));
                    }
                }
            }
        }

    }

    /**
     * Advance Time calls every node in the
     * environment to make it's move and then advances
     * the experimental time by 1.
     */
    public void advanceTime()
    {
        for(Node currentNode : nodeList){
            randomEvent(currentNode);
            periodQuery(currentNode);
            currentNode.makeMove();
        }

        // after one time round, reset all busy states
        for(Node currentNode: nodeList){
            currentNode.resetBusyState();
        }

        this.time++;
    }

    /**
     * add a Node object to the Environment
     * @param node
     */
    public void addNode(Node node)
    {
        this.nodeList.add(node);
    }

    /**
     * This method is called in every advanceTime() step
     * to randomly create a new event. It takes a Node object
     * as parameter
     * @param node  Node for which to randomly create an event
     */
    public void randomEvent(Node node)
    {
        Random generator;
        int oneOutOf = (int) (1 / this.newEventProb);
        generator = new Random();
        if(generator.nextInt(oneOutOf) == 1){

            Event newEvent = new Event(this.eventCounter, this.time);
            this.eventList.add(newEvent);
            node.detectEvent(newEvent);
            this.eventCounter++;
        }

    }

    /**
     * Compare experimental time with
     * Query periodicity of each node in the
     * Environment. If period and current time matches,
     * a random existing event is chosen to be searched
     * for and a new query is instantiated and put into
     * the message queue of the current node.
     * @param node Node object to check
     */
    public void periodQuery(Node node){

        if(node.getPeriodQuery() == 0)
            return;

        if (this.time % node.getPeriodQuery() == 0 && this.time != 0){

            Random randomGenerator = new Random();
            if(this.eventList.size() != 0){
                int index = randomGenerator.nextInt(this.eventList.size());
                Event item = this.eventList.get(index);
                node.newQuery(item.getEventId());
            }



        }
    }

}
