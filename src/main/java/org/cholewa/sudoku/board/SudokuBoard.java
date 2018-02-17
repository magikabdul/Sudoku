package org.cholewa.sudoku.board;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
//@Scope("singleton")
@Getter
public class SudokuBoard {
    public final static int SUDOKU_AXIS_LENGTH = 9;

    private List<SudokuRow> sudokuRows = new ArrayList<>();

    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            sudokuRows.add(new SudokuRow());
        }
    }

    public SudokuField getSudokuField(int axisX, int axisY) {
        return sudokuRows.get(axisY).getSudokuFields().get(axisX);
    }
}
