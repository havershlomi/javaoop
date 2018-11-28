package com.mmn13.q1;

import javax.swing.*;
import java.awt.*;

public class FourInARow extends JFrame {

    private int windowSize = 350;
    private BoardGame bGame = new BoardGame();
    //game initializer
    public FourInARow() {
        Dimension dimm = new Dimension(windowSize, windowSize);
        setLayout(new BorderLayout());
        add(new Controllers(bGame), BorderLayout.SOUTH);
        add(bGame, BorderLayout.CENTER);
        setSize(dimm);
        setVisible(true);
    }
}
