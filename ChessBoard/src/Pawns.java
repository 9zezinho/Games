
import java.util.Scanner;

public class Pawns extends Board {

    protected String wPawn = "W-Pawn";
    protected String bPawn = "B-Pawn";
    protected int plyrChoose;
    protected int plyrMove;

    @Override

    public void displayBoard(String[][] chessboard) {

        for (int i = 0; i < chessboard.length; i++){
            chessboard[1][i] = wPawn;
            chessboard[6][i] = bPawn;
        }
        System.out.println("==============================================" +
                "=========================");
        super.displayBoard(chessboard);
    }

    /**
     *
     * This is the move that this piece of chess can do
     *
     * @param chessboard is the 8x8 board.
     */
    @Override
    public void moves(String[][] chessboard) {
        boolean isWhiteTurn = true; // Flag to track the current player's turn
        int rowMove;
        int rowChoose;
        int colChoose;
        int colMove;

        displayBoard(chessboard);
        do {
            Scanner in = new Scanner(System.in);
            do {
                System.out.println("Please choose the number you want to move:");
                while(!in.hasNextInt()) {
                    System.out.println("Error! invalid input");
                    System.out.println("Please choose the number you want to move:");
                    in.next();
                }
                plyrChoose = in.nextInt();
                in.nextLine(); // Consume remaining new line character

                System.out.println("Please choose the number where you want to move:");
                while (!in.hasNextInt()) {
                    System.out.println("Error! invalid input");
                    System.out.println("Please choose the number where you want to move:");
                    in.next();
                }
                plyrMove = in.nextInt();
                in.nextLine();
                if (plyrChoose > 64 || plyrMove > 64) {
                    System.out.println("Error! you number shouldn't exceed 64");
                }
            } while (plyrChoose > 64 || plyrMove > 64);

            rowChoose = (plyrChoose - 1) / 8;
            colChoose = (plyrChoose - 1) % 8;

            rowMove = (plyrMove - 1) / 8;
            colMove = (plyrMove - 1) % 8;

            String sourcePiece = chessboard[rowChoose][colChoose];

            // Check if the current player can move the chosen piece
            if ((isWhiteTurn && sourcePiece.equals(wPawn)) || (!isWhiteTurn && sourcePiece.equals(bPawn))) {
                boolean isValidMove;
                if (isWhiteTurn) {
                    isValidMove = isValidMoveForWhite(rowChoose, colChoose, rowMove, colMove);
                } else {
                    isValidMove = isValidMoveForBlack(rowChoose, colChoose, rowMove, colMove);
                }

                if (isValidMove) {
                    chessboard[rowChoose][colChoose] = "      ";
                    chessboard[rowMove][colMove] = sourcePiece;
                    isWhiteTurn = !isWhiteTurn; // Switch turns to the other player
                } else {
                    System.out.println("Invalid move for the pawn");
                }
            } else {
                System.out.println("Invalid move! You can only move your pawns");
            }

            super.displayBoard(chessboard);
        } while (true);
    }
    private boolean isValidMoveForWhite(int rowChoose, int colChoose,
                                        int rowMove, int colMove) {
        //first white row has the option for the pawn to move two steps.
        if(rowChoose == 1) {
            return ((rowMove == rowChoose + 1) || (rowMove == rowChoose + 2))
                    && colMove == colChoose;
        }else if (rowChoose > 1) { //only one steps after this.
            return rowMove == rowChoose + 1 && colMove == colChoose;
            //change piece if the rowMove moves to the row 7//
        }
        return false;
    }
    private boolean isValidMoveForBlack(int rowChoose, int colChoose,int rowMove, int colMove) {
        //first black row has the option for the pawn to move two steps.
        if(rowChoose == 6) {
            return ((rowMove == rowChoose - 1) || (rowMove == rowChoose - 2))
                    && colMove == colChoose;
        }else if (rowChoose < 6) { //only one steps after this.
            return rowMove == rowChoose + 1 && colMove == colChoose;
            //change piece if the rowMove moves to the row 0//
        }
        return false;
    }

}
