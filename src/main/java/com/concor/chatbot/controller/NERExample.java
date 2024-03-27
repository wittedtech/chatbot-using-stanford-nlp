package com.concor.chatbot.controller;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class NERExample {
    public static void main(String[] args) {
        // Set up Stanford NLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Create scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Continuously process user input
        while (true) {
            // Get user question
            System.out.print("User: ");
            String question = scanner.nextLine();

            // Process the question
            Annotation document = new Annotation(question);
            pipeline.annotate(document);

            // Extract named entities
            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
            String personName = null;
            for (CoreMap sentence : sentences) {
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    String nerTag = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                    if (nerTag.equals("PERSON")) { // Check if the entity is a person
                        personName = token.get(CoreAnnotations.TextAnnotation.class);
                        break;
                    }
                }
            }

            // Generate answer based on the identified person
            String answer;
            if (personName != null) {
                answer = "Yes, I know " + personName + ".";
                // You can add more logic to search for additional information about the mentioned person
            } else {
                answer = "I'm sorry, I don't know who you're referring to.";
            }

            // Output the answer
            System.out.println("Bot: " + answer);
        }
    }
}