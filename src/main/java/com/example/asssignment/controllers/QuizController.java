package com.example.asssignment.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.asssignment.models.Question;
import com.example.asssignment.models.User;
import com.example.asssignment.repositories.QuestionRepository;
import com.example.asssignment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    private final Map<String, Set<Long>> userAnsweredQuestions = new HashMap<>();

    @PostMapping("/start")
    public String startQuiz(@RequestParam(required = true) String username) {
        userAnsweredQuestions.put(username, new HashSet<>());
        return "Quiz session started for user: " + username;
    }

    @GetMapping("/question")
    public Question getQuestion(@RequestParam(required = true) String username) {
        Set<Long> answeredQuestions = userAnsweredQuestions.getOrDefault(username, new HashSet<>());
        List<Question> list = questionRepository.findRandomUnansweredQuestion(answeredQuestions);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;            
    }

    @PostMapping("/submit")
    public String submitAnswer(@RequestParam(required = true) String username, @RequestParam(required = true) Long questionId, @RequestParam(required = true) String answer) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));

        if (question.getCorrectAnswer().equalsIgnoreCase(answer)) {
            user.setCorrectAnswers(user.getCorrectAnswers() + 1);
        } else {
            user.setIncorrectAnswers(user.getIncorrectAnswers() + 1);
        }
        userRepository.save(user);
        userAnsweredQuestions.get(username).add(questionId);

        return "Answer submitted for question: " + questionId;
    }

    @GetMapping("/stats")
    public Map<String, Integer> getStats(@RequestParam(required = true) String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Map<String, Integer> stats = new HashMap<>();
        stats.put("totalAnswered", user.getCorrectAnswers() + user.getIncorrectAnswers());
        stats.put("correctAnswers", user.getCorrectAnswers());
        stats.put("incorrectAnswers", user.getIncorrectAnswers());
        return stats;
    }
}
