package org.cholewa.sudoku.filler;

import lombok.Getter;

@Getter
public class SudokuDataDto {
    private int axisX;
    private int axisY;
    private int value;

    public SudokuDataDto(int axisX, int axisY, int value) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SudokuDataDto that = (SudokuDataDto) o;

        if (axisX != that.axisX) return false;
        if (axisY != that.axisY) return false;
        return value == that.value;
    }
}
