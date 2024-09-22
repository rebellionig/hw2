package shadenade62.javahomework.lec3;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeAI {
    private char[][] board;
    private char currentPlayer;
    private boolean isSinglePlayer;

    public TicTacToeAI(boolean isSinglePlayer) {
        board = new char[3][3];
        currentPlayer = 'X'; // X starts first
        this.isSinglePlayer = isSinglePlayer;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void displayBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            System.out.print(" ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----");
        }
    }

    public void playGame() {
        int moves = 0;
        while (true) {
            displayBoard();
            if (currentPlayer == 'X' || !isSinglePlayer) {
                System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
                int row = new Scanner(System.in).nextInt();
                int col = new Scanner(System.in).nextInt();

                if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') {
                    System.out.println("This move is not valid. Try again.");
                    continue;
                }

                board[row][col] = currentPlayer;
            } else {
                aiMove();
            }
            moves++;

            if (checkWin()) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                return;
            }

            if (moves == 9) {
                displayBoard();
                System.out.println("The game is a draw!");
                return;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
        }
    }

    private void aiMove() {
        // First, check if AI can win
        if (makeWinningMove('O')) {
            return;
        }

        // Then, check if the player is about to win and block
        if (makeWinningMove('X')) {
            return;
        }

        // If no immediate moves, pick a random empty space
        randomMove();
    }

    private boolean makeWinningMove(char player) {
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == ' ') ||
                    (board[i][0] == player && board[i][2] == player && board[i][1] == ' ') ||
                    (board[i][1] == player && board[i][2] == player && board[i][0] == ' ')) {
                if (board[i][0] == ' ') board[i][0] = 'O'; // Replace 'O' for AI
                else if (board[i][1] == ' ') board[i][1] = 'O';
                else if (board[i][2] == ' ') board[i][2] = 'O';
                return true;
            }

            if ((board[0][i] == player && board[1][i] == player && board[2][i] == ' ') ||
                    (board[0][i] == player && board[2][i] == player && board[1][i] == ' ') ||
                    (board[1][i] == player && board[2][i] == player && board[0][i] == ' ')) {
                if (board[0][i] == ' ') board[0][i] = 'O';
                else if (board[1][i] == ' ') board[1][i] = 'O';
                else if (board[2][i] == ' ') board[2][i] = 'O';
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == ' ') ||
                (board[0][0] == player && board[2][2] == player && board[1][1] == ' ') ||
                (board[1][1] == player && board[2][2] == player && board[0][0] == ' ')) {
            if (board[0][0] == ' ') board[0][0] = 'O';
            else if (board[1][1] == ' ') board[1][1] = 'O';
            else if (board[2][2] == ' ') board[2][2] = 'O';
            return true;
        }

        if ((board[0][2] == player && board[1][1] == player && board[2][0] == ' ') ||
                (board[0][2] == player && board[2][0] == player && board[1][1] == ' ') ||
                (board[1][1] == player && board[2][0] == player && board[0][2] == ' ')) {
            if (board[0][2] == ' ') board[0][2] = 'O';
            else if (board[1][1] == ' ') board[1][1] = 'O';
            else if (board[2][0] == ' ') board[2][0] = 'O';
            return true;
        }

        return false;
    }

    private void randomMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');

        board[row][col] = currentPlayer;
        System.out.println("AI (Player " + currentPlayer + ") has made a move at (" + row + ", " + col + ")");
    }

    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Choose game mode: 1) Single Player 2) Two Players");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        boolean isSinglePlayer = (mode == 1);

        TicTacToeAI game = new TicTacToeAI(isSinglePlayer);
        game.playGame();
    }
}
