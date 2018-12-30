package graph;

import edu.princeton.cs.algorithms.Graph;
import edu.princeton.cs.algorithms.ST;
import edu.princeton.cs.introcs.In;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class PGraph {

    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Graph graph;             // the underlying graph
    private TreeMap<String,String> map;


    public Graph PGraph() {
        st = new ST<String, Integer>();

        // First construct the TreeMap by reading from csv: Vertex + Sentence1/Sentence2/...
        // delimiter here: "~"
        build_map("dictionary.csv");

        // Second pass builds the index by reading map to associate
        // distinct strings with an index
        int count = 0;
        for(Map.Entry<String,String> entry : map.entrySet()) {
            String key = entry.getKey();
            if(!st.contains(key)){
                st.put(key,st.size());
            }
            System.out.println(key + " => " + count);
            count++;
        }


        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // Third pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Graph(st.size());
        int size = keys.length;
//        int size = map.keySet().size()-1;
        for(int i=0; i<size-1; i++){
            for(int j=i+1;j<size;j++){
                String key1 = keys[i];
                String key2 = keys[j];
                if(map.get(key1).contains(map.get(key2)) || map.get(key2).contains(map.get(key1))){
                    graph.addEdge(i,j);
//                    System.out.println("1 Edge added!");
                }
            }
        }
        return graph;

    }


    public void build_map(String filename){

        map = new TreeMap<>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] pieces = line.split(cvsSplitBy);
                String nnp = pieces[0];
                String sentences = pieces[1];
                map.put(nnp,sentences);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Graph graph() {
        return graph;
    }

    public static void main(String[] args){


//        new PGraph().build_map("dictionary_test.csv");
     Graph graph = new PGraph().PGraph();
     System.out.println(graph.toString());

    }




}
