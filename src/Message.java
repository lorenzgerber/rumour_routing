import java.util.Deque;
import java.util.LinkedList;

/**
 * Messages are sent between nodes. Message is an
 * abstract class. A Message knows how many time steps/jumps
 * it has travelled. A Message knows it's TTL and it has
 * an array with id's of recent visited Nodes.
 */
public abstract class Message
{

    private int TTL;
    protected Deque<Integer> recentNodes;
    private int numRecentNodes;

    public Message(int TTL, int numRecentNodes)
    {
        this.TTL = TTL;
        this.recentNodes = new LinkedList<Integer>();
        this.numRecentNodes = numRecentNodes;

    }

    // returns false when TTL is zero
    public boolean checkTTL()
    {
        if(this.TTL == 0){
            return false;
        }

        return true;
    }


    public void reduceTTL(){
        this.TTL--;
    }

    public void addRecentNodeId(int nodeId)
    {
        recentNodes.add(nodeId);
        while ( this.recentNodes.size() > this.numRecentNodes){
            this.recentNodes.remove();
        }
    }

    public Deque<Integer> getRecentNodes(){
        return this.recentNodes;
    }

    public void messageAction(){

    }

    public Node nextNode(Node currentNode){

        return currentNode;
    }

    public void onSendAction(){

    }

}
