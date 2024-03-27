package com.concor.chatbot.controller;


import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.concor.chatbot.service.BotService;
import com.concor.chatbot.util.PDFParser;

@Controller
@RequestMapping("/document-training")
public class DocumentTrainingController {

    private final PDFParser pdfParser;
    @Autowired private BotService botService;
    @Autowired private PDFQuestionAnswerSeparator qaSeperator;

    public DocumentTrainingController(PDFParser pdfParser) {
        this.pdfParser = pdfParser;
    }

    @GetMapping
    public String showDocumentTrainingForm() {
        return "document-training-form"; // Assuming you have a Thymeleaf template named document-training-form.html
    }

    @PostMapping
    public String processDocumentTrainingForm(MultipartFile file) {
        // Perform document training logic
        try {
        	Map<String, String> qaMap = qaSeperator.separateQuestionsAndAnswers(file);
            String text = pdfParser.parsePDF(file); // Parse the uploaded PDF file
            extractAndStoreQA(text);
            // Process the extracted text and train the bot
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error if PDF parsing fails
        }
        return "redirect:/"; // Redirect to the home page after training
    }
    
    private void extractAndStoreQA(String text) {
        String[] lines = text.split("\\r?\\n");
        for (int i = 0; i < lines.length - 1; i += 2) {
            String question = lines[i].trim(); // Extract question
            String answer = lines[i + 1].trim(); // Extract corresponding answer
            botService.trainBot(question, answer); // Store question-answer pair
        }
    }
    
}