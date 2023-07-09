import java.util.Scanner;
public class InputHandler {
    private static final String CHOOSE_NUM =
            "Please choose the number you want to move:";

    private static final String MOVE_NUM =
            "Please choose the number where you want to move:";
    private static final String ERROR = "Error! invalid input";
    private static int rowChoose;
    private static  int colChoose;
    private static int rowMove;
    private static int colMove;

    public InputHandler(int rowChoose, int colChoose,
                        int rowMove, int colMove) {
        InputHandler.colChoose = colChoose;
        InputHandler.rowChoose = rowChoose;
        InputHandler.rowMove = rowMove;
        InputHandler.colMove = colMove;
    }

    public static InputHandler movePosition() {
        Scanner in = new Scanner(System.in);
        int plyrChoose;
        int plyrMove;

        do {
                System.out.println(CHOOSE_NUM);
                while(!in.hasNextInt()) {
                    System.out.println(ERROR);
                    System.out.println(CHOOSE_NUM);
                    in.next();
                }
                plyrChoose = in.nextInt();
                in.nextLine(); // Consume remaining new line character

                System.out.println(MOVE_NUM);
                while (!in.hasNextInt()) {
                    System.out.println(ERROR);
                    System.out.println(MOVE_NUM);
                    in.next();
                }
                plyrMove = in.nextInt();
                in.nextLine();
                if (plyrChoose > 64 || plyrMove > 64) {
                    System.out.println("Error! Both number shouldn't exceed 64");
                }
            } while (plyrChoose > 64 || plyrMove > 64);

        rowChoose = (plyrChoose - 1) / 8;
        colChoose = (plyrChoose - 1) % 8;

        rowMove = (plyrMove -1) / 8;
        colMove = (plyrMove -1) % 8;

        return new InputHandler(rowChoose, colChoose,rowMove,colMove);
    }

    public int getRowChoose() {
        return rowChoose;
    }
    public int getColChoose() {
        return colChoose;
    }
    public int getRowMove() {
        return rowMove;
    }
    public int getColMove(){
        return colMove;
    }

}
