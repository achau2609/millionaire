import java.util.Scanner;

public class Main {

    public Main() {

    }

    // prints game rules
    public void printRules() {
        System.out.println("\nGeneral rules are as follows:");
        System.out.println("There will be 3 rounds, and each round has 3 questions for Easy difficulty, and 5 for Hard.");
        System.out.println("At the end of the rounds, you can win $1,000, $32,000, $1,000,000 respectively.");
        System.out.println("You may choose to leave after any round and take the corresponding amount of winnings with you; leaving mid round is not allowed.");
        System.out.println("You will get three lifelines:");
        System.out.println("- 50/50, which eliminates 2 incorrect answers;");
        System.out.println("- Ask the audience, in which you’ll be given multiple audience responses and you will have to decide which one is the correct answer;");
        System.out.println("- Phone a friend, in which you will be given one hint related to the correct answer.");
        System.out.println("You will lose all your winning if there is a single incorrect answer and the game will end.\n");
    }

    // obtain difficulty
    public String difficulty (Scanner userInput) {

        String difficulty;
        System.out.println("What difficulty do you want to play on? Enter E for easy mode, and H for hard mode.");
        while(true) {
            difficulty = userInput.nextLine();
            if (difficulty.equals("E") || difficulty.equals("H")) {
                break;
            } else {
                System.out.println("Unable to set difficulty based on input. Please try again!");
            }
        }
        return difficulty;

    }

    public static void main(String[] args) {

        // new main class object
        Main main = new Main();

        int roundNo;
        String menuInput;
        Scanner userInput = new Scanner(System.in);

        System.out.println("Welcome to WHO WANTS TO BE A MILLIONAIRE!");

        while (true) {
            System.out.println("Select an option!");
            System.out.println("- Press S to start the game!");
            System.out.println("- Press R to check the rules!");
            System.out.println("- Press Q to exit the game.");
            menuInput = userInput.nextLine();

            // quits app if Q is input
            if (menuInput.equals("Q")) {            // displays thank you msg and exits
                System.out.println("Thank you for playing!");
                break;
            } else if (menuInput.equals("R")) {     // enter code for outputting the rules of the game
                main.printRules();
            } else if (menuInput.equals("S")) {     // start the game here

                // game start prompt
                System.out.println("Game will start now!");

                // enter name
                System.out.println("Please enter your name:");
                String username = userInput.nextLine();

                // enter difficulty
                System.out.println("Please enter E if you wish to play on Easy mode, or H for Hard mode.");
                String diff = main.difficulty(userInput);

                // Initialize player, game, lifeline objects
                //Player player = new Player();
                Game game = new Game(diff);

                game.gameStart(diff, userInput);


            } else { // error handling for incorrect user input
                System.out.println("That is not a valid option. Please try again.");
            }

        }
    }
}