/**
 * Board.java
 *
 * @author Suresh
 * @version 1.4
 * @date 10/07/2023
 * @since 15/07/2023
 */

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a main board game where the function of chess game takes place
 * like swapping player after turn and display of the board as well as keeping track
 * after each move. All the chess pieces are implementation or valid move is checked
 * in this class for further movement in the game. And you use the number to move the
 * pieces in the board
 *   1    |    2   |    3   |    4   |    5   |    6   |    7   |    8   |
 * -------+--------+--------+--------+--------+--------+--------+--------
 *   9    |   10   |   11   |   12   |    13   |   14  |   15  |   16    |
 * -------+--------+--------+--------+--------+--------+--------+--------
 *   17   |   18   |   19   |   20   |   21   |   22   |   23   |   24   |
 * -------+--------+--------+--------+--------+--------+--------+--------
 *   25   |   26   |   27   |   28   |   29   |   30   |   31   |   32   |
 * -------+--------+--------+--------+--------+--------+--------+--------
 *   33   |   34   |   35   |   36   |   37   |   38   |   39   |   40   |
 * -------+--------+--------+--------+--------+--------+--------+--------
 *   41   |   42   |   43   |   44   |   45   |   46   |   47   |   48   |
 * -------+--------+--------+--------+--------+--------+--------+--------
 *   49   |   50   |   51   |   52   |   53   |   54   |   55   |   56   |
 * -------+--------+--------+--------+--------+--------+--------+--------
 *   57   |   58   |   59   |   60   |   61   |   62   |   63   |   64   |
 * -------+--------+--------+--------+--------+--------+--------+--------
 */

public class Board {
    protected final String[][] chessboard =
            {{"      ", "      ", "      ", "      ", "      ", "      ", "      ", "      "},
                    {"      ", "      ", "      ", "      ", "      ", "      ", "      ", "      "},
                    {"      ", "      ", "      ", "      ", "      ", "      ", "      ", "      "},
                    {"      ", "      ", "      ", "      ", "      ", "      ", "      ", "      "},
                    {"      ", "      ", "      ", "      ", "      ", "      ", "      ", "      "},
                    {"      ", "      ", "      ", "      ", "      ", "      ", "      ", "      "},
                    {"      ", "      ", "      ", "      ", "      ", "      ", "      ", "      "},
                    {"      ", "      ", "      ", "      ", "      ", "      ", "      ", "      "}};
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
    private static final String CHECK = "Check !!!";
    protected String newPiece = " ";
    private boolean isBoardInitialized = false;
    private boolean isWhiteTurn = true;
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
     * Method to display the chess board.
     */
    public void displayBoard() {
        //using the boolean value to make sure that the
        //displayBoardWithPieces is called only once at beginning
        if (!isBoardInitialized) {
            displayBoardWithPieces();
            isBoardInitialized = true;
        }

        for (String[] strings : chessboard) {
            for (String string : strings) {
                System.out.print(string + " | ");
            }
            System.out.println("\n-------+--------+--------+--------" +
                    "+--------+--------+--------+--------");
        }
    }

    public void displayBoardWithPieces() {

        for (int i = 0; i < chessboard.length; i++) {
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

        chessboard[0][3] = wQueen;
        chessboard[0][4] = wKing;
        chessboard[7][4] = bKing;
        chessboard[7][3] = bQueen;
    }

    /**
     * Method where game is played and chess pieces are
     * moved according to their legal moves. Also checkmate is
     * checked as well for the King
     */
    public void playGame() {
        boolean gameOver = false;
        boolean isValidMove = true;
        int plyr = 1;
        String sourcePiece;
        displayBoard();

        while (true) {
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
                        (chessboard[rowMove][colMove])
                        || chessboard[rowChoose][colChoose]
                        .equals("      ")) {
                    System.out.println("Sorry! " +
                            "Please choose the box with the piece.");
                    System.out.println("OR - Please choose the different " +
                            "moving position.");
                    isValidMove = false;
                }
            } while (chessboard[rowChoose][colChoose].
                    equals(chessboard[rowMove][colMove]));

            // Check if the chosen piece is a pawn
            if (sourcePiece.equals(wPawn) || sourcePiece.equals(bPawn)) {
                if (sourcePiece.equals(wPawn) && isWhiteTurn) {
                    isValidMove = pawns.isValidMoveForWhite(rowChoose,
                            colChoose, rowMove, colMove);
                } else if (sourcePiece.equals(bPawn) && !isWhiteTurn) {
                    isValidMove = pawns.isValidMoveForBlack(rowChoose,
                            colChoose, rowMove, colMove);
                } else {
                    System.out.println(ERROR_MOVE);
                    isValidMove = false;
                }
                if (!isValidMove) {
                    System.out.println("Invalid move for the pawn");
                }
            }

            //Check if the chosen piece is a bishop
            if (sourcePiece.equals(wBishop) || sourcePiece.equals(bBishop)) {
                if (sourcePiece.equals(wBishop) && isWhiteTurn) {
                    isValidMove = bishops.isValidMoveForWhite(rowChoose, colChoose,
                            rowMove, colMove);
                } else if (sourcePiece.equals(bBishop) && !isWhiteTurn) {
                    isValidMove = bishops.isValidMoveForBlack(rowChoose, colChoose,
                            rowMove, colMove);
                } else {
                    System.out.println(ERROR_MOVE);
                    isValidMove = false;
                }
                if (!isValidMove) {
                    System.out.println("Invalid move for bishop");
                }
            }

            //Check if the chosen piece is a Rook
            if (sourcePiece.equals(wRook) || sourcePiece.equals(bRook)) {
                if (sourcePiece.equals(wRook) && isWhiteTurn) {
                    isValidMove = rook.isValidMoveForWhite(rowChoose, colChoose,
                            rowMove, colMove);
                } else if (sourcePiece.equals(bRook) && !isWhiteTurn) {
                    isValidMove = rook.isValidMoveForBlack(rowChoose, colChoose,
                            rowMove, colMove);
                } else {
                    System.out.println(ERROR_MOVE);
                    isValidMove = false;
                }
                if (!isValidMove) {
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
            if (sourcePiece.equals(wQueen) || sourcePiece.equals(bQueen)) {
                if (sourcePiece.equals(wQueen) && isWhiteTurn) {
                    isValidMove = queen.isValidMoveForWhite(rowChoose, colChoose,
                            rowMove, colMove);
                } else if (sourcePiece.equals(bQueen) && !isWhiteTurn) {
                    isValidMove = queen.isValidMoveForBlack(rowChoose, colChoose,
                            rowMove, colMove);
                } else {
                    System.out.println(ERROR_MOVE);
                    isValidMove = false;
                }
                if (!isValidMove) {
                    System.out.println("Invalid move for Queen");
                }
            }
            //If the chosen piece is King
            if (sourcePiece.equals(wKing) || sourcePiece.equals(bKing)) {
                if (sourcePiece.equals(wKing)) {
                    if (king.isValidMoveForWhite(rowChoose, colChoose,
                            rowMove, colMove)) {
                        if (isPosUnderAttack(rowMove, colMove, true)) {
                            if (isThereAnySpace()) {
                                System.out.println("That place is already" +
                                        " marked!!");
                                //Reset
                                chessboard[rowMove][colMove] = "      ";
                                chessboard[rowChoose][colChoose] = sourcePiece;
                                isValidMove = false;
                            }
                        } else {
                            isValidMove = king.isValidMoveForWhite(rowChoose, colChoose,
                                    rowMove, colMove);
                        }
                    } else {
                        isValidMove = false;
                    }
                } else {
                    if (king.isValidMoveForBlack(rowChoose, colChoose,
                            rowMove, colMove)) {
                        if (isPosUnderAttack(rowMove, colMove, false)) {
                            if (isThereAnySpace()) {
                                System.out.println("That place is already" +
                                        " marked!!");
                                //Reset
                                chessboard[rowMove][colMove] = "      ";
                                chessboard[rowChoose][colChoose] = sourcePiece;
                                isValidMove = false;
                            }
                        } else {
                            isValidMove = king.isValidMoveForBlack(rowChoose, colChoose,
                                    rowMove, colMove);
                        }
                    } else {
                        isValidMove = false;
                    }
                }
                if (!isValidMove) {
                    System.out.println("Invalid move for King");
                }
            }
            //Moving and switching after validating the piece move
            if (isValidMove) {
                if (newPiece.equals(" ")) {
                    chessboard[rowChoose][colChoose] = "      ";
                    chessboard[rowMove][colMove] = sourcePiece;
                } else {
                    chessboard[rowChoose][colChoose] = "      ";
                    chessboard[rowMove][colMove] = newPiece;
                }
                newPiece = " ";

                //Checkmate conditions
                if (isCheckMate(isWhiteTurn)) {
                    displayBoard();
                    System.out.println(CHECK);
                    System.out.println(plyr + " !! has made a check.");

                    if (!canPieceCatchAttacker() || !canPieceBlockAttacker()) {
                        gameOver = true;
                        System.out.println("No possible movements.");
                        plyr = (plyr == 1) ? 2 : 1;
                        break;
                    }

                    while (true) {
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
                                    (chessboard[rowMove][colMove])
                                    || chessboard[rowChoose][colChoose]
                                    .equals("      ")) {
                                System.out.println("Sorry! " +
                                        "Please choose the box with the piece.");
                                System.out.println("OR - Please choose the different " +
                                        "moving position.");
                            }
                        } while (chessboard[rowChoose][colChoose].
                                equals(chessboard[rowMove][colMove]));

                        // Check if the chosen piece is a pawn
                        if (sourcePiece.equals(wPawn) || sourcePiece.equals(bPawn)) {
                            if (sourcePiece.equals(wPawn) && isWhiteTurn) {
                                isValidMove = pawns.isValidMoveForWhite(rowChoose,
                                        colChoose, rowMove, colMove);
                            } else if (sourcePiece.equals(bPawn) && !isWhiteTurn) {
                                isValidMove = pawns.isValidMoveForBlack(rowChoose,
                                        colChoose, rowMove, colMove);
                            } else {
                                isValidMove = false;
                            }
                            if (!isValidMove) {
                                System.out.println("Invalid move for the pawn");
                            }
                        }

                        //Check if the chosen piece is a bishop
                        if (sourcePiece.equals(wBishop) || sourcePiece.equals(bBishop)) {
                            if (sourcePiece.equals(wBishop) && isWhiteTurn) {
                                isValidMove = bishops.isValidMoveForWhite(rowChoose, colChoose,
                                        rowMove, colMove);
                            } else if (sourcePiece.equals(bBishop) && !isWhiteTurn) {
                                isValidMove = bishops.isValidMoveForBlack(rowChoose, colChoose,
                                        rowMove, colMove);
                            } else {
                                isValidMove = false;
                            }
                            if (!isValidMove) {
                                System.out.println("Invalid move for bishop");
                            }
                        }

                        //Check if the chosen piece is a Rook
                        if (sourcePiece.equals(wRook) || sourcePiece.equals(bRook)) {
                            if (sourcePiece.equals(wRook) && isWhiteTurn) {
                                isValidMove = rook.isValidMoveForWhite(rowChoose, colChoose,
                                        rowMove, colMove);
                            } else if (sourcePiece.equals(bRook) && !isWhiteTurn) {
                                isValidMove = rook.isValidMoveForBlack(rowChoose, colChoose,
                                        rowMove, colMove);
                            } else {
                                isValidMove = false;
                            }
                            if (!isValidMove) {
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
                                isValidMove = false;
                            }
                            if (!isValidMove) {
                                System.out.println("Invalid move for Knight");
                            }
                        }

                        //Check if the chosen piece is Queen
                        if (sourcePiece.equals(wQueen) || sourcePiece.equals(bQueen)) {
                            if (sourcePiece.equals(wQueen) && isWhiteTurn) {
                                isValidMove = queen.isValidMoveForWhite(rowChoose, colChoose,
                                        rowMove, colMove);
                            } else if (sourcePiece.equals(bQueen) && !isWhiteTurn) {
                                isValidMove = queen.isValidMoveForBlack(rowChoose, colChoose,
                                        rowMove, colMove);
                            } else {
                                System.out.println(ERROR_MOVE);
                                isValidMove = false;
                            }
                            if (!isValidMove) {
                                System.out.println("Invalid move for Queen");
                            }
                        }

                        //If the chosen piece is King
                        if (sourcePiece.equals(wKing) || sourcePiece.equals(bKing)) {
                            if (sourcePiece.equals(wKing)) {
                                if (king.isValidMoveForWhite(rowChoose, colChoose,
                                        rowMove, colMove)) {
                                    if (isPosUnderAttack(rowMove, colMove, true)) {
                                        if (isThereAnySpace()) {
                                            System.out.println("That place is already" +
                                                    " marked!!");
                                            //Reset
                                            chessboard[rowMove][colMove] = "      ";
                                            chessboard[rowChoose][colChoose] = sourcePiece;
                                            isValidMove = false;
                                        }
                                    } else {
                                        isValidMove = king.isValidMoveForWhite(rowChoose, colChoose,
                                                rowMove, colMove);
                                    }
                                } else {
                                    isValidMove = false;
                                }
                            } else if (sourcePiece.equals(bKing)) {
                                if (king.isValidMoveForBlack(rowChoose, colChoose,
                                        rowMove, colMove)) {
                                    if (isPosUnderAttack(rowMove, colMove, false)) {
                                        if (isThereAnySpace()) {
                                            System.out.println("That place is already" +
                                                    " marked!!");
                                            //Reset
                                            chessboard[rowMove][colMove] = "      ";
                                            chessboard[rowChoose][colChoose] = sourcePiece;
                                            isValidMove = false;
                                        }
                                    } else {
                                        isValidMove = king.isValidMoveForBlack(rowChoose, colChoose,
                                                rowMove, colMove);
                                    }
                                } else {
                                    isValidMove = false;
                                }
                            }
                            if (!isValidMove) {
                                System.out.println("Invalid move for King");
                            }
                        }

                        if (isValidMove) {
                            if (!isCheckMate(isWhiteTurn)) {
                                chessboard[rowChoose][colChoose] = "      ";
                                chessboard[rowMove][colMove] = sourcePiece;
                                System.out.println("King is out of checkmate.");
                                break;
                            }
                        } else {
                            System.out.println("Invalid move! Your king " +
                                    "is still in checkmate!");
                            //Reset the move to allow the current player
                            //to make a move again.
                            chessboard[rowMove][colMove] = "      ";
                            chessboard[rowChoose][colChoose] = sourcePiece;
                        }
                        //swapping player
                        plyr = (plyr == 1) ? 2 : 1;
                        isWhiteTurn = !isWhiteTurn;
                    }
                }
                plyr = (plyr == 1) ? 2 : 1;
                isWhiteTurn = !isWhiteTurn;
            }
            displayBoard();
        }

        System.out.print("Game Over!");
        System.out.println(plyr + " -> Wins");
    }

    /**
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
     * Method that checks for the checkmate
     */
    public boolean isCheckMate(boolean isWhiteTurn) {
        String kingPos = isWhiteTurn ? bKing : wKing;
        int rowKing = 0;
        int colKing = 0;
        boolean isUnderAttack = false;

        //Find the king's position in the board
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                if (chessboard[i][j].equals(kingPos)) {
                    rowKing = i;
                    colKing = j;
                    break;
                }
            }
        }
        //Check for the possible attack for the king
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {

                String pawnPiece = isWhiteTurn ? wPawn : bPawn;
                if (chessboard[i][j].equals(pawnPiece)) {
                    if (!isWhiteTurn) { //check for the pawn validation
                        if (pawns.isValidMoveForBlack(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if (pawns.isValidMoveForWhite(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String knightPiece = isWhiteTurn ? wKnight : bKnight;
                if (chessboard[i][j].equals(knightPiece)) {
                    if (!isWhiteTurn) { //check for the knight validation
                        if (knight.isValidMoveForBlack(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if (knight.isValidMoveForWhite(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String bishopPiece = isWhiteTurn ? wBishop : bBishop;
                if (chessboard[i][j].equals(bishopPiece)) {
                    if (!isWhiteTurn) { //check for the bishop validation
                        if (bishops.isValidMoveForBlack(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if (bishops.isValidMoveForWhite(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String rookPiece = isWhiteTurn ? wRook : bRook;
                if (chessboard[i][j].equals(rookPiece)) {
                    if (!isWhiteTurn) { //check for the oop validation
                        if (rook.isValidMoveForBlack(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if (rook.isValidMoveForWhite(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String queenPiece = isWhiteTurn ? wQueen : bQueen;
                if (chessboard[i][j].equals(queenPiece)) {
                    if (!isWhiteTurn) { //check for the oop validation
                        if (queen.isValidMoveForBlack(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if (queen.isValidMoveForWhite(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    }
                }
                String kingPiece = isWhiteTurn ? wKing : bKing;
                if (chessboard[i][j].equals(kingPiece)) {
                    if (!isWhiteTurn) { //check for the oop validation
                        if (king.isValidMoveForBlack(i, j, rowKing, colKing)) {
                            isUnderAttack = true;
                            break;
                        }
                    } else {
                        if (king.isValidMoveForWhite(i, j, rowKing, colKing)) {
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
     * Method that checks for the possible move of
     * the King after Checkmate
     */
    public boolean isPosUnderAttack(int rowMove, int colMove, boolean isWhiteTurn) {
        boolean kingCannotMove = false;

        //Check for the possible attack for the king
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                String pawnPiece = isWhiteTurn ? bPawn : wPawn;
                if (chessboard[i][j].equals(pawnPiece)) {
                    if (isWhiteTurn) { //check for the pawn validation
                        if (pawns.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if (pawns.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
                String knightPiece = isWhiteTurn ? bKnight : wKnight;
                if (chessboard[i][j].equals(knightPiece)) {
                    if (isWhiteTurn) { //check for the knight validation
                        if (knight.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if (knight.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
                String bishopPiece = isWhiteTurn ? bBishop : wBishop;
                if (chessboard[i][j].equals(bishopPiece)) {
                    if (isWhiteTurn) { //check for the bishop validation
                        if (bishops.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if (bishops.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
                String rookPiece = isWhiteTurn ? bRook : wRook;
                if (chessboard[i][j].equals(rookPiece)) {
                    if (isWhiteTurn) { //check for the oop validation
                        if (rook.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if (rook.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }

                String queenPiece = isWhiteTurn ? bQueen : wQueen;
                if (chessboard[i][j].equals(queenPiece)) {
                    if (isWhiteTurn) { //check for the oop validation
                        if (queen.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if (queen.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
                String kingPiece = isWhiteTurn ? bKing : wKing;
                if (chessboard[i][j].equals(kingPiece)) {
                    if (isWhiteTurn) { //check for the oop validation
                        if (king.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    } else {
                        if (king.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCannotMove = true;
                            break;
                        }
                    }
                }
            }
        }
        return kingCannotMove;
    }

    /**
     * Method for the possible moves of the king
     */
    public boolean isThereAnySpace() {
        //pos around the current cell
        int[] rowOffSets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffSets = {-1, 0, 1, -1, 1, -1, 0, 1};
        int tempKingRow = 0;
        int tempKingCol = 0;
        String kingPiece = (isWhiteTurn) ? bKing : wKing;

        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                if (chessboard[i][j].equals(kingPiece)) {
                    tempKingRow = i;
                    tempKingCol = j;
                    break;
                }
            }
        }

        for (int i = 0; i < rowOffSets.length; i++) {
            int newRow = tempKingRow + rowOffSets[i];
            int newCol = tempKingCol + colOffSets[i];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8
                    && chessboard[newRow][newCol].equals("      ")) {

                boolean isKingUnderAttack = isPosUnderAttack(newRow, newCol, false);

                if (!isKingUnderAttack) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method that checks if the piece can catch the piece that
     * made check on the King
     */
    private boolean canPieceCatchAttacker() {
        boolean kingCanBeSaved = false;

        //Check for the possible attack to the piece that made check
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {

                String pawnPiece = isWhiteTurn ? bPawn : wPawn;
                if (chessboard[i][j].equals(pawnPiece)) {
                    if (isWhiteTurn) { //check for the pawn validation
                        if (pawns.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    } else {
                        if (pawns.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    }
                }
                String knightPiece = isWhiteTurn ? bKnight : wKnight;
                if (chessboard[i][j].equals(knightPiece)) {
                    if (isWhiteTurn) { //check for the knight validation
                        if (knight.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    } else {
                        if (knight.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    }
                }
                String bishopPiece = isWhiteTurn ? bBishop : wBishop;
                if (chessboard[i][j].equals(bishopPiece)) {
                    if (isWhiteTurn) { //check for the bishop validation
                        if (bishops.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    } else {
                        if (bishops.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    }
                }
                String rookPiece = isWhiteTurn ? bRook : wRook;
                if (chessboard[i][j].equals(rookPiece)) {
                    if (isWhiteTurn) { //check for the oop validation
                        if (rook.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    } else {
                        if (rook.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    }
                }
                String queenPiece = isWhiteTurn ? bQueen : wQueen;
                if (chessboard[i][j].equals(queenPiece)) {
                    if (isWhiteTurn) { //check for the oop validation
                        if (queen.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    } else {
                        if (queen.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    }
                }
                String kingPiece = isWhiteTurn ? bKing : wKing;
                if (chessboard[i][j].equals(kingPiece)) {
                    if (isWhiteTurn) { //check for the oop validation
                        if (king.isValidMoveForBlack(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    } else {
                        if (king.isValidMoveForWhite(i, j, rowMove, colMove)) {
                            kingCanBeSaved = true;
                            break;
                        }
                    }
                }
            }
        }
//        if(canPieceBlockAttacker(rowMove,colMove,kingRow,kingCol)) {
//            kingCanBeSaved = true;
//        }
        return kingCanBeSaved;
    }

    /**
     * Method that checks if the piece can block the attacker piece
     * @return true if the piece can block the path
     */
    private boolean canPieceBlockAttacker() {
        int kingRow = 0;
        int kingCol = 0;
        rowChoose = rowMove;
        colChoose = colMove;

        String kingPos = (isWhiteTurn) ? bKing : wKing;

        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                if (chessboard[i][j].equals(kingPos)) {
                    kingRow = i;
                    kingCol = j;
                    break;
                }
            }
        }
        // Check if the attacker is a rook or queen (horizontal or vertical movement)
        if (rowChoose == kingRow || colChoose == kingCol) {
            int rowDir = (kingRow > rowChoose) ? 1 : -1;
            int colDir = (kingCol > colChoose) ? 1 : -1;

            // Loop through the squares between the attacking piece and the king
            int currentRow = rowChoose + rowDir;
            int currentCol = colChoose + colDir;
            while (currentRow != rowMove || currentCol != colMove) {
                // Loop through all the black pieces
                String blackPieceType = (isWhiteTurn) ? "B" : "W";
                for (int i = 0; i < chessboard.length; i++) {
                    for (int j = 0; j < chessboard[i].length; j++) {
                        String piece = chessboard[i][j];
                        if (piece.startsWith(blackPieceType)) {
                            // Check if the black piece can move to the current square
                            switch (piece) {
                                case bPawn:
                                    if (pawns.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true; // Black piece can block the check
                                    }
                                    break;
                                case bKnight:
                                    if (knight.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case bQueen:
                                    if (queen.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case bRook:
                                    if (rook.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case bBishop:
                                    if (bishops.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                            }

                        } else {
                            switch (piece) {
                                case wPawn:
                                    if (pawns.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true; // Black piece can block the check
                                    }
                                    break;
                                case wKnight:
                                    if (knight.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case wQueen:
                                    if (queen.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case wRook:
                                    if (rook.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case wBishop:
                                    if (bishops.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                            }

                        }
                    }
                }
                currentRow += rowDir;
                currentCol += colDir;
            }
        }

        // Check if the attacker is a bishop or queen (diagonal movement)
        if (Math.abs(rowChoose - kingRow) == Math.abs(colChoose - kingCol)) {
            int rowDir = (kingRow > rowChoose) ? 1 : -1;
            int colDir = (kingCol > colChoose) ? 1 : -1;

            // Loop through the squares between the attacking piece and the king
            int currentRow = rowChoose + rowDir;
            int currentCol = colChoose + colDir;
            while (currentRow != rowMove || currentCol != colMove) {
                // Loop through all the black pieces
                String blackPieceType = (isWhiteTurn) ? "B" : "W";
                for (int i = 0; i < chessboard.length; i++) {
                    for (int j = 0; j < chessboard[i].length; j++) {
                        String piece = chessboard[i][j];
                        if (piece.startsWith(blackPieceType)) {
                            // Check if the black piece can move to the current square
                            switch (piece) {
                                case bPawn:
                                    if (pawns.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true; // Black piece can block the check
                                    }
                                    break;
                                case bKnight:
                                    if (knight.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case bQueen:
                                    if (queen.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case bRook:
                                    if (rook.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case bBishop:
                                    if (bishops.isValidMoveForBlack(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                            }
                        } else {
                            switch (piece) {
                                case wPawn:
                                    if (pawns.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case wKnight:
                                    if (knight.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case wQueen:
                                    if (queen.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case wRook:
                                    if (rook.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                                case wBishop:
                                    if (bishops.isValidMoveForWhite(i, j, currentRow, currentCol)) {
                                        return true;
                                    }
                                    break;
                            }
                        }
                    }
                }
                currentRow += rowDir;
                currentCol += colDir;
            }
        }
        // If the attacker is neither rook, bishop, nor queen, it cannot be blocked.
        return false;
    }

    /**
     * Method that holds for the piece for pawn when reached the end
     */
    public List<String> whitePieces() {
        List<String> whitePieces = new ArrayList<>();
        whitePieces.add(wQueen);
        whitePieces.add(wKnight);
        whitePieces.add(wBishop);
        whitePieces.add(wRook);
        return whitePieces;
    }

    public List<String> blackPieces() {
        List<String> blackPieces = new ArrayList<>();
        blackPieces.add(bQueen);
        blackPieces.add(bKnight);
        blackPieces.add(bBishop);
        blackPieces.add(bRook);
        return blackPieces;
    }

}
