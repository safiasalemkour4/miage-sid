/**
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 *
 */
public class Theatre 
{
	Integer[][] places = new Integer[100][2];
	
	public Theatre()
	{
		for (int i=0; i<100; i++)
		{
			places[i][0] = 1;
			places[i][1] = i+1;
		}
	}
	
	public int reserve()
	{
		int result = -1;
		
		while (result == -1)
		{
			int numPlace = Math.round((float)Math.random()*99);
			if (places[numPlace][0] == 1)
			{
				places[numPlace][0] = 0;
				result = places[numPlace][1];
			}
		}
		return result;
	}
	
	public String toString()
	{
		return ("Nombre de place libre :");
	}
}
