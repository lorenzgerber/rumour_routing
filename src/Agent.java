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

    public void messageAction(Node currentNode)
    {
        HashMap<Event, Integer> nodeEvents = currentNode.getEventMap();
        // cycling through the Routing map of the Agent
        for( Event agentEventKey : this.routingMap.keySet()){
            if(nodeEvents.containsKey(agentEventKey)){
                for(Event NodeEventKey : nodeEvents.keySet()){
                    if(NodeEventKey.equals(agentEventKey)){
                        if(NodeEventKey.getDistance()>agentEventKey.getDistance()){
                            try
                            {
                                nodeEvents.put((Event)agentEventKey.clone(), this.routingMap.get(agentEventKey));
                            } catch(CloneNotSupportedException e){
                                System.out.println("cloning problem");
                            }

                        }
                    }
                }
            } else {
                try {
                    nodeEvents.put((Event)agentEventKey.clone(), this.routingMap.get(agentEventKey));
                } catch (CloneNotSupportedException e) {
                    System.out.println("cloning problem");
                }
            }

        }

        // cycling through the nodeEvents
        for( Event nodeEventKey : nodeEvents.keySet()){
            if(this.routingMap.containsKey(nodeEventKey)){
                for(Event agentEventKey : this.routingMap.keySet()){
                    if(agentEventKey.equals(nodeEventKey)){
                        if(agentEventKey.getDistance() > nodeEventKey.getDistance()){
                            try
                            {
                                this.routingMap.put((Event)nodeEventKey.clone(), nodeEvents.get(nodeEventKey));
                            } catch(CloneNotSupportedException e){
                                System.out.println("cloning problem");
                            }

                        }
                    }
                }

            } else {
                try{
                    this.routingMap.put((Event) nodeEventKey.clone(), nodeEvents.get(nodeEventKey));
                } catch (CloneNotSupportedException e){
                    System.out.println("cloning problem");
                }
            }
        }

    }

    @Override
    public void onSendAction(){

        this.updateRoutingMapEventDist();
        this.updateRoutingMapNodeId();

    }



    @Override
    public Node nextNode(Node currentNode){

        for(Node checkNode : currentNode.getNeighbourIds()){
            if(!currentNode.getMessageQueue().element().getRecentNodes().contains(checkNode.getNodeId())){
                if(!checkNode.getBusyState()){
                    return checkNode;
                }

            }
        }

        return currentNode;
    }


    public void updateRoutingMapEventDist()
    {
        for(Event updateEventDist : this.routingMap.keySet()){
            updateEventDist.increaseDistance();

        }

    }

    public void updateRoutingMapNodeId()
    {
        for(Event updateEventNode : this.routingMap.keySet()){

            int newId = this.recentNodes.getLast();
            this.routingMap.put(updateEventNode, newId);

        }


    }



}
