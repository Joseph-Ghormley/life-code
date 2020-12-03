package pkg;

import java.util.LinkedList;

/*
Represents an town as a node of a graph.
The Town class holds the name of the town and a list of adjacent towns,
and other fields as desired, and the traditional methods (constructors, getters/setters, toString, etc.).
It will implement the Comparable interface These are the minimum methods that are needed.
Please feel free to add as many methods as you need.
 */
public class Town implements Comparable<Town>
{
    //data field
    private String name;
    private LinkedList <Town> listOfAdjacenttTowns;
    private int distance;
    private Town parent;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Town getParent() {
        return parent;
    }

    public void setParent(Town parent) {
        this.parent = parent;
    }

//    @Override
//    /*
//    o string method
//    Overrides:toString in class java.lang.Object
//    Returns:the town name
//     */
//    public String toString()
//    {
//        return "Town{" +
//                "name='" + name + '\'' +
//                ", listOfAdjacenttTowns=" + listOfAdjacenttTowns +
//                '}';
//    }

    public LinkedList<Town> getListOfAdjacenttTowns()
    {
        return listOfAdjacenttTowns;
    }


    public Town(String name)
    {
     this.listOfAdjacenttTowns = new LinkedList<>();
     this.name= name;
    }

    /*
      Constructor. Requires town's name.
      Parameters: name -
         */

    /*
    Copy constructor.
    Parameters: templateTown - an instance of Town
     */
    public Town(Town templateTown)
    {
        this(templateTown.name);
    }
    /*
    Returns the town's name
    Returns:town's name
     */
    public String getName()
    {
    return name;
    }
    /*
    Specified by:
    compareTo in interface java.lang.Comparable<Town>
    Returns: 0 if names are equal, a positive or negative number if the names are not equal
     */
    public int compareTo(Town o) {
        if (distance >= 0 && o.distance >= 0) {
            return distance - o.distance;
        }
        return this.name.compareTo(o.name);
    }
    /*
    Overrides: hashCode in class java.lang.Object
    Returns: the hashcode for the name of the town
     */
    public int hashCode()
    {
        return name.hashCode();
    }
    /*Overrides:
     equals in class java.lang.Object
     Returns:true if the town names are equal, false if not
    */
    public boolean equals(Object obj)
    {
        if(this==obj)
        {
            return true;
        }
        if(obj ==null)
            return false;
        if(!(obj instanceof Town))
        {
            return false;
        }
        Town o = (Town)obj;
        return name.equals(o.name);
    }
    public void addToListToAdjacent(Town addToTown)
    {
        listOfAdjacenttTowns.add(addToTown);
    }
    public void subtractToListToAdjacent(Town subtractToTown)
    {
        listOfAdjacenttTowns.remove(subtractToTown);
    }


}
