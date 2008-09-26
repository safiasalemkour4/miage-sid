
public class System extends Thread {
	
	private Processus p1, p2, p3, p4;
	
	public System() {
		
		this.p1 = new Processus();
		this.p2 = new Processus();
		this.p3 = new Processus();
		this.p4 = new Processus();
		
	}
	
	/**
	 * Methode Run
	 */

	public void run() {

		while (true) {

			try {

				checkCrashPourcent();
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

	private void checkCrashPourcent() {
		
		// Verif ici que qu’il n’y a jamais plus que f processus en panne (20%).
		
	}

}
