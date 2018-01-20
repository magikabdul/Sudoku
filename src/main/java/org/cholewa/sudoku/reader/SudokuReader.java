package org.cholewa.sudoku.reader;

import org.cholewa.sudoku.filler.SudokuDataDto;
import org.springframework.stereotype.Component;

@Component
public class SudokuReader {

    public SudokuDataDto getSingleDataFromConsole(String myEntry) {
        boolean end = false;
        int axisX = 0;
        int axisY = 0;
        int value = 0;

        //while (!end) {
            if (SudokuEntryValidator.validateSingleDataEntry(myEntry)) {
                axisX = parseValueForAxisX(myEntry);
                axisY = parseValueForAxisY(myEntry);
                value = parseValueForValue(myEntry);
                end = true;
            //}
        }

        return new SudokuDataDto(axisX, axisY, value);
    }

    private int parseValueForAxisX(String data) {
        return Integer.parseInt(data.substring(0, data.indexOf(",")));
    }

    private int parseValueForAxisY(String data) {
        return Integer.parseInt(data.substring(data.indexOf(",") + 1, data.lastIndexOf(",")));
    }

    private int parseValueForValue(String data) {
        return Integer.parseInt(data.substring(data.lastIndexOf(",") + 1));
    }
}
