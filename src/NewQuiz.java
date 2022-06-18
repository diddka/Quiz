import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NewQuiz {
    public static String[][] readKategory(String choice) {
        String fileLokation = "";
        if (choice.toLowerCase().equals("capitals")) {
            fileLokation = "src\\capitals.csv";
        } else if (choice.toLowerCase().equals("funny")) {
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

    public static String shuffleQuestion(String[][] chosenCategory) {

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
        //System.out.println(question);

        StringBuilder askQuestion = new StringBuilder();
        askQuestion.append(question + "\n");
        for (int i = 0; i < answers.length; i++) {
            askQuestion.append(answers[i] + "\n");
        }
        String result = askQuestion.toString();

        return result;

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose category: \"Capitals\" or \"Funny question\"");
        String choice = sc.nextLine();
        String[][] chosenCategory = readKategory(choice);
        StringBuilder rightAnswers = new StringBuilder();
        //String[] rightAnswers = new String[5];
        String answer = "";
        int score = 0;
        for (int i = 0; i < chosenCategory[0].length; i++) {
            rightAnswers.append( chosenCategory[i][1]);
        }
        String result = rightAnswers.toString();
        System.out.println(result);

        do{
            System.out.println(shuffleQuestion(chosenCategory));
             answer = sc.nextLine();
             if(answer.toLowerCase().equals(result)){
                 score++;
                 System.out.println("You guess right " + score + "/5");
             }else {
                 System.out.println("Wrong answer. your score:" + score + "/5");
             }
        }while(!answer.toLowerCase().equals(result));


    }
}
