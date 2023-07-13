/**
 *
 * Knight.java
 * @date 10/07/2023
 * @author Suresh
 * @version 1.0
 */
public class Knight extends Piece {

    public Knight(Board board) {
        super(board);
    }
    @Override
    public boolean isValidMoveForWhite(int rowChoose, int colChoose,
                                       int rowMove, int colMove) {

        //Check if its vertical 'L' or horizontal 'L'
        int rowDiff = Math.abs(rowMove - rowChoose);
        int colDiff = Math.abs(colMove - colChoose);

        //Logic to make a 'L' move
        if((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            return board.chessboard[rowMove][colMove].equals(" ")
                    || !board.stringArray().contains
                    (board.chessboard[rowMove][colMove]);
        }
        return false;
    }
    @Override
    public boolean isValidMoveForBlack ( int rowChoose, int colChoose,
                                         int rowMove, int colMove){

        //Check if its vertical 'L' or horizontal 'L'
        int rowDiff = Math.abs(rowMove - rowChoose);
        int colDiff = Math.abs(colMove - colChoose);

        //Logic to make a 'L' move
        if((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            return board.chessboard[rowMove][colMove].equals(" ")
                    || !board.stringArray().contains
                    (board.chessboard[rowMove][colMove]);
        }
        return false;
    }
}
