package org.cholewa.sudoku.processor;

import lombok.Getter;
import org.cholewa.sudoku.board.SudokuBoard;

import java.util.Set;

@Getter
public class SudokuAdvancedProcessor {
    private int iterations;
    private int axisX;
    private int axisY;
    private int value;

    public boolean hasSolution(SudokuBoard board, SudokuProcessor processor) {
        SudokuBoard altBoard;
        int numberOfSolvedFields;

        for (int y = 0; y < SudokuBoard.SUDOKU_AXIS_LENGTH; y++) {
            for (int x = 0; x < SudokuBoard.SUDOKU_AXIS_LENGTH; x++) {
                altBoard = makeCopy(board);

                if (!processor.isFieldSolved(altBoard, x, y)) {
                    Set<Integer> set = altBoard.getSudokuField(x, y).getAllowedDigits();
                    for (int availableValue : set) {
                        altBoard = makeCopy(board);
                        numberOfSolvedFields = 0;

                        axisX = x;
                        axisY = y;
                        value = availableValue;
                        iterations++;

                        //System.out.println("Putting alt values to x = " + axisX + ", y = " + axisY + ", value =" + value);

                        altBoard.getSudokuField(x, y).setDigit(value);
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

        for (int y = 0; y < SudokuBoard.SUDOKU_AXIS_LENGTH; y++) {
            for (int x = 0; x < SudokuBoard.SUDOKU_AXIS_LENGTH; x++) {
                Set<Integer> set = sudokuBoard.getSudokuField(x, y).getAllowedDigits();

                if (set.size() == 1) {
                    boardCopy.getSudokuField(x, y).setDigit(
                            sudokuBoard.getSudokuField(x, y).getDigit());
                } else {
                    for (int digit = 1; digit < 10; digit++) {
                        if (!set.contains(digit)) {
                            boardCopy.getSudokuField(x,y).removeAllowedDigit(digit);
                        }
                    }
                }
            }
        }

        return boardCopy;
    }
}
