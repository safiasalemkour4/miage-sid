/**
 * Represent one commercial agent
 * Extend Thread
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 */

public class Agent extends Thread {

	/**	Theater affected to the agent */
	private Theater theaterRoom;

	/** Name of the agent */
	private String name;

	/** List of the places reserved by the agent */
	private Integer[] places;

	/**
	 * Constructor for one agent
	 * @param n , name of the agent
	 * @param t , theater of the agent
	 */

	public Agent (String n, Theater t) {

		this.name = n;
		this.theaterRoom = t;
	}

	@Override
	public void run () {

		// Initialize the array of reserved places to 5
		this.places = new Integer[5];

		// Reserve randomly 5 places in the theater
		for (int i=0; i<5; i++) {
			
			try {
				// Wait randomly many second before reserve one place
				Thread.sleep(Math.round((float)(1000 * Math.random())));
				this.places[i] = this.theaterRoom.reserve();
				
			} catch(InterruptedException e)	{
				e.printStackTrace();
			}
		}

		// Print the list of the 5 reserved places
		System.out.println(this.toString());
	}

	// Override of the toString method
	@Override
	public String toString() {

		// Add one header
		String result = "Liste des 5 places réservées par l'agent "+this.name+" : \n";

		// Add the list of 5 reserved places
		for (int i=0; i<places.length; i++)
			result += ("Place "+(i+1)+" : Numéro "+this.places[i]+"\n");

		result += "\n";

		return result;
	}
}
