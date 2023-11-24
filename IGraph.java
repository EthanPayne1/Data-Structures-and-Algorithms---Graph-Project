/*
 Ethan Payne
 11/01/2023
 ECP230000
 */
import java.util.List;

public interface IGraph<V> {

    /**
     * -- Returns the number of vertices currently in the graph.
     * @return
     */
    public int getSize();


    /**
     * -- Return all vertices currently in the graph.
     * @return
     */
    public List<V> getVertices();


    /**
     * -- Returns the object associated with the specified vertex.
     * @param index
     * @return
     */
    public V getVertex(int index);


    /**
     * -- Returns the index for the specified vertex object.
     * @param vertex
     * @return 
     */
    public int getIndex(V vertex);

    /**
     * -- Return the neighbors of the vertex associated with the specified index.
     * @param index
     * @return
     */
    public List<Integer> getNeighbors(int index);

    /**
     * -- Returns the neighbors of the specified vertex.
     * @param vertex
     * @return
     */
    public List<Integer> getNeighbors(V vertex);

    /**
     * -- Return the degree of a vertex with the specified index.
     * @param index
     * @return
     */
    public int getDegree(int index);

    /**
     * -- Returns the degree of a vertex.
     * @param vertex
     * @return
     */
    public int getDegree(V vertex);

    /**
     * -- Add an edge to the graph between verticies with indicies u and v
     * @param v1
     * @param v2
     * @return
     */
    public boolean addEdge(int v1, int v2);

    /**
     * -- Add an edge to the graph between vertex1 and vertex2
     * @param vertex1
     * @param vertex2
     * @return
     */
    public boolean addEdge(V vertex1, V vertex2);

    /**
     * -- Add a vertex to the graph. 
     * -- This vertex will be unconnected until an edge is added that connects it.
     * @param vertex
     * @return
     */
    public boolean addVertex(V vertex);

    /**
     * -- Obtain a list of vertices sorted by a breadth first search
     * -- Iterates through all nodes sequentially and adding all unseen nodes to the frontierQueue
     * @param index
     * @return
     */
    public List<V> BFS(int index);


}
/*
 int getIndex
 List<Int> getNeighbors(index)
 List<Int> getNeighbors(vertex)
 getDegree
 addVertex
 addEdge

 */