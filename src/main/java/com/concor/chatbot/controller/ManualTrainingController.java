package com.concor.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concor.chatbot.model.Training;
import com.concor.chatbot.repository.TrainingRepository;

@Controller
@RequestMapping("/manual-training")
public class ManualTrainingController {

    private final TrainingRepository trainingRepository;

    @Autowired
    public ManualTrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @GetMapping
    public String showManualTrainingForm(@ModelAttribute("training") Training training) {
        return "manual-training-form"; // Assuming you have a Thymeleaf template named manual-training-form.html
    }

    @PostMapping
    public String processManualTrainingForm(@ModelAttribute("training") Training training) {
        // Save the question and answer to the database
    	trainingRepository.save(training);
        return "redirect:/"; // Redirect to the home page after training
    }
}