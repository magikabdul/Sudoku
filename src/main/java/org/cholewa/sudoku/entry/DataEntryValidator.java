package org.cholewa.sudoku.entry;

public class DataEntryValidator {

    public static boolean isReadyToFindSolution(String s) {
        return s.toLowerCase().equals("sudoku");
    }
}
