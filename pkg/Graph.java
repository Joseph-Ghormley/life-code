package pkg;

import java.util.*;

public class Graph implements GraphInterface<Town,Road> {


    private final LinkedList<Town> adjacencyListTowns;
    private final LinkedList<Road> roads;
    private final ArrayList<String> pathToPrint;

    public Graph()
    {
        this.roads= new LinkedList<>();
        this.adjacencyListTowns= new LinkedList<>();
        this.pathToPrint=new ArrayList<>(0);
    }


    /**
     * The root interface in the graph hierarchy. A mathematical graph-theory graph
     * object G(V,E) contains a set V of vertices and a set
     * E of edges. Each edge e=(v1,v2) in E connects vertex v1 to vertex v2.
     *
     * Through generics, a graph can be typed to specific classes for vertices
     * V and edges E<T>. Such a graph can contain
     * vertices of type V and all sub-types and Edges of type
     * E and all sub-types.
     */

    //~ Methods ----------------------------------------------------------------

    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex)
    {
        if (sourceVertex == null || destinationVertex == null)
        {
            return null;
        }
        if (!(adjacencyListTowns.contains(sourceVertex) || adjacencyListTowns.contains(destinationVertex))) {
            return null;
        }
        for (Road road:roads)//each element of datastruction:datastruction
        {
            if(createRoadForComparison(sourceVertex,destinationVertex).equals(road))
            {
                return road;
            }
        }
        return null;
    }
    private Road createRoadForComparison(Town sourceVertex, Town destinationVertex)
    {
        return new Road(sourceVertex,destinationVertex,"");
    }

    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge.
     *
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
    {
       if (sourceVertex==null||destinationVertex==null)
       {
           throw new NullPointerException();
       }
       if(weight<0)
       {
           throw new IllegalArgumentException();
       }
        if (!(adjacencyListTowns.contains(sourceVertex) || adjacencyListTowns.contains(destinationVertex))) {
            throw new IllegalArgumentException();
        }

        //update roads
        Road updateRoad= new Road(sourceVertex,destinationVertex,weight,description);
        if(!roads.add(updateRoad))
        {
            return null;
        }
        adjacencyListTowns.get(adjacencyListTowns.indexOf(sourceVertex)).addToListToAdjacent(adjacencyListTowns.get(adjacencyListTowns.indexOf(destinationVertex)));
        adjacencyListTowns.get(adjacencyListTowns.indexOf(destinationVertex)).addToListToAdjacent(adjacencyListTowns.get(adjacencyListTowns.indexOf(sourceVertex)));
        return updateRoad;
    }
    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
    @Override
    public boolean addVertex(Town town)
    {
        if (town==null)
        {
            throw new NullPointerException();
        }
        if(adjacencyListTowns.contains(town))
        {
            return false;
        }
        adjacencyListTowns.add(town);

        return true;
    }
    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex)
    {
        for (Road road : roads)
        {
            //if (road.contains(createRoadForComparison(sourceVertex,destinationVertex)))
            if(road.equals(createRoadForComparison(sourceVertex,destinationVertex)))
            {
                return true;
            }
        }
        return false;    }
    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
    @Override
    public boolean containsVertex(Town town) {
        return adjacencyListTowns.contains(town);
    }
    /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
    @Override
    public Set<Road> edgeSet()
    {
        return new HashSet<>(roads);
    }
    /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex..c
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
    @Override
    public Set<Road> edgesOf(Town vertex)
    {
        if(vertex==null)
        {
            throw new NullPointerException();
        }
        if(!(adjacencyListTowns.contains(vertex)))
        {
            throw new IllegalArgumentException();
        }
        Set <Road> roadsEdge =new HashSet<>();
        for (Road road:roads )
        {
            if (road.getSource().equals(vertex)||road.getDestination().equals(vertex))
            {
                roadsEdge.add(road);
            }
        }
        return roadsEdge;
    }
    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph.
     *
     * If weight >- 1 it must be checked
     * If description != null, it must be checked
     *
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
    {

        if(sourceVertex==null||destinationVertex==null)
        {
            return null;
        }
        if(!(adjacencyListTowns.contains(sourceVertex)||adjacencyListTowns.contains(destinationVertex)))
        {
           return null;
        }
        adjacencyListTowns.get(adjacencyListTowns.indexOf(sourceVertex)).subtractToListToAdjacent(destinationVertex);
        adjacencyListTowns.get(adjacencyListTowns.indexOf(destinationVertex)).subtractToListToAdjacent(sourceVertex);
        for(Road road: roads) {
            if(createRoadForComparison(sourceVertex, destinationVertex).equals(road))
            {
                roads.remove(road);
                return road;
            }
        }
        return null;

    }
    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
    @Override
    public boolean removeVertex(Town town) {
        if(town==null)
        {
            return false;
        }
        if (!(adjacencyListTowns.contains(town)))
        {
            return false;
        }
        adjacencyListTowns.remove(edgesOf(town));
        adjacencyListTowns.remove(town);
        return true;
    }
    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
    @Override
    public Set<Town> vertexSet() {
        return new HashSet<>(adjacencyListTowns);
    }
    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
     * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
     * would be in the following format(this is a hypothetical solution):
     * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
     * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
     * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex)
    {
        if(sourceVertex==null||destinationVertex==null)
        {
            throw new NullPointerException();
        }
        if(!(adjacencyListTowns.contains(sourceVertex)||adjacencyListTowns.contains(destinationVertex)))
        {
            throw new IllegalArgumentException();
        }
        dijkstraShortestPath(sourceVertex);

        Town presentTown = adjacencyListTowns.get(adjacencyListTowns.indexOf(destinationVertex));
        while(presentTown.getParent()!=null)
        {
            String string = presentTown.getParent().getName()+" via " +
                    getEdge(presentTown,presentTown.getParent()).getName()+ " to " +
                    presentTown.getName()+" "+getEdge(presentTown,presentTown.getParent()).getWeight()+ " mi";
            pathToPrint.add(string);

            presentTown=presentTown.getParent();
        }

        if(! presentTown.equals(sourceVertex))
        {
            pathToPrint.clear();
        }
        for (Town thistown: adjacencyListTowns) {
            thistown.setDistance(Integer.MIN_VALUE);
        }
        if (pathToPrint.isEmpty())
        {
            return new ArrayList<>();
        }
        Collections.reverse(pathToPrint);
        return pathToPrint;
    }
    /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     *
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        for (Town thisTown:adjacencyListTowns)
        {
            thisTown.setDistance(Integer.MAX_VALUE);
            thisTown.setParent(null);
        }
        adjacencyListTowns.get(adjacencyListTowns.indexOf(sourceVertex)).setDistance(0);
        pathToPrint.clear();

        Queue<Town> minHeap =new PriorityQueue<>(adjacencyListTowns);
        while(! minHeap.isEmpty())
        {
            Town thisTown =minHeap.remove();
            for (Town hood: thisTown.getListOfAdjacenttTowns())
            {
                if(minHeap.contains(hood))
                {
                    if(hood.getDistance()> thisTown.getDistance()+getEdge(thisTown,hood).getWeight())
                    {
                        hood.setDistance(thisTown.getDistance()+getEdge(thisTown,hood).getWeight());
                        hood.setParent(thisTown);
                        minHeap.remove(hood);
                        minHeap.add(hood);

                    }
                }
            }
        }

    }
}