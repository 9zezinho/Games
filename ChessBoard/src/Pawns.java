import java.util.Scanner;

public class Pawns extends Board {

//    protected char diagonal = ' ';
//    protected boolean check = true;
    protected char pawn = 'P';
    protected int plyr;
    protected int comp;
    @Override
    public void displayBoard(char[][] chessboard) {

        for (int i = 0; i < chessboard.length; i++){
            chessboard[1][i] = pawn;
            chessboard[6][i] = pawn;
        }
        System.out.println("=============================");
        super.displayBoard(chessboard);

    }

    /**
     *
     * This is the move that this piece of chess can do
     *
     * @param chessboard is the 8x8 board.
     */
    @Override
    public void moves(char[][] chessboard) {
        displayBoard(chessboard);
        Scanner in = new Scanner(System.in);
        System.out.println("Please choose the number:");
        plyr = in.nextInt();

        int row = (plyr - 1) / 8;
        int col = (plyr - 1) % 8;

        if(chessboard[row][col] == ' '){
            chessboard[row][col] = pawn;
        }
        super.displayBoard(chessboard);
    }

}
