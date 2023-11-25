/*
 Ethan Payne
 11/01/2023
 ECP230000
 */
import java.util.*;

public class Graph<V> implements IGraph<V> {

    protected List<V> vertices; //-- Store the verticies
    protected List<List<Edge>> neighbors; //-- Stores the adjaceny list
    protected Map<Boolean, int[]> visited[]; //* Marks all of index as not visited (set false by default)


    public Graph() {
        vertices = new ArrayList<>();
        neighbors = new ArrayList<>();
    }

    /**
    *-- Copy constructor 
    * @param vertices
    * @param edges
    */
    protected Graph(List<V> vertices, List<Edge> edges) {
        this();
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }
        newAdjacentList(edges, vertices.size());
    }

    /**
    *-- Initialize the neighbors list from a list of edges
    * @param edges
    * @param numberOfVeratices
    */
    private void newAdjacentList(List<Edge> edges, int numOfVertices) {
        for (Edge edge : edges) {
            addEdge(edge.v,edge.u);
        }
    }

    @Override
    public int getSize() {

        return vertices.size();
    }


    @Override
    public List<V> getVertices() {
        
        return vertices;
    }


    @Override
    public V getVertex(int index) {
        
        return vertices.get(index);
    }


    @Override
    public int getIndex(V vertex) {
       
        return vertices.indexOf(vertex);
    }


    @Override
    public List<Integer> getNeighbors(int index) {
        
        List<Integer> result = new ArrayList<>();
        for (Edge edge : neighbors.get(index)) {
            result.add(edge.u);
        }
        return result;
    }


    @Override
    public List<V> getNeighbors(V vertex) {
        
        List<V> result = new ArrayList<>();
        int index = vertices.indexOf(vertex);
        if (index != -1) {
            for (Edge<V> edge : neighbors.get(index)) {
                result.add(edge.targetVertex);
            }
        }

        // return getNeighbors(vertices.indexOf(vertex));
        return result;
    }


    @Override
    public int getDegree(int index) {
        
        return neighbors.get(index).size();
    }


    @Override
    public int getDegree(V vertex) {
        
        return getDegree(vertices.indexOf(vertex));
    }

    
    /*boolean addEdge(Edge edges) {
        if (edges.sourceIndex < 0 || edges.sourceIndex > getSize() - 1) {
            throw new IllegalArgumentException("Index does not exist");
        }
        if (edges.targetVertex < 0 || edges.targetVertex > getSize() -1) {
            throw new IllegalArgumentException("Index does not exist");
        }
        if (!neighbors.get(edges.sourceIndex).contains(edges)) {
            neighbors.get(edges.sourceIndex).add(edges); return true;
        }
        else return false;
    } */


   /*  @Override
    public boolean addEdge(int v1, int v2) {
        
        return addEdge(new Edge(v1,v2));
    }
    

    public boolean addEdge(V vertex1, V vertex2) {

        return addEdge(new Edge(vertices.indexOf(vertex1), vertices.indexOf(vertex2)));
    }


    @Override
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
        else return false;
    }
    //-- Possible replacement?
    /*public void addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<>());
        }
    } */


    // Todo: Implement a breadth first search using the algorithm provided in the textbook. 
    // Todo: BFS method should return a list of vertices in the order that they were visited.
    // Todo: Figure out why the array isnt working and why the list is being difficult


    /* 
    BFS(startV)
        Push startV(index) to frontierQueue(fQueue)
        Add startV(index) to discoveredSet(orderedVisited)
            While (fQueue is not empty)
                currentIndex = Pop fQueue
                "Visit" currentIndex
                    for each vertex adjVertex adjacent to currentIndex
                        (if adjVertex is not in discoveredSet)
                            Push adjVertext to fQueue
                            Add adjVertex to discoveredSet */

    @Override
    public List<V> BFS(int index) {
        boolean[] visited = new boolean[vertices.size()];
        LinkedList<Integer> fQueue = new LinkedList<Integer>(); //* Creates a frontierQueue for the BFS
        List<V> orderVisited = new ArrayList<>();
        fQueue.add(index);
        visited[index] = false;
        //-- Is this code redunant??
        visited[index] = true; //* Mark the current node as visited and add it
        fQueue.add(index);
        //-- Is this code redunant??

        while(!fQueue.isEmpty()) { 
            index = fQueue.pop();
            System.out.println(index + " "); 
            orderVisited.add(vertices.get(index));
            List<Integer> adjVertex = getNeighbors(index);
            for (int i = 0; i < adjVertex.size(); i++) {
                Integer n = adjVertex.get(i);
                if (!visited[n]) { 
                    fQueue.add(n);
                    visited[n] = true;
                }
            }
            /** while(neighbors.hasNext()) {
                neighbors.get(index);
                List<AbstractGraph.Edge> n = i.next();
                if(!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            } **/
        }
        return orderVisited;
    }


    public static class Edge {
        int v, u;


        public Edge(int v, int u) {
            this.u = u;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return v == edge.v && u == edge.u;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, u);
        }
    }
}
