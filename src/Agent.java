import java.util.Map;

/**
 * An Agent is a specialisation of a message. An Agent
 * spreads information among Nodes. An is instantiated with
 * a certain probability at nodes after detected events.
 * An Agent maintains a routing map with 'his' event and all
 * further events he learns about.
 */
public class Agent
{
    private Map<Event, Node> routingMap;

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
