/*
 Ethan Payne
 11/01/2023
 ECP230000
 */
import java.util.*;

public class Graph<V> implements IGraph<V> {

    protected List<V> vertices; //-- Store the verticies
    protected List<List<Edge<V>>> neighbors; //-- Stores the adjaceny list
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
   /*  protected Graph(List<V> vertices, List<Edge> edges) {
        this();
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }
        newAdjacentList(edges, vertices.size());
    } */

    /**
    *-- Initialize the neighbors list from a list of edges
    * @param edges
    * @param numberOfVeratices
    */
   /*  private void newAdjacentList(List<Edge> edges, int numOfVertices) {
        for (Edge edge : edges) {
            addEdge(edge.sourceIndex,edge.targetVertex);
        }
    } */


    public void addEdge(V from, V to, int weight) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        if (fromIndex != -1 && toIndex != -1) {
            neighbors.get(fromIndex).add(new Edge<>(vertices.get(fromIndex),vertices.get(toIndex),weight));
            neighbors.get(toIndex).add(new Edge<>(vertices.get(toIndex),vertices.get(fromIndex),weight));
        }
  
    }



    //-- Possible replacement?
    //-- New version of addVertex method
    public void addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<>());
        }
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
        index = vertices.indexOf(result);
        if (index != -1) {
            for (Edge<V> edge : neighbors.get(index)) {
                result.add(index);
            }
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

        //return getNeighbors(vertices.indexOf(vertex));
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
    // ** This method was changed for testing
    @Override
    public List<V> BFS(V startVertex) {
        List<V> orderVisited = new ArrayList<>();
        Queue<V> fQueue = new LinkedList<>(); //* Creates a frontierQueue for the BFS
        boolean[] visited = new boolean[vertices.size()];
        int startIndex = vertices.indexOf(startVertex);

        if (startIndex != -1) {
            fQueue.offer(startVertex);
            visited[startIndex] = true;

            while(!fQueue.isEmpty()) { 
                V currentVertex = fQueue.poll();
                orderVisited.add(currentVertex);
                int currIndex = vertices.indexOf(currentVertex);
                for (Edge<V> edge : neighbors.get(currIndex)) {
                    V nextVertex = edge.targetVertex;
                    int nextIndex = vertices.indexOf(nextVertex);
                    
                    if (!visited[nextIndex]) {
                        fQueue.offer(nextVertex);
                        visited[nextIndex] = true;
                    }
                }


            }
        }
        return orderVisited;

        /*fQueue.add(index);
        visited[index] = false;
        //-- Is this code redunant??
        visited[index] = true; //* Mark the current node as visited and add it
        fQueue.add(index);
        //-- Is this code redunant??

            * while(neighbors.hasNext()) {
                neighbors.get(index);
                List<AbstractGraph.Edge> n = i.next();
                if(!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            } 
            **/
            
    }
    
    @Override
public List<V> DFS(V startVertex) {
    List<V> orderVisited = new ArrayList<>();
    Stack<V> stack = new Stack<>();
    boolean[] visited = new boolean[vertices.size()];
    int startIndex = vertices.indexOf(startVertex);

    if (startIndex != -1) {
        stack.push(startVertex);
        visited[startIndex] = true;

        while (!stack.isEmpty()) {
            V currentVertex = stack.pop();
            orderVisited.add(currentVertex);
            int currIndex = vertices.indexOf(currentVertex);

            for (Edge<V> edge : neighbors.get(currIndex)) {
                V nextVertex = edge.targetVertex;
                int nextIndex = vertices.indexOf(nextVertex);

                if (!visited[nextIndex]) {
                    stack.push(nextVertex);
                    visited[nextIndex] = true;
                }
            }
        }
    }
    return orderVisited;
} 



    @Override
    public int getSize() {

        return vertices.size();
    }

    
    public static class Edge<V> {
        V sourceIndex, targetVertex;
        int weight;


        public Edge(V sourceIndex, V targetVertex, int weight) {
            this.targetVertex = targetVertex;
            this.sourceIndex = sourceIndex;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return sourceIndex == edge.sourceIndex && targetVertex == edge.targetVertex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(sourceIndex, targetVertex);
        }
    }

}
