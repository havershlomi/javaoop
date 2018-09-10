package com.mmn12.q1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {

    ArrayList<Dot> dots = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();

    //add dot ot the collections and repaint
    public void addDot(int x, int y) {
        Dot dot = new Dot(x, y);
        if (dots.size() > 0) {
            Dot pDot = dots.get(dots.size() - 1);
            Line line = new Line(pDot, dot);
            lines.add(line);
        }
        dots.add(dot);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        //paint all the dots
        for (Dot d : dots) {
            g.drawLine(d.getX(), d.getY(), d.getX(), d.getY());
        }
        g.setColor(Color.BLACK);
        //paint all the lines and the distance of the line
        int sx = 0, sy = 0, d1x, d2x, d1y, d2y;
        double distance;
        for (Line l : lines) {
            //calulaye the position of the distance string and print it
            d1x = l.getD1().getX();
            d2x = l.getD2().getX();
            sx = Math.min(d1x, d2x) + Math.abs(d1x - d2x) / 2;
            d1y = l.getD1().getY();
            d2y = l.getD2().getY();
            sy = Math.min(d1y, d2y) + Math.abs(d1y - d2y) / 2;
            g.drawLine(d1x, d1y, d2x, d2y);
            distance = l.getDistance();
            g.drawString(Double.toString(distance), sx, sy);
        }
    }
}
