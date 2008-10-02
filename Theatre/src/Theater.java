/**
 * Represent one theater and this places
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 */

public class Theater 
{

	/** 
	 * List of the places, 
	 * the first dimension represent the 100 places
	 * the second dimension represent the name and the state of one place
	 * state : 1 = free | 0 = busy
	 */
	private Integer[][] places = new Integer[100][2];

	/** Number of free places */
	private int freePlaces;

	/**
	 * Empty constructor for Theater
	 */

	public Theater() 
	{

		// Initialize all places with one number for name and at the free state
		for (int i=0; i<100; i++) 
		{

			this.places[i][0] = 1;
			this.places[i][1] = i+1;
		}

		this.freePlaces = 100;
	}

	/**
	 * Reserve randomly one place in the theater
	 * Synchronize method, allow that many agent don't reserved the same place
	 * @return number of the reserved place
	 */
	public synchronized int reserve() 
	{

		int result = -1;

		// While no free place is found, search one 
		while (result == -1) 
		{

			// Affect randomly one place number
			int numPlace = Math.round((float)Math.random()*99);

			// If this one is free
			if (places[numPlace][0] == 1) 
			{

				// Change the state to busy
				places[numPlace][0] = 0;

				// Save the number of place
				result = places[numPlace][1];

				// Decrement the number of free places
				this.freePlaces --;
			}
		}
		return result;
	}

	// Override of the toString method
	@Override
	public String toString() 
	{

		// Add the number of free places
		String result = ("Nombre de places libres : "+this.freePlaces+"\n");

		// Add the list of reserved places
		result += ("Places réservées :");

		for (Integer place[] : this.places) 
		{
			if (place[0] == 0) 
			{
				result += (" "+place[1]);
			}
		}
		return result;
	}
}
