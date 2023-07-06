import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        char [][] chessboard = {{' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' '}, {' ',' ',' ',' ',' ',' ',' ',' '}
                ,{' ',' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' ',' '},
                {' ',' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' ',' '}
                ,{' ',' ',' ',' ',' ',' ',' ',' '}};
        Board one = new Board();
        one.displayBoard(chessboard);
    }

}