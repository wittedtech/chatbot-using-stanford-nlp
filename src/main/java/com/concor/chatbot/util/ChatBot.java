package com.concor.chatbot.util;

import java.util.HashMap;
import java.util.Map;

public class ChatBot {
	 private Map<String, String> responses;

	    public ChatBot() {
	        responses = new HashMap<>();
	    }

	    public void setResponse(String pattern, String response) {
	        responses.put(pattern, response);
	    }

	    public String getResponse(String input) {
	        // Implement logic to retrieve response based on input pattern
	        return responses.getOrDefault(input, "I'm sorry, I don't understand.");
	    }

}
