import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NewQuiz {
    public static String[][] readKategory(String choice) {
        String fileLokation = "";
        if (choice.equalsIgnoreCase("capitals")) {
            fileLokation = "src\\capitals.csv";
        } else if (choice.equalsIgnoreCase("funny")) {
            fileLokation = "src\\funnyQuestions.csv";
        }
        String line;
        String[] value;
        String[][] file = new String[5][5];
        int rows = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLokation));
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

    public static StringBuilder shuffleQuestion(String[][] chosenCategory) {
        List<String[]> asList = Arrays.asList(chosenCategory);
        Collections.shuffle(asList);
        chosenCategory = asList.toArray(new String[0][0]);
        String question = "";
        String[] answers = new String[4];

        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                question = chosenCategory[0][i];
            } else {
                answers[i - 1] = chosenCategory[0][i];
            }
        }
        List<String> answersToList = Arrays.asList(answers);
        Collections.shuffle(answersToList);

        StringBuilder askQuestion = new StringBuilder();
        askQuestion.append(question).append("\n");
        int numeric = 1;
        for (String answer : answers) {
            askQuestion.append(numeric).append(".").append(answer).append("\n");
            numeric++;
        }
        askQuestion.toString();

        return askQuestion;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose category: \"Capitals\" or \"Funny question\"");
        String choice = sc.nextLine();
        String[][] chosenCategory = readKategory(choice);
        StringBuilder rightAnswers = new StringBuilder();

        for (int i = 0; i < chosenCategory[0].length; i++) {
            rightAnswers.append(chosenCategory[i][1]);       //take right answer
        }
        String result = rightAnswers.toString();
        System.out.println(result);

        int score = 0;
        String answer;
        for (int i = 0; i < chosenCategory.length ; i++) {
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
            if (score==5) {
                System.out.println("Game over.\nYou win!!!");
                break;
            }

        }


    }
}
