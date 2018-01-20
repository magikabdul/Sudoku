package org.cholewa.sudoku;

import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.entry.DataEntryValidator;
import org.cholewa.sudoku.entry.KeyboardHandler;
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

    @Autowired
    private KeyboardHandler keyboardHandler;

    @Override
    public void run(String... args) throws Exception {
        boolean doFindSolution = false;
        SudokuPrinter.printBoard(board);
        System.out.println("Enter sudoku single field data!!!\n> ");

        while (!doFindSolution) {
            String myEntry = keyboardHandler.readKeyboardEntry().trim();

            doFindSolution = DataEntryValidator.isReadyToFindSolution(myEntry);

            if (!doFindSolution) {
                filler.setFieldDigit(board, reader.getSingleDataFromConsole(myEntry));
                SudokuPrinter.printBoard(board);
                System.out.println("Enter sudoku single field data!!!\n> ");
            }
        }
    }
}
