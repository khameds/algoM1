import java.util.ArrayList;
import java.util.Scanner;

public class EditDistance {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 50;
    private static final int WORD_NUMBER = 1000;

    private static String word1,word2;

    private static void testDA()
    {
        ArrayList<Double> runtimes = new ArrayList<>();
        DynamicProgrammingED dp;
        int wordNumber = WORD_NUMBER;
        double startTime, stopTime, runtime = 0;
        String [] words;
        String [] refWord;
        System.out.println("Starting test...");

        if((WORD_NUMBER % 2) != 0)
            wordNumber++;

        refWord = ProblemGenerator.generateProblem(1,1,1);

        for(int i = MIN_LENGTH; i < MAX_LENGTH ; i++){
            words = ProblemGenerator.generateProblem(wordNumber, i, i);
            startTime = System.currentTimeMillis();
            dp = new DynamicProgrammingED(words[0], words[0]);
            for(int j = 0 ; j < wordNumber ; j = j+1){
                dp.setWord1(refWord[0]);
                dp.setWord2(words[j]);
                dp.EditDistance();
            }
            stopTime = System.currentTimeMillis();
            runtime = stopTime - startTime;
            runtimes.add(runtime);
        }

        for(int i = 0 ; i < runtimes.size() ; i ++)
        {
            System.out.println("Runtime word(" + i +") = " + runtimes.get(i)/(double)wordNumber);
        }

    }


    private static int menu(){
        int choice;

        Scanner scan = new Scanner(System.in /*null*/);

        System.out.println("Choose your algorithm : ");
        System.out.println("1 : Dynamic Programming approach");
        System.out.println("2 : Recursive Approach");
        System.out.println("3 : Branch and Bound");
        System.out.println("4 : Greedy approach");
        System.out.println("5 : Quit");

        choice = scan.nextInt();
        return choice;
    }

    private static void inputWords(){
        Scanner scan = new Scanner(System.in /*null*/);

        System.out.println("Word 1 ? ");
        word1 = scan.nextLine();

        System.out.println("Word 2 ?");
        word2 = scan.nextLine();
    }

    public static void main(String args[])
    {
        boolean keepGoing = true;
        int choice;



        while(keepGoing) {
            choice = menu();

            switch (choice) {
                case 1: // Dynamic programming
                    inputWords();
                    DynamicProgrammingED editDistance = new DynamicProgrammingED(word1, word2);
                    editDistance.DisplayMatrix();
                    break;
                case 2: // Recursive approach
                    inputWords();
                    RecursiveApproach editDistanceRA = new RecursiveApproach(word1, word2);
                    DistAlign da = editDistanceRA.Execute(word1.length(),word2.length());
                    da.PrintDistAlign(word1,word2);

                    break;
                case 3: // Branch and bound
                    inputWords();
                    BranchAndBoundApproach bba = new BranchAndBoundApproach(word1,word2);
                    DistAlign da2 = bba.Execute(word1.length(),word2.length());
                    da2.PrintDistAlign(word1,word2);
                    break;
                case 4: // Greedy approach
                    inputWords();
                    GreedyED greedy = new GreedyED();
                    greedy.DisplayED(word1,word2);
                    break;
                case 5: // Quit program
                    keepGoing = false;
                    break;
                case 6:
                    testDA();
                    break;
                default:
                    System.out.println(" Invalid choice...");
                    break;
            }
        }

    }
}
