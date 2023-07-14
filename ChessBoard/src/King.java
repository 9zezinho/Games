/**
 *
 * King.java
 * @date 14/07/2023
 * @author Suresh
 * @version 1.0
 */
public class King extends Piece{
    public King(Board board) {
        super(board);
    }
    @Override
    public boolean isValidMoveForWhite(int rowChoose, int colChoose,
                                       int rowMove, int colMove) {
        if (Math.abs(rowMove - rowChoose) == 1 || Math.abs(colMove - colChoose) == 1) {
            if(!board.chessboard[rowMove][colMove].equals("      ")){
                return false;
            }
            return !board.chessboard[rowMove][colMove].equals(" ")
                    && !board.stringArray().contains(board.chessboard[rowMove][colMove]);
        }
        return false;
    }

    @Override
    public boolean isValidMoveForBlack(int rowChoose, int colChoose,
                                       int rowMove, int colMove) {
        if (Math.abs(rowMove - rowChoose) == 1 || Math.abs(colMove - colChoose) == 1) {
            if(!board.chessboard[rowMove][colMove].equals("      ")){
                return false;
            }
            return !board.chessboard[rowMove][colMove].equals(" ")
                    && board.stringArray().contains(board.chessboard[rowMove][colMove]);
        }
        return false;
    }
}
