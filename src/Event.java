/**
 * An Event has a unique identifier. An Event knows
 * when it occured. An Event can remember the
 * distance to where it occured.
 */
public class Event
    implements java.lang.Cloneable
{
    private int id;
    private int zeroTime;
    private int distance;

    public Event(int id, int zeroTime)
    {
        this.id = id;
        this.zeroTime = zeroTime;

    }

    public int getEventId(){
        return this.id;
    }

    public int getDistance() {return this.distance;}

    public void increaseDistance()
    {
        this.distance++;
    }

    @Override
    public int hashCode(){
        return this.id;
    }

    @Override
    public boolean equals(Object otherObject){
        if(this.hashCode() == otherObject.hashCode())
            return true;
        return false;
    }

    @Override
    public Object clone() throws java.lang.CloneNotSupportedException
    {
        return super.clone();
    }



}
