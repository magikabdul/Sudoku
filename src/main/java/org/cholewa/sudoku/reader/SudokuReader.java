package org.cholewa.sudoku.reader;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import org.cholewa.sudoku.board.SudokuBoard;
import org.cholewa.sudoku.filler.SudokuFiller;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SudokuReader {
    private Scanner scanner = new Scanner(System.in);

    private boolean isAxisXCorrect;
    private boolean isAxisYCorrect;
    private boolean isValueCorrect;

    private int axisX;
    private int axisY;
    private int value;

    public void getSingleData(SudokuBoard sudokuBoard, SudokuFiller sudokuFiller) {
        isAxisXCorrect = false;
        isAxisYCorrect = false;
        isValueCorrect = false;

        System.out.println("Enter sudoku single field data!!!");
        while (!isAxisXCorrect && !isAxisYCorrect && !isValueCorrect) {
            String myEntry = scanner.nextLine();
            myEntry = myEntry.trim();

            if (!checkDataLength(myEntry)) {
                System.out.println("Wrong data format, expected \"a,b,c\" where all digits are between 1 and 9 inclusive");
            } else if (!checkDataForAxisX(myEntry)) {
                System.out.println("Wrong data for axis X, correct is where all digits are between 1 and 9 inclusive");
            } else if (!checkDataForAxisY(myEntry)) {
                System.out.println("Wrong data for axis Y, correct is where all digits are between 1 and 9 inclusive");
            } else if (!checkDataForValue(myEntry)) {
                System.out.println("Wrong data for field value, correct is where all digits are between 1 and 9 inclusive");
            }
        }

        try {
            sudokuFiller.setFieldDigit(sudokuBoard, axisX, axisY, value);
        } catch (WrongNumberArgsException e) {
            e.printStackTrace();
        }
    }

    private boolean checkDataLength(String data) {
        if (data.length() == 5) {
            return (data.substring(1, 2).equals(",") && data.substring(4, 5).equals(","));
        } else {
            return false;
        }
    }

    private boolean checkDataForAxisX(String data) {
        int analysis = Integer.parseInt(data.substring(0, data.indexOf(",")));
        if (analysis > 0 && analysis < 10) {
            this.isAxisXCorrect = true;
            this.axisX = analysis;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDataForAxisY(String data) {
        int analysis = Integer.parseInt(data.substring(data.indexOf(",") + 1, data.lastIndexOf(",")));
        if (analysis > 0 && analysis < 10) {
            this.isAxisYCorrect = true;
            this.axisY = analysis;
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDataForValue(String data) {
        int analysis = Integer.parseInt(data.substring(data.lastIndexOf(",") + 1));
        if (analysis > 0 && analysis < 10) {
            this.isValueCorrect = true;
            this.value = analysis;
            return true;
        } else {
            return false;
        }
    }
}
