
import java.util.Scanner;
class AIPlayer extends Player {
    private String name;
    private Player opponent;

    public AIPlayer(String name, Player opponent) {
        this.name = name;
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    @Override
    public String toString() {
        return name + " (AI)";
    }

    // Recursive method to find maximum value move
    public double maxValue(TicTacToe board) {
        // Base cases
        if (board.checkWin(this)) return 1.0;
        if (board.checkLose(this)) return -1.0;
        if (board.checkDraw()) return 0.0;
        
        double max = -2.0;
        TicTacToe[] moves = board.possibleMoves(this);
        
        for (TicTacToe move : moves) {
            double value = minValue(move);
            if (value > max) {
                max = value;
            }
        }
        
        return max;
    }

    // Recursive method to find minimum value move
    public double minValue(TicTacToe board) {
        // Base cases
        if (board.checkWin(this)) return 1.0;
        if (board.checkLose(this)) return -1.0;
        if (board.checkDraw()) return 0.0;
        
        double min = 2.0;
        TicTacToe[] moves = board.possibleMoves(opponent);
        
        for (TicTacToe move : moves) {
            double value = maxValue(move);
            if (value < min) {
                min = value;
            }
        }
        
        return min;
    }

    @Override
    public double boardValue(TicTacToe board) {
        return maxValue(board);
    }

    @Override
    public TicTacToe chooseMove(TicTacToe board) {
        TicTacToe[] moves = board.possibleMoves(this);
        TicTacToe bestMove = null;
        double bestValue = -2.0;
        
        for (TicTacToe move : moves) {
            double value = minValue(move);
            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }
        
        return bestMove;
    }
}