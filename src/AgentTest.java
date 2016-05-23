import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by loge on 11/05/16.
 */
public class AgentTest
{

    private Deque<Integer> recentNodes;

    @Test
    public void testConstructor(){
        assertNotNull(new Agent(5,new Event(7,1),7,10));

    }


    @Test(expected = CloneNotSupportedException.class)
    public void testMessageAction() throws CloneNotSupportedException {
        Node currentNode = new Node(1, new Position(2,5),5, 0.5,10,3);
        Event event = new Event(3,6);
        currentNode.detectEvent(event);
        HashMap<Event, Integer> nodeEvents = currentNode.getEventMap();
        nodeEvents.put((Event) event.clone(),1);


        }

    @Test
    public void testUpdateRoutingMapEventDist(){
        Event eventdistance = new Event(1,6);
        eventdistance.increaseDistance();
        int newdistance = 1;
        assertEquals(eventdistance,newdistance);

    }

    @Test
    public void testUpdateRoutingMap(){
        HashMap<Event, Integer> routingMap = new HashMap<>();

        Event event = new Event(79, 5);

        routingMap.put(event,4);

        assertNotNull(routingMap);
    }




}
