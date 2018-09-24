package com.mmn13.q2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

public class Trivia extends JFrame implements ActionListener {

    private boolean timerStoped;
    final int ANSWERING_TIME_SEC = 5;
    private int gameScore = 0;
    private QuestionBank bank;
    private JButton startBtn;
    private JButton finishBtn;
    private Iterator<Question> currentSession;
    private Panel qPanel;
    private JLabel questionLbl;
    private JLabel messageLbl;
    private ButtonGroup answersBtnGrp;
    private Timer timer;
    private Question currentQuestion;
    private JLabel scoreLbl;

    public Trivia() {
        //set layout
        setLayout(new BorderLayout());
        setSize(700, 500);
        setControllersPanel();
        setQuestionPanel();
        setScorePanel();

        //get the path to the question file
        URL url = getClass().getResource("trivia.txt");
        //create a question bank
        if (url != null)
            this.bank = new QuestionBank(url.getPath());

        //load questions if possible
        if (this.bank == null || !bank.loadQuestions()) {
            changeGameStatus(false);
            startBtn.setEnabled(false);
            scoreLbl.setText("Couldn't load questions from file");
        } else {
            //scoreLbl.setText("Press 'Start', you have " + ANSWERING_TIME_SEC + " sec for a question");
            timer = new Timer(ANSWERING_TIME_SEC * 1000, this);
        }
        setVisible(true);
        messageLbl.setText("Press 'Start', you have " + ANSWERING_TIME_SEC + " sec for a question");
    }

    //create message and score area
    private void setScorePanel() {
        Panel tPanel = new Panel();
        scoreLbl = new JLabel();
        tPanel.add(scoreLbl, BorderLayout.EAST);

        add(tPanel, BorderLayout.NORTH);
        tPanel.setVisible(true);
    }

    //set buttons area
    private void setControllersPanel() {
        Panel bPanel = new Panel();
        startBtn = new JButton("Start");
        startBtn.addActionListener(this);
        bPanel.add(startBtn);

        finishBtn = new JButton("Finish");
        finishBtn.addActionListener(this);
        finishBtn.setEnabled(false);
        bPanel.add(finishBtn);

        messageLbl = new JLabel();
        bPanel.add(messageLbl);

        add(bPanel, BorderLayout.SOUTH);
    }

    //generate question area
    private void setQuestionPanel() {
        qPanel = new Panel();
        qPanel.setLayout(new GridLayout(5, 1));
        qPanel.setSize(700, 200);
        questionLbl = new JLabel();
        qPanel.add(questionLbl);
        answersBtnGrp = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            JRadioButton a = new JRadioButton();
            answersBtnGrp.add(a);
            qPanel.add(a);
            a.addActionListener(this);
        }

        add(qPanel, BorderLayout.CENTER);
        qPanel.setVisible(false);
    }

    //handle click callback
    @Override
    public void actionPerformed(ActionEvent e) {
        //timer elapsed
        if (e.getActionCommand() == null) {
            timer.stop();
            timerStoped = true;
            gameScore -= 5;
            scoreLbl.setText("You are out of time");
            changeToNextQuestion();
            timerStoped = false;
        } else if (e.getActionCommand().toLowerCase().equals("start")) {
            //start a new game
            changeGameStatus(true);
            currentSession = bank.getQuestionIterator();
            changeToNextQuestion();
            gameScore = 0;

        } else if (e.getActionCommand().toLowerCase().equals("finish")) {
            //finsih existing game
            changeGameStatus(false);
            timer.stop();
        } else {
            //check if the answer is good
            if (!timerStoped) {
                timer.stop();
                if (currentQuestion.isCorrectAnswer(e.getActionCommand())) {
                    gameScore += 10;
                    scoreLbl.setText("You are correct");
                } else {
                    scoreLbl.setText("You are wrong");
                }
            } else {
                gameScore -= 5;
                scoreLbl.setText("You are out of time");
            }

            changeToNextQuestion();
        }
    }

    //reset the game to a certain status
    private void changeGameStatus(Boolean status) {
        finishBtn.setEnabled(status);
        qPanel.setVisible(status);

        if (status) {
            scoreLbl.setText("");
        } else {
            scoreLbl.setText("Your total score is: " + gameScore);
        }
    }

    //change to the next question if available
    private void changeToNextQuestion() {
        if (currentSession.hasNext()) {
            currentQuestion = currentSession.next();
            questionLbl.setText(currentQuestion.getQuestion());
            ArrayList<String> answers = currentQuestion.getAnswers();
            int index = 0;
            Enumeration<AbstractButton> btns = answersBtnGrp.getElements();
            while (btns.hasMoreElements()) {
                AbstractButton rb = btns.nextElement();
                rb.setText(answers.get(index));
                rb.setSelected(false);

                index++;
            }
            answersBtnGrp.clearSelection();
            timer.restart();
        } else {
            timer.stop();
            changeGameStatus(false);
        }
    }
}
