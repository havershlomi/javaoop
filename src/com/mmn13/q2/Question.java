package com.mmn13.q2;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Collections;

public class Question {

    private String question;
    private String correctAnswer;
    private ArrayList<String> allAnswers;

    public Question(String question, String correctAnswer, ArrayList<String> otherAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.allAnswers = otherAnswers;
        this.allAnswers.add(correctAnswer);
    }

    public String getQuestion() {
        return question;
    }

    //check if the answer supplied is the right one
    public Boolean isCorrectAnswer(String selectedAnswer) {
        return correctAnswer.toLowerCase().equals(selectedAnswer.toLowerCase());
    }

    public ArrayList<String> getAnswers() {
        Collections.shuffle(allAnswers);
        return allAnswers;
    }
}
