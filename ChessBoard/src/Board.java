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
    private final Bishops bishops;
    private final Rook rook;
    private boolean isBoardInitialized = false;
    private boolean isWhiteTurn = true;

    public Board() {
        pawns = new Pawns(this);
        bishops = new Bishops(this);
        rook = new Rook(this);
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
        boolean isValidMove = true;
        int plyr = 1;
        int colMove;
        int rowMove;
        int colChoose;
        int rowChoose;
        String sourcePiece;

        displayBoard();
        while (!gameOver) {

            if (plyr == 1) {
                System.out.println("Player1:");
            } else {
                System.out.println("Player2:");
            }
            //Make sure that user doesn't choose the empty space.
            do {
                InputHandler inputHandler = InputHandler.movePosition();
                rowChoose = inputHandler.getRowChoose();
                colChoose = inputHandler.getColChoose();
                rowMove = inputHandler.getRowMove();
                colMove = inputHandler.getColMove();

                sourcePiece = chessboard[rowChoose][colChoose];
                if (chessboard[rowChoose][colChoose].equals
                        (chessboard[rowMove][colMove])) {
                    System.out.println("Sorry! " +
                            "Please choose the box with the piece");
                }
            } while(!sourcePiece.equals(" "));

            // Check if the chosen piece is a pawn
            if (sourcePiece.equals(wPawn) || sourcePiece.equals(bPawn)) {
                if (sourcePiece.equals(wPawn) && isWhiteTurn) {
                    isValidMove = pawns.isValidMoveForWhite(rowChoose,
                            colChoose, rowMove, colMove);
                } else if (sourcePiece.equals(bPawn) && !isWhiteTurn){
                    isValidMove = pawns.isValidMoveForBlack(rowChoose,
                            colChoose, rowMove, colMove);
                }
                if(!isValidMove) {
                    System.out.println("Invalid move for the pawn");
                }
            }

            //Check if the chosen piece is a bishop
            if(sourcePiece.equals(wBishop) || sourcePiece.equals(bBishop)){
                if(sourcePiece.equals(wBishop) && isWhiteTurn) {
                    isValidMove = bishops.isValidMoveForWhite(rowChoose,colChoose,
                            rowMove,colMove);
                } else if (sourcePiece.equals(bBishop) && !isWhiteTurn) {
                    isValidMove = bishops.isValidMoveForBlack(rowChoose,colChoose,
                            rowMove,colMove);
                }
                if(!isValidMove) {
                    System.out.println("Invalid move for bishop");
                }
            }

            //Check if the chosen piece is a Rook
            if(sourcePiece.equals(wRook) || sourcePiece.equals(bRook)) {
                if(sourcePiece.equals(wRook) && isWhiteTurn) {
                    isValidMove = rook.isValidMoveForWhite(rowChoose,colChoose,
                            rowMove,colMove);
                } else if (sourcePiece.equals(bBishop) && !isWhiteTurn) {
                    isValidMove = rook.isValidMoveForBlack(rowChoose,colChoose,
                            rowMove,colMove);
                }
                if(!isValidMove) {
                    System.out.println("Invalid move for Rook");
                }
            }

            //Moving and switching after validating the piece move
            if (isValidMove) {
                chessboard[rowChoose][colChoose] = "      ";
                chessboard[rowMove][colMove] = sourcePiece;
                isWhiteTurn = !isWhiteTurn;
                //swapping player
                if (plyr == 1) {
                    plyr += 1;
                } else if (plyr == 2) {
                    plyr -= 1;
                }
            }
            displayBoard();

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
