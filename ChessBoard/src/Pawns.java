/**
 * Pawns.java
 *
 * @author Suresh
 * @version 1.0
 * @date 10/07/2023
 */

import java.util.Scanner;

/**
 * This class represents the Pawn class, and it moves one step ahead and similarly
 * catches the opponent players diagonally. And if the selected piece reaches the
 * other end you can select new piece to add to your side.
 */
public class Pawns extends Piece {
    private final Scanner in = new Scanner(System.in);
    public Pawns(Board board) {
        super(board);
    }

    public boolean isValidMoveForWhite(int rowChoose, int colChoose,
                                       int rowMove, int colMove) {
        if (rowChoose == 1) {
            if(colChoose == colMove) {

                //can move one or two-step from the 1st row
                return (rowMove == rowChoose + 1 || rowMove == rowChoose + 2)
                        && board.chessboard[rowMove][colMove].equals("      ");

                //catch the other player diagonally.
            }else if(((colMove - colChoose == 1 || colMove - colChoose == -1)
                    && rowMove == rowChoose + 1)
                    && !board.chessboard[rowMove][colMove].equals("      ")) {
                return !board.stringArray().contains(board.chessboard[rowMove][colMove]);

            }
        } else if (rowChoose > 1) {
            if(colChoose == colMove) {
                return (rowMove == rowChoose + 1)
                        && board.chessboard[rowMove][colMove].equals("      ");

                //catch the other player diagonally.
            }else if (((colMove - colChoose == 1) || (colMove - colChoose == -1)
                    && rowMove == rowChoose + 1)
                    && !board.chessboard[rowMove][colMove].equals(" ")) {
                //new Piece
                if(rowChoose == 6 && rowMove == 7) {
                    System.out.println("Select one of the piece from: " + board.whitePieces());
                    while (!in.hasNextLine()) {
                        System.out.println("Please type name of Piece:");
                        in.next();
                    }
                    board.newPiece = in.nextLine();
                    while (!board.whitePieces().contains(board.newPiece)) {
                        System.out.println("Please enter one of the above:");
                        board.newPiece = in.nextLine();
                    }
                    if(!board.stringArray().contains(board.chessboard[rowMove][colMove])){
                        board.chessboard[rowChoose][colChoose] = "      ";
                        board.chessboard[rowMove][colMove] = board.newPiece;
                        return  true;
                    }
                }
                return !board.stringArray().contains(board.chessboard[rowMove][colMove]);
            }
        }
        return false;
    }

    public boolean isValidMoveForBlack(int rowChoose, int colChoose, int rowMove, int colMove) {
        //condition for catching diagonally
        boolean condition = (colMove - colChoose == 1 || colMove - colChoose == -1)
                && rowMove == rowChoose - 1;

        if (rowChoose == 6) {
            if (colChoose == colMove) {

                //can move one or two-step from the 1st row
                return (rowMove == rowChoose - 1 || rowMove == rowChoose - 2)
                        && board.chessboard[rowMove][colMove].equals("      ");

                //catch the other player diagonally.
            } else if (condition
                    && !board.chessboard[rowMove][colMove].equals(" ")) {
                return board.stringArray().contains(board.chessboard[rowMove][colMove]);

            }
        } else if (rowChoose < 6) {
            if (colChoose == colMove) {
                return (rowMove == rowChoose - 1)
                        && board.chessboard[rowMove][colMove].equals("      ");

                //catch the other player diagonally.
            } else if (condition
                    && !board.chessboard[rowMove][colMove].equals(" ")) {
                //new piece
                if(rowChoose == 1 && rowMove == 0) {
                    System.out.println("Select one of the piece from: " + board.blackPieces());
                    while (!in.hasNextLine()) {
                        System.out.println("Please type name of Piece:");
                        in.next();
                    }
                    board.newPiece = in.nextLine();
                    while (!board.blackPieces().contains(board.newPiece)) {
                        System.out.println("Please enter one of the above:");
                        board.newPiece = in.nextLine();
                    }
                    if(board.stringArray().contains(board.chessboard[rowMove][colMove])){
                        board.chessboard[rowChoose][colChoose] = "      ";
                        board.chessboard[rowMove][colMove] = board.newPiece;
                        return  true;
                    }
                }
                return board.stringArray().contains(board.chessboard[rowMove][colMove]);
            }

        }
        return false;
    }
}
