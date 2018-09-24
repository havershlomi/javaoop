package com.mmn13.q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class QuestionBank {

    private String filePath;
    private ArrayList<Question> bank = new ArrayList<>();

    public QuestionBank(String filePath) {
        this.filePath = filePath;
    }

    //load all the questions from the file
    public boolean loadQuestions() {
        Scanner input = null;
        try {
            String question;
            String correctAnswer;
            ArrayList<String> otherAnswers = new ArrayList<>();
            input = new Scanner(new File(filePath));
            //I assumed that all the questions are in a valid format
            while (input.hasNextLine()) {
                question = input.nextLine();
                otherAnswers = new ArrayList<>();
                correctAnswer = input.nextLine();
                for (int i = 0; i < 3; i++) {
                    otherAnswers.add(input.nextLine());
                }
                //add questions to the bank
                Question q = new Question(question, correctAnswer, otherAnswers);
                bank.add(q);
            }

            return true;
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (input != null)
                input.close();
        }
    }

    // get enumerator ober the questions
    public Iterator<Question> getQuestionIterator() {
        Collections.shuffle(bank);
        return bank.iterator();
    }
}
