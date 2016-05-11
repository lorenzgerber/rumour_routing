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
public class Query
{
    private int queryEvent;
    private Stack<Integer> route;
    private enum status {SEARCH, TRACK, HOMING};
    private Node destNode;

    public Query(int eventId){

    }

    public void queryAction()
    {

    }

    public boolean checkEvent(Node node)
    {
        return false;
    }

    public void homing()
    {

    }

}
