package javaProject.QuizApplication;

import java.util.ArrayList;

public class User {
    ArrayList<QuizRecord> history = new ArrayList<>();
    private String name;
    private int userid;
    int totalQuestionsAttend;
    int totalCorrectAnswer;
    int totalMarks;

    User(String name, int userid){
        this.name = name;
        this.userid = userid;
    }

    String getName(){
        return name;
    }

    int getUserId(){
        return userid;
    }
}

class QuizRecord{
    String question;
    String userAnswer;
    String correctAnswer;
    boolean isCorrect;

    QuizRecord(
               String question,
               String userAnswer,
               String correctAnswer,
               boolean isCorrect){

        this.question = question;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
        this.isCorrect = isCorrect;
    }
}
