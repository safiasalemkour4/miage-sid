import java.util.ArrayList;


public class System extends Thread {
	
	private ArrayList<Processus> listProcessus;
	
	public System() {
		
		this.listProcessus = new ArrayList<Processus>();
		
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

	public void addProcessus() {
		
		this.listProcessus.add(new Processus());
		
	}
	
	private void checkCrashPourcent() {
		
		// Verif ici que qu’il n’y a jamais plus que f processus en panne (20%).
		
	}
	
	public ArrayList<Processus> getOthersProc(Processus processus) {
		
		ArrayList<Processus> res = new ArrayList<Processus>();
		
		for (Processus p : this.listProcessus) {
			
			if (processus.getId() != p.getId()) {
				
				res.add(p);
			}
		}
		
		return res;
		
	}

}
