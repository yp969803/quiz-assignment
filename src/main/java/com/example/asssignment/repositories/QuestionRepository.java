package com.example.asssignment.repositories;

import java.util.List;
import java.util.Set;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.asssignment.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    @Query("SELECT q FROM Question q WHERE (:answeredQuestions IS NULL OR q.id NOT IN :answeredQuestions) ORDER BY FUNCTION('RAND')")
    List<Question> findRandomUnansweredQuestion(@Param("answeredQuestions") Set<Long> answeredQuestions);
}
