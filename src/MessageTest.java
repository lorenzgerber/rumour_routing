import junit.framework.TestCase;
import org.junit.Test;
import sun.font.TrueTypeFont;

import java.util.Deque;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by loge on 11/05/16.
 */
public class MessageTest
{
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


    @Test
    public void reduceTTL() throws Exception {
        int temp = 50;
        int temp2 = 49;

        temp--;
        assertEquals(temp,temp2);

    }

    @Test
    public void addRecentNodeId(int nodeid) throws Exception {

        Deque<Integer> recentNodes;
        recentNodes = new LinkedList<Integer>();
        recentNodes.add(nodeid);
        assertEquals(recentNodes.contains(nodeid), true);



    }

    @Test
    public void getRecentNodes() throws Exception {
        int numRecentNodes;


    }

    @Test
    public void messageAction() throws Exception {

    }

    @Test
    public void nextNode() throws Exception {

    }

    @Test
    public void onSendAction() throws Exception {

    }

}
