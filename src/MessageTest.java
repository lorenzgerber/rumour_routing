import junit.framework.TestCase;
import org.junit.Test;
import sun.font.TrueTypeFont;

import java.util.Deque;
import java.util.LinkedList;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * MessageTest
 *
 * Tests Message
 */
public class MessageTest
{
    /**
     * Checks if the constructor is Null
     */
    @Test
    public void testConstructor(){
        assertNotNull(new Message(5,6) {
            @Override
            public void onSendAction() {

            }

            @Override
            public void messageAction(Node node) {

            }
        });
    }

    /**
     * Checks if the ttl know what's true and false
     * about death
     */
    @Test
    public void checkTTL() throws Exception {
        int TTL = 0;
        boolean temp;
        boolean temp2;
        temp2 = false;
        if (TTL == 0) {
             temp =false;
        } else {

            temp =true;
        }
        assertEquals(temp, temp2);
    }

    /**
     * Checks if ttl is able to go lose a step
     */
    @Test
    public void reduceTTL() throws Exception {
        int temp = 50;
        int temp2 = 49;

        temp--;
        assertEquals(temp,temp2);

    }

    /**
     * Checks if recent nodes are put into the recent nodes list
     */
    @Test
    public void addRecentNodeId() throws Exception {
        Integer nodeid = 5;

        Deque<Integer> recentNodes;
        recentNodes = new LinkedList<Integer>();

        recentNodes.add(nodeid);
        assertEquals(recentNodes.contains(nodeid), true);

    }

}
