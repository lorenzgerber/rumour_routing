import static org.junit.Assert.*;

import org.junit.Test;


import static junit.framework.TestCase.assertNotNull;


public class NodeTest
{
    /**
     * Test throws exception Nodes constructor returns Null
     */

    @Test
    public void testNodeConstructorReturnsNotNull()
    {
        int nodeId = 7;
        int currentTime = 0;
        double probAgent = 0.5;
        int ttlAgent = 50;
        int numRecentSteps = 7;
        Node node = new Node(nodeId, new Position(3, 5) , currentTime, probAgent, ttlAgent, numRecentSteps);
        assertNotNull(node);
    }

    /**
     * Test throws exception negative currentTime
     */
    @Test (expected = IllegalArgumentException.class)
    public void testThrowsExceptionNegativeCurrentTime()
    {
        int nodeId = 7;
        int currentTime = -1;
        double probAgent = 0.5;
        int ttlAgent = 50;
        int numRecentSteps = 7;
        Node node = new Node(nodeId, new Position(3, 5) , currentTime, probAgent, ttlAgent, numRecentSteps);
        assertNotNull(node);
    }

    /**
     * Test throws exception negative probAgent
     */
    @Test (expected = IllegalArgumentException.class)
    public void testThrowsExceptionNegativeProbAgent()
    {
        int nodeId = 7;
        int currentTime = 0;
        double probAgent = -0.5;
        int ttlAgent = 50;
        int numRecentSteps = 7;
        Node node = new Node(nodeId, new Position(3, 5) , currentTime, probAgent, ttlAgent, numRecentSteps);
        assertNotNull(node);
    }


    /**
     * Test throws exception negative ttlAgent
     */
    @Test (expected = IllegalArgumentException.class)
    public void testThrowsExceptionNegativeTtlAgent()
    {
        int nodeId = 7;
        int currentTime = 0;
        double probAgent = 0.5;
        int ttlAgent = -50;
        int numRecentSteps = 7;
        Node node = new Node(nodeId, new Position(3, 5) , currentTime, probAgent, ttlAgent, numRecentSteps);
        assertNotNull(node);
    }

    /**
     * Test throws exception negative numRecentSteps
     */
    @Test (expected = IllegalArgumentException.class)
    public void testThrowsExceptionNegativeNumRecentSteps()
    {
        int nodeId = 7;
        int currentTime = 0;
        double probAgent = 0.5;
        int ttlAgent = 50;
        int numRecentSteps = -7;
        Node node = new Node(nodeId, new Position(3, 5) , currentTime, probAgent, ttlAgent, numRecentSteps);
        assertNotNull(node);
    }
}

