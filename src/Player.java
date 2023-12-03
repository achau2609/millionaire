import java.util.Random;
import java.util.Scanner;

public class Player {
    private String playerName;
    private char choice;
    private Lifeline[] lifelines;
    private Scanner scanner; // Scanner for user input

    public Player(String playerName, Lifeline[] lifelines) {
        this.playerName = playerName;
        this.lifelines = lifelines;
        this.scanner = new Scanner(System.in);
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

}