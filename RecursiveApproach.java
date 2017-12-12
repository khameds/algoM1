
public class RecursiveApproach
{
	private String word1;

	public String getWord1() {
		return word1;
	}

	public void setWord1(String word1) {
		this.word1 = word1;
	}

	public String getWord2() {
		return word2;
	}

	public void setWord2(String word2) {
		this.word2 = word2;
	}

	private String word2;

	public RecursiveApproach(String w1, String w2)
	{
		word1 = w1;
		word2 = w2;
	}
	
	private DistAlign Min(DistAlign i, DistAlign j, DistAlign k)
	{//i being insert, j being remove, k being replace

		if(i.GetDist()<=j.GetDist())
		{
			if(i.GetDist()<=k.GetDist())	//i<=j && i<=k
			{
				i.Insert(1);				//increment distance and add '+' to alignment
				return i;
			}
		}
		else if(j.GetDist()<=k.GetDist())	//j<i && j<=k
		{
			j.Remove(1);	//increment distance and add '-' to alignment
			return j;
		}
		
		k.Replace(1);		//increment distance and add '_' to alignment
		return k;			//j<i && k<j
	}
	
	public DistAlign Execute(int len1, int len2)
	{
		DistAlign da = new DistAlign();
		if(len1==0)				//If word1 empty
		{
			da.Insert(len2);	//copy all remaining char of word2, for all remaining char :
								//increment distance and add '+' to alignment
			return da;
		}
		
		if(len2==0)				//If word2 empty
		{
			da.Remove(len1);	//remove all remaining char of word1, for all remaining char :
								//increment distance and add '+' to alignment
			return da;
		}
		
		if(word1.charAt(len1-1) == word2.charAt(len2-1))	//If char equal
		{
			da.Fusion(Execute(len1-1, len2-1));				//do nothing go to next char
			da.Equal(1);									//simply add '|' to the alignment
			return da;
		}
		
		//Else choose the less costly of all three operation :
		da.Fusion(Min(Execute(len1, len2-1),		//Insert
						Execute(len1-1, len2),		//Remove
						Execute(len1-1, len2-1)));	//Replace
		
		return da;
	}
	
	public static void main(String[] args)
	{
		String word1 = "triste";		//The word to transform
		String word2 = "vide";		//the goal word
		DistAlign da = new DistAlign();	//object containing the distance and aligment
		RecursiveApproach recur = new RecursiveApproach(word1,word2);
		da = recur.Execute(word1.length(),word2.length());
		da.PrintDistAlign(word1, word2);
	}
	
}
