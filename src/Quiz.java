import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Quiz {
    public static void readQestionFile(String choice) {
        if (choice.toLowerCase().equals("fruits")) {
            String[][] fruitsquestion = {
                    {"What is the color of banana", "1.yellow", "2.green", "3.red", "4.blue"},
                    {"What is the taste of a lemon", "1.sour", "2.sweet", "3.bitter", "4.no taste"},
                    {"Is tomato fruit or vegetable", "1.fruit", "2.vegetable", "3.neither", "4.unknown"},
                    {"What is the color of carrot", "1.orange", "2.yellow", "3.green", "4.purple"}
            };
            shuffleQuestionsAndAnswers(fruitsquestion);

        } else if (choice.equals("capitals")) {
            String[][] capitals = {{""}};
            shuffleQuestionsAndAnswers(capitals);

        } else {
            System.out.println("Error \n Enter valid input");
        }
    }

    public static void shuffleQuestionsAndAnswers(String[][] choice) {
        String[][] fruitsquestion = choice;
        Random rand = new Random();
        int rdm1 = rand.nextInt(fruitsquestion.length);  //take random question whith his answers

        String question = "";
        String[] answers = new String[4];
        //checkRightQuestion(answers);

        for (int i = 0; i < 5; i++) {                    //devide question from answers
            if (i == 0) {
                question = fruitsquestion[rdm1][i];      //qwestion goes to String
            } else {
                answers[i - 1] = fruitsquestion[rdm1][i];  //answers goes to an array
            }
        }
        List<String> answersToList = Arrays.asList(answers); //answers from Array becomes to List to get shuffle them
        Collections.shuffle(answersToList);                  // shuffle the list of answers

        System.out.println(question);

        int numeric = 1;
        for (String s : answersToList) {                   //numeric answers
            System.out.println(numeric + "." + s);
            numeric++;
        }
        String s = answersToList.toString().replace("[", "").replace("]", ""); //remove brackets from list
        do {

            int score = 0;
            //for (int i = 0; i < answers.length; i++) {
            if (answers[0].equals(choice)) {
                System.out.println("You gess wright");
                score++;
                System.out.println("Score " + score + "/5");
            } else {
                System.out.println("Wrong answer " + "\n" + "Score " + score + "/5");
            }
            //}

        } while (true);

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Lets play a game");
        System.out.println("Choose category: fruits or capitals");
        String choice = sc.nextLine();
        readQestionFile(choice);



    }


}
