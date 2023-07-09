public class Pawns extends Board {
    private final String wPawn = "W-Pawn";
    private final String bPawn = "B-Pawn";

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


        displayBoard(chessboard);
        do {
            //handles the inputs
            InputHandler handler = InputHandler.movePosition();
            int rowChoose = handler.getRowChoose();
            int colChoose = handler.getColChoose();
            int rowMove = handler.getRowMove();
            int colMove = handler.getColMove();

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
        if (rowChoose == 1) {
            if(colChoose == colMove) {

                //can move one or two-step from the 1st row
                return (rowMove == rowChoose + 1 || rowMove == rowChoose + 2)
                        && chessboard[rowMove][colMove].equals("      ");

                //catch the other player diagonally.
            }else return (colMove - colChoose == 1 || colMove - colChoose == -1)
                    && !chessboard[rowMove][colMove].equals(" ");
        } else if (rowChoose > 1) {
            if(colChoose == colMove) {
                return (rowMove == rowChoose + 1)
                        && chessboard[rowMove][colMove].equals("      ");

                //catch the other player diagonally.
            }else return (colMove - colChoose == 1) || (colMove - colChoose == -1)
                    && !chessboard[rowMove][colMove].equals(" ");
        }
        return false;
    }
    private boolean isValidMoveForBlack(int rowChoose, int colChoose,int rowMove, int colMove) {

        if (rowChoose == 6) {
            if(colChoose == colMove) {

                //can move one or two-step from the 1st row
                return (rowMove == rowChoose - 1 || rowMove == rowChoose - 2)
                        && chessboard[rowMove][colMove].equals("      ");

                //catch the other player diagonally.
            }else return (colMove - colChoose == 1 || colMove - colChoose == -1)
                    && !chessboard[rowMove][colMove].equals(" ");
        } else if (rowChoose < 6) {
            if(colChoose == colMove) {
                return (rowMove == rowChoose - 1)
                        && chessboard[rowMove][colMove].equals("      ");

                //catch the other player diagonally.
            }else return (colMove - colChoose == 1 || colMove - colChoose == -1)
                    && !chessboard[rowMove][colMove].equals(" ");
        }

        //if the pawn reaches to the other side then it can replace with other piece of the board//
        return false;
    }
}
