package org.cholewa.sudoku.filler;

import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.processor.RangeLimiter;
import org.springframework.stereotype.Component;

@Component
public class SudokuFiller {

    public void setFieldDigit(SudokuBoard sudokuBoard, SudokuDataDto dataDto) {
        if (!isDigitUsedInRow(sudokuBoard, dataDto)) {
            System.out.println("Digit: " + dataDto.getValue() + " is used in row " + dataDto.getAxisX());
            System.out.println("Please use different one!!\n");
        } else if (!isDigitUsedInColumn(sudokuBoard, dataDto)) {
            System.out.println("Digit: " + dataDto.getValue() + " is used in column " + dataDto.getAxisY());
            System.out.println("Please use different one!!\n");
        } else if (!isDigitUsedInLocalSquare(sudokuBoard, dataDto)) {
            System.out.println("Digit: " + dataDto.getValue() + " is used in local square");
            System.out.println("Please use different one!!\n");
        } else {
            sudokuBoard.getSudokuField(dataDto.getAxisX() - 1, dataDto.getAxisY() - 1).setDigit(dataDto.getValue());


            sudokuBoard.getSudokuRows().get(dataDto.getAxisY() - 1).getSudokuFields().get(dataDto.getAxisX() - 1).getAllowedDigits().clear();
        }
    }

    private boolean isDigitUsedInRow(SudokuBoard sudokuBoard, SudokuDataDto sudokuDataDto) {
        int axisX = sudokuDataDto.getAxisX() - 1;
        int axisY = sudokuDataDto.getAxisY() - 1;
        int value = sudokuDataDto.getValue();

        for (int x = 0; x < SudokuBoard.SUDOKU_AXIS_LENGTH; x++) {
            if (x != axisX) {
                if (sudokuBoard.getSudokuField(x, axisY).getDigit() == value) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDigitUsedInColumn(SudokuBoard sudokuBoard, SudokuDataDto sudokuDataDto) {
        int axisX = sudokuDataDto.getAxisX() - 1;
        int axisY = sudokuDataDto.getAxisY() - 1;
        int value = sudokuDataDto.getValue();

        for (int y = 0; y < SudokuBoard.SUDOKU_AXIS_LENGTH; y++) {
            if (y != axisY) {
                if (sudokuBoard.getSudokuField(axisX, y).getDigit() == value) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDigitUsedInLocalSquare(SudokuBoard sudokuBoard, SudokuDataDto sudokuDataDto) {
        int axisX = sudokuDataDto.getAxisX() - 1;
        int axisY = sudokuDataDto.getAxisY() - 1;
        int value = sudokuDataDto.getValue();

        int initX = RangeLimiter.findInit(axisX);
        int limitX = RangeLimiter.findLimit(axisX);
        int initY = RangeLimiter.findInit(axisY);
        int limitY = RangeLimiter.findLimit(axisX);

        for (int y = initY; y < limitY; y++) {
            for (int x = initX; x < limitX; x++) {
                if (x != axisX && y != axisY) {
                    if (sudokuBoard.getSudokuField(x, y).getDigit() == value) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
