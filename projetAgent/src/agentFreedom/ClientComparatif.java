package agentFreedom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

public class ClientComparatif extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public ClientComparatif(Agent a) {
		super(a);
	
	}

	@Override
	public void action() {
		
		HashMap<String, Integer> listprix = ((ClientAgent)this.myAgent).getPrix_cds();
		Collection<Integer> ens_prix = listprix.values();
		Iterator<Integer> iter = ens_prix.iterator();
		int prixTemp = Integer.MIN_VALUE;
		int index_prixmax = 0;
		int index_temp = -1;
		while (iter.hasNext()){
			index_temp++;
			int prixcourant = iter.next();
			if(prixTemp < prixcourant) {
				prixTemp = prixcourant;
				index_prixmax = index_temp;
			}
		}
		System.out.println("max des prix : "+prixTemp);
		
	
		
		
	}

	@Override
	public boolean done() {
	
		return true;
	}

}
