package com.concor.chatbot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="TRAINING_DATA")
public class Training {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
    private Long id;
	@Column(name="QUESTION")
    private String question;
	@Column(name="ANSWER")
    private String answer;
	
	
	 public Training() {
	    }

	    public Training(String question, String answer) {
	        this.question = question;
	        this.answer = answer;
	    }

}
