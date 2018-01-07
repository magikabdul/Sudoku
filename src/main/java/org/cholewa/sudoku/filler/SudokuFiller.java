package org.cholewa.sudoku.filler;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import org.cholewa.sudoku.board.SudokuBoard;
import org.springframework.stereotype.Component;

@Component
public class SudokuFiller {

    public boolean setFieldDigit(SudokuBoard sudokuBoard, int axisX, int axisY, int value) throws WrongNumberArgsException {
        if (axisX < 1 || axisX > 9) {
            throw new WrongNumberArgsException("valid args for X axis are 1 to 9");
        }

        if (axisY < 1 || axisY > 9) {
            throw new WrongNumberArgsException("valid args for Y axis are 1 to 9");
        }

        if (value < 1 || value > 9) {
            throw new WrongNumberArgsException("valid args for field value are 1 to 9");
        }

        sudokuBoard.getSudokuRows().get(axisX).getSudokuFields().get(axisY).setDigit(value);
        sudokuBoard.getSudokuRows().get(axisX).getSudokuFields().get(axisY).getAllowedDigits().remove(value);
        return true;
    }
}
