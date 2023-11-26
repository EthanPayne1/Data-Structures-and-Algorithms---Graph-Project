/*
 Ethan Payne
 11/01/2023
 ECP230000
 */

import java.io.File;
//import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.*;


//  Todo: 1) Update and Finish the Graph.java file.✅
//  Todo: 2) Make a method for prompting the user for the name of a file containing the galaxy map.✅
//  Todo: 3) Make a method for prompting the user for the name of a file containing the and the pilot routes.❌
//  Todo: 4) Make a method for route validation. ❌

/* 
*Galaxy_Map.txt File Format
    -- Each edge will be represented teas <adjacent vertex><comma><weight> 
    -- Line example:  
    -- Hoth Endor,9 Coruscant,3 Tatooine,1 Dagobah,5 
    ** Hoth is the vertex, Endor is the edge and it has a weight of 9...

* Current Output: 
--     Hoth -> (Coruscant, Weight: 3) Hoth -> (Dagobah, Weight: 5) Hoth -> (Tatooine, Weight: 1)
--     Coruscant -> (Hoth, Weight: 3) Coruscant -> (Yavin, Weight: 12)
--     Dagobah -> (Hoth, Weight: 5) Dagobah -> (Endor, Weight: 2) Dagobah -> (Yavin, Weight: 3) Dagobah -> (Naboo, Weight: 8)
*/

public class Main {
    public static void main(String[] args) throws IOException {
        //Graph<String> graph = new Graph<>();
        readGalaxy();
    }


    //-- Helper method to get the weight between two vertices
    private static int getWeight(Graph<String> graph, String firstVertex, String secondVertex ) {

        for (Graph.Edge<String> edge : graph.neighbors.get(graph.vertices.indexOf(firstVertex))) {
            if (edge.targetVertex.equals(secondVertex)) {
                return edge.weight;
            }
        }
        return -1; //-- If there's no edge between the vertices
    }


        //System.out.println(fread.read());
        /*graph.addVertex("Hoth");
        graph.addVertex("Endor");
        graph.addVertex("Coruscant");
        graph.addVertex("Tatooine");
        graph.addVertex("Dagobah");
        graph.addEdge("Hoth", "Endor", 9);
        graph.addEdge("Hoth", "Coruscant", 3);
        graph.addEdge("Hoth", "Tatooine", 1);
        graph.addEdge("Hoth", "Dagobah", 5); */
        ////PrintWriter writer = new PrintWriter((new FileOutputStream("patrols.txt")));
        /* 
        writer.print(bfsTrav);
        writer.print(fileString);
        writer.close();
        */
    

    //-- Changed from private static <V> List<V> readGalaxy(Graph<String> graph).
    private static void readGalaxy() {
        Graph<String> graph = new Graph<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the map file"); 
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
                //System.out.println(vertex + "-> ");
                for (String neighbor : graph.getNeighbors(vertex)) {
                    int weight = getWeight(graph, vertex, neighbor);
                    System.out.print(vertex + " -> " + "(" + neighbor + ", Weight: " + weight + ") ");
                }
                System.out.println("\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File does not ");
            e.printStackTrace();
        } in.close();
    } 

    /*
    * Route File Format: 
    -- 1) Each line in the file will contain the pilot's name followed by a list of planets
    -- 2) Each planet will be seperated by a space
    -- 3) The first vertex listed is the starting vertex of the patrol
    --      Example: Sully_Olvar Tatooine Dagobah Naboo Yavin Alderaan
    -- 
    */

    public static List<String> readRoute() {
        List<String> pRoute = new ArrayList<>();
        Graph<String> graph = new Graph<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the route file"); 
        String fileString = in.nextLine();

        try {
            File file = new File(fileString);
            Scanner fileScan = new Scanner(file);

            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                String[] parts = line.split(" ");
                String name = parts[0];
                String startingVertex = parts[1];
                graph.addVertex(startingVertex);
                //String path = parts[2].substring(2);
                
                for (int i = 2; i < parts.length; i++) {
                    graph.addVertex(parts[i]);
                }
                pRoute.add(line);
                System.out.println("route info test: " + line);
                System.out.println("name test" + name);
                System.out.println("start vertex test" + startingVertex);
            } fileScan.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Route file doesnt exist");
            e.printStackTrace();
        } in.close();
        return pRoute;
    }
    
    
    
    /*StringTokenizer token = new StringTokenizer(line, " ");
    String name = token.nextToken();
    String startingVertex = token.nextToken();
    String planets = token.nextToken(" ");
    System.out.println("name: " + "starting vertex: " + "planets: "); */
    //pRoute.add(new List(name,startingVertex,planets)); 
    
}

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

    




