package com.concor.chatbot.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

@Service
public class PDFQuestionAnswerSeparator {
    
	public Map<String, String> separateQuestionsAndAnswers(MultipartFile file) {
        Map<String, String> questionAnswerMap = new HashMap<>();
        
        try {
            // Extract text from PDF
            String text = extractTextFromPDF(file);
            
            // Separate content into questions and answers
            List<String> questions = new ArrayList<>();
            List<String> answers = new ArrayList<>();
            separateQuestionsAndAnswers(text, questions, answers);
            
            // Populate question-answer map
            for (int i = 0; i < questions.size(); i++) {
                String question = questions.get(i);
                String answer = (i < answers.size()) ? answers.get(i) : ""; // Handle index out of bounds
                questionAnswerMap.put(question, answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return questionAnswerMap;
    }
    
    private String extractTextFromPDF(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
    
    private void separateQuestionsAndAnswers(String text, List<String> questions, List<String> answers) {
        // Split text into sentences
        StanfordCoreNLP pipeline = new StanfordCoreNLP();
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        
        // Separate sentences into questions and answers based on heuristics
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            String sentenceText = sentence.get(CoreAnnotations.TextAnnotation.class);
            if (isQuestion(sentenceText)) {
                questions.add(sentenceText);
            } else {
                answers.add(sentenceText);
            }
        }
    }
    
    public boolean isQuestion(String sentence) {
        return sentence.endsWith("?");
    }
}