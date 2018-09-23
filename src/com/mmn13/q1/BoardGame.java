package com.mmn13.q1;

import javax.swing.*;
import java.awt.*;

public class BoardGame extends JPanel {

    private Cell[][] cells;
    private CellType currentPlayer = CellType.Red;
    private int[] currentState = new int[7];
    final int COLS = 7;
    final int ROWS = 6;

    public BoardGame() {
        setLayout(new GridLayout(ROWS, COLS));
        setSize(350, 250);
        initCells();
        setVisible(true);
    }

    //set the board with empty disks
    private void initCells() {
        cells = new Cell[ROWS][COLS];
        for (int j = ROWS - 1; j >= 0; j--) {
            for (int i = 0; i < COLS; i++) {
                Cell cell = new Cell();
                cells[j][i] = cell;
                add(cell);
                cell.setSize(50, 50);
                cell.setCellType(CellType.Empty);
            }
        }
    }

    //add disk to the board
    public CellType AddDisk(int column) {
        //if possible to put more disks add one
        if (currentState[column] < ROWS) {
            int y = currentState[column]++;
            cells[y][column].setCellType(currentPlayer);
            //set how is the next player
            if (currentPlayer == CellType.Red) {
                currentPlayer = CellType.Black;
            } else {
                currentPlayer = CellType.Red;
            }
        }
        return currentPlayer;
    }

    //clean the board for a new game
    public void reset() {
        for (int j = ROWS - 1; j >= 0; j--) {
            for (int i = 0; i < COLS; i++) {
                cells[j][i].setCellType(CellType.Empty);
            }
        }
        currentState = new int[COLS];
        currentPlayer = CellType.Red;
    }

    //check logic to see if a user wins
    //return the color of the winner
    public CellType checkWin() {
        for (int r = ROWS - 1; r >= 0; r--) { // iterate rows, bottom to top
            for (int c = 0; c < COLS; c++) { // iterate columns, left to right
                CellType player = this.cells[r][c].getCellType();
                if (player == CellType.Empty)
                    continue; // don't check empty slots

                if (c + 3 < COLS &&
                        player == this.cells[r][c + 1].getCellType() && // look right
                        player == this.cells[r][c + 2].getCellType() &&
                        player == this.cells[r][c + 3].getCellType())
                    return player;
                if (r + 3 < ROWS) {
                    if (player == this.cells[r + 1][c].getCellType() && // look up
                            player == this.cells[r + 2][c].getCellType() &&
                            player == this.cells[r + 3][c].getCellType())
                        return player;
                    if (c + 3 < COLS &&
                            player == this.cells[r + 1][c + 1].getCellType() && // look up & right
                            player == this.cells[r + 2][c + 2].getCellType() &&
                            player == this.cells[r + 3][c + 3].getCellType())
                        return player;
                    if (c - 3 >= 0 &&
                            player == this.cells[r + 1][c - 1].getCellType() && // look up & left
                            player == this.cells[r + 2][c - 2].getCellType() &&
                            player == this.cells[r + 3][c - 3].getCellType())
                        return player;
                }
            }
        }
        return CellType.Empty; // no winner found
    }
}