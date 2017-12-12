import java.util.ArrayList;
import java.util.Random;


public class ProblemGenerator {

        public static String[] generateProblem(int word_number, int minSize, int maxSize) {
            String[] randomWords = new String[word_number];
            Random random = new Random();
            for (int i = 0; i < word_number; i++) {
                char[] currentWord = new char[random.nextInt(maxSize - minSize + 1 /* include uppuer bound*/) + minSize];
                for (int j = 0; j < currentWord.length; j++) {
                    currentWord[j] = (char) ('a' + random.nextInt(26));
                }
                randomWords[i] = new String(currentWord);
            }
            return randomWords;
        }
    }