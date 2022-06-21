import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NewQuiz {
    public static String[][] readKategory(String choice) {
        String fileLokation = "";
        if (choice.equalsIgnoreCase("capitals")) {
            fileLokation = "src\\capitals.csv";
        } else if (choice.equalsIgnoreCase("funny")) {
            fileLokation = "src\\funnyQuestions.csv";
        }
        String line = "";
        String[] value = new String[5];
        String[][] file = new String[5][5];
        int rows = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLokation));
            while ((line = br.readLine()) != null) {
                value = line.split(",");
                for (int i = 0; i < value.length; i++) {
                    file[rows][i] = value[i];
                }
                rows++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        int row = 0;
        int col = 0;
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                question = chosenCategory[0][i];

            } else {
                answers[i - 1] = chosenCategory[0][i];
                col++;
            }
        }
        List<String> answersToList = Arrays.asList(answers);
        Collections.shuffle(answersToList);

        StringBuilder askQuestion = new StringBuilder();
        askQuestion.append(question + "\n");
        int numeric = 1;
        for (int i = 0; i < answers.length; i++) {
            askQuestion.append(numeric + "." + answers[i] + "\n");
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
        String answer = "";
        for (int i = 0; i < chosenCategory[0].length; i++) {
            rightAnswers.append(chosenCategory[i][1]);
        }
        String result = rightAnswers.toString();
        System.out.println(result);
        boolean gameOver = true;
        int score = 0;
        String shuffleQuestion = String.valueOf(shuffleQuestion(chosenCategory));
        do {
            System.out.println(shuffleQuestion);
            answer = sc.nextLine();
            if (result.toLowerCase().contains(answer.toLowerCase())){
                score++;
                System.out.println("You guess right.  Score: " + score + "/5");
            }else{
                gameOver = false;
                System.out.println("Wrong answer. Your score:" + score + "/5");
            }
        } while (false || score == 5);
        if(!gameOver){
            System.out.println("Game over.\nYour score: " + score + "/5");
        }else if (score==5) {
            System.out.println("Game over.\nYour score: 5/5\n YOU WIN");
        }


    }
}
