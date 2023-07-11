import java.util.ArrayList;
import java.util.List;

/**
 * Board.java
 * @date 10/07/2023
 * @author Suresh
 * @version 1.2
 */

public class  Board {
    protected final String [][] chessboard =
            {{"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "}};
    String wPawn = "W-Pawn";
    String bPawn = "B-Pawn";
    String wBishop = "W-Bisp";
    String bBishop = "B-Bisp";
    String wRook = "W-Rook";
    String bRook = "B-Rook";
    String wKnight = "W-Kght";
    String bKnight = "B-Kght";
    String wQueen = "W-Quen";
    String wKing = "W-King";
    String bKing = "B-King";
    String bQueen = "B-Quen";
    private final Pawns pawns;
    private boolean isBoardInitialized = false;
    private boolean isWhiteTurn = true;

    public Board() {
        pawns = new Pawns(this);
    }

    /**
     *
     * Method to display the chess board.
     */
    public void displayBoard() {
        //using the boolean value to make sure that the
        //displayBoardWithPieces is called only once at beginning
        if (!isBoardInitialized) {
            displayBoardWithPieces();
            isBoardInitialized = true;
        }

        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                System.out.print(chessboard[i][j] + " | ");
            }
            System.out.println("\n-------+--------+--------+--------" +
                    "+--------+--------+--------+--------");
        }
    }
    public void displayBoardWithPieces() {

        for (int i = 0; i < chessboard.length; i++){
            chessboard[1][i] = wPawn;
            chessboard[6][i] = bPawn;
        }
        chessboard[0][2] = wBishop;
        chessboard[0][5] = wBishop;
        chessboard[7][2] = bBishop;
        chessboard[7][5] = bBishop;

        chessboard[0][7] = wRook;
        chessboard[0][0] = wRook;

        chessboard[7][7] = bRook;
        chessboard[7][0] = bRook;

        chessboard[0][6] = wKnight;
        chessboard[0][1] = wKnight;

        chessboard[7][1] = bKnight;
        chessboard[7][6] = bKnight;

        chessboard[0][4] = wQueen;
        chessboard[0][3] = wKing;
        chessboard[7][3] = bKing;
        chessboard[7][4] = bQueen;
    }

    public void playGame() {
        boolean gameOver = false;
        boolean isValidMove;
        displayBoard();

        while (!gameOver) {

            InputHandler inputHandler = InputHandler.movePosition();
            int rowChoose = inputHandler.getRowChoose();
            int colChoose = inputHandler.getColChoose();
            int rowMove = inputHandler.getRowMove();
            int colMove = inputHandler.getColMove();

            String sourcePiece = chessboard[rowChoose][colChoose];

            // Check if the chosen piece is a pawn
            if (sourcePiece.equals(wPawn) || sourcePiece.equals(bPawn)) {
                if (sourcePiece.equals(wPawn) && isWhiteTurn) {
                    isValidMove = pawns.isValidMoveForWhite(rowChoose, colChoose, rowMove, colMove);
                } else {
                    isValidMove = pawns.isValidMoveForBlack(rowChoose, colChoose, rowMove, colMove);
                }
                if (isValidMove) {
                    chessboard[rowChoose][colChoose] = "      ";
                    chessboard[rowMove][colMove] = sourcePiece;
                    isWhiteTurn = !isWhiteTurn;
                } else {
                    System.out.println("Invalid move for the pawn");
                }
                displayBoard();
                if(!isValidMove) {
                    System.out.println("Please move your piece!");
                }
            } else {
                System.out.println("Invalid move! You can only move pawns");
            }


            // Example game over condition (checkmate for white) doesn't work for now
            if (sourcePiece.equals(wKing)) {
                gameOver = true;
            }
        }
    }

    /**
     *
     * Method that holds the list of the White pieces
     *
     * @return the list to check when catch the opposition
     * piece
     */
    public List<String> stringArray() {
        List<String> strgList = new ArrayList<>();
        strgList.add(wBishop);
        strgList.add(wKing);
        strgList.add(wKnight);
        strgList.add(wPawn);
        strgList.add(wQueen);
        strgList.add(wRook);
        return strgList;
    }
}
