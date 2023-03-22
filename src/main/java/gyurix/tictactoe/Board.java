package gyurix.tictactoe;

public class Board {
    private int[][] fields = new int[3][3];

    private boolean player2Turn;

    private void doMove(int playerId, int column, int row) throws InvalidMoveException {
        if (column < 0 || column > 2 || row < 0 || row > 2) {
            throw new InvalidMoveException("Incorrect column or row");
        }
        if (fields[row][column] != 0) {
            throw new InvalidMoveException("The given column & row combination is already occupied");
        }
        if (player2Turn && playerId != 2 || !player2Turn && playerId != 1) {
            throw new InvalidMoveException("The other player should move first");
        }
        player2Turn = !player2Turn;
        fields[row][column] = playerId;
    }

    /**
     * Verifies whether the game ended as a draw, by checking if the board is full.
     *
     * @return
     */
    public GameResult getGameResult() {
        if (isPlayerWin(1)) {
            return GameResult.PLAYER_1_WIN;
        }
        if (isPlayerWin(2)) {
            return GameResult.PLAYER_2_WIN;
        }
        if (isBoardFull()) {
            return GameResult.DRAW;
        }
        return GameResult.UNFINISHED_GAME;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                if (fields[row][col] == 0)
                    return false;
            }
        }
        return true;
    }

    public boolean isPlayer2Turn() {
        return player2Turn;
    }

    private boolean isPlayerWin(int playerId) {
        if (isPlayerWinByPiecesInTheSameColumn(playerId))
            return true;
        if (isPlayerWinByPiecesInTheSameRow(playerId))
            return true;
        return isPlayerWinByPiecesInCross(playerId);
    }

    private boolean isPlayerWinByPiecesInCross(int playerId) {
        if (fields[1][1] != playerId)
            return false;
        return (fields[0][0] == playerId && fields[2][2] == playerId) || (fields[0][2] == playerId && fields[2][0] == playerId);
    }

    private boolean isPlayerWinByPiecesInTheSameColumn(int playerId) {
        for (int column = 0; column < 3; ++column) {
            int score = 0;
            for (int row = 0; row < 3; ++row) {
                if (fields[row][column] == playerId)
                    ++score;
            }
            if (score == 3)
                return true;
        }
        return false;
    }

    private boolean isPlayerWinByPiecesInTheSameRow(int playerId) {
        for (int row = 0; row < 3; ++row) {
            int score = 0;
            for (int column = 0; column < 3; ++column) {
                if (fields[row][column] == playerId)
                    ++score;
            }
            if (score == 3)
                return true;
        }
        return false;
    }

    public void player1Step(int x, int y) throws InvalidMoveException {
        doMove(1, x, y);
    }

    public void player2Step(int column, int row) throws InvalidMoveException {
        doMove(2, column, row);
    }

    public void playerStep(int x, int y) throws InvalidMoveException {
        doMove(player2Turn ? 2 : 1, x, y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : fields) {
            for (int col : row) {
                sb.append(col);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
