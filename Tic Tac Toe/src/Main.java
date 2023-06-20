/**
 * @author Suresh
 * @date 18/06/2023
 * @version 1.0
 * @since 18/06/2023
 */

import java.util.Arrays;
import java.util.Random;
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
        char [][] board = {{' ',' ',' '}, {' ',' ',' '}, {' ',' ',' '}};

        System.out.println("Let's play tic-tac-toe");

        printBoard(board);
        chooseNum(board);

    }

    /**
     * Method to choose the number by the player and
     * the computer
     * @param board is the 2 dimensional array that it
     *              takes as the game is.
     */
    public static void chooseNum(char[][] board) {
        int plyr = 0;
        boolean gameIsNotOver = false;

        while(!gameIsNotOver) {
            System.out.println("Choose number between 1-9");
            Scanner in = new Scanner(System.in);
            plyr = in.nextInt();
            while(plyr > 9 || plyr == 0) {
                System.out.println("Please choose the number between from 1 to 9 only");
                plyr = in.nextInt();
                in.nextLine();
            }
            switch (plyr) {
                case 1 -> board[0][0] = 'X';
                case 2 -> board[0][1] = 'X';
                case 3 -> board[0][2] = 'X';
                case 4 -> board[1][0] = 'X';
                case 5 -> board[1][1] = 'X';
                case 6 -> board[1][2] = 'X';
                case 7 -> board[2][0] = 'X';
                case 8 -> board[2][1] = 'X';
                case 9 -> board[2][2] = 'X';
            }
            printBoard(board);

            Random rnd = new Random();
            int pc = rnd.nextInt(9) + 1;
            System.out.print("PC chooses the number:" + pc);
            System.out.println();
            while(pc == plyr) {
                System.out.println("Opps! that's already taken.");
                pc = rnd.nextInt(9) + 1;
                System.out.print("PC chooses the number:" + pc);
                System.out.println();
            }
            switch (pc) {
                case 1 -> board[0][0] = '0';
                case 2 -> board[0][1] = '0';
                case 3 -> board[0][2] = '0';
                case 4 -> board[1][0] = '0';
                case 5 -> board[1][1] = '0';
                case 6 -> board[1][2] = '0';
                case 7 -> board[2][0] = '0';
                case 8 -> board[2][1] = '0';
                case 9 -> board[2][2] = '0';
            }
            printBoard(board);
            //if the game is not over it keeps on continue.
            gameIsNotOver = checkWinner(board) != ' ';
        }
        char winner = checkWinner(board);
        if (winner != ' ') {
            if (winner == 'X') {
                System.out.println("Player wins:");
            }else {
                System.out.println("Computer wins");
            }
        } else {
            System.out.println("Its a draw.");
        }
    }


    /**
     *
     * Method to check if there is winner already
     * @param board the 2 dimensional board game
     *
     * @return ' ' if there is no winner.
     */
    public static char checkWinner(char[][] board) {
        //check columns
        for(int i = 0; i < board.length; i++) {
           if(board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
           }
        }
        //check rows
        for(int i = 0; i < board.length; i++) {
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        //check diagonals
        if(board[0][1] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][1];
        }
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        return ' ';

    }

    /**
     * Method to print the board
     * @param board is the char with [][]
     */
    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }

}