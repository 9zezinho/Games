public abstract class  Board {
    public Board(){

    }

    /**
     *
     * Method to display the chess board.
     * @param chessboard is the 8x8 grid board.
     */
    public void displayBoard(String[][] chessboard) {

        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                System.out.print(chessboard[i][j] + " | ");
            }
            System.out.println("\n-------+--------+--------+--------" +
                    "+--------+--------+--------+--------");
        }
    }

    /**
     * This is the move that this piece of chess can do
     * @param chessboard is the 8x8 board.
     */
    public abstract void moves(String[][] chessboard);
}
