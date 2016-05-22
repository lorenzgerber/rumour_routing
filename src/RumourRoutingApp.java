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
        int NODES_X                 = 50;
        int NODES_Y                 = 50;
        int NO_NODES                = NODES_X * NODES_Y;
        double NEW_EVENTS           = 0.0001;
        int NODE_RANGE              = 15;
        double PROB_AGENT           = 0.5;
        int TTL_AGENT               = 10;
        int TTL_QUERY               = 50;
        int QUERY_NODES             = 4;
        int QUERY_PERIODICITY       = 400;
        int TIMESTEPS               = 10000;
        int NUMBER_OF_RECENT_NODES  = 5;
        int QUERY_RESEND_WAIT       = TTL_QUERY *8;

        // Counters
        int nodeIdCounter = 0;

        // Exception Handling

        if (NODES_X < 1 || NODES_Y < 1)
            throw new IllegalArgumentException("Sizes of X and Y must be 1 or higher");

        if (NEW_EVENTS > 1 || PROB_AGENT > 1)
            throw new IllegalArgumentException("probabilities must be 1 or less");

        if (TTL_AGENT < 0 || TTL_QUERY < 0)
            throw new IllegalArgumentException("TTL's can't be negative");

        if (QUERY_NODES < 0)
            throw new IllegalArgumentException("Number of querynodes can't be negative");

        if (NODE_RANGE < 0)
            throw new IllegalArgumentException("Node range can't be negative");

        if (TIMESTEPS < 0 || QUERY_PERIODICITY < 0)
            throw new IllegalArgumentException("Times must be positive");


        Environment myEnv = new Environment(NEW_EVENTS);

        // Create and Position Nodes
        for ( int xGrid = 0; xGrid < NODES_X; xGrid++)
        {
            for ( int yGrid = 0; yGrid < NODES_Y; yGrid++ )
            {
                myEnv.addNode(new Node(nodeIdCounter,
                        new Position(xGrid * 10, yGrid * 10),
                        myEnv.getTime(),
                        PROB_AGENT,
                        TTL_AGENT,
                        NUMBER_OF_RECENT_NODES));
                // todo should implement in environment a check that no nodes with the same id can be put into the array list
                nodeIdCounter++;
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
            myEnv.setPeriodQuery(noQueryNode, QUERY_PERIODICITY);
            myEnv.setTTLQuery(noQueryNode, TTL_QUERY);
            myEnv.setQueryResendTime(noQueryNode, QUERY_RESEND_WAIT);
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
