import java.util.ArrayList;
import java.util.List;

/**
 * Board.java
 * @date 10/07/2023
 * @author Suresh
 * @version 1.4
 * @since 15/07/2023
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
    private final Pawns pawns;
    private final Bishops bishops;
    private final Rook rook;
    private final Knight knight;
    private final Queen queen;
    private final King king;
    private static final String wPawn = "W-Pawn";
    private static final String bPawn = "B-Pawn";
    private static final String wBishop = "W-Bisp";
    private static final String bBishop = "B-Bisp";
    private static final String wRook = "W-Rook";
    private static final String bRook = "B-Rook";
    private static final String wKnight = "W-Kght";
    private static final String bKnight = "B-Kght";
    private static final String wQueen = "W-Quen";
    private static final String wKing = "W-King";
    private static final String bKing = "B-King";
    private static final String bQueen = "B-Quen";
    private static final String ERROR_MOVE =
            "Sorry You are not allowed to move opponent's piece";
    private static final String CHECK = "Check Mate !!!";
    private boolean isBoardInitialized = false;
    private boolean isWhiteTurn = true;
    private boolean kingOutOfDanger = false;
    int colMove;
    int rowMove;
    int colChoose;
    int rowChoose;
    public Board() {
        pawns = new Pawns(this);
        bishops = new Bishops(this);
        rook = new Rook(this);
        knight = new Knight(this);
        queen = new Queen(this);
        king = new King(this);
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

//        for (int i = 0; i < chessboard.length; i++){
//            chessboard[1][i] = wPawn;
//            chessboard[6][i] = bPawn;
//        }
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
                            "Please choose the box with the piece.");
                    System.out.println("OR - Please choose the different " +
                            "moving position.");
                }
            } while(chessboard[rowChoose][colChoose].
                    equals(chessboard[rowMove][colMove]));

            // Check if the chosen piece is a pawn
            if (sourcePiece.equals(wPawn) || sourcePiece.equals(bPawn)) {
                if (sourcePiece.equals(wPawn) && isWhiteTurn) {
                    isValidMove = pawns.isValidMoveForWhite(rowChoose,
                            colChoose, rowMove, colMove);
                } else if (sourcePiece.equals(bPawn) && !isWhiteTurn){
                    isValidMove = pawns.isValidMoveForBlack(rowChoose,
                            colChoose, rowMove, colMove);
                } else {
                    System.out.println(ERROR_MOVE);
                    isValidMove = false;
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
                } else {
                    System.out.println(ERROR_MOVE);
                    isValidMove = false;
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
                } else if (sourcePiece.equals(bRook) && !isWhiteTurn) {
                    isValidMove = rook.isValidMoveForBlack(rowChoose,colChoose,
                            rowMove,colMove);
                } else {
                    System.out.println(ERROR_MOVE);
                    isValidMove = false;
                }
                if(!isValidMove) {
                    System.out.println("Invalid move for Rook");
                }
            }

            //Check for the knight piece
            if (sourcePiece.equals(wKnight) || sourcePiece.equals(bKnight)) {
                if (sourcePiece.equals(wKnight) && isWhiteTurn) {
                    isValidMove = knight.isValidMoveForWhite(rowChoose, colChoose,
                            rowMove, colMove);
                } else if (sourcePiece.equals(bKnight) && !isWhiteTurn) {
                    isValidMove = knight.isValidMoveForBlack(rowChoose, colChoose,
                            rowMove, colMove);
                } else {
                    System.out.println(ERROR_MOVE);
                    isValidMove = false;
                }
                if (!isValidMove) {
                    System.out.println("Invalid move for Knight");
                }
            }

            //Check if the chosen piece is Queen
            if (sourcePiece.equals(wQueen) || sourcePiece.equals(bQueen)){
                if(sourcePiece.equals(wQueen) && isWhiteTurn) {
                    isValidMove = queen.isValidMoveForWhite(rowChoose,colChoose,
                            rowMove,colMove);
                } else if (sourcePiece.equals(bQueen) && !isWhiteTurn) {
                    isValidMove = queen.isValidMoveForBlack(rowChoose,colChoose,
                            rowMove,colMove);
                } else {
                    System.out.println(ERROR_MOVE);
                    isValidMove = false;
                }
                if(!isValidMove) {
                    System.out.println("Invalid move for Queen");
                }
            }
            //If the chosen piece is King
            if(sourcePiece.equals(wKing) || sourcePiece.equals(bKing)) {
                if(sourcePiece.equals(wKing)) {
                    //check for the possible move so that king doesn't move to
                    //opponent marking pos
                    if(isPosUnderAttack(rowMove,colMove, isWhiteTurn)) {
                        System.out.println("That place is already" +
                                " marked!!");
                        //Reset
                        chessboard[rowMove][colMove] = "      ";
                        chessboard[rowChoose][colChoose] = sourcePiece;
                        isValidMove = false;
                    } else {
                        isValidMove = king.isValidMoveForWhite(rowChoose,colChoose,
                                rowMove,colMove);
                    }
                } else {
                    if(isPosUnderAttack(rowMove,colMove, !isWhiteTurn)) {
                        System.out.println("That place is already" +
                                " marked!!");
                        //Reset
                        chessboard[rowMove][colMove] = "      ";
                        chessboard[rowChoose][colChoose] = sourcePiece;
                        isValidMove = false;
                    } else {
                        isValidMove = king.isValidMoveForBlack(rowChoose,colChoose,
                                rowMove,colMove);
                    }
                }
                if(!isValidMove) {
                    System.out.println("Invalid move for King");
                }
            }

            //Moving and switching after validating the piece move
            if (isValidMove) {
                chessboard[rowChoose][colChoose] = "      ";
                chessboard[rowMove][colMove] = sourcePiece;

                //Checkmate conditions
                if(isCheckMate(isWhiteTurn)) {
                    displayBoard();
                    System.out.println(CHECK);
                    System.out.println(plyr + " !! has made a checkmate.");

                    while(!kingOutOfDanger) {
                        plyr = (plyr == 1) ? 2 : 1;
                        if (plyr == 1) {
                            System.out.println("Player1:");
                        } else {
                            System.out.println("Player2:");
                        }
                        isWhiteTurn = !isWhiteTurn;

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
                                        "Please choose the box with the piece.");
                                System.out.println("OR - Please choose the different " +
                                        "moving position.");
                            }
                        } while(chessboard[rowChoose][colChoose].
                                equals(chessboard[rowMove][colMove]));

                        // Check if the chosen piece is a pawn
                        if (sourcePiece.equals(wPawn) || sourcePiece.equals(bPawn)) {
                            if (sourcePiece.equals(wPawn) && isWhiteTurn) {
                                isValidMove = pawns.isValidMoveForWhite(rowChoose,
                                        colChoose, rowMove, colMove);
                            } else if (sourcePiece.equals(bPawn) && !isWhiteTurn){
                                isValidMove = pawns.isValidMoveForBlack(rowChoose,
                                        colChoose, rowMove, colMove);
                            } else {
                                System.out.println(ERROR_MOVE);
                                isValidMove = false;
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
                            } else {
                                System.out.println(ERROR_MOVE);
                                isValidMove = false;
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
                            } else if (sourcePiece.equals(bRook) && !isWhiteTurn) {
                                isValidMove = rook.isValidMoveForBlack(rowChoose,colChoose,
                                        rowMove,colMove);
                            } else {
                                System.out.println(ERROR_MOVE);
                                isValidMove = false;
                            }
                            if(!isValidMove) {
                                System.out.println("Invalid move for Rook");
                            }
                        }

                        //Check for the knight piece
                        if (sourcePiece.equals(wKnight) || sourcePiece.equals(bKnight)) {
                            if (sourcePiece.equals(wKnight) && isWhiteTurn) {
                                isValidMove = knight.isValidMoveForWhite(rowChoose, colChoose,
                                        rowMove, colMove);
                            } else if (sourcePiece.equals(bKnight) && !isWhiteTurn) {
                                isValidMove = knight.isValidMoveForBlack(rowChoose, colChoose,
                                        rowMove, colMove);
                            } else {
                                System.out.println(ERROR_MOVE);
                                isValidMove = false;
                            }
                            if (!isValidMove) {
                                System.out.println("Invalid move for Knight");
                            }
                        }

                        //Check if the chosen piece is Queen
                        if (sourcePiece.equals(wQueen) || sourcePiece.equals(bQueen)){
                            if(sourcePiece.equals(wQueen) && isWhiteTurn) {
                                isValidMove = queen.isValidMoveForWhite(rowChoose,colChoose,
                                        rowMove,colMove);
                            } else if (sourcePiece.equals(bQueen) && !isWhiteTurn) {
                                isValidMove = queen.isValidMoveForBlack(rowChoose,colChoose,
                                        rowMove,colMove);
                            } else {
                                System.out.println(ERROR_MOVE);
                                isValidMove = false;
                            }
                            if(!isValidMove) {
                                System.out.println("Invalid move for Queen");
                            }
                        }

                        //If the chosen piece is King
                        if(sourcePiece.equals(wKing) || sourcePiece.equals(bKing)) {
                            if(sourcePiece.equals(wKing)) {
                                if(isPosUnderAttack(rowMove,colMove, isWhiteTurn)) {
                                    System.out.println("That place is already" +
                                            " marked!!");
                                    //Reset
                                    chessboard[rowMove][colMove] = "      ";
                                    chessboard[rowChoose][colChoose] = sourcePiece;
                                } else {
                                    isValidMove = king.isValidMoveForWhite(rowChoose,colChoose,
                                            rowMove,colMove);
                                }
                            } else {
                                if(isPosUnderAttack(rowMove,colMove, !isWhiteTurn)) {
                                    System.out.println("That place is already" +
                                            " marked!!");
                                    //Reset
                                    chessboard[rowMove][colMove] = "      ";
                                    chessboard[rowChoose][colChoose] = sourcePiece;
                                } else {
                                    isValidMove = king.isValidMoveForBlack(rowChoose,colChoose,
                                            rowMove,colMove);
                                }
                            }
                            if(!isValidMove) {
                                System.out.println("Invalid move for King");
                            }
                        }

                        if(isValidMove) {
                            if(!isCheckMate(isWhiteTurn)) {
                                    chessboard[rowChoose][colChoose] = "      ";
                                    chessboard[rowMove][colMove] = sourcePiece;
                                    System.out.println("King is out of checkmate.");
                                    kingOutOfDanger = true;
                                    break;
                            } else {
                                System.out.println("Invalid move! Your king " +
                                        "is still in checkmate!");
                                //Reset the move to allow the current player
                                //to make a move again.
                                chessboard[rowMove][colMove] = "      ";
                                chessboard[rowChoose][colChoose] =sourcePiece;
                            }
                            //swapping player
                            plyr = (plyr == 1) ? 2 : 1;
                            isWhiteTurn = !isWhiteTurn;
                        }

                    }
                }
                isWhiteTurn = !isWhiteTurn;
                plyr = (plyr == 1) ? 2 : 1;
            }
            displayBoard();
        }
        System.out.println("Game Over!");

    }

    /**
     *
     * Method that holds the list of the White pieces
     *
     * @return the list of white pieces
     */
    public List<String> stringArray() {
        List<String> strgList = new ArrayList<>();
        strgList.add(wBishop);
        strgList.add(wKnight);
        strgList.add(wPawn);
        strgList.add(wQueen);
        strgList.add(wKing);
        strgList.add(wRook);
        return strgList;
    }

    /**
     *
     * Method that checks for the checkmate
     */
    public boolean isCheckMate(boolean isWhiteTurn) {
        String kingPos = isWhiteTurn ? bKing : wKing;
        int rowKing = 0;
        int colKing = 0;
        boolean isUnderAttack = false;

        //Find the king's position in the board
        for(int i = 0 ; i < chessboard.length; i++) {
            for(int j = 0; j < chessboard[i].length; j++) {
                if(chessboard[i][j].equals(kingPos)){
                    rowKing = i;
                    colKing = j;
                    break;
                }
            }
        }

        //Check for the possible attack for the king
        for(int i = 0 ; i < chessboard.length; i++) {
            for(int j = 0; j < chessboard[i].length; j++) {

                String pawnPiece = isWhiteTurn ? wPawn : bPawn;
                if(chessboard[i][j].equals(pawnPiece)) {
                    if(!isWhiteTurn){ //check for the pawn validation
                        if(pawns.isValidMoveForBlack(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if(pawns.isValidMoveForWhite(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String knightPiece = isWhiteTurn ? wKnight : bKnight;
                if(chessboard[i][j].equals(knightPiece)) {
                    if(!isWhiteTurn){ //check for the knight validation
                        if(knight.isValidMoveForBlack(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if(knight.isValidMoveForWhite(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String bishopPiece = isWhiteTurn ? wBishop : bBishop;
                if(chessboard[i][j].equals(bishopPiece)) {
                    if(!isWhiteTurn){ //check for the bishop validation
                        if(bishops.isValidMoveForBlack(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if(bishops.isValidMoveForWhite(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String rookPiece = isWhiteTurn ? wRook : bRook;
                if(chessboard[i][j].equals(rookPiece)) {
                    if(!isWhiteTurn){ //check for the oop validation
                        if(rook.isValidMoveForBlack(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if(rook.isValidMoveForWhite(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String queenPiece = isWhiteTurn ? wQueen : bQueen;
                if(chessboard[i][j].equals(queenPiece)) {
                    if(!isWhiteTurn){ //check for the oop validation
                        if(queen.isValidMoveForBlack(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if(queen.isValidMoveForWhite(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String kingPiece = isWhiteTurn ? wKing : bKing;
                if(chessboard[i][j].equals(kingPiece)) {
                    if(!isWhiteTurn){ //check for the oop validation
                        if(king.isValidMoveForBlack(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if(king.isValidMoveForWhite(i,j,rowKing,colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
            }
        }
        return isUnderAttack;
    }


    /**
     *
     * Method that checks for the possible move of
     * the King after Checkmate
     */
    public boolean isPosUnderAttack(int rowMove, int colMove, boolean isWhiteTurn) {
        boolean kingCannotMove = false;
        
        //Check for the possible attack for the king
        for(int i = 0 ; i < chessboard.length; i++) {
            for(int j = 0; j < chessboard[i].length; j++) {

                String pawnPiece = isWhiteTurn ? bPawn : wPawn;
                if(chessboard[i][j].equals(pawnPiece)) {
                    if(isWhiteTurn){ //check for the pawn validation
                        if(pawns.isValidMoveForBlack(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if(pawns.isValidMoveForWhite(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
                String knightPiece = isWhiteTurn ? bKnight : wKnight;
                if(chessboard[i][j].equals(knightPiece)) {
                    if(isWhiteTurn){ //check for the knight validation
                        if(knight.isValidMoveForBlack(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if(knight.isValidMoveForWhite(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
                String bishopPiece = isWhiteTurn ? bBishop : wBishop;
                if(chessboard[i][j].equals(bishopPiece)) {
                    if(isWhiteTurn){ //check for the bishop validation
                        if(bishops.isValidMoveForBlack(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if(bishops.isValidMoveForWhite(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
                String rookPiece = isWhiteTurn ? bRook : wRook;
                if(chessboard[i][j].equals(rookPiece)) {
                    if(isWhiteTurn){ //check for the oop validation
                        if(rook.isValidMoveForBlack(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if(rook.isValidMoveForWhite(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }

                String queenPiece = isWhiteTurn ? bQueen : wQueen;
                if(chessboard[i][j].equals(queenPiece)) {
                    if(isWhiteTurn){ //check for the oop validation
                        if(queen.isValidMoveForBlack(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if(queen.isValidMoveForWhite(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
                String kingPiece = isWhiteTurn ? bKing : wKing;
                if(chessboard[i][j].equals(kingPiece)) {
                    if(isWhiteTurn){ //check for the oop validation
                        if(king.isValidMoveForBlack(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if(king.isValidMoveForWhite(i,j,rowMove,colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
            }
        }
        return kingCannotMove;
    }
}
