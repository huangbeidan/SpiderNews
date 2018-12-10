package com.corenlp;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

import java.util.*;


public class test {


    public static String text = "Joe Smith was born in California. " +
            "In 2017, he went to Paris, France in the summer. " +
            "His flight left at 3:00pm on July 10th, 2017. " +
            "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
            "He sent a postcard to his sister Jane Smith. " +
            "After hearing about Joe's trip, Jane decided she might go to France one day.";

    public static void main(String[] args) {
// build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(
                PropertiesUtils.asProperties(
                        "annotators", "tokenize,ssplit,pos",
//                        "annotators", "tokenize,ssplit,pos,lemma,parse,natlog",
                        "ssplit.isOneSentence", "true",
                        "parse.model", "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz",
                        "tokenize.language", "en"));

// read some text in the text variable
        Annotation document = new Annotation(text);

// run all Annotators on this text
        pipeline.annotate(document);

        // these are all the sentences in this document
// a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                //System.out.println(word);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                //System.out.println(pos);
                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                //System.out.println(ne);
            }

            // this is the parse tree of the current sentence
            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
            System.out.println(tree);

            // this is the Stanford dependency graph of the current sentence
            //SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
        }
    }


}
