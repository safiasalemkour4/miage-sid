import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;


public class SystemProcess extends Thread {

	private ArrayList<Processus> listProcessus;

	private CyclicBarrier barrier;
	
	private int lastId;

	private int currentPhase;
	
	private boolean processStarted;
	public SystemProcess(int numberOfProcess) {

		this.listProcessus = new ArrayList<Processus>();
		this.lastId = 0;
		this.currentPhase = 0;
		this.processStarted = false;
		System.out.println("Un systeme de "+numberOfProcess+" processus vient d'etre cree.");
		
		barrier = new CyclicBarrier(numberOfProcess);
		System.out.println(" waiting :" +barrier.getNumberWaiting());
		System.out.println(" parties :" +barrier.getParties());
		
	}

	/**
	 * Methode Run
	 */

	public void run() {

		while (this.currentPhase<this.checkNbProcessus()) {
			
			this.currentPhase++;
			
			System.out.println("\nPhase "+this.currentPhase);
			
			//checkCrashPourcent();
			System.out.println(" waiting :" +barrier.getNumberWaiting());
			System.out.println(" parties :" +barrier.getParties());
			
			//if (!this.processStarted) {
				//this.processStarted = true;
				startSendAllProcessus();
			//}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
	}

	public void startSendAllProcessus() {

		//for (Processus p : this.listProcessus) {

			//p.sendFirstNumber();
		//}
		
		for (Processus p : this.listProcessus) {
			
				p.start();
		
		}
	}

	public void stopSendAllProcessus() {

		//for (Processus p : this.listProcessus) {

			//p.sendFirstNumber();
		//}
		
		for (Processus p : this.listProcessus) {
			
				p.stop();
		
		}
	}
	
	public void startReceiveAllProcessus() {

		//for (Processus p : this.listProcessus) {

			//p.sendFirstNumber();
		//}
		
		for (Processus p : this.listProcessus) {

			p.notifyAll();
		}
		
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addProcessus() {

		this.lastId++;
		
		System.out.println("Ajout du processus "+this.lastId);
		
		this.listProcessus.add(new Processus(""+this.lastId,this));

	}

	private void checkCrashPourcent() {

		// Verif ici que quÕil nÕy a jamais plus que f processus en panne (20%).
		//checkNbCrash() / this.listProcessus.size();

	}
	
	public int checkNbProcessus() {
		
		return this.listProcessus.size();
	}
	
	public int checkNbCrash() {
		
		int res = 0;
		
		for (Processus p : this.listProcessus) {

			if (p.isCrashed()) {

				res++;
			}
		}
		
		return res;
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
		return this.barrier;
	}

	public boolean isAllMsgSended() {
		
		int res = 0;
		
		for (Processus p : this.listProcessus) {

			if (p.isMsgSended()) {

				res++;
			}
		}
		
		//System.out.println("res : "+res);
		
		if (res==this.listProcessus.size()) {
			return true;
		} else {
			return false;
		}

	}

	
}
