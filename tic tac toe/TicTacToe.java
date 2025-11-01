import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name for Player 1 (X): ");
        String name1 = sc.nextLine().trim();
        if (name1.isEmpty()) name1 = "Player1";

        System.out.print("Enter name for Player 2 (O): ");
        String name2 = sc.nextLine().trim();
        if (name2.isEmpty()) name2 = "Player2";

        Player player1 = new Player(name1, 'X');
        Player player2 = new Player(name2, 'O');

        Game game = new Game(player1, player2);

        while (true) {
            printBoard(game.getBoard().getBoard());
            Player current = game.getCurrentPlayer();
            System.out.println(current.getName() + "'s turn (" + current.getSymbol() + ")");

            int row = -1, col = -1;
            boolean moved = false;

            while (!moved) {
                try {
                    System.out.print("Enter row (0-2): ");
                    row = sc.nextInt();
                    System.out.print("Enter column (0-2): ");
                    col = sc.nextInt();

                    moved = game.makeMove(row, col);
                } catch (InputMismatchException ime) {
                    System.out.println("Please enter integer values between 0 and 2.");
                    sc.next(); // clear bad token
                }
            }

            if (game.checkWin()) {
                printBoard(game.getBoard().getBoard());
                System.out.println(current.getName() + " wins! Congratulations!");
                break;
            }

            if (game.isDraw()) {
                printBoard(game.getBoard().getBoard());
                System.out.println("Game is a draw!");
                break;
            }

            game.switchTurn();
        }

        sc.close();
    }

    // Nicely prints the board
    private static void printBoard(char[][] b) {
        System.out.println();
        System.out.println("  0   1   2");
        for (int i = 0; i < b.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j] == ' ' ? "-" : b[i][j]);
                if (j < b[i].length - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < b.length - 1) System.out.println("  ---------");
        }
        System.out.println();
    }
}
