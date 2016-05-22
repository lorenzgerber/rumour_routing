import static org.junit.Assert.*;

import org.junit.Test;

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
        assertNotNull(new Query(ttlQuery, queryEventId, new Node(7, new Position(3, 5), 7, 7, 7, 7), numRecentNodes));
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

    
}

