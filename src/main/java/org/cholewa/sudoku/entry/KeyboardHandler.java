package org.cholewa.sudoku.entry;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class KeyboardHandler {
    private Scanner scanner = new Scanner(System.in);

    public String readKeyboardEntry() {
        return scanner.nextLine().trim();
    }
}
