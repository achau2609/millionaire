
import java.util.Random;

public class Lifeline {
	public enum LifelineType {
		FIFTY_FIFTY, ASK_AUDIENCE, PHONE
	}
    private LifelineType type;
    private boolean used;

    // Constructor, getters, and setters for type and used attributes...
    Lifeline(LifelineType type){
    	this.type = type;
    	used = false;
    }

    public String getType() {
    	if(type == LifelineType.FIFTY_FIFTY)
    		return "50/50";
    	else if (type == LifelineType.ASK_AUDIENCE)
    			return "Ask the Audience";
    	else return "Phone a Friend";
    }

    public boolean isUsed() {
        return used;
    }

    public void markUsed() {
        used = true;
    }

    public void applyLifeline(Question question) {
        if (type.equals(LifelineType.FIFTY_FIFTY)) {
        	apply50_50Lifeline(question);
        } else if (type.equals(LifelineType.ASK_AUDIENCE)) {
            applyAskTheAudienceLifeline(question);
        } else if (type.equals(LifelineType.PHONE)) {
            applyPhoneAFriendLifeline(question);
        }
    }

    private void apply50_50Lifeline(Question question) {
        // Randomly eliminate two incorrect answer choices
        Random random = new Random();
        int correctIndex = question.getCorrectIndex();

        // Generate random indices until finding two distinct incorrect choices
        int eliminated1, eliminated2;
        do {
            eliminated1 = random.nextInt(question.getChoices().length);
        } while (eliminated1 == correctIndex);

        do {
            eliminated2 = random.nextInt(question.getChoices().length);
        } while (eliminated2 == correctIndex || eliminated2 == eliminated1);

        question.eliminateChoices(eliminated1, eliminated2);
    }

    private void applyAskTheAudienceLifeline(Question question) {
        // Simulate audience response with a random distribution
        Random random = new Random();
        int[] votes = new int[question.getChoices().length];
        
        for (int i = 0; i < votes.length; i++) {
        	int minimumVotes = i == question.getCorrectIndex()? 60 : 0;
        	// correct answer has at least 60 votes;
            votes[i] = random.nextInt(41) + minimumVotes;
        }

        question.displayAudienceResponse(votes);
    }

    private void applyPhoneAFriendLifeline(Question question) {
        // Simulate friend's response with a correct answer suggestion
    	question.displayHint();
    }
}