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
@Table(name="USER_INTERACTION")
public class UserInteraction {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
    private Long id;
	@Column(name="USER_ID")
	private String userId;
	@Column(name="USER_INPUT")
    private String userInput;
	@Column(name="BOT_RESPONSE")
    private String botResponse;

}
