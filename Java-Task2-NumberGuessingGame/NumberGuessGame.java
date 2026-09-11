import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int totalGames = 0;
        int wins = 0;
        int highScore = 0;
        int streak = 0;

        System.out.println("==========================================");
        System.out.println("          NUMBER GUESSING GAME");
        System.out.println("==========================================");

        String playAgain;

        do {

            // -----------------------------
            // DIFFICULTY SELECTION
            // -----------------------------

            System.out.println("\nSelect Difficulty:");
            System.out.println("1. Easy   (1-50, 7 attempts)");
            System.out.println("2. Medium (1-100, 5 attempts)");
            System.out.println("3. Hard   (1-500, 3 attempts)");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            int maxNumber;
            int maxAttempts;
            int difficultyMultiplier;

            if (choice == 1) {
                maxNumber = 50;
                maxAttempts = 7;
                difficultyMultiplier = 1;
            }
            else if (choice == 2) {
                maxNumber = 100;
                maxAttempts = 5;
                difficultyMultiplier = 2;
            }
            else if (choice == 3) {
                maxNumber = 500;
                maxAttempts = 3;
                difficultyMultiplier = 3;
            }
            else {
                System.out.println("Invalid choice! Medium difficulty selected.");
                maxNumber = 100;
                maxAttempts = 5;
                difficultyMultiplier = 2;
            }

            // Generate random number
            int number = r.nextInt(maxNumber) + 1;

            int attempts = 0;
            int score = 0;
            boolean correct = false;
            boolean hintUsed = false;

            totalGames++;

            System.out.println("\n==========================================");
            System.out.println("Guess a number between 1 and " + maxNumber);
            System.out.println("You have " + maxAttempts + " attempts.");
            System.out.println("Type 0 anytime to use a hint.");
            System.out.println("==========================================");

            // -----------------------------
            // GUESSING LOOP
            // -----------------------------

            while (attempts < maxAttempts && !correct) {

                System.out.print("\nEnter your guess: ");
                int guess = sc.nextInt();

                // Hint
                if (guess == 0) {

                    if (!hintUsed) {

                        hintUsed = true;

                        if (number % 2 == 0) {
                            System.out.println("💡 Hint: The number is EVEN.");
                        }
                        else {
                            System.out.println("💡 Hint: The number is ODD.");
                        }

                        System.out.println("⚠ Hint used! Your final score will be reduced.");

                    }
                    else {
                        System.out.println("You already used your hint!");
                    }

                    continue;
                }

                // Invalid range
                if (guess < 1 || guess > maxNumber) {
                    System.out.println("Please enter a number between 1 and " + maxNumber);
                    continue;
                }

                attempts++;

                // Compare guess
                if (guess > number) {

                    System.out.println("📉 Too High!");

                }
                else if (guess < number) {

                    System.out.println("📈 Too Low!");

                }
                else {

                    correct = true;

                    // Score calculation
                    score = (maxAttempts - attempts + 1)
                            * 100
                            * difficultyMultiplier;

                    if (hintUsed) {
                        score = score / 2;
                    }

                    System.out.println("\n🎉 CORRECT!");
                    System.out.println("You guessed the number in "
                            + attempts + " attempts.");

                    System.out.println("Your score: " + score);

                    // Update high score
                    if (score > highScore) {
                        highScore = score;
                        System.out.println("🏆 NEW HIGH SCORE!");
                    }

                    wins++;
                    streak++;

                    System.out.println("🔥 Current winning streak: " + streak);
                }

                System.out.println("Attempts used: "
                        + attempts + "/" + maxAttempts);
            }

            // -----------------------------
            // GAME OVER
            // -----------------------------

            if (!correct) {

                streak = 0;

                System.out.println("\n❌ GAME OVER!");
                System.out.println("You used all your attempts.");
                System.out.println("The correct number was: " + number);

            }

            // -----------------------------
            // STATISTICS
            // -----------------------------

            double winRate = ((double) wins / totalGames) * 100;

            System.out.println("\n==========================================");
            System.out.println("              GAME STATISTICS");
            System.out.println("==========================================");

            System.out.println("Games Played : " + totalGames);
            System.out.println("Games Won    : " + wins);
            System.out.println("Games Lost   : " + (totalGames - wins));
            System.out.printf("Win Rate     : %.2f%%\n", winRate);
            System.out.println("High Score   : " + highScore);
            System.out.println("Current Streak: " + streak);

            // -----------------------------
            // PLAY AGAIN
            // -----------------------------

            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = sc.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        // -----------------------------
        // FINAL SUMMARY
        // -----------------------------

        System.out.println("\n==========================================");
        System.out.println("           FINAL GAME SUMMARY");
        System.out.println("==========================================");

        System.out.println("Total Games : " + totalGames);
        System.out.println("Total Wins  : " + wins);
        System.out.println("Total Losses: " + (totalGames - wins));
        System.out.println("High Score  : " + highScore);

        if (totalGames > 0) {
            double finalWinRate = ((double) wins / totalGames) * 100;
            System.out.printf("Win Rate    : %.2f%%\n", finalWinRate);
        }

        System.out.println("\nThank you for playing!");
        System.out.println("==========================================");

        sc.close();
    }
}