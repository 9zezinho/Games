import java.util.Objects;
import java.util.Scanner;

public class Pawns extends Board {

//    protected char diagonal = ' ';
//    protected boolean check = true;
    protected String wPawn = "W-Pawn";
    protected String bPawn = "B-Pawn";
    protected int plyrChoose;
    protected int plyrMove;
    protected int comp;
    @Override

    public void displayBoard(String[][] chessboard) {

        for (int i = 0; i < chessboard.length; i++){
            chessboard[1][i] = wPawn;
            chessboard[6][i] = bPawn;
        }
        System.out.println("==============================================" +
                "=========================");
        super.displayBoard(chessboard);

    }

    /**
     *
     * This is the move that this piece of chess can do
     *
     * @param chessboard is the 8x8 board.
     */
    @Override
    public void moves(String[][] chessboard) {
        displayBoard(chessboard);
        Scanner in = new Scanner(System.in);
        System.out.println("Please choose the number you want to move:");
        plyrChoose = in.nextInt();
        in.nextLine(); //consume remaining new line character

        int rowChoose = (plyrChoose - 1) / 8;
        int colChoose = (plyrChoose - 1) % 8;

        chessboard[rowChoose][colChoose] = " ";

        System.out.println("Please choose the number where you want to move:");
        plyrMove = in.nextInt();
        int row = (plyrMove - 1) / 8;
        int col = (plyrMove - 1) % 8;

        if(Objects.equals(chessboard[row][col], " ")) { //checking if the front space only is empty
            if (plyrChoose >=9 && plyrChoose <=16){
                chessboard[row][col] = wPawn;
            } else if (plyrChoose >= 56 && plyrChoose <= 64) {
                chessboard[row][col] = bPawn;
            }
        }
        super.displayBoard(chessboard);
    }

}
