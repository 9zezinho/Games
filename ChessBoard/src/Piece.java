/**
 * Piece.java
 * @date 10/07/2023
 * @author Suresh
 * @version 1.0
 */

public abstract class  Piece{
    Board board;
    public Piece(Board board){
        this.board = board;
    }
    public abstract  boolean isValidMoveForWhite(int rowChoose,int colChoose,
                                                 int rowMove, int colMove);
    public abstract  boolean isValidMoveForBlack(int rowChoose,int colChoose,
                                                 int rowMove, int colMove);
}