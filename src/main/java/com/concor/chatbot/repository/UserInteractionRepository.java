package com.concor.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concor.chatbot.model.UserInteraction;

@Repository
public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {

}
