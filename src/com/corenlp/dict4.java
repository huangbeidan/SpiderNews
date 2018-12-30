package com.corenlp;

import com.news.util.Stemmer;
import com.news.util.Stemmer_dict;
import com.opencsv.CSVWriter;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.trees.Tree;
import javafx.beans.binding.StringBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.xalan.lib.sql.ObjectArray;

import java.io.*;
import java.util.*;

import static com.news.util.Stopwords.isStopword;
import static java.lang.Character.isUpperCase;

public class dict4 {

    public static void simplewriter (String filePath, TreeMap<String, String> dict)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            //put the value from dict to array so as to write to the csv file
            for (String name: dict.keySet()){
                String value = dict.get(name);

                writer.writeNext(new String[] {name,value});

            }
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }





    public static void writeData2csv (String filePath, TreeMap<String, String> dict)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            //put the value from dict to array so as to write to the csv file
            for (String name: dict.keySet()){
                String value = dict.get(name);

                String[] na = new String[] {name};
               String[] s = value.split("[~]");
               String[] joined = ArrayUtils.addAll(na,s);
                writer.writeNext(joined);

            }
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    public static void readfromcsv(String csvFile){
//        BufferedReader br = null;
//        String line = "";
//        String cvsSplitBy = ",";
//        try {
//            br = new BufferedReader(new FileReader(csvFile));
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] news = line.split(cvsSplitBy);
//                if(news.length == 3){
//
//                System.out.println(news[2]);}
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


    public static void main(String[] args) {


        // Sentence sentence = new Sentence("President Trump’s aggressive trade strategy accelerated on two fronts Monday, with the White House pressing China to quickly follow through on commitments made over the weekend while simultaneously clashing with lawmakers over a fragile North America pact.");
//        String test = "Political observers can already see how protests are erupting throughout the state in response to how the Republican legislature voted to significantly reduce the powers of Gov.-elect Tony Evers and Attorney General-elect Josh Kaul before they can take office, according to CNN. Wisconsin Republicans are hardly innovators here — North Carolina Republicans passed a similar law after a Democrat won the governor's mansion in their state in 2016, and Michigan Republicans are aiming to do something similar right now — but the situation is particularly galling in Wisconsin due to its reputation as a bastion of integrity when it comes to the democratic process. In order to better understand both the radical nature of the legislature's actions and what it will mean for the future of democracy in Wisconsin, Salon spoke by email with Michael Wagner, an associate professor at the University of Wisconsin-Madison who specializes in \"research, teaching, and service are animated by the question, 'how well does democracy work?'\"";
//        String test2 = "Pompeo has traveled to Pyongyang twice in recent weeks for meetings with Kim Jong Un, and has said there is a \"shared understanding\" between the two sides about what they hope to achieve in talks. South Korean media speculated that Pompeo could make a third trip to Pyongyang after Kim Yong Chol`s U.S. trip.";
//        String test3 = "Kim Yong Chol`s trip comes amid two sets of other pre-summit talks between Washington and Pyongyang. Kim Yong Chol`s trip comes amid two sets of other pre-summit talks between Washington and Pyongyang.";

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        TreeMap<String, String> dict  = new TreeMap<>();
        String csvFile = "10knewsarticles.csv";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] news = line.split(cvsSplitBy);
                if(news.length == 3){

                    Document doc = new Document(news[2]);



                    for (Sentence sent : doc.sentences()) {

                        Tree parser = sent.parse();
                        Iterator<Tree> items = parser.iterator();
                        while (items.hasNext()) {
                            Tree temp = items.next();
                            // Can add functions to restrict length here later
                            List<List<String>> output = new ArrayList<>();
                            if (temp.depth() == 2 && temp.toString().contains("NP")) {
                                String[] iters = temp.toString().split("[\\s,(,)]+");
                                List<String> out_first = new ArrayList<>();
                                int i = 0;

                            if(iters.length!=0 && iters[3].length()!=0 && isUpperCase(iters[3].charAt(0)) && !isStopword(iters[3].toLowerCase()) ){

                                    for (String iter : iters) {
                                        if (i > 1 && i % 2 == 1) {

                                                    out_first.add(new Stemmer_dict().stem2(iter).replaceAll("['`]{2}", ""));

                                        }
                                        i++;

                                    }
                                    String out_first_key = String.join(" ", out_first).trim();
                                    System.out.println(out_first_key);

                                }




                                String out_first_key = String.join(" ", out_first).trim();

                                if(out_first.size()>0){
                                    // if dict already has the key, then merge value
                                    if(dict.containsKey(out_first_key)){

                                        StringBuilder builder = new StringBuilder();
                                        builder.append(dict.get(out_first_key));
                                        builder.append("~");
                                        builder.append(sent.toString());
                                        String result = builder.toString();


//                            String new_V = dict.get(out_first_key) + "666"+ sent.toString();
                                        dict.put(out_first_key,result);

                                    }
                                    else {
                                        dict.put(out_first_key,sent.toString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // print out TreeMap
//            for (String name: dict.keySet()){
//                String value = dict.get(name);
//                System.out.println(name + ":" + value);
//
//            }

            // Write to csv
            String filepath = "dictionary.csv";
//            writeData2csv(filepath,dict);
            simplewriter(filepath,dict);

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

}
