import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String [][] chessboard =
                {{"      ","      ","      ","      ","      ","      ","      ","      "},
                        {"      ","      ","      ","      ","      ","      ","      ","      "},
                        {"      ","      ","      ","      ","      ","      ","      ","      "},
                        {"      ","      ","      ","      ","      ","      ","      ","      "},
                        {"      ","      ","      ","      ","      ","      ","      ","      "},
                        {"      ","      ","      ","      ","      ","      ","      ","      "},
                        {"      ","      ","      ","      ","      ","      ","      ","      "},
                        {"      ","      ","      ","      ","      ","      ","      ","      "}};
        Board board = new Pawns();
        board.moves(chessboard);



    }

}