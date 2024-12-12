package com.example.asssignment;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.asssignment.models.Question;
import com.example.asssignment.models.User;
import com.example.asssignment.repositories.QuestionRepository;
import com.example.asssignment.repositories.UserRepository;

@SpringBootApplication
public class AsssignmentApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AsssignmentApplication.class, args);
	}

	@Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        questionRepository.saveAll(Arrays.asList(
			new Question("What is 5 + 3?", "6", "7", "8", "9", "8"),
			new Question("What is 10 - 7?", "2", "3", "4", "5", "3"),
			new Question("What is 6 x 3?", "12", "18", "15", "9", "18"),
			new Question("What is 20 / 4?", "3", "5", "4", "6", "5"),
			new Question("What is 15 - 9?", "6", "5", "7", "4", "6"),
			new Question("What is 7 + 6?", "12", "14", "13", "15", "13"),
			new Question("What is 9 x 2?", "16", "17", "18", "19", "18"),
			new Question("What is 16 / 2?", "8", "7", "6", "5", "8"),
			new Question("What is 25 - 15?", "11", "12", "10", "13", "10"),
			new Question("What is 8 + 7?", "13", "14", "15", "16", "15")
        ));

        userRepository.save(new User("user1"));
    }

}
