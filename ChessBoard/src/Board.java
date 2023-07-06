public class Board {

//    protected char [][] chessboard = {{' ',' ',' ',' ',' ',' ',' ',' '},
//            {' ',' ',' ',' ',' ',' ',' ',' '}, {' ',' ',' ',' ',' ',' ',' ',' '}
//            ,{' ',' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' ',' '},
//            {' ',' ',' ',' ',' ',' ',' ',' '},{' ',' ',' ',' ',' ',' ',' ',' '}
//            ,{' ',' ',' ',' ',' ',' ',' ',' '}};

    public Board(){

    }

    /**
     * Method to display the chess board.
     * @param chessboard is the 8x8 grid board.
     */
    public void displayBoard(char[][] chessboard) {

        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length-1; j++) {
                System.out.print(chessboard[i][j] + " | ");
            }
            System.out.println("\n- + - + - + - + - + - + - + -");
        }
    }
}
