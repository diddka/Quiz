import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NewQuiz {
    public static String[][] readCategory(String choice) {    //read csv file
        String fileLocation = "";
        if (choice.equalsIgnoreCase("capitals")) {
            fileLocation = "src\\capitals.csv";
        } else if (choice.equalsIgnoreCase("funny")) {
            fileLocation = "src\\funnyQuestions.csv";
        }
        String line;
        String[] value;
        String[][] file = new String[5][5];
        int rows = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            while ((line = br.readLine()) != null) {
                value = line.split(",");
                System.arraycopy(value, 0, file[rows], 0, value.length);
                rows++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void shuffleQuestion(String[][] chosenCategory) {
        List<String[]> asList = Arrays.asList(chosenCategory);    //turn array to list to get shuffled
        Collections.shuffle(asList);                              //  shuffle the list of questions
        chosenCategory = asList.toArray(new String[0][0]);        //  return list to array
        shuffleAnswers(chosenCategory);
    }

    public static void shuffleAnswers(String[][] chosenCategory) {
        String question = "";
        String[] answers = new String[4];
        for (int j = 0; j < chosenCategory.length; j++) {
            for (int i = 0; i < chosenCategory[0].length; i++) {
                if (i == 0) {
                    question = chosenCategory[j][0];             //remove questions from array
                } else {
                    answers[i - 1] = chosenCategory[j][i];       //take aswers to new 1d array
                }
            }
            List<String> answersToList = Arrays.asList(answers);// array answers to list
            Collections.shuffle(answersToList);                 //shuffle the answers

            int count = 1;
            for (String answer : answersToList) {               //return shuffled answers into array
                StringBuilder askQuestion = new StringBuilder();
                askQuestion.append(count).append(".").append(answer);  //numeric the answers
                askQuestion.toString();
                chosenCategory[j][count] = String.valueOf(askQuestion); //return shuffle and numeric answers to 2d array
                String.valueOf(count++);
            }
        }
    }

    public static String findRightAnswers(String[][] chosenCategory) {
        StringBuilder rightAnswers = new StringBuilder();
        for (int i = 0; i < chosenCategory[0].length; i++) {
            rightAnswers.append(chosenCategory[i][1]);
        }
        return rightAnswers.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose category of questions: \"Capitals\" or \"Funny\"");
        String choice = sc.nextLine();
        String[][] chosenCategory = readCategory(choice);
        String result = findRightAnswers(chosenCategory);
        shuffleQuestion(chosenCategory);

        int score = 0;
        String answer;
        for (int i = 0; i < chosenCategory.length; i++) {
            for (int j = 0; j < chosenCategory[0].length; j++) {
                System.out.println(chosenCategory[i][j]);
            }
            answer = sc.nextLine();
            if (result.toLowerCase().contains(answer.toLowerCase())) {
                score++;
                System.out.println("You guess right.  Score: " + score + "/5");
            } else {

                System.out.println("Wrong answer.\n Game over.\nYour score:" + score + "/5");
                break;
            }
            if (score == 5) {
                System.out.println("Game over.\nYou win!!!");
                break;
            }
        }
    }
}
