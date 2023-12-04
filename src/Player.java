import java.util.Scanner;

public class Player {
    private String playerName;
    private Lifeline[] lifelines;

    public Player(String playerName, Lifeline[] lifelines) {
        this.playerName = playerName;
        this.lifelines = lifelines;
    }

    public Lifeline[] getLifelines() {
        return lifelines.clone();
    }

    public boolean useLifeline(String type, Question currentQuestion) {
        for (Lifeline lifeline : lifelines) {
            if (lifeline.getType().equals(type) && !lifeline.isUsed()) {
                lifeline.markUsed();
                lifeline.applyLifeline(currentQuestion);
                return true; 
            }
        }
        return false; 
    }
    
    public String getName()
    {
    	return playerName;
    }

}