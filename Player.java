import java.util.Scanner;
abstract class Player {
    // Abstract method to choose a move
    public abstract TicTacToe chooseMove(TicTacToe board);
    
    // Default board value implementation
    public double boardValue(TicTacToe board) {
        if (board.checkWin(this)) {
            return 1.0;
        } else if (board.checkLose(this)) {
            return -1.0;
        } else {
            return 0.0;
        }
    }
}