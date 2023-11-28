public class Question {
	private String questionText;
    private String[] choices;
    private int correctIndex;
    private String difficulty;
	
	Question(String question, String[] choices, int correctIndex, String diff){
		this.questionText = question;
		this.choices = choices;
		this.correctIndex = correctIndex;
		this.difficulty = diff;
	}
	
	public String getQuestionText() {
        return questionText;
    }

    public String[] getChoices() {
        return choices.clone(); // Return a copy to avoid modification outside the class
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public String getCorrectAnswer() {
        return choices[correctIndex];
    }

    public String getDifficulty() {
        return difficulty;
    }
    
    public void eliminateChoices(int... indices) {
        // Eliminate choices at specified indices
        for (int index : indices) {
            choices[index] = "[Eliminated]";
        }
        printQuestion();
    }

    public void displayAudienceResponse(int[] votes) {
        // Display audience response with the given distribution of votes
        
        System.out.println("Audience Response:");
        for (int i = 0; i < choices.length; i++) {
            System.out.println(choices[i] + ": " + votes[i] + "%");
        }
    }

    public void displayFriendResponse() {
        // Display friend's response
        System.out.println("You now have 1 minute to call your friend.");
    }
    
    public void printQuestion() {
    	System.out.println("Q: " + getQuestionText());
    	for (int i = 0; i < choices.length; i++) {
    		System.out.println(choices);
    	}
    }
}
