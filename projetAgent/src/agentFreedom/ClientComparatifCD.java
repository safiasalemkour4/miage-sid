package agentFreedom;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.HashMap;

@SuppressWarnings("serial")
public class ClientComparatifCD extends SimpleBehaviour {

	public ClientComparatifCD(Agent a) {
		super(a);
	}

	@Override
	public void action() {
		
		HashMap<String, Double> listprix = ((ClientAgent)this.myAgent).getPrix_cds();
        int prixMin = Integer.MAX_VALUE;
        String agentMin = "";
       
        /* pour tous les agents */
        for(String nomAgent : listprix.keySet()){
            int prixcourant = (listprix.get(nomAgent)).intValue();
            if(prixcourant < prixMin && prixcourant > 0 && ! ClientAgent.deja_achete_dvds.contains(nomAgent)) {
                prixMin = prixcourant;
                agentMin = nomAgent;
            }          
        }       
       
        ClientAgent.log.addText("			[CLIENT] Min des prix CD : " + prixMin + " pour l'agent '" + agentMin + "'");
		ClientAgent.commercialElu = agentMin;
        ClientAgent.deja_achete_cds.add(agentMin);
	}

	@Override
	public boolean done() {
		return true;
	}

}
