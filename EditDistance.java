import java.util.Scanner;

public class EditDistance {
    private static String word1,word2;

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
                    // TODO
                    break;
                case 3: // Branch and bound
                    inputWords();
                    // TODO
                    break;
                case 4: // Greedy approach
                    //TODO
                    break;
                case 5: // Quit program
                    keepGoing = false;
                    break;
                default:
                    System.out.println(" Invalid choice...");
                    break;
            }
        }

    }
}
