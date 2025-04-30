import java.util.Scanner;

public class UserPlayer extends Player {
    private String name;
    private Scanner input;

    public UserPlayer(Scanner input, String name) {
        this.input = input;
        this.name = name;
    }

    @Override
    public String toString() {
        // debug and console 
        return name;
    }

    @Override
    public TicTacToe chooseMove(TicTacToe board) {
        System.out.println("Current board:");
        System.out.println(board);
        
        TicTacToe[] options = board.possibleMoves(this);
        System.out.println("Possible moves:");
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + ":\n" + options[i]);
        }
        
        System.out.print("Enter your choice (0-" + (options.length - 1) + "): ");
        int choice = input.nextInt();
        return options[choice];
    }
}