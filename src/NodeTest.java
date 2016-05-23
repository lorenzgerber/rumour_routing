import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import static junit.framework.TestCase.assertNotNull;


public class NodeTest
{
    Node node = new Node(1, new Position(5,6),9,0.5,10,5);
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


    @Test
    public void testGetNodId(){
        assertEquals(node.getNodeId(),1);
    }

    @Test
    public void testGetPeriodQuery(){
        Assert.assertNotNull(String.valueOf(node.getPeriodQuery()),0.5);
    }

    @Test
    public void testGetBusyState(){
        node.resetBusyState();
        assertEquals(node.getBusyState(), false);
    }

    @Test
    public void testDetectNeighbour(){
        ArrayList <Node> neighbourIds = new ArrayList<Node>();
        neighbourIds.add(node);
        assertEquals(neighbourIds.contains(node), true);
    }

    @Test
    public void testDetectEvent() throws CloneNotSupportedException {
        Event event = new Event(1,5);
        HashMap <Event, Integer> eventMap = new HashMap<Event, Integer>();
        Deque <Message> messageQueue;
        messageQueue = new LinkedList<Message>();
        Agent agent = new Agent(10,event,1,5);

        eventMap.put((Event)event.clone(),1);

        assertEquals(eventMap.containsValue(event), true);
        messageQueue.add(agent);

    }


    @Test
    public void testDetectAgent() {
        Event event = new Event(1,5);
        HashMap <Event, Integer> eventMap = new HashMap<Event, Integer>();
        Deque <Message> messageQueue;
        messageQueue = new LinkedList<Message>();
        Agent agent = new Agent(10,event,1,5);


        messageQueue.add(agent);
        assertEquals(messageQueue.contains(agent), true);
    }

    @Test
    public void testDetectQuery() {
        Event event = new Event(1,5);
        HashMap <Event, Integer> eventMap = new HashMap<Event, Integer>();
        Deque <Message> messageQueue;
        messageQueue = new LinkedList<Message>();

        Query query = new Query(10,1,node,5);

        messageQueue.add(query);
        assertEquals(messageQueue.contains(query), true);

    }

    @Test
    public void testSendMessage() {
        Event event = new Event(1, 5);
        HashMap<Event, Integer> eventMap = new HashMap<Event, Integer>();
        Deque<Message> messageQueue;
        messageQueue = new LinkedList<Message>();

        Query query = new Query(10, 1, node, 5);

        messageQueue.add(query);
        Node destination = new Node(2, new Position(5, 2), 8, 0.5, 10, 5);

        messageQueue.element().onSendAction();
        destination.receiveMessage(messageQueue.remove());
        assertEquals(messageQueue.contains(query), false);
    }
}

