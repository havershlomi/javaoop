package com.mmn12.q1;

import javax.swing.*;
import java.awt.*;

public class Window {

    private int windowSize = 400;
    private Panel panel;

    public Window() {
        //set all the frame properties
        JFrame newFrame = new JFrame();
        Dimension dimm = new Dimension(windowSize, windowSize);
        newFrame.setSize(dimm);
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setPreferredSize(dimm);
        this.panel = new Panel();
        newFrame.add(this.panel);
        newFrame.setVisible(true);
        newFrame.pack();
    }

    //Add dot to the frame
    public String addDot(String command) {
        String[] split = command.split(",");
        int x = 0, y = 0;
        if (split.length != 2)
            return "Invalid input";
        try {
            x = Integer.parseInt(split[0]);
            y = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            return "Number is incorrect format!";
        }
        if (x < 0 || x > windowSize || y < 0 || y > windowSize)
            return "Number is out of the range of (0," + windowSize + ")";
        this.panel.addDot(x, y);
        return null;
    }

}
