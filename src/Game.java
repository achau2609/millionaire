import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Game {

    private final int[] rOneEasy = {100, 500, 1000};
    private final int[] rTwoEasy = {8000, 16000, 32000};
    private final int[] rThreeEasy = {125000, 500000, 1000000};
    private final int[] rOneHard = {100, 200, 300, 500, 1000};
    private final int[] rTwoHard = {2000, 4000, 8000, 16000, 32000};
    private final int[] rThreeHard = {64000, 125000, 250000, 500000, 1000000};
    private int rounds;
    private Question question;
    private HashSet<String> answerOptions = new HashSet<String>();
    Map<String, int[]> winningList = new HashMap<String, int[]>();


    public Game(String diff) {

        this.winningList.put("1E", rOneEasy);
        this.winningList.put("2E", rTwoEasy);
        this.winningList.put("3E", rThreeEasy);
        this.winningList.put("1H", rOneHard);
        this.winningList.put("2H", rTwoHard);
        this.winningList.put("3H", rThreeHard);

        this.answerOptions.add("1");
        this.answerOptions.add("2");
        this.answerOptions.add("3");
        this.answerOptions.add("4");
        this.answerOptions.add("Lifeline");

        if (diff.equals("E")) {
            this.rounds = 3;
        } else {
            this.rounds = 5;
        }

    }

    // list for winning output
    public int[] winningList(int roundNo, String diff) {

        String concatRound = String.valueOf(roundNo).concat(diff);
        return winningList.get(concatRound);

    }

    // start the round
    public boolean roundStart(int roundNo, String diff, Scanner userInput, Player player, Question[] questions) {

        // int array for how many user has won after each question
        int[] winnings = winningList(roundNo, diff);

        // all variables
        String qInput;  // user input
        String userConfirm;
        boolean haveLifeline;
        String currentQuestion;
        String[] currentQuestionChoices;
        int correctIndex;
        String correctAnswer;

        // loop through set amount of questions; depending on difficulty.
        for(int currentRound = 0; currentRound < rounds; currentRound++) {

            // String variable to obtain userInput
            haveLifeline = false;

            correctIndex = question.getCorrectIndex();
            /*
            // obtain question + answers + correct answer
            currentQuestion = question.getQuestionText();
            currentQuestionChoices = question.getChoices();


            // display question and the option for lifelines if available
            System.out.println(currentQuestion);
            for (String currentQuestionChoice : currentQuestionChoices) {
                System.out.println(currentQuestionChoice);
            }*/

            // print question
            questions[currentRound].printQuestion();

            if (diff.equals("H") && roundNo == 1) {
                System.out.println("Lifelines are not available this round.");
            } else {
                haveLifeline = true;
                System.out.println("You may also opt to use a lifeline! Type \'Lifelife\' for the list of available lifelines.");
            }

            // requests userInput for answer; user also has option to use lifeline; checks for correct input
            while(true) {
                System.out.println("Please pick the correct answer using the corresponding number, or use a lifeline if it's available.");
                qInput = userInput.nextLine();
                if (!answerOptions.contains(qInput)) {
                    System.out.println("Invalid option, try again.");
                } else if (!haveLifeline) {
                    System.out.println("Lifelines are not available this round.");
                } else {
                    break;
                }
            }

            // check if there's any remaining lifelines; if yes show options

            // if lifeline is used, display corresponding effect, mark lifeline used and change answers; then ask for userInput again
                // if 50-50, remove wrong answer index from array

            // checks for incorrect input + if user tries to use lifeline again
            while(true) {
                System.out.println("Please pick the correct answer using the corresponding number.");
                qInput = userInput.nextLine();
                if (!answerOptions.contains(qInput)) {
                    System.out.println("Invalid option, try again.");
                } else if (qInput.equals("Lifeline")) {
                    System.out.println("You have already used up your available lifeline chance for this round/there are no more available lifelines.");
                } else {
                    System.out.println("Are you sure about your answer? Enter Y to confirm or N to reselect an answer.");
                    userConfirm = userInput.nextLine();
                    if (userConfirm.equals("Y")) {
                        break;
                    } else if (userConfirm.equals("N")) {
                        System.out.println("No worries, try again.");
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
            }

            // determines if the answer is correct or not; if not, break loop
            // otherwise, display current winnings, end of loop
            if (Integer.parseInt(qInput) != correctIndex) {
                System.out.println("This is the wrong answer.");
                break;
            } else {
                System.out.println("You have answered correctly! Your current winnings is " + winnings[currentRound]);
            }

            // return true if player has finished all rounds with correct answers
            if (currentRound == (rounds-1)) {
                return true;
            }
        }

        // return false if player has lost a single round
        return false;

    }

    public void gameStart(String diff, Scanner userInput, Player player, QuestionReader qReader) {

        int roundNo = 1;
        int[] roundWinnings = new int[] {1000,32000,1000000};

        // round 1
        // generate questions
        Question[] roundOneQuestions = qReader.getQuestions(rounds);
        // start round
        boolean result_1 = roundStart(roundNo,diff,userInput, player, roundOneQuestions);
        if(!result_1) {
            System.out.println("You have lost the game, and your winnings are $0. Thank you for playing!");
            return;
        } else {
            roundNo++;
        }

        // round 2
        // generate questions
        Question[] roundTwoQuestions = qReader.getQuestions(rounds);
        // start round
        boolean result_2 = roundStart(roundNo,diff,userInput, player, roundTwoQuestions);
        if(!result_2) {
            System.out.println("You have lost the game, and your winnings are $0. Thank you for playing!");
            return;
        } else {
            roundNo++;
        }

        // round 3
        // generate questions
        Question[] roundThreeQuestions = qReader.getQuestions(rounds);
        // start round
        boolean result_3 = roundStart(roundNo,diff,userInput, player, roundThreeQuestions);
        if(!result_3) {
            System.out.println("You have lost the game, and your winnings are $0. Thank you for playing!");
            return;
        } else {
            System.out.println();
        }

    }


}
