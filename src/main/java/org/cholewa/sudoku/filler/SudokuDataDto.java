package org.cholewa.sudoku.filler;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class SudokuDataDto {
    private int axisX;
    private int axisY;
    private int value;

    public SudokuDataDto(int axisX, int axisY, int value) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.value = value;
    }
}
