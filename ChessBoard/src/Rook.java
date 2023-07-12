/**
 *
 * Rook.java
 * @date 12/07/2023
 * @author Suresh
 * @version 1.0
 */

public class Rook extends Piece{
    public Rook(Board board) {
        super(board);
    }
    @Override
    public boolean isValidMoveForWhite(int rowChoose, int colChoose, int rowMove, int colMove) {
        //Move horizontal or vertical only
        if(rowChoose == rowMove || colChoose == colMove){
            int currentRow = (rowMove > rowChoose) ? 1 : -1;
            int currentCol = (colMove > colChoose) ? 1 : -1;

            //Vertical movement
            if (rowMove != rowChoose) {
                int i = currentRow + rowChoose;
                while(i != rowMove) {
                    if (!board.chessboard[rowMove][colMove].equals("      ")) {
                        return false;
                    }
                    i += currentRow;
                }
            } else { //Horizontal movement
                int i = currentCol + colChoose;
                while(i != colMove) {
                    if (!board.chessboard[rowMove][colMove].equals("      ")) {
                        return false;
                    }
                    i += currentCol;
                }
            }
            //Catch other player horizontally or vertically
            return(!board.chessboard[rowMove][colMove].equals(" ")
                    && !board.stringArray().contains(board.chessboard[rowMove][colMove]));
        }
        return false;
    }

    @Override
    public boolean isValidMoveForBlack(int rowChoose, int colChoose, int rowMove, int colMove) {
        if(rowChoose == rowMove || colChoose == colMove){
            int currentRow = (rowMove > rowChoose) ? 1 : -1;
            int currentCol = (colMove > colChoose) ? 1 : -1;

            //Vertical movement
            if (rowMove != rowChoose) {
                int i = currentRow + rowChoose;
                while(i != rowMove) {
                    if (!board.chessboard[rowMove][colMove].equals("      ")) {
                        return false;
                    }
                    i += currentRow;
                }
            } else { //Horizontal movement
                int i = currentCol + colChoose;
                while(i != colMove) {
                    if (!board.chessboard[rowMove][colMove].equals("      ")) {
                        return false;
                    }
                    i += currentCol;
                }
            }
            //Catch other player horizontally or vertically
            return(!board.chessboard[rowMove][colMove].equals(" ")
                    && !board.stringArray().contains(board.chessboard[rowMove][colMove]));
        }
        return false;
    }
}
