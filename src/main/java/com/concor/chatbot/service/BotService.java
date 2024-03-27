package com.concor.chatbot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concor.chatbot.model.Training;
import com.concor.chatbot.repository.TrainingRepository;

@Service
public class BotService {

    private final TrainingRepository trainingRepository;

    @Autowired
    public BotService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public String getBotResponse(String userInput) {
        // First, check if there's an exact match for the user input in the database
        Optional<Training> exactMatch = trainingRepository.findByQuestion(userInput);
        if (exactMatch.isPresent()) {
            return exactMatch.get().getAnswer();
        }else {
        	
        }
		/*
		 * 
		 * // If no exact match found, try to find a similar question in the database
		 * List<Training> similarQuestions =
		 * trainingRepository.findSimilarQuestions(userInput); if
		 * (!similarQuestions.isEmpty()) { return similarQuestions.get(0).getAnswer();
		 * // Return the answer for the most similar question }
		 */

        // If no similar question found, provide a default response
        return "I'm sorry, I don't understand your question.";
    }

    public void trainBot(String question, String answer) {
        // Store the new question and answer in the database for training the bot
        Training training = new Training(question, answer);
        trainingRepository.save(training);
    }
}
