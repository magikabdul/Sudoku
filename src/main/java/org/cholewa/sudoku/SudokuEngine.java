package org.cholewa.sudoku;

import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.filler.SudokuFiller;
import org.cholewa.sudoku.printer.SudokuPrinter;
import org.cholewa.sudoku.reader.SudokuReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SudokuEngine implements CommandLineRunner {
    @Autowired
    private SudokuBoard board;

    @Autowired
    private SudokuReader reader;

    @Autowired
    private SudokuFiller filler;

    @Override
    public void run(String... args) throws Exception {
        SudokuPrinter.printBoard(board);

        filler.setFieldDigit(board, reader.getSingleDataFromConsole());

        SudokuPrinter.printBoard(board);
    }
}
