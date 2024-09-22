package shadenade62.javahomework.lec3;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GuessTheWordGame {
    private String[] words = {"java", "programming", "computer", "science", "development"};
    private String chosenWord;
    private Set<Character> guessedLetters;
    private int attempts;

    public GuessTheWordGame(int attempts) {
        this.attempts = attempts;
        this.guessedLetters = new HashSet<>();
        this.chosenWord = chooseRandomWord();
    }

    private String chooseRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public void startGame() {
        System.out.println("Welcome to Guess the Word!");
        System.out.println("You have " + attempts + " attempts to guess the word.");

        while (attempts > 0) {
            displayCurrentState();
            System.out.print("Guess a letter: ");
            char guess = new Scanner(System.in).next().charAt(0);

            if (guessedLetters.contains(guess)) {
                System.out.println("You've already guessed that letter.");
                continue;
            }

            guessedLetters.add(guess);

            if (chosenWord.indexOf(guess) >= 0) {
                System.out.println("Good guess!");
            } else {
                attempts--;
                System.out.println("Wrong guess! Attempts left: " + attempts);
            }

            if (isWordGuessed()) {
                System.out.println("Congratulations! You've guessed the word: " + chosenWord);
                return;
            }
        }

        System.out.println("Game over! The word was: " + chosenWord);
    }

    private void displayCurrentState() {
        StringBuilder currentState = new StringBuilder();
        for (char letter : chosenWord.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                currentState.append(letter).append(" ");
            } else {
                currentState.append("_ ");
            }
        }
        System.out.println("Current word: " + currentState);
    }

    private boolean isWordGuessed() {
        for (char letter : chosenWord.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GuessTheWordGame game = new GuessTheWordGame(6); // Set attempts to 6
        game.startGame();
    }
}