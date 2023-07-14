/**
 *
 * Queen.java
 * @date 13/07/2023
 * @author Suresh
 * @version 1.0
 */

public class Queen extends Piece{
    public Queen(Board board) {
        super(board);
    }
    @Override
    public boolean isValidMoveForWhite(int rowChoose, int colChoose,
                                       int rowMove, int colMove) {
        int rowDir = (rowMove > rowChoose) ? 1 : -1;
        int colDir = (colMove > colChoose) ? 1 : -1;
        int currentRow = rowChoose + rowDir;
        int currentCol = colChoose + colDir;

        //Move horizontal or vertical or diagonally
        if(rowChoose == rowMove || colChoose == colMove
                || Math.abs(rowChoose - rowMove) == Math.abs(colChoose - colMove)){

            //Vertical movement
            if (colMove == colChoose) {
                while(currentRow != rowMove) {
                    if (!board.chessboard[currentRow][currentCol].equals("      ")) {
                        return false;
                    }
                    currentRow += rowDir;
                }
            } else if (rowMove == rowChoose) { //Horizontal movement
                while(currentCol != colMove) {
                    if (!board.chessboard[currentRow][currentCol].equals("      ")) {
                        return false;
                    }
                    currentCol += colDir;
                }
            } else { //Diagonal movement
                while (currentRow != rowMove && currentCol != colMove) {
                    if (!board.chessboard[currentRow][currentCol].equals("      ")) {
                        return false;
                    }
                    currentRow += rowDir;
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
    public boolean isValidMoveForBlack(int rowChoose, int colChoose,
                                       int rowMove, int colMove) {
        int rowDir = (rowMove > rowChoose) ? 1 : -1;
        int colDir = (colMove > colChoose) ? 1 : -1;
        int currentRow = rowDir + rowChoose;
        int currentCol = colDir + colChoose;

        if(rowChoose == rowMove || colChoose == colMove ||
                Math.abs(rowChoose - rowMove) == Math.abs(colChoose - colMove)){
            //Vertical movement
            if(colMove == colChoose) {
                while(currentRow != rowMove) {
                    if(!board.chessboard[currentRow][currentCol].
                            equals("      ")){
                        return false;
                    }
                    currentRow += rowDir;
                }

            } else if (rowChoose == rowMove){ //Horizontal movement
                while(currentCol != colMove) {
                    if(!board.chessboard[currentRow][currentCol].
                            equals("      ")) {
                        return false;
                    }
                    currentCol += colDir;
                }
            } else { //Diagonal movement
                while(currentRow != rowMove && currentCol != colMove) {
                    if(!board.chessboard[currentRow][currentCol].equals("      ")){
                        return false;
                    }
                    currentRow += rowDir;
                    currentCol += colDir;
                }
            }
            //Catch other player horizontally or vertically or diagonally
            if(!board.chessboard[rowMove][colMove].equals(" ")
                    && board.stringArray().contains(board.chessboard[rowMove][colMove])){
                return true;
            }
            //when moving one step for black piece
            if(currentRow == rowMove && currentCol == colMove) {
                return board.chessboard[currentRow][currentCol].equals("      ");
            }
        }
        return false;
    }
}
