package shadenade62.javahomework.lec3;
import java.util.Random;
import java.util.Scanner;

public class homework4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do
        {
            playAgain = playGameExt(scanner);
        } while (playAgain);

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    public static boolean playGameExt(Scanner scanner) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
        int userGuess = 0;

        System.out.println("Welcome to Guess the Number! Try to guess the number between 1 and 100.");

        while (userGuess != numberToGuess) {
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();

            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the number: " + numberToGuess);
            }
        }

        return askToPlayAgain(scanner);
    }

    public static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next().trim().toLowerCase();
        return response.equals("yes");
    }
}