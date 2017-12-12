
/*Class containing the edit distance and the alignment*/

public class DistAlign
{
	private int distance;		//edit distance value
	private String alignement;	//possible alignment
	private int bound;
	
	public DistAlign()
	{//Constructor
		distance = 0;
		alignement = "";
	}
	
	public void SetBound(int x)
	{
		bound = x;
	}
	
	public void SetDist(int x)
	{
		distance = x;
	}
	
	public int GetBound()
	{//Getter
		return bound;
	}
	
	public int GetDist()
	{//Getter
		return distance;
	}

	private void Insert()
	{
		alignement = alignement.concat("+");
	}
	
	private void Remove()
	{
		alignement = alignement.concat("-");
	}
	
	private void Replace()
	{
		alignement = alignement.concat("_");
	}
	
	private void Equal()
	{
		alignement = alignement.concat("|");
	}
	
	public void Insert(int x)
	{
		for(;x!=0;x--)
		{
			distance++;
			Insert();
		}
	}
	
	public void Remove(int x)
	{
		for(;x!=0;x--)
		{
			distance++;
			Remove();
		}
	}
	
	public void Replace(int x)
	{
		for(;x!=0;x--)
		{
			distance++;
			Replace();
		}
	}
	
	public void Equal(int x)
	{
		for(;x!=0;x--)
		{
			Equal();
		}
	}
	
	public void Fusion(DistAlign da2)
	{
		distance += da2.distance;
		alignement = alignement.concat(da2.alignement);
	}

	public void PrintDistAlign(String str1, String str2)
	{
		System.out.print("Distance : " + distance + "\nAlignement : \n"+str1+"\n"+ alignement + "\n"+str2+"\n");
	}
}
