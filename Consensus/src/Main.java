import java.util.Random;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Entre 1 et 10
		int numberOfProcess = new Random().nextInt(10)+1;
		
		SystemProcess system = new SystemProcess(numberOfProcess);
		
		for (int i=0;i<numberOfProcess;i++) {
			
			system.addProcessus();
		}


		system.startAllProcessus();
	}

}
