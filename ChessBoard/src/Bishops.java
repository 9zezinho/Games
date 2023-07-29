/**
 *
 * Bishops.java
 * @date 10/07/2023
 * @author Suresh
 * @version 1.0
 */

/**
 * This class represents the movement of the Bishop piece in the board
 */
public class Bishops extends Piece{
    public Bishops(Board board) {
        super(board);
    }

    @Override
    public boolean isValidMoveForWhite(int rowChoose, int colChoose,
                                       int rowMove, int colMove) {
        if(Math.abs(rowChoose - rowMove) == Math.abs(colChoose - colMove)){

            //Moving diagonally
            int rowDir = (rowMove > rowChoose) ? 1 : -1;
            int colDir = (colMove > colChoose) ? 1 : -1;

            //Check if there is any obstacles in the path
            int currentRow = rowChoose + rowDir;
            int currentCol = colChoose + colDir;
            while(currentRow != rowMove && currentCol != colMove) {
                if(!board.chessboard[currentRow][currentCol].
                        equals("      ")){
                    return false;
                }
                currentRow += rowDir;
                currentCol += colDir;
            }
            //Catch other player piece diagonally.
            return !board.chessboard[rowMove][colMove].equals(" ")
                    &&  !board.stringArray().
                    contains(board.chessboard[rowMove][colMove]);
        }
        return false;
    }

    @Override
    public boolean isValidMoveForBlack(int rowChoose, int colChoose,
                                       int rowMove, int colMove) {
        if(Math.abs(rowChoose - rowMove) == Math.abs(colChoose - colMove)){

            //Moving diagonally
            int rowDir = (rowMove > rowChoose) ? 1 : -1;
            int colDir = (colMove > colChoose) ? 1 : -1;

            //Check if there is any obstacles in the path
            int currentRow = rowChoose + rowDir;
            int currentCol = colChoose + colDir;
            while(currentRow != rowMove && currentCol != colMove) {
                if(!board.chessboard[currentRow][currentCol].
                        equals("      ")){
                    return false;
                }
                currentRow += rowDir;
                currentCol += colDir;
            }
            //Catch other player piece diagonally.
            if(!board.chessboard[rowMove][colMove].equals(" ")
                    &&  board.stringArray().
                    contains(board.chessboard[rowMove][colMove])){
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
