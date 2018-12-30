package com.corenlp;

import com.news.util.Stemmer;
import com.news.util.Stemmer_dict;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.trees.Tree;

import java.util.*;

import static com.news.util.Stopwords.isStopword;
import static java.lang.Character.isUpperCase;

public class dict3 {


    public static void main(String[] args) {


        // Sentence sentence = new Sentence("President Trump’s aggressive trade strategy accelerated on two fronts Monday, with the White House pressing China to quickly follow through on commitments made over the weekend while simultaneously clashing with lawmakers over a fragile North America pact.");
        String test = "Political observers can already see how protests are erupting throughout the state in response to how the Republican legislature voted to significantly reduce the powers of Gov.-elect Tony Evers and Attorney General-elect Josh Kaul before they can take office, according to CNN. Wisconsin Republicans are hardly innovators here — North Carolina Republicans passed a similar law after a Democrat won the governor's mansion in their state in 2016, and Michigan Republicans are aiming to do something similar right now — but the situation is particularly galling in Wisconsin due to its reputation as a bastion of integrity when it comes to the democratic process. In order to better understand both the radical nature of the legislature's actions and what it will mean for the future of democracy in Wisconsin, Salon spoke by email with Michael Wagner, an associate professor at the University of Wisconsin-Madison who specializes in \"research, teaching, and service are animated by the question, 'how well does democracy work?'\"";
        Document doc = new Document(test);

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
                    for (String iter : iters) {
                        if (i > 1 && i % 2 == 1) {

                            if(!isStopword(iter) ) {
                                if (isUpperCase(iter.charAt(0))) {
                                    out_first.add(new Stemmer_dict().stem1(iter).replaceAll("['`]{2}", ""));

                                }
                                else{
                                   out_first.add(new Stemmer_dict().stem2(iter).replaceAll("['`]{2}", ""));
                                }
                            }

                        }
                        i++;
                    }
                    String res = String.join(" ", out_first).trim();
//                    System.out.println(sent.toString());
                    System.out.println(res);
//                    System.out.println(out_first);
//                    output.add(out_first);
                }

            }


        }
    }
}
