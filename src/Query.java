import java.util.Stack;

/**
 * A query is a specialisation of a Message.
 * A query is instantiated at a node and
 * travels the nodes in search of a specific
 * event. When the query finds a route to the
 * event, it follows it to the node where the
 * event happend. Then it returns to his node
 * of orgin, reporting success in finding the
 * event.
 */

public class Query extends Message
{
    private int queryEvent;
    private Stack<Node> route;
    private enum queryMode {SEARCH, TRACK, HOMING};
    private queryMode activeMode;
    private Node destNode;

    public Query(int ttlQuery, int eventId, Node currentNode, int numRecentNodes){

        super(ttlQuery, numRecentNodes);
        this.route = new Stack<Node>();

        this.queryEvent = eventId;
        this.route.push(currentNode);

        addRecentNodeId(currentNode.getNodeId());

        this.activeMode = queryMode.SEARCH;

    }

    public void messageAction(Node currentNode) {
        switch (activeMode) {
            case SEARCH:{
                //todo: If we are in Search mode, check whether the current node has a routing for the eventId we're looking for
                if(checkEvent(currentNode)){
                    this.activeMode = queryMode.TRACK;
                    System.out.println("we found the track");
                }

            }
            case TRACK:{
                //todo: if we are in Track mode, did we reach the node that has our event?
                for(Object checkEvent : currentNode.getEventMap().keySet()){
                    if(((Event)checkEvent).getEventId() == this.queryEvent){
                        if (((Event)checkEvent).getDistance() == 0){
                            System.out.println("we found the event node");
                            this.activeMode = queryMode.HOMING;
                        }
                    }
                }



            }
            case HOMING:{
                //todo: if we are in Homing mode, did we reach back to our home node / is the stack empty

            }
        }

    }

    public void onSendAction() {
        switch (activeMode) {
            case SEARCH: {

            }
            case TRACK: {

            }
            case HOMING: {
                //while (!route.empty()) {

                    //nextNode(route.pop());

                //}
            }

        }
    }
    public Node nextNode(Node currentNode) {
        switch (activeMode){
            case SEARCH: {
                if (activeMode == queryMode.SEARCH) {
                    for (Node checkNode : currentNode.getNeighbourIds()) {
                        if (!currentNode.getMessageQueue().element().getRecentNodes().contains(checkNode.getNodeId())) {
                            if (!checkNode.getBusyState()) {
                                //todo should find a better place to add nodeId's to the route stack
                                this.route.push(checkNode);
                                return checkNode;
                            }

                        }
                    }
                }
            }

            case TRACK: {
                for(Object findEvent : currentNode.getEventMap().keySet()){
                    if(((Event)findEvent).getEventId()==this.queryEvent){
                        Integer findNodeId = (Integer) currentNode.getEventMap().get((Event)findEvent);
                        for(Node findNode : currentNode.getNeighbourIds()){
                            if(findNode.getNodeId() == findNodeId){
                                //System.out.println("we found the next tracking node");
                                if (!findNode.getBusyState()){
                                    //todo should find a better place to add nodeId's to the route stack
                                    this.route.push(findNode);
                                    return findNode;
                                }

                            }
                        }
                    }
                }

                //System.out.println("next node tracking mode");
            }

            case HOMING: {
                //System.out.println("next node homing mode");

            }
        }

        return currentNode;

    }


    public boolean checkEvent(Node node)
    {

        for(Object checkEvent : node.getEventMap().keySet()){
            if(((Event)checkEvent).getEventId()==this.queryEvent){
                return true;
            }
        }
        return false;
    }

}
