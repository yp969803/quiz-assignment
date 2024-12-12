## Prerequisite

- java-17
- mvn-3.8.7

## Run locally

```
# clone the repo
# cd into folder
mvn spring-boot:run

# Application will start in localhost:8080

```

## Api example

### To start quiz

POST http://localhost:8080/api/quiz/start?username=user1

### To get question

GET http://localhost:8080/api/quiz/question?username=user1 

### To submit answer to question

POST http://localhost:8080/api/quiz/submit?username=user1&questionId=8&answer=8 

### To get the status of quiz

GET http://localhost:8080/api/quiz/stats?username=user1