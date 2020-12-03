package pkg;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface
{
    /*
    Your TownGraphManager will hold an object of your Graph. Implement the TownGraphManagerInterface.
    There are methods to populate the graph (reading from a text file), add a town (vertices), add a road (edge),
    list all towns and all roads, and list towns adjacent to a given town.
    Your solution will find the shortest path from a start town to a destination town.
    It will account for the possibility of a disjoint graph (i.e., not all vertices can be reached from all other vertices.)
    You may add any methods as needed for your design.

     */
    private Graph graph = new Graph();
;    /**
     * Adds a road with 2 towns and a road name
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param roadName name of road
     * @return true if the road was added successfully
     */
    @Override
    public boolean addRoad(String town1, String town2, int weight, String roadName) {

       graph.addEdge(new Town(town1),new Town(town2),weight,roadName );
       return true;

    }
    /**
     * Returns the name of the road that both towns are connected through
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return name of road if town 1 and town2 are in the same road, returns null if not
     */
    @Override
    public String getRoad(String town1, String town2) {
       return graph.getEdge(new Town(town1), new Town(town2)).getName();

    }
    /**
     * Adds a town to the graph
     * @param v the town's name  (lastname, firstname)
     * @return true if the town was successfully added, false if not
     */
    @Override
    public boolean addTown(String v) {
        return graph.addVertex(new Town(v));
    }
    /**
     * Gets a town with a given name
     * @param name the town's name
     * @return the Town specified by the name, or null if town does not exist
     */
    @Override
    public Town getTown(String name) {
        if(!(graph.containsVertex(new Town(name))==true))
        {
            return null;
        }
        return new Town(name);

    }
    /**
     * Determines if a town is already in the graph
     * @param v the town's name
     * @return true if the town is in the graph, false if not
     */
    @Override
    public boolean containsTown(String v) {
        return graph.containsVertex(new Town(v));
    }
    /**
     * Determines if a road is in the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return true if the road is in the graph, false if not
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(new Town(town1),new Town(town2));
    }
    /**
     * Creates an arraylist of all road titles in sorted order by road name
     * @return an arraylist of all road titles in sorted order by road name
     */
    @Override
    public ArrayList<String> allRoads() {
        ArrayList<String>roadNames=new ArrayList<String>();
        for (Road road: graph.edgeSet())
        {
            roadNames.add(road.getName());

        }
        Collections.sort(roadNames);
        return roadNames;
    }
    /**
     * Deletes a road from the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param road the road name
     * @return true if the road was successfully deleted, false if not
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        if(graph.removeEdge(new Town(town1),new Town(town2),0,road)==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
     * Deletes a town from the graph
     * @param v name of town (lastname, firstname)
     * @return true if the town was successfully deleted, false if not
     */
    @Override
    public boolean deleteTown(String v) {
        if(graph.removeVertex(new Town(v))==true)
        {
            return true;
        }
        return false;
    }
    /**
     * Creates an arraylist of all towns in alphabetical order (last name, first name)
     * @return an arraylist of all towns in alphabetical order (last name, first name)
     */
    @Override
    public ArrayList<String> allTowns() {
        ArrayList<String>townNames=new ArrayList<String>();
        for (Town town: graph.vertexSet())
        {
            townNames.add(town.getName());

        }
        Collections.sort(townNames);
        return townNames;


    }

    /**
     * Returns the shortest path from town 1 to town 2
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return an Arraylist of roads connecting the two towns together, null if the
     * towns have no path to connect them.
     */

    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        return graph.shortestPath(new Town(town1),new Town(town2));
    }

    public void populateTownGraph(File selectedFile) throws FileNotFoundException
    {
        Scanner file= new Scanner(selectedFile);
        while (file.hasNextLine())
        {
            String[]readLine=file.nextLine().split("[,;]");
            graph.addVertex(new Town(readLine[2]));
            graph.addVertex(new Town(readLine[3]));
            graph.addEdge(new Town(readLine[2]),new Town(readLine[3]),Integer.parseInt(readLine[1]),readLine[0]);



        }
        file.close();

    }
}
