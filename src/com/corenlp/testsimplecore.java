package com.corenlp;

import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.simple.*;
import edu.stanford.nlp.trees.Constituent;
import edu.stanford.nlp.trees.Tree;
import org.apache.commons.collections.ArrayStack;

import java.util.*;


public class testsimplecore {


    public static void main(String[] args) {
        // Sentence sentence = new Sentence("President Trump’s aggressive trade strategy accelerated on two fronts Monday, with the White House pressing China to quickly follow through on commitments made over the weekend while simultaneously clashing with lawmakers over a fragile North America pact.");
        String test1 = "Both discussions, driven largely by Trump and U.S. Trade Representative Robert E. Lighthizer, are at the center of the White House’s agenda and could have profound implications for the global economy if negotiations collapse.";
        String test2 = "I love Beidan,Anny. PHP is great. How about CS?";
        String test4 = "Sen. Lindsey Graham said Tuesday night that he's going to come down \"like a ton of bricks\" on Saudi Arabian Crown Prince Mohammed bin Salman (MBS) following the murder of Jamal Khashoggi.";
        String test5 = "\n" +
                "Sen. Lindsey Graham said Tuesday night that he's going to come down \"like a ton of bricks\" on Saudi Arabian Crown Prince Mohammed bin Salman (MBS) following the murder of Jamal Khashoggi.\n" +
                "\n" +
                "Graham (R-S.C.) said that the October killing of Khashoggi could not have taken place without MBS knowing about it.\n" +
                "\n" +
                "Khashoggi, a Washington Post columnist who was born in Saudi Arabia, was killed inside the Saudi Consulate in Istanbul, Turkey in October.\n" +
                "\n" +
                "\"It's not in our interests to give the crown prince ... a pass for murder of an American resident with three American children,\" Graham said. \"Because then you're going to have open season on Americans everywhere.\"";


        Document doc = new Document(test5);

        // Name Entity
//        List<String> nerTags = sent.nerTags();
//        String firstPOSTag = sent.posTag(0);
//        System.out.println(nerTags);


        for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences
            /** Get lists of words - We're only asking for words -- no need to load any models yet */
            //System.out.println("The second word of the sentence '" + sent + "' is " + sent.word(1));
            //List<String> words= sent.words();
            //System.out.println(words);

            /** Get lists of lemma - When we ask for the lemma, it will load and run the part of speech tagger */
            /** It return the orginal tense and form of each word */
            //System.out.println("The third lemma of the sentence '" + sent + "' is " + sent.lemma(2));
//            List<String> lemmas = sent.lemmas();
//            String lemma = sent.lemma(2);
//            System.out.println(lemmas);

            /** return part of speech tags */

//            System.out.println(sent.posTags().size());
//            System.out.println(sent.posTags().get(2));
//            System.out.println(sent.posTags());
//


            /** Parse the sentence - it will load and run the parser. It will return a tree of constituency */
            //System.out.println("The parse of the sentence '" + sent + "' is " + sent.parse());
            Tree parser = sent.parse();
            //System.out.println(parser);
            // This will print out first child of each sentence;
            //System.out.println(parser.getChild(0).firstChild());
//            Set<Constituent> constituents = parser.constituents();
//            System.out.println(constituents);
            //System.out.println(parser.firstChild());

            //List<? extends Tree> leaves = parser.getLeaves();
            //System.out.println(leaves);

            //  Collection<Label> labels = parser.labels();
            //System.out.println(labels);

            //Lets try iterator here, list the structure of the tree


            Iterator<Tree> items = parser.iterator();
//
//            while(items.hasNext()){
//                System.out.println(items.next().depth());
//            }


//            Iterator<Tree> items2 =  parser.iterator();
//            while(items2.hasNext()){
//                System.out.println(items2.next().toString());
//            }


//
//
            while (items.hasNext()) {
                Tree temp = items.next();
                // Can add functions to restrict length here later
                List<List<String>> output = new ArrayList<>();
                if (temp.depth() == 2 && temp.toString().contains("NP")) {
                    String[] iters = temp.toString().split("[\\s,(,)]+");
                    List<String> out_first = new ArrayList<>();
                    int i = 0;
                    for (String iter : iters) {
                        if (i > 1 && i % 2 == 1) {
                            out_first.add(iter);
//                            System.out.println(iter);
                        }
                        i++;
                    }
                    System.out.println(out_first);
                    output.add(out_first);
                }


                /** Name Entity Recognition */
//            List<String> nerTags = sent.nerTags();
//            System.out.println(nerTags);

            }
        }


    }
}






