/**
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 *
 */
public class Agent extends Thread 
{
	Theatre theatreRoom;
	String name;
	Integer[] places;
	
	public Agent (String n, Theatre t)
	{
		this.name = n;
		this.theatreRoom = t;
	}
	
	public void run ()
	{
		this.places = new Integer[5];
		for (int i=0; i<5; i++)
		{
			try
			{
				Thread.sleep(Math.round((float)(1000 * Math.random())));
				this.places[i] = this.theatreRoom.reserve();
			}catch(InterruptedException e){}
		}
		System.out.println(this.toString());
	}

	public String toString()
	{
		String result = "Liste des 5 places réservées par l'agent "+this.name+" : \n";
		
		for (int i=0; i<places.length; i++)
			result += ("Place "+i+" : Numéro "+this.places[i]+"\n");
		
		result += "\n";
		
		return result;
	}
}
