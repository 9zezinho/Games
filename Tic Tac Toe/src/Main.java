/**
 * @author Suresh
 * @date 18/06/2023
 * @version 1.1.0
 * @since 21/06/2023
 */

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
        boolean playAgain = true;
        System.out.println("Let's play tic-tac-toe");

        do {
            char [][] board = {{' ',' ',' '}, {' ',' ',' '}, {' ',' ',' '}};

            printBoard(board);
            chooseNum(board);

            Scanner in = new Scanner(System.in);
            System.out.println("Play Again? 'Y' for yes "
                    + "'N' for no.");
            String x = in.nextLine();

            while(!x.equalsIgnoreCase("N") && !x.equalsIgnoreCase("Y")) {
                System.out.println("Invalid input! Y or N");
                x = in.nextLine();
            }

            if (x.equalsIgnoreCase("N")) {
                playAgain = false;
            }
        } while(playAgain);

        System.out.println("Thanks for playing");
    }

    /**
     *
     * Method to choose the number by the player and
     * the computer
     * @param board is the 2 dimensional array that it
     *              takes as the game is.
     */
    public static void chooseNum(char[][] board) {
        int plyr = 0;
        int pc = 0;
        char winner = ' ';

        while(true) {
            System.out.println("Choose number between 1-9");
            Scanner in = new Scanner(System.in);

            while(!in.hasNextInt()){
                System.out.println("Please input a number:");
                in.next(); //Discard the invalid input
            }
            plyr = in.nextInt();

            while(plyr > 9 || plyr == 0) {
                System.out.println("Please choose the number between from 1 to 9 only");
                plyr = in.nextInt();
                in.nextLine();
            }

            int row = (plyr - 1) / 3; //this would just give us either 0,1 or 3
            int col = (plyr - 1) % 3; //same as the row

            //This checks if that cell is already taken? if it is then just restarts.
            if (board[row][col] != ' ') {
                System.out.println("That cell is already taken! Please try again.");
                continue;
            }

            //based on input by user we put the 'X' in the game.
            board[row][col] = 'X';
            printBoard(board);

            winner = checkWinner(board);
            if (winner == 'X'){
                System.out.println("Player wins");
                break;
            }
            if (isBoardFull(board)) {
                System.out.println("It's a draw");
                break;
            }

            Random rnd = new Random();
            pc = rnd.nextInt(9) + 1;
            System.out.print("PC chooses the number:" + pc);
            System.out.println();

            //checks if pc chooses the number same as the plyr choose
            //and if the number is equal to ' '.
            while(pc == plyr || board[(pc-1)/3][(pc-1)%3] != ' ') {
                System.out.println("Opps! that's already taken.");
                pc = rnd.nextInt(9)+ 1;
                System.out.print("PC chooses the number:" + pc);
                System.out.println();
            }
            int pcRow = (pc - 1) / 3;
            int pcCol = (pc - 1) % 3;
            board[pcRow][pcCol] = 'O';
            printBoard(board);

            winner = checkWinner(board);
            if (winner == 'O') {
                System.out.println("Computer wins");
                break;
            }
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
        boolean isDraw = true;

        //check columns
        for(int i = 0; i < board.length; i++) {
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        //check rows
        for(int i = 0; i < board.length; i++) {
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }
        //check diagonals
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        //check draws
        for (char[] chars : board) {
            for (int j = 0; j < board.length; j++) {
                if (chars[j] == ' ') {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw){
            return '#';
        }
        return ' ';

    }

    /**
     *
     * Method to check if the board is full
     */
    public static boolean isBoardFull(char[][] board) {
        for (char[] chars: board) {
            for(int j = 0; j < board.length; j++) {
                if (chars[j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
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