package org.cholewa.sudoku.processor;

import lombok.Getter;
import org.cholewa.sudoku.SudokuEngine;
import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.printer.SudokuPrinter;

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
                altBoard = board.makeCopy();

                if (processor.isNotFieldSolved(altBoard, x, y)) {
                    Set<Integer> set = altBoard.getSudokuField(x, y).getAllowedDigits();
                    for (int availableValue : set) {
                        altBoard = board.makeCopy();
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
                                SudokuEngine.printFinalMessage(iterations);
                                SudokuPrinter.printBoard(altBoard);
                                System.exit(0);
                                //return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
