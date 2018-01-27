package org.cholewa.sudoku;

import lombok.AllArgsConstructor;
import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.entry.DataEntryValidator;
import org.cholewa.sudoku.entry.KeyboardHandler;
import org.cholewa.sudoku.filler.SudokuFiller;
import org.cholewa.sudoku.printer.SudokuPrinter;
import org.cholewa.sudoku.reader.SudokuReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("!test")
public class SudokuEngine implements CommandLineRunner {
    private SudokuBoard board;
    private SudokuReader reader;
    private SudokuFiller filler;
    private KeyboardHandler keyboardHandler;

//    public SudokuEngine(SudokuBoard board, SudokuReader reader, ) {
//        this.board = board;
//    }

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
