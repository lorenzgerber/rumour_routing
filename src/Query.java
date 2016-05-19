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
    private Stack<Integer> route;
    private enum queryMode {SEARCH, TRACK, HOMING};
    private queryMode activeMode;
    private Node destNode;

    public Query(int ttlQuery, int eventId, int nodeId, int numRecentNodes){

        super(ttlQuery, numRecentNodes);
        this.route = new Stack<Integer>();

        this.queryEvent = eventId;
        this.route.push(nodeId);

        addRecentNodeId(nodeId);

        this.activeMode = queryMode.SEARCH;

    }

    public void messageAction()
    {

    }

    public void onSendAction()
    {

    }

    public Node nextNode(Node currentNode)
    {
        return currentNode;
    }

    public boolean isSearchMode(){
        return activeMode == queryMode.SEARCH;
    }

    public boolean isTrackMode(){
        return activeMode == queryMode.TRACK;
    }

    public boolean isHomingMode(){
        return activeMode == queryMode.HOMING;
    }

    public boolean checkEvent(Node node)
    {
        return false;
    }
    

}
