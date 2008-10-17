import java.util.concurrent.ArrayBlockingQueue;

/**
 * 
 */

/**
 * @author Florian
 *
 */
public class SystemProcess extends Thread 
{
	/** The list of System process */
	private ArrayBlockingQueue<Processus> listProcessus;
	
	private int numberOfProcessus;
	
	public SystemProcess(int numberOfProcessus)
	{
		this.numberOfProcessus = numberOfProcessus;
		this.listProcessus = new ArrayBlockingQueue<Processus>(numberOfProcessus);
		
		for (int i=0; i<0; i++)
		{
			try {
	
				this.listProcessus.put(new Processus("proc_"+i,this));
	
			} catch (InterruptedException e) {
	
				e.printStackTrace();
			}
		}
	}
	
	public void run()
	{
		startAllprocessus();
	}

	private void startAllprocessus() 
	{
		for (Processus p:this.listProcessus)
		{
			p.start();
		}
	}
	
	/**
	 * @return the listProcessus
	 */
	public ArrayBlockingQueue<Processus> getListProcessus() {
		return listProcessus;
	}

	/**
	 * @param listProcessus the listProcessus to set
	 */
	public void setListProcessus(ArrayBlockingQueue<Processus> listProcessus) {
		this.listProcessus = listProcessus;
	}

	/**
	 * @return the numberOfProcessus
	 */
	public int getNumberOfProcessus() {
		return numberOfProcessus;
	}

	/**
	 * @param numberOfProcessus the numberOfProcessus to set
	 */
	public void setNumberOfProcessus(int numberOfProcessus) {
		this.numberOfProcessus = numberOfProcessus;
	}
	
}
