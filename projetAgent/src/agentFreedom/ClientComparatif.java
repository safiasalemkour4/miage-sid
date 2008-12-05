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

        int prixMax = Integer.MIN_VALUE;
        String agentMax = "";
       
        /* pour tous les agents */
        for(String nomAgent : listprix.keySet()){
            int prixcourant = (listprix.get(nomAgent)).intValue();
            if(prixcourant > prixMax) {
                prixMax = prixcourant;
                agentMax = nomAgent;
            }           
        }       
       
        ClientAgent.log.addText("max des prix : " + prixMax + " pour l'agent '" + agentMax + "'");
		
	
		
		
	}

	@Override
	public boolean done() {
	
		return true;
	}

}
