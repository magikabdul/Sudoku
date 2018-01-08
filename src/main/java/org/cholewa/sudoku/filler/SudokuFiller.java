package org.cholewa.sudoku.filler;

import org.cholewa.sudoku.board.SudokuBoard;
import org.springframework.stereotype.Component;

@Component
public class SudokuFiller {

    public void setFieldDigit(SudokuBoard sudokuBoard, int axisX, int axisY, int value) {
        sudokuBoard.getSudokuRows().get(axisX).getSudokuFields().get(axisY).setDigit(value);
        sudokuBoard.getSudokuRows().get(axisX).getSudokuFields().get(axisY).getAllowedDigits().clear();
    }

    public void setFieldDigit(SudokuBoard sudokuBoard, SudokuDataDto dataDto) {
        sudokuBoard.getSudokuRows().get(dataDto.getAxisY() - 1).getSudokuFields().get(dataDto.getAxisX() - 1).setDigit(dataDto.getValue());
        sudokuBoard.getSudokuRows().get(dataDto.getAxisY() - 1).getSudokuFields().get(dataDto.getAxisX() - 1).getAllowedDigits().clear();
    }
}
