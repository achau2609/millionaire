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
    private final int rounds;
    private final HashSet<String> answerOptions = new HashSet<>();
    private Map<String, int[]> winningList = new HashMap<>();


    public Game(String diff) {
        initializeWinningList();
        initializeAnswerOptions();
        this.rounds = (diff.equals("E")) ? 3 : 5;
    }

    // initialize winningList
    private void initializeWinningList() {
        winningList.put("1E", rOneEasy);
        winningList.put("2E", rTwoEasy);
        winningList.put("3E", rThreeEasy);
        winningList.put("1H", rOneHard);
        winningList.put("2H", rTwoHard);
        winningList.put("3H", rThreeHard);
    }

    // initialize answerOptions
    private void initializeAnswerOptions() {
        answerOptions.add("A");
        answerOptions.add("B");
        answerOptions.add("C");
        answerOptions.add("D");
        answerOptions.add("Lifeline");
    }

    // list for winning output
    private int[] winningList(int roundNo, String diff) {
        String concatRound = String.valueOf(roundNo).concat(diff);
        return winningList.get(concatRound);
    }

    // returns corresponding index from input
    private int pairCorrectIndex(String qInput) {
        return switch (qInput) {
            case "B" -> 1;
            case "C" -> 2;
            case "D" -> 3;
            default -> 0;
        };
    }

    // start the round
    private boolean roundStart(int roundNo, String diff, Scanner userInput, Player player, Question[] questions) {

        // int array for how many user has won after each question
        int[] winnings = winningList(roundNo, diff);

        // all variables
        boolean haveLifeline = true;
        boolean usedLifeline;

        String qInput;  // user input
        int qInputIndex;
        String userConfirm;

        // loop through set amount of questions; depending on difficulty.
        for(int currentRound = 0; currentRound < rounds; currentRound++) {

            // String variable to obtain userInput
            usedLifeline = false;

            // if user is playing on hard, no lifeline in round 1
            if (diff.equals("H") && roundNo == 1) {
                haveLifeline = false;
            }

            // print question
            questions[currentRound].printQuestion();

            while(true) {
                // prompt answer
                System.out.println("Please pick the correct answer using the corresponding letter, or use a lifeline if it's available.");
                qInput = userInput.nextLine();

                // check for appropriate input
                if (!answerOptions.contains(qInput)) {  // invalid input (not answer/lifeline)
                    System.out.println("Invalid option, try again.");
                } else if (qInput.equals("Lifeline")) { // use lifeline
                    // check if user can use lifeline; both Hard difficulty round 1 and used this round conditionals
                    if (!haveLifeline || usedLifeline) {
                        System.out.println("Lifelines are not available this round, or you have used one already.");
                    } else { // user can pick lifeline
                        System.out.println("Pick a lifeline from the list below!");
                        System.out.println("abcd");
                        // check whether lifeline can be used

                        // if can use, display outcome (50/50 list answers, audience list probability, phone list the hint)

                        // set flag to true so user can't use lifelines twice on the same question.
                        usedLifeline = true;
                    }

                } else { // when user entered answer
                    // ask for answer confirmation
                    System.out.println("Are you sure about your answer? Enter Y to confirm or N to reselect an answer.");
                    userConfirm = userInput.nextLine();
                    // return when Y (yes)
                    if (userConfirm.equals("Y")) {
                        break;
                    } else if (userConfirm.equals("N")) {
                        System.out.println("No worries, try again.");
                    } else {
                        System.out.println("Invalid option.");
                    }
                }
            }

            // pair the alphabetical index to array index
            qInputIndex = pairCorrectIndex(qInput);

            //System.out.println("Lifelines are not available this round.");
            //System.out.println("You may also opt to use a lifeline! Type \'Lifelife\' for the list of available lifelines.");



            // requests userInput for answer; user also has option to use lifeline; checks for correct input
            /*while(true) {
                System.out.println("Please pick the correct answer using the corresponding number, or use a lifeline if it's available.");
                qInput = userInput.nextLine();
                if (!answerOptions.contains(qInput)) {
                    System.out.println("Invalid option, try again.");
                } else if (!haveLifeline) {
                    System.out.println("Lifelines are not available this round.");
                } else {
                    break;
                }
            }*/

            // check if there's any remaining lifelines; if yes show options

            // if lifeline is used, display corresponding effect, mark lifeline used and change answers; then ask for userInput again
                // if 50-50, remove wrong answer index from array

            // checks for incorrect input + if user tries to use lifeline again
            /*while(true) {
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
            }*/

            // determines if the answer is correct or not; if not, break loop
            // otherwise, display current winnings, end of loop
            if (qInputIndex != questions[currentRound].getCorrectIndex()) {
                System.out.println("This is the wrong answer.");
                break;
            } else {
                System.out.println("You have answered the question correctly! Your current winnings is $" + winnings[currentRound] + ".");
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
        String cont;

        // round 1
        // generate questions
        Question[] roundOneQuestions = qReader.getQuestions(rounds);
        // start round
        boolean result_1 = roundStart(roundNo,diff,userInput, player, roundOneQuestions);
        if(!result_1) {
            System.out.println("\nYou have lost the game, and your winnings are $0. Thank you for playing!");
            return;
        } else {
            System.out.println("\nCongratulations on finishing round 1!");
            System.out.println("Your have earned $1000 so far. Would you like to continue playing or walk away with your current winnings?");
            System.out.println("Enter Yes to keep playing, or No to finish the game.");
            cont = userInput.nextLine();
            if (cont.equals("No")) {
                System.out.println("Thank you for playing! You have won $1,000.");
                return;
            } else {
                roundNo++;
                System.out.println("We will now move onto round 2.\n");
            }
        }

        // round 2
        // generate questions
        Question[] roundTwoQuestions = qReader.getQuestions(rounds);
        // start round
        boolean result_2 = roundStart(roundNo,diff,userInput, player, roundTwoQuestions);
        if(!result_2) {
            System.out.println("\nYou have lost the game, and your winnings are $0. Thank you for playing!");
            return;
        } else {
            System.out.println("\nCongratulations on finishing round 2!");
            System.out.println("Your have earned $32000 so far. Would you like to continue playing or walk away with your current winnings?");
            System.out.println("Enter Yes to keep playing, or No to finish the game.");
            cont = userInput.nextLine();
            if (cont.equals("No")) {
                System.out.println("Thank you for playing! You have won $32,000.");
                return;
            } else {
                roundNo++;
                System.out.println("We will now move onto round 3.\n");
            }
        }

        // round 3
        // generate questions
        Question[] roundThreeQuestions = qReader.getQuestions(rounds);
        // start round
        boolean result_3 = roundStart(roundNo,diff,userInput, player, roundThreeQuestions);
        if(!result_3) {
            System.out.println("You have lost the game, and your winnings are $0. Thank you for playing!");
        } else {
            System.out.println("\nCongratulations on finishing round 3, and the entire game!");
            System.out.println("You have won the grand prize of $1,000,000! Thank you for playing!");
        }
    }

}
