    /**
     *
     * Pawns.java
     * @date 10/07/2023
     * @author Suresh
     * @version 1.0
     */
    public class Pawns extends Piece{
        public Pawns(Board board) {
            super(board);
        }

        public boolean isValidMoveForWhite(int rowChoose, int colChoose,
                                            int rowMove, int colMove) {
            if (rowChoose == 1) {
                if(colChoose == colMove) {

                    //can move one or two-step from the 1st row
                    return (rowMove == rowChoose + 1 || rowMove == rowChoose + 2)
                            && board.chessboard[rowMove][colMove].equals("      ");

                    //catch the other player diagonally.
                }else if((colMove - colChoose == 1 || colMove - colChoose == -1)
                        && !board.chessboard[rowMove][colMove].equals(" ")){
                    return !board.stringArray().contains(board.chessboard[rowMove][colMove]);

                }
            } else if (rowChoose > 1) {
                if(colChoose == colMove) {
                    return (rowMove == rowChoose + 1)
                            && board.chessboard[rowMove][colMove].equals("      ");

                    //catch the other player diagonally.
                }else if ((colMove - colChoose == 1) || (colMove - colChoose == -1)
                        && !board.chessboard[rowMove][colMove].equals(" ")) {
                    return !board.stringArray().contains(board.chessboard[rowMove][colMove]);
                };
            }
            return false;
        }
        public boolean isValidMoveForBlack(int rowChoose, int colChoose,int rowMove, int colMove) {

            if (rowChoose == 6) {
                if(colChoose == colMove) {

                    //can move one or two-step from the 1st row
                    return (rowMove == rowChoose - 1 || rowMove == rowChoose - 2)
                            && board.chessboard[rowMove][colMove].equals("      ");

                    //catch the other player diagonally.
                }else if((colMove - colChoose == 1 || colMove - colChoose == -1)
                        && !board.chessboard[rowMove][colMove].equals(" ")){
                    return board.stringArray().contains(board.chessboard[rowMove][colMove]);

                }
            } else if (rowChoose < 6) {
                if(colChoose == colMove) {
                    return (rowMove == rowChoose - 1)
                            && board.chessboard[rowMove][colMove].equals("      ");

                    //catch the other player diagonally.
                }else if((colMove - colChoose == 1 || colMove - colChoose == -1)
                        && !board.chessboard[rowMove][colMove].equals(" ")){
                    return board.stringArray().contains(board.chessboard[rowMove][colMove]);
                }
            }

            //if the pawn reaches to the other side then it can replace with other piece of the board//
            return false;
        }
    }
