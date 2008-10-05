import java.util.Random;

/**
 * Main class
 * Test the system of process
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 */

public class Main {

	/**
	 * Main method
	 * @param args useless here
	 */
	
	public static void main(String[] args) {
		
		/* Create a number between 1 and 10 */
		int numberOfProcess = (new Random().nextInt(9))+1;
		
		/* Create the system of process (context) */
		SystemProcess system = new SystemProcess(numberOfProcess);
		
		/* We add n process to the system */
		for (int i=0;i<numberOfProcess;i++) {
			system.addProcessus();
		}

		/* We start the system */
		system.start();
	}
}