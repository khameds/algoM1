/*
    fail, wrong algo...
 */

public class DivideAndConquerED {

    private int GetMinValue(int x, int y, int z){
        return (x <= y && x <= z)? x : (y < x && y <=z)? y : z ;
    }

    private int DivideAndConquerRec(String w1, String w2, int x1, int x2){
        int editDistance;

        if(x1 == 0)
            return x2; // we are using x2 insertions

        if(x2 == 0)
            return x1; // we are using x1 deletions

        if(w1.charAt(x1) == w2.charAt(x2)){
            // Last character are equals -> cost 0
            return DivideAndConquerRec(w1,w2,x1-1,x2-1); // current editDistance + 0
        }
        else{
            /*
            In the case last caracaters doesnt match
            Looking for best edit distance depending on the chosen operation (insert, sub, delete)
             */
            /*** DIVIDE ***/
            //Subproblem if we insert
            int usingInsert = DivideAndConquerRec(w1,w2,x1,x2-1);

            //Subproblem if we substitute
            int usingSubstituion = DivideAndConquerRec(w1,w2,x1-1,x2-1);

            //Subproblem if we delete w1 last character
            int usingDelete = DivideAndConquerRec(w1,w2,x1-1,x2);

            /*** CONQUER ***/
            editDistance = GetMinValue(usingDelete +1,usingInsert +1,usingSubstituion +1)
                     /*cost of the current operation, in our case it's always 1*/ ;

            return editDistance;
        }
    }

    public DivideAndConquerED(String w1, String w2) {
        System.out.println("Minimal edit distance : " +computeEditDistance(w1,w2));
    }

    private int computeEditDistance(String w1, String w2) {
        if(w1.length() == 0)
            return w2.length();
        else if(w2.length() == 0)
            return w1.length();
        else
            return DivideAndConquerRec(w1,w2,w1.length()-1, w2.length()-1);
    }
}
