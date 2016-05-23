import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class QueryTest
{
    /**
     * Test that the constructor returns a non null object.
     */
    @Test
    public void testConstructorOfRequest() {
        int ttlQuery = 7;
        int queryEventId = 7;
        int numRecentNodes = 7;
        assertNotNull(new Query(ttlQuery, queryEventId, new Node(7, new Position(3, 5), 7, 0.5, 7, 7), numRecentNodes));
    }


    /**
     * Test that the constructor gives exception when given
     * negative ttlQuery
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorGivesExceptionForNegativeTtlQuery()
    {
        int ttlQuery = -7;
        int queryEventId = 7;
        int numRecentNodes = 7;
        new Query(ttlQuery, queryEventId, new Node(7, new Position(3, 5), 7, 7, 7, 7), numRecentNodes);
    }

    /**
     * Test that the constructor gives exception when given
     * negative queryEventId
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorGivesExceptionForNegativeQueryEventId()
    {
        int ttlQuery = 7;
        int queryEventId = -7;
        int numRecentNodes = 7;
        new Query(ttlQuery, queryEventId, new Node(7, new Position(3, 5), 7, 7, 7, 7), numRecentNodes);
    }

    /**
     * Test that the constructor gives exception when given
     * negative numRecentNodes
     */
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorGivesExceptionForNegativeNumRecentNodes()
    {
        int ttlQuery = 7;
        int queryEventId = 7;
        int numRecentNodes = -7;
        new Query(ttlQuery, queryEventId, new Node(7, new Position(3, 5), 7, 7, 7, 7), numRecentNodes);
    }

    /**
     * Test to see if the enums are Null
     */
    @Test
    public void testConstantsInEnumState() {
        assertNotNull(Query.queryMode.SEARCH);
        assertNotNull(Query.queryMode.TRACK);
        assertNotNull(Query.queryMode.HOMING);
    }

    @Test
    public void testGetQueryEventId(){
        Node node = new Node(1, new Position(5,6),10,0.5,10,5);
        Query query = new Query(10,1,node,5);
        assertEquals(query.getQueryEventId(),1);
    }

    @Test
    public void testResetTTL(){
        Message message = new Message(10,5) {
            @Override
            public void onSendAction() {

            }

            @Override
            public void messageAction(Node node) throws Exception {

            }
        };
        Node node = new Node(1, new Position(5,6),10,0.5,10,5);
        Event event = new Event(1,5);
        HashMap <Event, Integer> eventMap = new HashMap<Event, Integer>();
        Deque<Message> messageQueue;
        messageQueue = new LinkedList<Message>();

        Query query = new Query(10,1,node,5);

        messageQueue.add(query);
        query.resetTTL();
        assertEquals(query.checkTTL(),false);
    }

    @Test
    public void testCheckEvent() throws CloneNotSupportedException {
        Node node = new Node(1, new Position(5,6),10,0.5,10,5);
        Event event = new Event(1,5);
        node.detectEvent(event);
        Query query = new Query(10,1,node,5);

        assertEquals(query.checkEvent(node), true);

    }

}

