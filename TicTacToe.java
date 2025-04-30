import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private Player x;
    private Player o;

    // Constructor initializes board and players
    public TicTacToe(Player x, Player o) {
        this.x = x;
        this.o = o;
        this.board = new char[3][3];
        initializeBoard();
    }

    // Initialize board with empty spaces
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_';
            }
        }
    }

    // Getters and setters
    public char[][] getBoard() {
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    public void setBoard(char[][] board) {
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, 3);
        }
    }

    public Player getX() {
        return x;
    }

    public void setX(Player x) {
        this.x = x;
    }

    public Player getO() {
        return o;
    }

    public void setO(Player o) {
        this.o = o;
    }

    // Count blank spaces on the board
    public int countBlanks() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    count++;
                }
            }
        }
        return count;
    }

    // Get marker character for a player
    public char markerForPlayer(Player player) {
        return (player == x) ? 'X' : 'O';
    }

    // Check if player has won
    public boolean checkWin(Player player) {
        char marker = markerForPlayer(player);
        
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            // Check row i
            if (board[i][0] == marker && board[i][1] == marker && board[i][2] == marker) {
                return true;
            }
            // Check column i
            if (board[0][i] == marker && board[1][i] == marker && board[2][i] == marker) {
                return true;
            }
        }
        
        // Check diagonals
        if (board[0][0] == marker && board[1][1] == marker && board[2][2] == marker) {
            return true;
        }
        return board[0][2] == marker && board[1][1] == marker && board[2][0] == marker;
    }

    // Check if player has lost
    public boolean checkLose(Player player) {
        Player opponent = (player == x) ? o : x;
        return checkWin(opponent);
    }

    // Check if game is a draw
    public boolean checkDraw() {
        return countBlanks() == 0 && !checkWin(x) && !checkWin(o);
    }

    // String representation of the board
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    // Get all possible next moves for a player
    public TicTacToe[] possibleMoves(Player player) {
        int blanks = countBlanks();
        if (blanks == 0) return new TicTacToe[0];
        
        TicTacToe[] moves = new TicTacToe[blanks];
        char marker = markerForPlayer(player);
        int index = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    // Create new board with this move
                    char[][] newBoard = getBoard();
                    newBoard[i][j] = marker;
                    TicTacToe newGame = new TicTacToe(x, o);
                    newGame.setBoard(newBoard);
                    moves[index++] = newGame;
                }
            }
        }
        
        return moves;
    }
}