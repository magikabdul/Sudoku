package org.cholewa.sudoku.processor;

import lombok.Getter;
import org.cholewa.sudoku.board.SudokuBoard;

import java.util.Set;

@Getter
public class SudokuAdvancedProcessor {
    private static final int MAX_ITERATIONS = 10000;

    private int iterations;
    private int axisX;
    private int axisY;
    private int value;

    public boolean hasSolution(SudokuBoard board, SudokuProcessor processor) {
        SudokuBoard altBoard;
        int numberOfSolvedFields = 0;

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                altBoard = makeCopy(board);

                if (!processor.isFieldSolved(altBoard, x, y)) {
                    Set<Integer> set = altBoard.getSudokuRows().get(y).getSudokuFields().get(x).getAllowedDigits();
                    for (int availableValue : set) {
                        altBoard = makeCopy(board);
                        numberOfSolvedFields = 0;

                        axisX = x;
                        axisY = y;
                        value = availableValue;
                        iterations++;

                        System.out.println("Putting alt values to x = " + axisX + ", y = " + axisY + ", value =" + value);
                        //Thread.sleep(50000);

                        altBoard.getSudokuRows().get(y).getSudokuFields().get(x).setDigit(value);
                        processor.updateAvailableDigitsForFields(altBoard);

                        boolean isBoardSolved = false;

                        while (!isBoardSolved) {
                            processor.updateAvailableDigitsForFields(altBoard);

                            if (numberOfSolvedFields == processor.getSudokuSolvedFields(altBoard)) {
                                break;
                            }

                            numberOfSolvedFields = processor.getSudokuSolvedFields(altBoard);
                            isBoardSolved = processor.isSudokuSolved(altBoard);

                            if (numberOfSolvedFields == 81) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private SudokuBoard makeCopy(SudokuBoard sudokuBoard) {
        SudokuBoard boardCopy = new SudokuBoard();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Set<Integer> set = sudokuBoard.getSudokuRows().get(y).getSudokuFields().get(x).getAllowedDigits();
                int size = set.size();

                if (size == 1) {
                    boardCopy.getSudokuRows().get(y).getSudokuFields().get(x).setDigit(sudokuBoard.getSudokuRows().get(y).getSudokuFields().get(x).getDigit());
                } else {
                    for (int digit = 1; digit < 10; digit++) {
                        if (!set.contains(digit)) {
                            boardCopy.getSudokuRows().get(y).getSudokuFields().get(x).removeAllowedDigit(digit);
                        }
                    }
                }
            }
        }

        return boardCopy;
    }
}
