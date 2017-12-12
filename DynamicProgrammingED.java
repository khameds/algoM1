public class DynamicProgrammingED {

    private int matrix[][]; // Will store results of subproblems like LCS algorithm
    private String backtrace[][];

    public String getWord1() {
        return word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    private String word1, word2;
    private int editDistance;
    private static long runtime;

    public int getEditDistance() {
        return editDistance;
    }

    public DynamicProgrammingED(String word1, String word2){
        this.word1 = word1.toLowerCase();
        this.word2 = word2.toLowerCase();
        editDistance = -1;
        runtime = 0;
        InitMatrix();
    }

    private void InitMatrix() {
        matrix = new int[word1.length()+1][word2.length()+1];
        backtrace = new String[word1.length()+1][word2.length()+1];
        for(int i = 0 ; i <= word1.length() ; i++)
            matrix[i][0]= i;
        for(int i = 0 ; i <= word2.length() ; i++)
            matrix[0][i]= i;

        for(int i = 0 ; i <= word1.length() ; i++)
            for(int j = 0 ; j <= word2.length() ; j++)
                backtrace[i][j]= "*";
    }

    private int GetMinValue(int x, int y, int z, int i, int j){
        // ins del sub
        if(x <= y && x <= z){
            backtrace[i][j] = "L"; // left, insertion
            if(x == y)
                backtrace[i][j]+="U"; // up, deletion
            if(x == z)
                backtrace[i][j]+="D"; // diag, substitution
            return x;
        }
        else if(y < x && y <=z){
            backtrace[i][j] = "U"; // up, deletion
            if(y == z)
                backtrace[i][j]+="D"; // diag, substitution
            return y;
        }
        else
            backtrace[i][j] = "D"; // diagonal, substition
        return z;
    }

    public void EditDistance(){
        int min;
        long startTime = System.currentTimeMillis();

        for(int i = 1 ; i <= word1.length() ; i++){
            for (int j = 1 ; j <= word2.length() ; j++){
               if (word1.charAt(i-1) == word2.charAt(j-1)) {
                   matrix[i][j] = matrix[i - 1][j - 1];

               }
                else {
                   min = GetMinValue(matrix[i][j - 1]/*ins*/, matrix[i - 1][j]/*del*/, matrix[i - 1][j - 1]/*sub*/, i , j);
                   matrix[i][j] = 1 + min;
               }
            }
        }
        editDistance = matrix[word1.length()][word2.length()];
        long stopTime = System.currentTimeMillis();
        runtime += stopTime-startTime;
        System.out.println("The edit distance between <" + word1 + "> and <" + word2 + "> is " + editDistance);
        System.out.println("Execution time = " + runtime + " ms.");
    }

    public void DisplayMatrix()
    {
        if(editDistance == -1) // not calculated yet
            EditDistance();

        System.out.print("  | \u03A3 | ");
        for( int i = 0 ; i < word2.length() ; i++)
            System.out.print(word2.charAt(i) + " | ");
        System.out.println();
        System.out.print("\u03A3 | ");

        for(int i = 0 ; i < word1.length() + 1 ; i++) {
            if(i > 0)
                System.out.print(word1.charAt(i-1) + " | ");
            for(int j = 0; j < word2.length() + 1 ; j++){
                System.out.print(matrix[i][j] + " | ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Backtrace : ");
        System.out.print("  | \u03A3 | ");
        for( int i = 0 ; i < word2.length() ; i++)
            System.out.print(word2.charAt(i) + " | ");
        System.out.println();
        System.out.print("\u03A3 | ");
        for(int i = 0 ; i < word1.length() + 1 ; i++) {

            if(i > 0)
                System.out.print(word1.charAt(i-1) + " | ");
            for(int j = 0; j < word2.length() + 1 ; j++) {
                System.out.print(backtrace[i][j] + " | ");
            }
            System.out.println();
        }


    }

    public static void main(String args[])
    {
        DynamicProgrammingED dpED = new DynamicProgrammingED("intention", "execution");
        dpED.DisplayMatrix();
    }
}
