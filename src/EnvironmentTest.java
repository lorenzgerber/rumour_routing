
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;


import static junit.framework.TestCase.assertNotNull;

/**
 * EnvironmentTest
 *
 * Test for Environment
 */
public class EnvironmentTest
{
    /**
     * Checks if the constructor is Null
     */
    @Test
    public void testConstructor() throws Exception {
        double envProb = 0.5;
        assertNotNull(new Environment(envProb));
    }
    /**
     * Checks if distance is Null
     */
    @Test
    public void testNeighbourInit() {
        int numberOfNodes = 1;
        ArrayList<Node> nodeList = new ArrayList<>();
        Node node = new Node(3,new Position(3,4),5,0.5,10,3);
        nodeList.add(node);


        for (int fromNode = 0; fromNode < numberOfNodes; fromNode++) {
            for (int toNode = 0; toNode < numberOfNodes; toNode++) {

                if (fromNode != toNode) {
                    int x1 = nodeList.get(fromNode).position.getX();
                    int x2 = nodeList.get(toNode).position.getX();
                    int y1 = nodeList.get(fromNode).position.getY();
                    int y2 = nodeList.get(toNode).position.getY();

                    double distance = Math.sqrt(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
                    assertNotNull(distance);
                }
            }
        }
    }

    /**
     * Checks in addNode if nodelist is Null
     */
    @Test
    public void testAddNode(){
        ArrayList<Node> nodeList = new ArrayList<>();
        Node node = new Node(3,new Position(3,4),5,0.5,10,3);
        nodeList.add(node);
        assertNotNull(nodeList);
    }

    /**
     * Checks in randomEvent if the eventlist is Null
     */
    @Test
    public void testRandomEvent(){
        ArrayList<Event> eventList = new ArrayList<>();
        Node node = new Node(3,new Position(3,4),5,0.5,10,3);
        Event newEvent = new Event(2, 15);
        // Test to add an event to the node. Random function is a built in function in intellij so no need to be tested.
        eventList.add(newEvent);
        assertNotNull(eventList);

    }

    /**
     * Checks if periodQuery generates a Null random event
     */
    @Test
    public void testPeriodQuery(){
        double prob = 0.5;
        Node node = new Node(3,new Position(3,4),5,prob,10,3);
        int time = 12;
        int temp = 3;
        ArrayList<Event> eventList = new ArrayList<>();
        Event event = new Event(5,5);
        eventList.add(event);

        Random randomGenerator = new Random();

            if(eventList.size() != 0){
                int index = randomGenerator.nextInt(eventList.size());
                Event item = eventList.get(index);
                temp = item.getEventId();
            }


        assertNotNull(temp);

    }
}
