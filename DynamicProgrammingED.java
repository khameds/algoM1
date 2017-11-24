public class DynamicProgrammingED {

    private int matrix[][]; // Will store results of subproblems like LCS algorithm

    public String getWord1() {
        return word1;
    }

    public String getWord2() {
        return word2;
    }

    private String word1, word2;
    private int editDistance;


    public int getEditDistance() {
        return editDistance;
    }

    public DynamicProgrammingED(String word1, String word2){
        this.word1 = word1.toLowerCase();
        this.word2 = word2.toLowerCase();
        editDistance = -1;
        InitMatrix();
    }

    private void InitMatrix() {
        matrix = new int[word1.length()+1][word2.length()+1];
        for(int i = 0 ; i <= word1.length() ; i++)
            matrix[i][0]= i;
        for(int i = 0 ; i <= word2.length() ; i++)
            matrix[0][i]= i;
    }

    private int GetMinValue(int x, int y, int z){
        return (x <= y && x <= z)? x : (y < x && y <=z)? y : z ;
    }

    public void EditDistance(){
        for(int i = 1 ; i <= word1.length() ; i++){
            for (int j = 1 ; j <= word2.length() ; j++){
               if (word1.charAt(i-1) == word2.charAt(j-1))
                    matrix[i][j] = matrix[i-1][j-1];
                else
                    matrix[i][j] = 1 + GetMinValue(matrix[i][j-1], matrix[i-1][j], matrix[i-1][j-1]);
            }
        }
        editDistance = matrix[word1.length()][word2.length()];

        System.out.println("The edit distance between <" + word1 + "> and <" + word2 + "> is " + editDistance);
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
            for(int j = 0; j < word2.length() + 1 ; j++) {
                System.out.print(matrix[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static void main(String args[])
    {
        DynamicProgrammingED dpED = new DynamicProgrammingED("sitting", "kitten");
        dpED.DisplayMatrix();
    }
}
