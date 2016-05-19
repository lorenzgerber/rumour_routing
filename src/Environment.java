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

    public Environment(double newEventProb)
    {
        this.nodeList = new ArrayList<Node>();
        this.eventList = new ArrayList<Event>();
        this.newEventProb = newEventProb;
        this.time = 0;

    }

    public int getLengthNodes(){
        return this.nodeList.size();
    }

    public void setPeriodQuery(int noNodes, int periodicity){
        this.nodeList.get(noNodes).setPeriodQuery(periodicity);
    }

    public void setTTLQuery(int noNodes, int ttlQuery){
        this.nodeList.get(noNodes).setTTLQuery(ttlQuery);
    }

    public int getTime(){
        return this.time;
    }


    public void neighbourInit(double maxDistance)
    {
        for ( int fromNode = 0; fromNode < this.getLengthNodes(); fromNode++ ){
            for(int toNode = 0; toNode < this.getLengthNodes(); toNode++ ){

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
