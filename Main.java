/*
 Ethan Payne
 11/01/2023
 ECP230000
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JFileChooser;

// Todo: 

public class Main {
    public static void main(String[] args) {
        
    }


    static List<Graph.Edge> readEdgeData() {
        List<Graph.Edge> edges = new ArrayList<>();

        try { //-- User interfaces are not allowed 
            Scanner fileReader = new Scanner(new File("Galaxy_Map.txt"));
            while (fileReader.hasNextLine()) {
                int v = fileReader.nextInt();
                int u = fileReader.nextInt();
                edges.add(new Graph.Edge(v, u));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File does not ");
            e.printStackTrace();
        }
        return edges;
    }

    /* 
    *Galaxy_Map.txt File Format
        -- Each edge will be represented as <adjacent vertex><comma><weight> 
        -- Line example:  
        -- Hoth Endor,9 Coruscant,3 Tatooine,1 Dagobah,5 
    */
    static List<Graph.Edge> readVertexData() {
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
    }

    




}