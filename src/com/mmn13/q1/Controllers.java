package com.mmn13.q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controllers extends JPanel implements ActionListener {

    private BoardGame game;
    private JLabel turnLabel;
    private Boolean gameOver = false;

    //Set all the Controllers for the game
    public Controllers(BoardGame game) {
        this.game = game;
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //set the number buttons
        topPanel.setLayout(new GridLayout(1, 7));
        for (int i = 1; i <= 7; i++) {
            JButton jButton = new JButton(i + "");
            jButton.addActionListener(this);
            topPanel.add(jButton);
        }
        //set turn label
        bottomPanel.setLayout(new BorderLayout());
        turnLabel = new JLabel("Next is Red");
        bottomPanel.add(turnLabel, BorderLayout.WEST);

        //set the clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        bottomPanel.add(clearButton, BorderLayout.CENTER);

        //put all the panels in place
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }


    //handle click actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().toLowerCase().equals("clear")) {
            //reset the game
            this.game.reset();
            turnLabel.setText("Next is: Red");
            gameOver = false;
        } else if (!gameOver) {
            //if game not finished set a new disk
            //get column number
            int column = Integer.parseInt(e.getActionCommand()) - 1;
            //add disk and get next player
            CellType nextTurn = this.game.AddDisk(column);
            //check if someone wins
            CellType winner = this.game.checkWin();
            //announce the winner
            if (winner != CellType.Empty) {
                turnLabel.setText(winner.toString() + " Wins");
                gameOver = true;

            } else {
                //set next play
                turnLabel.setText("Next is: " + nextTurn.toString());
            }
        }
    }
}
