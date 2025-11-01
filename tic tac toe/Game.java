public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.board = new Board();
        this.currentPlayer = player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean makeMove(int row, int col) {
        if (board.placeMove(row, col, currentPlayer.getSymbol())) {
            return true;
        }
        return false;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean checkWin() {
        return board.checkWin(currentPlayer.getSymbol());
    }

    public boolean isDraw() {
        return board.isDraw();
    }

    public Board getBoard() {
        return board;
    }
}
