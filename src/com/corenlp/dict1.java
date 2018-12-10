package com.corenlp;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

import java.util.HashSet;
import java.util.Set;

public class dict1 {



    public static Set<String> me() {

        String test1 = "Both discussions, driven largely by Trump and U.S. Trade Representative Robert E. Lighthizer, are at the center of the White House’s agenda and could have profound implications for the global economy if negotiations collapse.";
        String test2 = "I love Beidan,Anny. PHP is great. How about CS?";
        Set<String> nounPhrases = new HashSet<>();

        Document doc = new Document(" " + test1);
        for (Sentence sent : doc.sentences()) {

            System.out.println("The parse of the sentence '" + sent + "' is " + sent.parse());
            //Iterate over every word in the sentence
            for(int i = 0; i < sent.words().size(); i++) {
                //Condition: if the word is a noun (posTag starts with "NN")
                if (sent.posTag(i) != null && sent.posTag(i).contains("NN")) {
                    //Put the word into the Set
                    nounPhrases.add(sent.word(i));
                }
            }
        }
        return nounPhrases;
    }

    public static void main(String[] args){

        System.out.println(dict1.me());

    }




}
