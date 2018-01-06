package org.cholewa.sudoku.printer;

import org.cholewa.sudoku.board.SudokuBoard;

public class SudokuPrinter {

    public static void printBoard(SudokuBoard sudokuBoard) {
        StringBuilder sb = new StringBuilder();

        drawLine();
        for (int rowNo = 0; rowNo < 9; rowNo++) {
            sb.setLength(0);
            sb.append("| ");
            for (int columnNo = 0; columnNo < 9; columnNo++) {
                int digitInField = sudokuBoard.getSudokuRows().get(rowNo).getSudokuFields().get(columnNo).getDigit();
                if (digitInField == 0) {
                    sb.append(" " + " | ");
                } else {
                    sb.append(digitInField + " | ");
                }
            }
            System.out.println(sb);
            drawLine();
        }
    }



    private static void drawLine() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 37; i++) {
            sb.append("=");
        }
        System.out.println(sb);
    }
}
