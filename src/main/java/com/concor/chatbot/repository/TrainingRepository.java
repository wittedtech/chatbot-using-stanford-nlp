package com.concor.chatbot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.concor.chatbot.model.Training;
@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
	
	Optional<Training> findByQuestion(String question);

    @Query(value = "SELECT * FROM TRAINING_DATA WHERE MATCH(question) AGAINST(:userInput IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<Training> findSimilarQuestions(@Param("userInput") String userInput);

}
