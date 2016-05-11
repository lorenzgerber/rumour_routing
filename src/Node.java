import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;


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

    private Position position;
    private int probAgent;
    private int periodQuery;
    private int nodeId;
    private ArrayList<Integer> neighbourIds;
    private boolean busyState;
    private Map<Integer, Integer> eventMap;
    private Queue<Message> messageQueue;


}
