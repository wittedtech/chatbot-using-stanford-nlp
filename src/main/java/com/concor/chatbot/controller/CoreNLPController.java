package com.concor.chatbot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;

@Service
public class CoreNLPController {
	@Autowired PDFQuestionAnswerSeparator questionIdentifier;
	
	public String processSentence(@RequestBody String text) {
		
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP("tokenize, ssplit, parse, sentiment");

        // Analyze the sentiment of the text
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        // Get the sentiment value
        String sentiment = annotation.get(SentimentCoreAnnotations.SentimentClass.class);

        // Generate a response based on the sentiment
        String response = generateResponse(sentiment);
		/*
		 * // Process the input text using Stanford CoreNLP Document doc = new
		 * Document(text);
		 * 
		 * // Get sentiment String sentiment = getSentiment(doc);
		 * 
		 * // Get verbs List<String> verbs = getVerbs(doc);
		 * 
		 * // Get named entities List<List<CoreLabel>> ner = getNamedEntities(doc);
		 * 
		 * // Check if the sentence is a question boolean isQuestion = isQuestion(text);
		 * 
		 * // Generate response based on identified aspects StringBuilder response = new
		 * StringBuilder();
		 * response.append("Sentiment: ").append(sentiment).append("\n");
		 * response.append("Verbs: ").append(verbs).append("\n");
		 * response.append("Named Entities: ").append(ner).append("\n");
		 * response.append("Is Question: ").append(isQuestion);
		 */
        return response.toString();
    }
    
	/*
	 * // Method to get sentiment of the document private String
	 * getSentiment(Document doc) {
	 * 
	 * // SentimentAnnotatedTree tree = doc.sentences().get(0).sentimentTree();
	 * return doc.sentences().get(0).sentiment().toString(); }
	 * 
	 * // Convert sentiment score to string representation private String
	 * sentimentToString(int sentimentScore) { if (sentimentScore < 2) { return
	 * "Negative"; } else if (sentimentScore > 2) { return "Positive"; } else {
	 * return "Neutral"; } }
	 * 
	 * // Method to get verbs from the document private List<String>
	 * getVerbs(Document doc) {
	 * 
	 * return doc.; }
	 * 
	 * // Method to get named entities from the document private
	 * List<List<CoreLabel>> getNamedEntities(Document doc) { return new
	 * ArrayList<>(); }
	 * 
	 * // Method to identify if a sentence is a question private boolean
	 * isQuestion(String text) { // Implement your logic to detect if the text is a
	 * question return text.trim().endsWith("?"); }
	 */
    
 // Generate a response based on sentiment
    public static String generateResponse(String sentiment) {
        switch (sentiment) {
            case "Very negative":
            case "Negative":
                return "I'm sorry to hear that. Is there anything I can do to help?";
            case "Neutral":
                return "I see. How can I assist you?";
            case "Positive":
            case "Very positive":
                return "I'm glad to hear that!";
            default:
                return "I'm not sure how to respond to that.";
        }
    }
}
