import java.util.HashMap;

/**
 * An Agent is a specialisation of a message. An Agent
 * spreads information among Nodes. An is instantiated with
 * a certain probability at nodes after detected events.
 * An Agent maintains a routing map with 'his' event and all
 * further events he learns about.
 */
public class Agent extends Message
{
    private HashMap<Event, Integer> routingMap;

    public Agent(int TTL, Event event, int nodeId, int numRecentNodes){

        super(TTL, numRecentNodes);
        this.routingMap = new HashMap<Event, Integer>();

        // clone the event and put into routing map
        try
        {
            this.routingMap.put((Event)event.clone(), nodeId);
        } catch ( java.lang.CloneNotSupportedException e)
        {
            System.out.println("event clone error");
        }

        addRecentNodeId(nodeId);

    }

    public void agentAction()
    {

    }

    public void updateRoutingMap(Node node)
    {

    }

    public void updateRecentNodes(Node node)
    {

    }

    public void incrementEventDistance(Event event)
    {

    }

}
