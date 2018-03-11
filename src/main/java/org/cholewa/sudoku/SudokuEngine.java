package org.cholewa.sudoku;

import lombok.AllArgsConstructor;
import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.entry.DataEntryValidator;
import org.cholewa.sudoku.entry.KeyboardHandler;
import org.cholewa.sudoku.entry.SampleBoard;
import org.cholewa.sudoku.filler.SudokuFiller;
import org.cholewa.sudoku.printer.SudokuPrinter;
import org.cholewa.sudoku.processor.SudokuAdvancedProcessor;
import org.cholewa.sudoku.processor.SudokuProcessor;
import org.cholewa.sudoku.reader.SudokuReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Profile("!test")
public class SudokuEngine implements CommandLineRunner {
    private SudokuBoard board;
    private SudokuReader reader;
    private SudokuFiller filler;
    private KeyboardHandler keyboardHandler;
    private SudokuProcessor processor;

    @Override
    public void run(String... args) {
        loadStartingData();
        enterSudokuData();
        solveBoard();
    }

    private void solveBoard() {
        boolean isBoardSolved = false;
        int iteration = 0;
        int numberOfSolvedFields = processor.getSudokuSolvedFields(board);

        while (!isBoardSolved) {
            iteration++;
            System.out.println("\nIteration number: " + iteration);

            processor.updateAvailableDigitsForFields(board);

            if (numberOfSolvedFields == processor.getSudokuSolvedFields(board)) {
                iteration += tryAdvancedMethod();
            } else {
                SudokuPrinter.printBoard(board);
            }

            numberOfSolvedFields = processor.getSudokuSolvedFields(board);

            System.out.println("Number of solved fields: " + numberOfSolvedFields + " of 81");

            isBoardSolved = processor.isSudokuSolved(board);
        }

        printFinalMessage(iteration);
        SudokuPrinter.printBoard(board);
    }

    private int tryAdvancedMethod() {
        System.out.println("Looking for more complex solution");

        SudokuAdvancedProcessor advancedProcessor = new SudokuAdvancedProcessor();

        board = advancedProcessor.findSolution(board, processor);

        if (board == null) {
            System.out.println("Can't find board solution :(\n\n");
            System.exit(0);
        }

        return advancedProcessor.getIterations();
    }

    private void enterSudokuData() {
        boolean areStartingDataComplete = false;
        System.out.println("Enter sudoku single field data!!!\n> ");

        while (!areStartingDataComplete) {
            areStartingDataComplete = processCollectingStartingData();
        }
    }

    private void loadStartingData() {
        new SampleBoard(board);
        System.out.println("\n\tBoard with demo data");
        SudokuPrinter.printBoard(board);
    }

    private boolean processCollectingStartingData() {
        boolean areStartingDataComplete;

        String myEntry = keyboardHandler.readKeyboardEntry();

        areStartingDataComplete = DataEntryValidator.isReadyToFindSolution(myEntry);

        if (!areStartingDataComplete) {
            Optional.ofNullable(reader.getSingleDataFromConsole(myEntry))
                        .ifPresent(field -> {
                            filler.setFieldDigit(board, field);
                            SudokuPrinter.printBoard(board);
                        });

            System.out.println("Enter sudoku single field data!!!\n> ");
        }

        return areStartingDataComplete;
    }

    private static void printFinalMessage(int iteration) {
        System.out.println("\n\n*************************************");
        System.out.println("*************************************");
        System.out.println("*************************************\n");

        System.out.println("Board solved in " + iteration + " iterations");
        System.out.println("Final solution below\n");
    }
}
