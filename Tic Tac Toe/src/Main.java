/**
 * @author Suresh
 * @date 18/06/2023
 * @version 1.0
 * @since 18/06/2023
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * This class is about a game called tic-tac-toe with two players
 * playing on grid 3x3 with the symbol 'X' and 'O' and the one
 * who makes a first 3 either x or o in a sequential order. That player
 * wins.
 */

public class Main {
    public static void main(String[] args) {
        final String PLAYER1 = "Player 1 turn: ";
        final String PLAYER2 = "Computer plays: ";
        final String PATTERN = " | | \n + + \n | | ";
        boolean gameOver = false;
        char [][] board = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};

        Scanner in = new Scanner(System.in);
        System.out.println("Let's play tic-tac-toe");

        System.out.println(PATTERN);



    }
}