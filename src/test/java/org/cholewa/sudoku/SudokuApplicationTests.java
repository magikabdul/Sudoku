package org.cholewa.sudoku;

import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.board.SudokuField;
import org.cholewa.sudoku.board.SudokuRow;
import org.cholewa.sudoku.printer.SudokuPrinter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SudokuApplicationTests {

	@Test
	public void testCreatingSimpleField() {
		//Given
		ApplicationContext context = new AnnotationConfigApplicationContext("org.cholewa");
        SudokuField sudokuField = context.getBean(SudokuField.class);

		//When
        int resultSize = sudokuField.getAllowedDigits().size();

		//Then
		Assert.assertEquals(0, sudokuField.getDigit());
		Assert.assertEquals(9, resultSize);
	}

    @Test
    public void testRemoveAllowedDigit() {
	    //Given
        ApplicationContext context = new AnnotationConfigApplicationContext("org.cholewa");
        SudokuField sudokuField = context.getBean(SudokuField.class);

        //When
        sudokuField.getAllowedDigits().remove(3);
        boolean hasThree = sudokuField.getAllowedDigits().contains(3);

        //Then
        Assert.assertFalse(hasThree);
        Assert.assertEquals(8, sudokuField.getAllowedDigits().size());
    }

    @Test
    public void testSudokuRowSize() {
	    //Given
        ApplicationContext context = new AnnotationConfigApplicationContext("org.cholewa");
        SudokuRow sudokuRow = context.getBean(SudokuRow.class);

        //When
        int resultSize = sudokuRow.getSudokuFields().size();

        //Then
        Assert.assertEquals(9, resultSize);
    }

    @Test
    public void testIsFieldInRowSolved() {
	    //Given
        ApplicationContext context = new AnnotationConfigApplicationContext("org.cholewa");
        SudokuRow sudokuRow = context.getBean(SudokuRow.class);

        //When
        sudokuRow.getSudokuFields().get(0).setDigit(9);

        boolean isAnyFieldSolved = sudokuRow.getSudokuFields().get(0).getDigit() != 0;

        //Then
        Assert.assertTrue(isAnyFieldSolved);
    }

    @Test
    public void testGetUnsolvedFieldsInRow() {
        //Given
        ApplicationContext context = new AnnotationConfigApplicationContext("org.cholewa");
        SudokuRow sudokuRow = context.getBean(SudokuRow.class);

        //When
        sudokuRow.getSudokuFields().get(0).setDigit(9);
        sudokuRow.getSudokuFields().get(3).setDigit(2);
        sudokuRow.getSudokuFields().get(7).setDigit(1);

        long numberOfSolved = sudokuRow.getSudokuFields().stream()
                .filter(sudokuField -> sudokuField.getDigit() != 0)
                .count();

        long numberOfUnsolved = sudokuRow.getSudokuFields().stream()
                .map(SudokuField::getDigit)
                .filter(r -> r == 0)
                .count();

        //Then
        Assert.assertEquals(3, numberOfSolved);
        Assert.assertEquals(6,numberOfUnsolved);
    }

    @Test
    public void testGetBoardRows() {
	    //Given
        ApplicationContext context = new AnnotationConfigApplicationContext("org.cholewa");
        SudokuBoard sudokuBoard = context.getBean(SudokuBoard.class);

        //When
        long numberOfRows = sudokuBoard.getSudokuRows().size();

        //Then
        Assert.assertEquals(9, numberOfRows);
    }

    @Test
    public void testGetBoardFields() {
	    //Given
        ApplicationContext context = new AnnotationConfigApplicationContext("org.cholewa");
        SudokuBoard sudokuBoard = context.getBean(SudokuBoard.class);

        //When
        long numberOfFields = sudokuBoard.getSudokuRows().stream()
                .mapToLong(sudokuRow -> sudokuRow.getSudokuFields().size())
                .sum();

        //Then
        Assert.assertEquals(81, numberOfFields);
    }

    @Test
    public void testDrawBoard() {
        //Given
        ApplicationContext context = new AnnotationConfigApplicationContext("org.cholewa");
        SudokuBoard sudokuBoard = context.getBean(SudokuBoard.class);

        //When
        SudokuPrinter.printBoard(sudokuBoard);

        //Then
    }
}
