package org.cholewa.sudoku.reader;

import org.cholewa.sudoku.filler.SudokuDataDto;
import org.springframework.stereotype.Component;

@Component
public class SudokuReader {

    public SudokuDataDto getSingleDataFromConsole(String myEntry) {

        if (SudokuEntryValidator.validateSingleDataEntry(myEntry)) {
            String[] values = myEntry.split(",");
            return new SudokuDataDto(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
        }

        return new SudokuDataDto(0,0,0);
    }
}
