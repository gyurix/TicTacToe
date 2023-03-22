package gyurix.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        while (board.getGameResult() == GameResult.UNFINISHED_GAME) {
            try {
                System.out.println(board);
                System.out.println(board.isPlayer2Turn() ? "Player 2 turn" : "Player 1 turn");
                System.out.println("Enter X Y:");
                String[] coords = scanner.nextLine().split(" ");
                board.playerStep(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        System.out.println(board.getGameResult());
    }
}