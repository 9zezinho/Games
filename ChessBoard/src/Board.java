import java.util.Scanner;

public abstract class  Board {
    protected final String [][] chessboard =
            {{"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "},
                    {"      ","      ","      ","      ","      ","      ","      ","      "}};
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

//    public void chooseNum(String[][] chessboard){
//        int plyr1Choose = 0;
//        int plyr1Move = 0;
//        int plyr2Choose = 0;
//        int plyr2Move = 0;
//
//        Scanner in = new Scanner(System.in);
//
//        //forces player to choose their own side.
//        while(true) {
//            System.out.println("Plyr1 choose a number to move:");
//            while(!in.hasNext()) {
//                System.out.println("Please input the number:");
//                in.next();//discard the invalid input
//            }
//            plyr1Choose = in.nextInt();
//
//            if (plyr1Choose >= 49 && plyr1Choose <= 64) {
//                System.out.println("Plyr1 choose a number where you wan to move:");
//                plyr1Move = in.nextInt();
//                Pawns pawns = new Pawns();
//                pawns.chooseNum(chessboard);
//                break;
//            } else {
//                System.out.println("Please choose the number between 49 and 64 at first to move");
//            }
//        }
//
//        while(true) {
//            System.out.println("Plyr1 choose a number to move:");
//            while(!in.hasNext()) {
//                System.out.println("Please input the number:");
//                in.next();//discard the invalid input
//            }
//            plyr2Choose = in.nextInt();
//
//            if (plyr2Choose >= 9 && plyr2Choose <= 16) {
//                System.out.println("Plyr1 choose a number where you want to move:");
//                plyr2Move = in.nextInt();
//                Pawns pawns = new Pawns();
//                pawns.chooseNum(chessboard);
//                break;
//            } else {
//                System.out.println("Please choose the number between 49 and 64 at first to move");
//            }
//        }
//
//
//
//
//
//    }

    /**
     * This is the move that this piece of chess can do
     * @param chessboard is the 8x8 board.
     */
    public abstract void moves(String[][] chessboard);
}
