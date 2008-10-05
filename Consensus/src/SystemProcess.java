import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * SystemProcess class
 * Represent the system of process
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 */

public class SystemProcess extends Thread {

	/** The list of process of the system */
	private ArrayBlockingQueue<Processus> listProcessus;

	/** CyclicBarrier use in the system */
	private CyclicBarrier barrier, barrierPhase;

	/** The id of the last thread create */
	private int lastProcesId;

	/** The current phase */
	private int currentPhase;

	/**
	 * Constructor
	 * @param numberOfProcess the number of process
	 */

	public SystemProcess(int numberOfProcess) {

		this.listProcessus = new ArrayBlockingQueue<Processus>(numberOfProcess, true);

		this.lastProcesId = 0;
		this.currentPhase = 0;

		System.out.println("----------------------------------------------------------------\n" +
				"---   Un systeme de "+numberOfProcess+" processus vient d'etre cree.   ---\n"+
		"----------------------------------------------------------------\n");

		barrier = new CyclicBarrier(numberOfProcess);
		barrierPhase = new CyclicBarrier(numberOfProcess+1);

	}

	/**
	 * Method Run
	 */

	public void run() {

		/* We run all the tread of the system */
		startAllProcess();

		/* While phase k < Nb Process */ 
		while (!itIsTheEnd()) {

			this.currentPhase++;

			System.out.println("------------\n- Phase "+this.currentPhase+" -\n------------");

			//System.out.println(" waiting :" +barrier.getNumberWaiting());
			//System.out.println(" parties :" +barrier.getParties());

			/* We wait all process have finish the phase */
			try {

				barrierPhase.await();
				System.out.println("\n");

			} catch (InterruptedException e) {

				e.printStackTrace();

			} catch (BrokenBarrierException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * Method startAllProcess
	 * Run all the thread
	 */

	public void startAllProcess() {

		for (Processus p : this.listProcessus) {

			p.start();
		}
	}

	/**
	 * Method addProcessus
	 * Add a thread (process) to the system
	 */

	public void addProcessus() {

		this.lastProcesId++;

		try {

			this.listProcessus.put(new Processus("proc_"+this.lastProcesId,this));

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Method checkCrashPourcent
	 * @return the pourcent of process crashed
	 */

	public double checkCrashPourcent() {

		return (this.checkNbCrash()/this.checkNbProcessus());
	}

	/**
	 * Method checkNbProcessus
	 * @return the number of process in the system
	 */

	public int checkNbProcessus() {

		return this.listProcessus.size();
	}

	/**
	 * Method checkNbCrash
	 * @return the number of process crashed
	 */

	public int checkNbCrash() {

		int res = 0;

		for (Processus p : this.listProcessus) {

			if (p.isCrashed()) {

				res++;
			}
		}

		return res;
	}

	/**
	 * Method getOthersProc
	 * @param processus the applicant
	 * @return a arrayList of process
	 */

	public ArrayList<Processus> getOthersProc(Processus processus) {

		ArrayList<Processus> res = new ArrayList<Processus>();

		for (Processus p : this.listProcessus) {

			if (processus.getId() != p.getId()) {

				res.add(p);
			}
		}

		return res;
	}

	/**
	 * Method itIsMyTurn
	 * @param p the applicant
	 * @return true if it's the turn of the process, else false
	 */

	public synchronized boolean itIsMyTurn(Processus p) {

		String res ="{";
		for (Object obj : this.listProcessus) {
			res += ((Processus)obj).getMyName()+"}, ";
		}

		if (((Processus)this.listProcessus.peek()).getMyName().compareTo(p.getMyName())==0) {

			System.out.println("My turn ? "+p.getMyName()+" YES \n"+res);
			return true;

		} else {
			System.out.println("My turn ? "+p.getMyName()+" NO - it's he turn of "+((Processus)this.listProcessus.peek()).getMyName()+"\n"+res);
			return false;
		}
	}

	/**
	 * Method finishMyTurn
	 * @param p the applicant
	 * Set to end the turn of the thread
	 */

	public synchronized void finishMyTurn(Processus p) {

		if (itIsMyTurn(p)) {

			try {

				if (p.isCrashed()) {
					
					this.listProcessus.take();
					
				} else {

					System.out.println("It is the end for me "+((Processus)this.listProcessus.peek()).getMyName());
					this.listProcessus.take();
					this.listProcessus.add(p);
				}
				
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * Method displayInfos (Syncrhonized)
	 * @param message the message to display
	 */

	public synchronized void displayInfos(String message) {

		System.out.println(message);
	}

	/**
	 * Method itIsTheEnd
	 * @return true if it's the end of the algorithme
	 */

	public synchronized boolean itIsTheEnd() {

		return !(this.currentPhase<this.checkNbProcessus()-1);
	}

	/**
	 * Getter Barrier
	 * @return CyclicBarrier
	 */

	public CyclicBarrier getBarrier() {

		return this.barrier;
	}

	/**
	 * Getter barrierPhase
	 * @return CyclicBarrier
	 */

	public CyclicBarrier getBarrierPhase() {

		return this.barrierPhase;
	}

	/**
	 * Getter phase
	 * @return currentPhase
	 */

	public int getCurrentPhase() {

		return currentPhase;
	}
}
