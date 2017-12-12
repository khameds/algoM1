import java.util.ArrayList;
import java.util.Random;


public class ProblemGenerator {

    private static final int WORD_NUMBER = 2;

        public static String[] generateProblem(int minSize, int maxSize) {
            String[] randomWords = new String[WORD_NUMBER];
            Random random = new Random();
            for (int i = 0; i < WORD_NUMBER; i++) {
                char[] currentWord = new char[random.nextInt(maxSize-minSize) + 3];
                for (int j = 0; j < currentWord.length; j++) {
                    currentWord[j] = (char) ('a' + random.nextInt(26));
                }
                randomWords[i] = new String(currentWord);
            }

            return randomWords;
        }
    }