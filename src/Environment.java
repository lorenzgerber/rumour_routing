import java.util.ArrayList;


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
    private int[] eventIds;

    public Environment()
    {
        this.nodeList = new ArrayList<Node>();

    }

    public int getLengthNodes(){
        return this.nodeList.size();
    }

    public void setQueryPeriod(int noNodes, int periodicity){
        this.nodeList.get(noNodes).setPeriodQuery(periodicity);
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

    }

    public void addNode(Node node)
    {
        this.nodeList.add(node);
    }

    public void detectEvent()
    {

    }

}
