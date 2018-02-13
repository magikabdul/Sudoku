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
        int value;

        if (board.getSudokuRows().get(axisY).getSudokuFields().get(axisX).getAllowedDigits().size() == 1) {
            value = board.getSudokuRows().get(axisY).getSudokuFields().get(axisX).getAllowedDigits().stream().findAny().orElse(-1);

            if (board.getSudokuRows().get(axisY).getSudokuFields().get(axisX).getDigit() != value) {
                //System.out.println("Inserted value = " + value);
                board.getSudokuRows().get(axisY).getSudokuFields().get(axisX).setDigit(value);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean isSudokuSolved(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getSudokuRows().get(j).getSudokuFields().get(i).getAllowedDigits().size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void updateAvailableDigitsForFields(SudokuBoard board) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                removeWrongDigits(board, x, y);
                isFieldSolved(board, x, y);
            }
        }
    }

    public int getSudokuSolvedFields(SudokuBoard board) {
        return (int)board.getSudokuRows().stream().flatMap(sudokuRow -> sudokuRow.getSudokuFields().stream()).map(SudokuField::getDigit).filter(integer -> integer>0).count();
    }

    private void checkRow(SudokuBoard board, int axisX, int axisY) {
        int fieldValue;

        for (int i = 0; i < 9; i++) {
            if (i != axisX) {
                fieldValue = board.getSudokuRows().get(axisY).getSudokuFields().get(i).getDigit();
                board.getSudokuRows().get(axisY).getSudokuFields().get(axisX).removeAllowedDigit(fieldValue);
            }
        }
    }

    private void checkColumn(SudokuBoard board, int axisX, int axisY) {
        int fieldValue;

        for (int i = 0; i < 9; i++) {
            if (i != axisY) {
                fieldValue = board.getSudokuRows().get(i).getSudokuFields().get(axisX).getDigit();
                board.getSudokuRows().get(axisY).getSudokuFields().get(axisX).removeAllowedDigit(fieldValue);
            }
        }
    }

    private void checkLocalSquare(SudokuBoard board, int axisX, int axisY) {
        int fieldValue;

        int xInit = findInit(axisX);
        int xLimit = findLimit(axisX);
        int yInit = findInit(axisY);
        int yLimit = findLimit(axisY);

        for (int i = xInit; i < xLimit; i++) {
            if (i != axisX) {
                for (int k = yInit; k < yLimit; k++) {
                    if (k != axisY) {
                        fieldValue = board.getSudokuRows().get(k).getSudokuFields().get(i).getDigit();
                        board.getSudokuRows().get(axisY).getSudokuFields().get(axisX).removeAllowedDigit(fieldValue);
                    }
                }
            }
        }
    }

    private int findInit(int digit) {
        if (digit < 3) {
            return 0;
        } else if (digit < 6) {
            return 3;
        } else {
            return 6;
        }
    }

    private int findLimit(int digit) {
        if (digit < 3) {
            return 3;
        } else if (digit < 6) {
            return 6;
        } else {
            return 9;
        }
    }
}
