import java.util.Random;

public class Player {
    private String playerName;
    private char choice;
    private Lifeline[] lifelines;
    private Scanner scanner; // Scanner for user input

    public Player(String playerName, Lifeline[] lifelines) {
        this.playerName = playerName;WS
        this.lifelines = lifelines;
        this.scanner = new Scanner(System.in);
    }

    public void makeChoice(char choice, Question currentQuestion, int currentRound) {
    	this.choice = Character.toUpperCase(choice);

        if (!choice.equals(currentQuestion.getCorrectAnswer())) {
            System.out.println(playerName + " has answered incorrectly and walks away with no money.");
            System.exit(0); // 
        }

        if (currentQuestion.isLastQuestionOfRound(currentRound)) {
            System.out.println("Congratulations! You've answered the last question of the round correctly.");

            if (currentRound == 3) {
                System.out.println("You win the grand prize of $1,000,000!");
                System.exit(0); 
            } else {
                System.out.print("Do you want to walk away? (Enter 'Y' for Yes, 'N' for No): ");
                char walkAwayChoice = Character.toUpperCase(scanner.nextLine().charAt(0));

                if (!"Y".equals(walkAwayChoice)) {
                    System.out.println("You've chosen to continue to the next round.");
                }
            }
        }
    }

    public void walkAway(int currentRound) {
        double finalScore = calculateFinalScore(currentRound);
        System.out.println(playerName + " has chosen to walk away with a final score of $" + finalScore);
        System.exit(0); 
    }

    public Lifeline[] getLifelines() {
        return lifelines.clone();
    }

    public void useLifeline(String type) {
        for (Lifeline lifeline : lifelines) {
            if (lifeline.getType().equals(type) && !lifeline.isUsed()) {
                lifeline.markUsed();
                System.out.println(playerName + " has used the " + type + " lifeline.");
                return;
            }
        }
        System.out.println("Invalid lifeline type or lifeline already used.");
    }

    public double calculateFinalScore(int currentRound) {

        if (currentRound == 1) {
            amount = 1000; 
        } else if (currentRound == 2) {
            amount = 32000; 
        }

        return amount;
    }
}