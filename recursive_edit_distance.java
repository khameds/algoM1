package edit_distance;

// Recursive class to find minimum number of operations to convert str1 to str2
class recursive_edit_distance
{
    static int min(int x,int y,int z)
    {
        if (x<=y && x<=z) return x;
        if (y<=x && y<=z) return y;
        else return z;
    }
 
    static int editDist(String str1 , String str2 , int m ,int n)
    {

    // If str1 empty copy all remaining char of str2 into str1
    if (m == 0) return n;
      
    // If str2 empty remove all remaining char of str1
    if (n == 0) return m;
      
    // If char are equal ignore it and continue
    if (str1.charAt(m-1) == str2.charAt(n-1))
        return editDist(str1, str2, m-1, n-1);
      
    // Else char not equal test all three operation, recursively calculate cost of each and take min
    return 1 + min ( editDist(str1,  str2, m, n-1),    // Insert
                     editDist(str1,  str2, m-1, n),   // Remove
                     editDist(str1,  str2, m-1, n-1) // Replace                     
                   );
    }
 
    public static void main(String args[])
    {
        String str1 = "triste";
        String str2 = "vide";
  
        System.out.println( editDist( str1 , str2 , str1.length(), str2.length()) );
    }
}