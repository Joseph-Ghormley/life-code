package pkg;

public class Road implements Comparable<Road>
{
    //data type
    private String name;
    private int degree;//weight
    Town source;
    Town destination;
    /*
    Constructor
    Parameters:source - One town on the road
    destination - Another town on the road
    i - Weight of the edge, i.e., distance from one town to the other
    roadName - Name of the road
     */
    public Road(Town source,
                Town destination,
                int degrees,
                String name)
    {
        this.source= source;
        this.destination=destination;
        this.degree= degrees;
        this.name=name;
    }
    /*
    Constructor with weight preset at 1
    Parameters:source - One town on the road
    destination - Another town on the road
    name - Name of the road
     */
    public Road(Town source,
                Town destination,
                String name)
    {
        this.source= source;
        this.destination=destination;
        this.degree= 1;
        this.name=name;
    }
    /*
    Returns true only if the edge contains the given town
    Parameters:town - a vertex of the graph
    Returns:true only if the edge is connected to the given vertex
     */
    public boolean contains(Town town)
    {
        return source.equals(town) ||
                destination.equals(town);

    }

    /*
    public java.lang.String toString()
    To string method.
    Overrides:toString in class java.lang.Object
 */
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", degree=" + degree +
                ", source=" + source +
                ", destination=" + destination +
                '}';
    }

    /*
    Returns the road name
    Returns:The name of the road
     */
    public String getName()
    {
        return name;
    }
    /*
    Returns the second town on the road
    Returns:A town on the road
     */
    public Town getDestination()
    {
       return destination;
    }
    /*
    Returns the first town on the road
    Returns:A town on the road
     */
    public Town getSource()
    {
        return source;
    }
    /*
    Specified by:
    compareTo in interface java.lang.Comparable<Road>
    Returns:0 if the road names are the same,
    a positive or negative number if the road names are not the same
     */
    public int compareTo(Road o)
    {
        return this.name.compareTo(o.name);
    }
    /*
    Returns the distance of the road
    Returns:the distance of the road
     */
    public int getWeight()
    {
        return degree;
    }
    /*Returns true if each of the ends of the road r is the same as the ends of this road.
    Remember that a road that goes from point A to point B is the same as a road that goes from point B to point A.
    Overrides:
    equals in class java.lang.Object
    Parameters:
    r - road object to compare it to
    */
    public boolean equals(Object r)
    {
        if(this==r)
        {
            return true;
        }
        if(r ==null)
            return false;
        if(!(r instanceof Road))
        {
            return false;
        }
        Road o = (Road) r;

        return (source.equals(o.source)&&destination.equals(o.destination))||(source.equals(o.destination)&& destination.equals(o.source));
    }
}
