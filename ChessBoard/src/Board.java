/**
 * @date: 10/07/2023
 * @author Suresh
 * @version 1.2
 */

public abstract class  Board {
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
    public Board() {

    }

    /**
     *
     * Method to display the chess board.
     * @param chessboard is the 8x8 grid board.
     */
    public void displayBoard(String[][] chessboard) {
        displayBoardWithPieces(chessboard);

        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                System.out.print(chessboard[i][j] + " | ");
            }
            System.out.println("\n-------+--------+--------+--------" +
                    "+--------+--------+--------+--------");
        }
    }
    public void displayBoardWithPieces(String[][] chessboard) {
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

    /**
     *
     * This is the move that this piece of chess can do
     * @param chessboard is the 8x8 board.
     */
    public abstract void moves(String[][] chessboard);
}
