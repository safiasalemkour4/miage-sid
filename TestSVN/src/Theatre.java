/**
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 *
 */
public class Theatre 
{
	private Integer[][] places = new Integer[100][2];
	private int libres;
	
	public Theatre()
	{
		for (int i=0; i<100; i++)
		{
			this.places[i][0] = 1;
			this.places[i][1] = i+1;
		}
		this.libres = 100;
	}
	
	public synchronized int reserve()
	{
		int result = -1;
		
		while (result == -1)
		{
			int numPlace = Math.round((float)Math.random()*99);
			if (places[numPlace][0] == 1)
			{
				places[numPlace][0] = 0;
				result = places[numPlace][1];
				this.libres --;
			}
		}
		return result;
	}
	
	public String toString()
	{
		String result = ("Nombre de places libres : "+this.libres+"\n");
		result += ("Places reservees :");
		for (Integer place[] : this.places)
		{
			if (place[0] == 0)
				result += (" "+place[1]);
		}
		return result;
	}
}
