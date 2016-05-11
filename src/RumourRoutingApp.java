import java.util.ArrayList;
import java.util.Collections;

/**
 * RumourRoutingApp is the main program that initiates the whole
 * experiment.
 */
public class RumourRoutingApp
{
    public static void main( String[] args )
    {

        // Setting Parameters

        int NODES_X             = 50;
        int NODES_Y             = 50;
        int NO_NODES            = NODES_X * NODES_Y;
        double NEW_EVENTS       = 0.0001;
        int NODE_RANGE          = 15;
        double PROB_AGENT       = 0.5;
        int TTL_AGENT           = 50;
        int TTL_QUERY           = 50;
        int QUERY_NODES         = 4;
        int QUERY_PERIODICITY   = 400;
        int TIMESTEPS           = 10000;

        Environment myEnv = new Environment();

        // Create and Position Nodes
        for ( int xGrid = 0; xGrid < NODES_X; xGrid++)
        {
            for ( int yGrid = 0; yGrid < NODES_Y; yGrid++ )
            {
                myEnv.addNode(new Node(new Position(xGrid * 10, yGrid * 10), PROB_AGENT));
            }
        }

        // Set Query nodes
        ArrayList<Integer> queryNodes = new ArrayList<Integer>(NO_NODES);
        for ( int fillList = 0; fillList < NO_NODES; fillList++)
        {
            queryNodes.add(fillList, fillList);
        }

        Collections.shuffle(queryNodes);

        for (int noQueryNode = 1; noQueryNode < QUERY_NODES; noQueryNode++ )
        {
            myEnv.setQueryPeriod(noQueryNode, QUERY_PERIODICITY);
        }

        // Create Neighbour List
        // for every Node
        myEnv.neighbourInit(NODE_RANGE);

        // Run Experiment
        for (int timeStepper = 0; timeStepper < TIMESTEPS; timeStepper++)
        {
            myEnv.advanceTime();
        }

        System.out.println("Experiment finished");
    }

}
