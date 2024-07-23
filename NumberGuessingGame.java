import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Number Guessing Game!");
        int totalScore = 0;
        int rounds = 0;
        boolean playAgain = true;
        
        while (playAgain) {
            rounds++;
            int targetNumber = random.nextInt(100) + 1; // Generate random number between 1 and 100
            System.out.println("Round " + rounds);
            int attempts = 0;
            final int maxAttempts = 10; // Maximum number of attempts per round
            
            while (attempts < maxAttempts) {
                attempts++;
                System.out.print("Attempt " + attempts + "/" + maxAttempts + ". Enter your guess: ");
                int userGuess = scanner.nextInt();
                
                if (userGuess < targetNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else if (userGuess > targetNumber) {
                    System.out.println("Too high! Try a lower number.");
                } else {
                    System.out.println("Congratulations! You guessed the number " + targetNumber + " correctly in " + attempts + " attempts!");
                    totalScore += attempts;
                    break;
                }
                
                if (attempts == maxAttempts) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
                }
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next();
            if (!playAgainInput.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }
        
        System.out.println("Game Over! You played " + rounds + " rounds and your total score is " + totalScore + ".");
        scanner.close();
    }
}