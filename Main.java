/*
 Ethan Payne
 11/01/2023
 ECP230000
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;



import java.io.*;


//  Todo: 1) Update and Finish the Graph.java file.✅

//  Todo: 2) Make a method for prompting the user for the name of a file containing the galaxy map.✅

//  Todo: 3) Make a method for prompting the user for the name of a file containing the and the pilot routes.✅
//-             ❗❗Need to fix "NoSuchElementElement" error caused by the map and route file ✅

//  Todo: 4) Make a method for route validation. ✅

//  Todo: 5) Need to make sure that Map is read BEFORE the Route file.✅

//  Todo: 6) Make a method for calculating total weight of the route. ❌

//  Todo: 7) Figure out why multiple lines cant be read from Pilot route file. ❌

//  Todo: 8) Have the final output be written onto the pilot.txt file. ❌

/* 
*Galaxy_Map.txt File Format
    -- Each edge will be represented teas <adjacent vertex><comma><weight> 
    -- Line example:  
    -- Hoth Endor,9 Coruscant,3 Tatooine,1 Dagobah,5 
    ** Hoth is the vertex, Endor is the edge and it has a weight of 9...

* Current Output for the readGraph method: 
--     Hoth -> (Coruscant, Weight: 3) Hoth -> (Dagobah, Weight: 5) Hoth -> (Tatooine, Weight: 1)
--     Coruscant -> (Hoth, Weight: 3) Coruscant -> (Yavin, Weight: 12)
--     Dagobah -> (Hoth, Weight: 5) Dagobah -> (Endor, Weight: 2) Dagobah -> (Yavin, Weight: 3) Dagobah -> (Naboo, Weight: 8)
*/


public class Main {
    public static void main(String[] args) throws NoSuchElementException, FileNotFoundException {
        Graph<String> graph = new Graph<>();
        readGalaxy(graph); // Read and build the galaxy graph
        List<List<String>> allRoutes = readRoute(); // Read multiple pilot routes
        PrintWriter writer = new PrintWriter((new FileOutputStream("patrols.txt", true)));

        for (List<String> pRoute : allRoutes) {
            String pilotName = pRoute.get(0);

            if (!isValid(pRoute.subList(1, pRoute.size()), graph)) {
                //writer.print(pilotName + getWeight(graph, pilotName, pilotName) + "valid");
                writer.write((pilotName + "\t" + "0" + "\t" + "invalid" + "\n"));
            } 
            else {
                //writer.println(pilotName + getWeight(graph, pilotName, pilotName) + "not valid");
                int totalWeight = calculateRouteWeight(pRoute.subList(1, pRoute.size()), graph);
                writer.write((pilotName + "\t" + totalWeight + "\t" + "valid"+ "\n"));
            }
            
        }
        writer.close();
    }


    //-- Helper method to get the weight between two vertices
    private static int getWeight(Graph<String> graph, String firstVertex, String secondVertex) {
        for (Graph.Edge<String> edge : graph.neighbors.get(graph.vertices.indexOf(firstVertex))) {
            if (edge.targetVertex.equals(secondVertex)) {
                return edge.weight;
            }
        }
        return -1; // If there's no edge between the vertices
    }

    public static int calculateRouteWeight(List<String> route, Graph<String> graph) {
        int totalWeight = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            
            String currentVertex = route.get(i);
            String nextVertex = route.get(i + 1);
            int weight = getWeight(graph, currentVertex, nextVertex);
            
            if (weight != -1) {
                totalWeight += weight;
            } else {
                System.out.println("No edge found between " + currentVertex + " and " + nextVertex);
            }
        }
        return totalWeight;
    }


    //** This method is working perfectly and the graph is being built correctly
    //-- Changed from private static <V> List<V> readGalaxy(Graph<String> graph).
    private static void readGalaxy(Graph<String> graph) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the map file: "); 
        String fileString = in.nextLine();

        try { //-- User interfaces are not allowed 
            File file = new File(fileString);
            Scanner fileScan = new Scanner(file);

            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                String[] parts = line.split(" ");
                String vertex = parts[0];
                graph.addVertex(vertex);
                //-- Hoth Endor,9 Coruscant,3 Tatooine,1 Dagobah,5
                for (int i = 1; i < parts.length; i++) {
                    String[] edge = parts[i].split(",");
                    String adjVertex = edge[0];
                    int weight = Integer.parseInt(edge[1]);
                    graph.addEdge(vertex, adjVertex, weight);
                    

                    
                }
                
            } fileScan.close();
            
            for (String vertex : graph.getVertices()) {
                for (String neighbor : graph.getNeighbors(vertex)) {
                    int weight = getWeight(graph, vertex, neighbor);
                    System.out.print(vertex + " -> " + "(" + neighbor + ", Weight: " + weight + ") ");
                }
                System.out.println("\n");
            } 

        } catch (FileNotFoundException e) {
            System.out.println("Error: File does not ");
            e.printStackTrace();
        } 
    } 



/* //*  Route File Format: 
-- 1) Each line in the file will contain the pilot's name followed by a list of planets
-- 2) Each planet will be seperated by a space
-- 3) The first vertex listed is the starting vertex of the patrol
-- Example: Sully_Olvar Tatooine Dagobah Naboo Yavin Alderaan 
*/
    //** This method is working perfectly and the routes are being read correctly 
    public static List<List<String>> readRoute() {
        //Graph<String> graph = new Graph<>();
        List<List<String>> allRoutes = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the route file: "); 
        String fString = in.nextLine();
        System.out.println("\n");
        try {
            File file = new File(fString);
            Scanner fileScan = new Scanner(file);

            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                String[] parts = line.split(" ");



                //parts = line.split(" ");
                allRoutes.add(Arrays.asList(parts));
    
            }
                //--These are just variable testing cases
                //System.out.println("route info test: " + line);
                //System.out.println("name test" + name);
                //System.out.println("start vertex test" + startingVertex);
            fileScan.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Route file doesnt exist");
            e.printStackTrace();
        } 
        return allRoutes;
    }
    //* Hoth is the is the first vertex in the graph 
    //* Hoth's neighbors SHOULD be Coruscant, Dagobah, T
    //* The ONLY thing to fix is making Endor connect to Hoth and Hoth to Endor.
  
    public static boolean isValid(List<String> pRoute, Graph<String> graph) {
        boolean isValidRoute = true;
        /*
        * What we know:
            -- 1) pRoute holds the individual route for each pilot
            -- 2) The reason Maya is invalid is because nextVertex skips over Hoth(index 1) and goes to Endor(index 2) 
            --    which correctly flagged as invalid because Coruscant isnt connected to Endor!
            -- 3) if nextVertex at Index 1 != getNeighbors at Index 1, then the path is invalid because the */
        System.out.println("Validating route: " + pRoute);
        System.out.println();
        System.out.println("Starting vertex: " + pRoute.get(0));
        System.out.println();
        System.out.println("Hoths neighbors: " + graph.getNeighbors("Hoth"));
        System.out.println("Endor neighbors: " + graph.getNeighbors("Endor"));
        System.out.println("Dagobah neighbors: " + graph.getNeighbors("Dagobah"));
        
            //System.out.println(pRoute.get(0));
        for (int i = 1; i < pRoute.size(); i++) {
            
            String currVertex = pRoute.get(i-1);
            
            String nextVertex = pRoute.get(i);
        

            List<String> neighbor = graph.getNeighbors(currVertex);
            List<String> nextNeighbor = graph.getNeighbors(nextVertex);
            List<String> visited = graph.BFS(currVertex); //-- Might still be usable 
            //System.out.println("Next vertex is: " +  nextVertex); System.out.println();
            //System.out.println("Checking connection between " + currVertex + " and " + nextVertex);

            //- These two loops are working for all pilots EXCEPT Zach and Steve 
            if (!neighbor.contains(nextVertex) || !nextNeighbor.contains(currVertex)) {

                //System.out.println("CV Neighbors: " + neighbor); System.out.println();//** Neighbors are all correct!
                
                //System.out.println("NV Neighbors: " + nextNeighbor); System.err.println(); //** Neighbors are all correct!
                
                //System.out.println("Current Vertex: " + currVertex + ": Not valid (No path found to " + nextVertex + ")");
                //System.out.println("This means the path was invalid");
                isValidRoute = false;
                
                //return false;
            }
            //- Need to review and change the logic of this loop because Steve and Zach are being falsing marked
            //- AND the 3rd planet visited equals the 3rd planet in the route. This would make Steve, Maya ,Zach, and Sully false 
            //- The 3rd planet in the route is the planet visited at the index of the routes size. Doesnt work for Luke and Steve BUT does work for Zach
            //- The LAST 2 planets have to be neighbors or else it's false. This fixes Steve and Zach.
            else if (neighbor.contains(nextVertex) && nextNeighbor.contains(currVertex)) {

                //System.out.println("CV Neighbors: " + neighbor); System.out.println();//** Neighbors are all correct!
                
                //System.out.println("NV Neighbors: " + nextNeighbor); System.err.println(); //** Neighbors are all correct!
                //return true;
            }
            System.out.println();
            currVertex = nextVertex;
        }
            return isValidRoute;
    }
}

    

    /*public static boolean isValid(List<String> pRoute, Graph<String> graph) {
        System.out.println("Validating route: " + pRoute);
        System.out.println("Starting vertex: " + pRoute.get(0));
    
        for (int i = 0; i < pRoute.size() - 1; i++) {
            String currVertex = pRoute.get(i);
            String nextVertex = pRoute.get(i + 1);
    
            List<String> neighbors = graph.getNeighbors(currVertex);
    
            if (neighbors.contains(nextVertex) || (i == pRoute.size() - 2 && neighbors.contains(pRoute.get(0)))) {
                // If the next vertex is in the neighbors OR it matches the starting vertex (loop closure)
                System.out.println("Valid path between " + currVertex + " and " + nextVertex);
            } else {
                // If the connection is invalid
                System.out.println("Checking connection between " + currVertex + " and " + nextVertex);
                System.out.println("This means the path was invalid");
                return false;
            }
        }
    
        return true;
    }   */



//* Old code to be reused if needed
/*if (!graph.getVertices().contains(currVertex)) {
                System.out.println(currVertex + ": Not valid (Vertex not found in the graph)");
                return false;
            } */
            
            /*if (!graph.getNeighbors(currVertex).contains(nextVertex)) {
                if (!graph.getNeighbors(nextVertex).contains(currVertex)) {
                    
                    // System.out.println("Current Vertex: " + currVertex + ": Not valid (Route connection not found in the graph)");
                    System.out.println("Current Vertex: " + currVertex + ": Not valid (No path found to " + nextVertex + ")");
                    
                    System.out.println("Next vertex is: " +  nextVertex);
                    System.out.println("CV Neighbors: " + graph.getNeighbors(currVertex));
                    return false;
                }
            } */



//if (!graph.getNeighbors(currVertex).contains(nextVertex)) {
    
    
    

//    return false;
// }

//-- First attempt at isValid method, Not sure if anything is usable 
 /*if (pRoute == null || pRoute.size() < 2) {
            System.out.println("Not valid: Route is too short");
            return false;
        }
        String pilotName = pRoute.get(0);
        List<String> planets = pRoute.subList(1, pRoute.size());
        System.out.println("Starting vertex: " + pilotName); //-- pilotName is pointing at the starting vertex NOT the name
        
        for (String planet : planets) {
            if (!graph.getVertices().contains(planet)) {
                System.out.println("Not valid: Vertex " + planet + " not found in the graph");
                return false;
            }
        }
        
        for (int i = 1; i < planets.size(); i++) {
            String currVertex = planets.get(i - 1);
            String nextVertex = planets.get(i);
            if (!graph.getNeighbors(currVertex).contains(nextVertex)) {
                System.out.println("Current Vertex: " + currVertex + ": Not valid (Route connection not found in the graph)");
                System.out.println("Next vertex is: " +  nextVertex);
                return false;
            }
        } */

/* //-- From the readRoute method
StringTokenizer token = new StringTokenizer(line, " ");
String name = token.nextToken();
String startingVertex = token.nextToken();
String planets = token.nextToken(" ");
System.out.println("name: " + "starting vertex: " + "planets: "); */
//pRoute.add(new List(name,startingVertex,planets)); 

/*Scanner fileReader = new Scanner(new File("Galaxy_Map.txt"));
while (fileReader.hasNextLine()) {
    int v = fileReader.nextInt();
    int u = fileReader.nextInt();
    edges.add(new Graph.Edge(sour, targetVertex));
} */

/*  static List<Graph.Edge> readVertexData() {
        List<Graph.Edge> planets = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(new File("Pilot_Routes.txt"));
            fileReader.useDelimiter("\t");

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                StringTokenizer st = new StringTokenizer(line, "\t");
                String planet = st.nextToken();
                double weight = Double.valueOf(st.nextToken());

                
            }


        } catch (Exception e) {
            
        }
        
    return planets;
} */

    




