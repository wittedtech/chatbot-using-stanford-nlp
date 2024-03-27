package com.concor.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.concor.chatbot.service.BotService;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class InteractionController {

    private final BotService botService;
    @Autowired private CoreNLPController nlpController;

    @Autowired
    public InteractionController(BotService botService) {
        this.botService = botService;
    }

    @GetMapping("/interact")
    public String showInteractionPage() {
        return "interaction"; // Assuming you have a Thymeleaf template named interaction.html
    }

    @PostMapping("/interact")
   @ResponseBody public String handleUserInput(@RequestBody JsonNode userInput) {
        // Process user input and get bot response
    	String nlpResponse = nlpController.processSentence(userInput.get("userInput").asText());
        String botResponse = botService.getBotResponse(userInput.get("userInput").asText());

        // Save user interaction data if necessary
        //saveUserInteractionData(userId, userInput, botResponse);

        // Redirect to the interaction page with updated data
        return botResponse;
    }

    private void saveUserInteractionData(String userId, String userInput, String botResponse) {
    	
        // Implement logic to save user interaction data to the database or any other storage
        // For example, you can create a new entity to represent user interactions and save it using a service or repository
    }
}