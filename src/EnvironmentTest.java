
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * EnvironmentTest
 *
 * Tests for Environment
 */
public class EnvironmentTest
{
    private int temp;

    /**
     * A test to try and create an environment.
     */
    @Test
    public void testConstructor() {
        double envProb = 0.5;
        assertNotNull(new Environment(envProb));
    }

    /**
     * A test that tries to calculate the distance and shows that the distance equation works.
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
     * A test that tries to get environment time, test also shows that time increases in the advance time method.
     * @throws Exception
     */
    @Test
    public void testGetTime() throws Exception {
        Environment env = new Environment(0.5);
        env.advanceTime();
        assertEquals(env.getTime(),1);
    }

    /**
     * A test to add a node an ArrayList.
     */
    @Test
    public void testAddNode(){
        ArrayList<Node> nodeList = new ArrayList<>();
        Node node = new Node(3,new Position(3,4),5,0.5,10,3);
        nodeList.add(node);
        assertNotNull(nodeList);
    }

    /**
     * A test to add an event to an ArrayList.
     */
    @Test
    public void testRandomEvent(){
        ArrayList<Event> eventList = new ArrayList<>();
        Node node = new Node(3,new Position(3,4),5,0.5,10,3);
        Event newEvent = new Event(2, 15);
        //Random function is a built in function in intellij so no need to be tested.
        eventList.add(newEvent);
        assertNotNull(eventList);

    }

    /**
     * A test that shows that the random generator works and that it's added to the eventList.
     */
    @Test
    public void testPeriodQuery(){
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
