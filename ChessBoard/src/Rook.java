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
            int rowDir = (rowMove > rowChoose) ? 1 : -1;
            int colDir = (colMove > colChoose) ? 1 : -1;
            int currentRow = rowChoose + rowDir;
            int currentCol = colChoose + colDir;

            //Vertical movement
            if (rowMove != rowChoose) {
                while(currentRow != rowMove) {
                    if (!board.chessboard[currentRow][currentCol].equals("      ")) {
                        return false;
                    }
                    currentRow += rowDir;
                }
            } else if (colMove == rowChoose) { //Horizontal movement
                while(currentCol != colMove) {
                    if (!board.chessboard[currentRow][currentCol].equals("      ")) {
                        return false;
                    }
                    currentCol += colDir;
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
            int rowDir = (rowMove > rowChoose) ? 1 : -1;
            int colDir = (colMove > colChoose) ? 1 : -1;
            int currentRow = rowDir + rowChoose;
            int currentCol = colDir + colChoose;

            //Vertical movement
            if(rowMove == colChoose) {
                while(currentRow != rowMove) {
                    if(!board.chessboard[currentRow][currentCol].
                            equals("      ")){
                        return false;
                    }
                    currentRow += rowDir;
                }
            } else if (colMove == rowChoose) { //Horizontal movement
                while(currentCol != colMove) {
                    if(!board.chessboard[currentRow][currentCol].
                            equals("      ")) {
                        return false;
                    }
                    currentCol += colDir;
                }
            }
            //Catch other player horizontally or vertically
            return(!board.chessboard[rowMove][colMove].equals(" ")
                    && board.stringArray().contains(board.chessboard[rowMove][colMove]));
        }
        return false;
    }
}
