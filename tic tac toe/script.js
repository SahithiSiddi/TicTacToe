let player1, player2, game;

document.getElementById("startGame").addEventListener("click", () => {
    const name1 = document.getElementById("player1").value || "Player 1";
    const name2 = document.getElementById("player2").value || "Player 2";

    player1 = { name: name1, symbol: "X" };
    player2 = { name: name2, symbol: "O" };

    game = {
        board: [
            ["", "", ""],
            ["", "", ""],
            ["", "", ""]
        ],
        currentPlayer: player1
    };

    document.getElementById("player-inputs").classList.add("hidden");
    document.getElementById("gameBoard").classList.remove("hidden");
    document.getElementById("message").innerText = `${game.currentPlayer.name}'s turn`;
});

const cells = document.querySelectorAll(".cell");
cells.forEach(cell => {
    cell.addEventListener("click", () => {
        const row = cell.dataset.row;
        const col = cell.dataset.col;

        if (game.board[row][col] === "") {
            game.board[row][col] = game.currentPlayer.symbol;
            cell.innerText = game.currentPlayer.symbol;

            if (checkWin(game.currentPlayer.symbol)) {
                document.getElementById("message").innerText = `${game.currentPlayer.name} wins!`;
                disableBoard();
                return;
            } else if (isDraw()) {
                document.getElementById("message").innerText = "It's a draw!";
                return;
            }

            game.currentPlayer = (game.currentPlayer === player1) ? player2 : player1;
            document.getElementById("message").innerText = `${game.currentPlayer.name}'s turn`;
        }
    });
});

function checkWin(symbol) {
    const b = game.board;
    for (let i = 0; i < 3; i++)
        if (b[i][0] === symbol && b[i][1] === symbol && b[i][2] === symbol) return true;
    for (let j = 0; j < 3; j++)
        if (b[0][j] === symbol && b[1][j] === symbol && b[2][j] === symbol) return true;
    if (b[0][0] === symbol && b[1][1] === symbol && b[2][2] === symbol) return true;
    if (b[0][2] === symbol && b[1][1] === symbol && b[2][0] === symbol) return true;
    return false;
}

function isDraw() {
    return game.board.flat().every(cell => cell !== "");
}

function disableBoard() {
    cells.forEach(cell => cell.style.pointerEvents = "none");
}
