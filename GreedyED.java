public class GreedyED {

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    long runtime;

    public int GetEditDistance(String w1, String w2) {

        long startTime = System.currentTimeMillis();

        if(w1.length() == 0)
            return w2.length();
        if(w2.length() == 0)
            return w1.length();

        String shortestWord;
        int editDistance;

        if(w1.length()< w2.length())
            shortestWord = w1;
        else
            shortestWord = w2;

        editDistance = Math.abs(w1.length() - w2.length());

        for(int i = 0 ; i < shortestWord.length() ; i++){
            if(w1.charAt(i) != w2.charAt(i))
                editDistance ++;
        }
        long stopTime = System.currentTimeMillis();
        runtime = stopTime - startTime;

        return editDistance;
    }

    public void DisplayED(String w1, String w2){
        int editDistance = GetEditDistance(w1,w2);

        System.out.println("The edit distance between <" + w1 + "> and <" + w2 + "> is " + editDistance);
        System.out.println("Execution time = " + runtime + " ms.");
    }

    public GreedyED() {
        this.runtime = 0;
    }
}
