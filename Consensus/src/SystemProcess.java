import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;


public class SystemProcess extends Thread {

	private ArrayList<Processus> listProcessus;

	private CyclicBarrier barrier;
	
	private int lastId;
	
	public SystemProcess(int numberOfProcess) {

		this.listProcessus = new ArrayList<Processus>();
		this.lastId = 0;
		
		System.out.println("Un systeme de "+numberOfProcess+" processus vient d'etre cree.");
		
		barrier = new CyclicBarrier(numberOfProcess);
	}

	/**
	 * Methode Run
	 */

	public void run() {

		while (true) {

			checkCrashPourcent();

		}
	}

	public void startAllProcessus() {

		for (Processus p : this.listProcessus) {

			p.sendFirstNumber();
		}
		
		for (Processus p : this.listProcessus) {

			p.start();
		}
	}

	public void addProcessus() {

		this.lastId++;
		
		System.out.println("Ajout du processus "+this.lastId);
		
		this.listProcessus.add(new Processus(""+this.lastId,this));

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

	public CyclicBarrier getBarrier() {
		return barrier;
	}

	
}
