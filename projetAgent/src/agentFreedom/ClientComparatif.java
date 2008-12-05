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

        int prixMin = Integer.MAX_VALUE;
        String agentMin = "";
       
        /* pour tous les agents */
        for(String nomAgent : listprix.keySet()){
            int prixcourant = (listprix.get(nomAgent)).intValue();
            if(prixcourant < prixMin && prixcourant > 0) {
            	prixMin = prixcourant;
                agentMin = nomAgent;
            }           
        }       
       
        ClientAgent.log.addText("Min des prix : " + prixMin + " pour l'agent '" + agentMin + "'");
		ClientAgent.commercialElu = agentMin;
	
		
		
	}

	@Override
	public boolean done() {
	
		return true;
	}

}
