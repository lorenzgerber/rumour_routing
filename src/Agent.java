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


    public void messageAction(HashMap<Event, Integer> nodeEvents)
    {
        for( Event agentEventKey : this.routingMap.keySet()){
            if(nodeEvents.containsKey(agentEventKey)){
                //System.out.println("Node has key already");
                // todo compare distance
            } else {
                nodeEvents.put(agentEventKey, this.routingMap.get(agentEventKey));
            }
        }

        for( Event nodeEventKey : nodeEvents.keySet()){
            if(this.routingMap.containsKey(nodeEventKey)){
                //System.out.println("Agent has key already");
                // todo compare distance
            } else {
                this.routingMap.put(nodeEventKey, nodeEvents.get(nodeEventKey));
            }
        }

    }

    public void onSendAction(){

    }

    // todo not tested yet. Should return the nodeId of a node not recently visited.
    public int nextNode(Node currentNode){

        for(Node checkNode : currentNode.getNeighbourIds()){
            if(!currentNode.getMessageQueue().element().getRecentNodes().contains(checkNode.getNodeId())){
                // todo implement check if next node is busy or not
                return checkNode.getNodeId();
            }
        }

        return -1;
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
