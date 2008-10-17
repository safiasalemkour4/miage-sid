import java.util.ArrayList;

/**
 * 
 */

/**
 * Processus class
 * Represent one process
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 */

public class Processus extends Thread 
{
	private SystemProcess system;
	
	private String name;
	
	private int vote;
	
	private ArrayList<Integer> listVotes;
	
	public Processus (String name, SystemProcess system)
	{
		this.name = name;
		this.system = system;
		
		this.vote = Math.round((float)Math.random()*1);
		
		this.listVotes = new ArrayList<Integer>();
	}
	
	public void run()
	{
		
	}
	
	public synchronized void addVote(int vote)
	{
		this.listVotes.add(vote);
	}
	
	public void sendToAll()
	{
		for (Processus p:this.system.getListProcessus())
		{
			p.addVote(this.vote);
		}
	}

	/**
	 * @return the system
	 */
	public SystemProcess getSystem() 
	{
		return system;
	}

	/**
	 * @param system the system to set
	 */
	public void setSystem(SystemProcess system) 
	{
		this.system = system;
	}

	/**
	 * @return the vote
	 */
	public int getVote() 
	{
		return vote;
	}

	/**
	 * @param vote the vote to set
	 */
	public void setVote(int vote) 
	{
		this.vote = vote;
	}

	/**
	 * @return the listVotes
	 */
	public ArrayList<Integer> getListVotes() 
	{
		return listVotes;
	}

	/**
	 * @param listVotes the listVotes to set
	 */
	public void setListVotes(ArrayList<Integer> listVotes)
	{
		this.listVotes = listVotes;
	}
	
	
}
