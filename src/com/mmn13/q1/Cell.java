package com.mmn13.q1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Cell extends JPanel {

    final int CIRCLE_SIZE = 25;
    private CellType cellType = CellType.Empty;

    public Cell() {
        super();
        setBorder(new LineBorder(Color.BLACK));
        setVisible(true);
    }

    public CellType getCellType() {
        return cellType;
    }


    private Color getColor() {
        if (this.cellType == CellType.Black)
            return Color.BLACK;
        return Color.RED;
    }

    //put a new disk in the cell
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
        repaint();
    }

    //paint the disk in the cell
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getCellType() == CellType.Empty)
            return;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getColor());
        g2.fillOval(10, 10, CIRCLE_SIZE, CIRCLE_SIZE);
    }
}
