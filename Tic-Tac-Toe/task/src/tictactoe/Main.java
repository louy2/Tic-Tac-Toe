package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void printBoard(char[] cells) {
        System.out.println("--------");
        for (int y = 0; y < 3; y++) {
            System.out.print("|");
            for (int x = 0; x < 3; x++) {
                char c = cells[x + 3 * y];
                System.out.print(" " + c);
            }
            System.out.println(" |");
        }
        System.out.println("--------");
    }

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter cells: ");
//        char[] cells = sc.nextLine().toCharArray();
        char[] cells = "_________".toCharArray();
        printBoard(cells);

        char userPiece = 'X';
        while (true) {
            // user input
            System.out.print("Enter the coordinates: ");
            while (true) {
                try {
                    int userX = sc.nextInt() - 1;
                    int userY = 3 - sc.nextInt();
                    if (userX < 0 || userX > 2 || userY < 0 || userY > 2) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    }
                    if (cells[userX + 3 * userY] != '_') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        cells[userX + 3 * userY] = userPiece;
                        if (userPiece == 'X') {
                            userPiece = 'O';
                        } else {
                            userPiece = 'X';
                        }
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.print("You should enter numbers!");
                }
            }
            printBoard(cells);

            // check board state
            boolean xWins = false;
            boolean oWins = false;
            // check rows
            for (int y = 0; y < 3; y++) {
                if (cells[3 * y] == cells[1 + 3 * y]
                        && cells[1 + 3 * y] == cells[2 + 3 * y]) {
                    if (cells[3 * y] == 'X') {
                        xWins = true;
                    }
                    if (cells[3 * y] == 'O') {
                        oWins = true;
                    }
                }
            }
            // check cols
            for (int x = 0; x < 3; x++) {
                if (cells[x] == cells[x + 3]
                        && cells[x + 3] == cells[x + 2 * 3]) {
                    if (cells[x] == 'X') {
                        xWins = true;
                    }
                    if (cells[x] == 'O') {
                        oWins = true;
                    }
                }
            }
            // check crosses
            if (cells[0] == cells[4]
                    && cells[4] == cells[8]) {
                if (cells[4] == 'X') {
                    xWins = true;
                }
                if (cells[4] == 'O') {
                    oWins = true;
                }
            }
            if (cells[2] == cells[4]
                    && cells[4] == cells[6]) {
                if (cells[4] == 'X') {
                    xWins = true;
                }
                if (cells[4] == 'O') {
                    oWins = true;
                }
            }
            // reject impossible boards
            var xNum = 0;
            var oNum = 0;
            for (var c : cells) {
                if (c == 'X') {
                    xNum++;
                }
                if (c == 'O') {
                    oNum++;
                }
            }
            boolean diffXOTooMany = Math.abs(xNum - oNum) > 1;
            if ((xWins && oWins) || diffXOTooMany) {
                System.out.println("Impossible");
                return;
            }
            if (xWins) {
                System.out.println("X wins");
                return;
            }
            if (oWins) {
                System.out.println("O wins");
                return;
            }
            // if reaches here no one has won
            // has empty cell - not finished
            boolean hasEmptyCell = false;
            for (var c : cells) {
                if (c == '_') {
                    hasEmptyCell = true;
                    break;
                }
            }
            if (!hasEmptyCell) {
                System.out.println("Draw");
                return;
            }
        }
    }
}
