package org.cholewa.sudoku.processor;


import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.board.SudokuField;
import org.springframework.stereotype.Component;

@Component
public class SudokuProcessor {

    private void removeWrongDigits(SudokuBoard board, int axisX, int axisY) {
        checkRow(board, axisX, axisY);
        checkColumn(board, axisX, axisY);
        checkLocalSquare(board, axisX, axisY);
    }

    public boolean isFieldSolved(SudokuBoard board, int axisX, int axisY) {
        return board.getSudokuField(axisX, axisY).getAllowedDigits().size() == 1;
    }

    public boolean isSudokuSolved(SudokuBoard board) {
        for (int y = 0; y < SudokuBoard.SUDOKU_AXIS_LENGTH; y++) {
            for (int x = 0; x < SudokuBoard.SUDOKU_AXIS_LENGTH; x++) {
                if (board.getSudokuField(x, y).getAllowedDigits().size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void updateAvailableDigitsForFields(SudokuBoard board) {
        for (int x = 0; x < SudokuBoard.SUDOKU_AXIS_LENGTH; x++) {
            for (int y = 0; y < SudokuBoard.SUDOKU_AXIS_LENGTH; y++) {
                if (!isFieldSolved(board, x, y)) {
                    removeWrongDigits(board, x, y);
                    checkAllowedDigitSize(board, x, y);
                }
            }
        }
    }

    private void checkAllowedDigitSize(SudokuBoard board, int axisX, int axisY) {
        SudokuField field = board.getSudokuField(axisX, axisY);

        if (field.getAllowedDigits().size() == 1) {
            int value = field.getAllowedDigits().stream().findFirst().orElse(-1);
            if (field.getDigit() != value) {
                field.setDigit(value);
            }
        }
    }

    public int getSudokuSolvedFields(SudokuBoard board) {
        return (int)board.getSudokuRows().stream()
                .flatMap(sudokuRow -> sudokuRow.getSudokuFields().stream())
                .map(SudokuField::getDigit)
                .filter(integer -> integer>0)
                .count();
    }

    private void checkRow(SudokuBoard board, int axisX, int axisY) {
        int fieldValue;

        for (int x = 0; x < SudokuBoard.SUDOKU_AXIS_LENGTH; x++) {
            if (x != axisX) {
                fieldValue = board.getSudokuField(x, axisY).getDigit();
                board.getSudokuField(axisX, axisY).removeAllowedDigit(fieldValue);
            }
        }
    }

    private void checkColumn(SudokuBoard board, int axisX, int axisY) {
        int fieldValue;

        for (int y = 0; y < SudokuBoard.SUDOKU_AXIS_LENGTH; y++) {
            if (y != axisY) {
                fieldValue = board.getSudokuField(axisX, y).getDigit();
                board.getSudokuField(axisX, axisY).removeAllowedDigit(fieldValue);
            }
        }
    }

    private void checkLocalSquare(SudokuBoard board, int axisX, int axisY) {
        int fieldValue;

        int xInit = RangeLimiter.findInit(axisX);
        int xLimit = RangeLimiter.findLimit(axisX);
        int yInit = RangeLimiter.findInit(axisY);
        int yLimit = RangeLimiter.findLimit(axisY);

        for (int x = xInit; x < xLimit; x++) {
            if (x != axisX) {
                for (int y = yInit; y < yLimit; y++) {
                    if (y != axisY) {
                        fieldValue = board.getSudokuField(x, y).getDigit();
                        board.getSudokuField(axisX, axisY).removeAllowedDigit(fieldValue);
                    }
                }
            }
        }
    }
}
