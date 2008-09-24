/**
 * Main test class
 * Create one theater and 10 commercial agents
 * All agent reserved randomly and at randomly moment 5 places
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 */
public class Test {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Initialize the theater (Sebastopol is one theater of Lille)
		Theater sebastopol = new Theater();
	
		// Initialize the 10 different agents
		Agent bob = new Agent("Bob",sebastopol);
		Agent marcel = new Agent("Marcel",sebastopol);
		Agent patrick = new Agent("Patrick",sebastopol);
		Agent maurice = new Agent("Maurice",sebastopol);
		Agent mickey = new Agent("Mickey",sebastopol);
		Agent eddy = new Agent("Eddy",sebastopol);
		Agent clara = new Agent("Clara",sebastopol);
		Agent suzy = new Agent("Suzy",sebastopol);
		Agent geraldine = new Agent("Geraldine",sebastopol);
		Agent anne = new Agent("Anne",sebastopol);

		// Start the 10 thread agent
		bob.start();
		marcel.start();
		patrick.start();
		maurice.start();
		mickey.start();
		eddy.start();
		clara.start();
		suzy.start();
		geraldine.start();
		anne.start();
		
		// While the threads agent are not finished, the main method wait
		try 
		{
			bob.join();
			marcel.join();
			patrick.join();
			maurice.join();
			mickey.join();
			eddy.join();
			clara.join();
			suzy.join();
			geraldine.join();
			anne.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		// Print the theater informations
		System.out.println(sebastopol);
	}

}
