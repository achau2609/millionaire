import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class QuestionReader {
	
	ArrayList<Question> questions;
	
	QuestionReader() throws FileNotFoundException{
		this.questions = new ArrayList<Question>();
		try {
			File file = new File("src/questions.txt");
			
			Scanner input = new Scanner(file);
			
			String[] answers = new String[4];
			while (input.hasNext()) {
				
				
                String questionText = input.nextLine();
                String hint = input.nextLine();
                answers[0] = input.nextLine();
                answers[1] = input.nextLine();
                answers[2] = input.nextLine();
                answers[3] = input.nextLine();
                String answer = input.nextLine();
                // Get correct answer ABCD
                char correctAnswer = answer.substring(8, 1).charAt(0);
                int correctIndex = (int) correctAnswer - (int)'A';
                questions.add(new Question(questionText, answers, correctIndex, hint));
            }
			
			input.close();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public Question[] getQuestions(int NumberOfQuestions) {
		Random rand = new Random();
		
		Question[] questions = new Question[NumberOfQuestions];
		
		for (int i = 0; i < NumberOfQuestions; i++) {
			int index = rand.nextInt(this.questions.size());
			
			questions[i] = this.questions.get(index);
			
			this.questions.remove(index);
		}
		
		return questions;
	}
}
