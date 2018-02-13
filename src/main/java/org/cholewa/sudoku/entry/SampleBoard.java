package org.cholewa.sudoku.entry;

import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.filler.SudokuFiller;

public class SampleBoard {
    private SudokuBoard board;
    private SudokuFiller filler;

    public SampleBoard(SudokuBoard board, SudokuFiller filler) {
        this.board = board;
        this.filler = filler;
        fill();
    }

    private void fill() {
        board.getSudokuRows().get(0).getSudokuFields().get(1).setDigit(2);
        board.getSudokuRows().get(0).getSudokuFields().get(3).setDigit(5);
        board.getSudokuRows().get(0).getSudokuFields().get(5).setDigit(1);
        board.getSudokuRows().get(0).getSudokuFields().get(7).setDigit(9);

        board.getSudokuRows().get(1).getSudokuFields().get(0).setDigit(8);
        board.getSudokuRows().get(1).getSudokuFields().get(3).setDigit(2);
        board.getSudokuRows().get(1).getSudokuFields().get(5).setDigit(3);
        board.getSudokuRows().get(1).getSudokuFields().get(8).setDigit(6);

        board.getSudokuRows().get(2).getSudokuFields().get(1).setDigit(3);
        board.getSudokuRows().get(2).getSudokuFields().get(4).setDigit(6);
        board.getSudokuRows().get(2).getSudokuFields().get(7).setDigit(7);

        board.getSudokuRows().get(3).getSudokuFields().get(2).setDigit(1);
        board.getSudokuRows().get(3).getSudokuFields().get(6).setDigit(6);

        board.getSudokuRows().get(4).getSudokuFields().get(0).setDigit(5);
        board.getSudokuRows().get(4).getSudokuFields().get(1).setDigit(4);
        board.getSudokuRows().get(4).getSudokuFields().get(7).setDigit(1);
        board.getSudokuRows().get(4).getSudokuFields().get(8).setDigit(9);

        board.getSudokuRows().get(5).getSudokuFields().get(2).setDigit(2);
        board.getSudokuRows().get(5).getSudokuFields().get(6).setDigit(7);

        board.getSudokuRows().get(6).getSudokuFields().get(1).setDigit(9);
        board.getSudokuRows().get(6).getSudokuFields().get(4).setDigit(3);
        board.getSudokuRows().get(6).getSudokuFields().get(7).setDigit(8);

        board.getSudokuRows().get(7).getSudokuFields().get(0).setDigit(2);
        board.getSudokuRows().get(7).getSudokuFields().get(3).setDigit(8);
        board.getSudokuRows().get(7).getSudokuFields().get(5).setDigit(4);
        board.getSudokuRows().get(7).getSudokuFields().get(8).setDigit(7);

        board.getSudokuRows().get(8).getSudokuFields().get(1).setDigit(1);
        board.getSudokuRows().get(8).getSudokuFields().get(3).setDigit(9);
        board.getSudokuRows().get(8).getSudokuFields().get(5).setDigit(7);
        board.getSudokuRows().get(8).getSudokuFields().get(7).setDigit(6);
    }
}
