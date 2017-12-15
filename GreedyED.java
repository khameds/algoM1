public class GreedyED {

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    long runtime;
    String alignment;

    public int GetEditDistance(String w1, String w2) {

        long startTime = System.currentTimeMillis();		// Starting time
        int i;
        alignment = "";
        
        if(w1.length() == 0)						// If w1 is empty
        {
        	for(i=0;i<w2.length();i++)
        		alignment = alignment.concat("+");	// Insert all w2
            return w2.length();
        }
        if(w2.length() == 0)						// If w2 is empty
        {
        	for(i=0;i<w1.length();i++)
        		alignment = alignment.concat("-");	// Remove all w1
            return w1.length();
        }

        int shortestLength;
        int editDistance = Math.abs(w1.length() - w2.length());	// EditDistance starts at the difference between the two words (insert or remove)
        
        // Get the shortest word length to compute
        if(w1.length()< w2.length())			
        	shortestLength = w1.length();
        else
        	shortestLength = w2.length();
        

        for(i = 0 ; i < shortestLength ; i++){			// For this length we either
            if(w1.charAt(i) != w2.charAt(i)) {			// If the characters are different
                editDistance ++;						// Increment the distance
                alignment = alignment.concat("_");		// And replace the character
            }
            else
            	alignment = alignment.concat("|");		// Else they are equal so we just keep them
        }
        
        for(;shortestLength<=editDistance;shortestLength++)	// The rest of the distance are either insert or remove according to which word is the shortest
        	alignment = alignment.concat("±");
        
        long stopTime = System.currentTimeMillis();		// Stopping time
        runtime = stopTime - startTime;		// Stopping time - starting time = total runtime 

        return editDistance;
    }

    public void DisplayED(String w1, String w2){
        int editDistance = GetEditDistance(w1,w2);

        System.out.println("The edit distance between <" + w1 + "> and <" + w2 + "> is " + editDistance);
        System.out.println("Execution time = " + runtime + " ms.");
        System.out.println("Possible alignment : \n" + w1 + "\n" + alignment + "\n" + w2);
    }

    public GreedyED() {
        this.runtime = 0;
    }
}
