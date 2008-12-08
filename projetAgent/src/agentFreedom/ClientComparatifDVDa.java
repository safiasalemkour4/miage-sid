package agentFreedom;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.HashMap;

@SuppressWarnings("serial")
public class ClientComparatifDVDa extends SimpleBehaviour {

	public ClientComparatifDVDa(Agent a) {
		super(a);
	}

	@Override
	public void action() {
		HashMap<String, Double> listprix = ((ClientAgent)this.myAgent).getPrix_dvds();
		Double prixMin = Double.MAX_VALUE;
        String agentMin = "";

        /* pour tous les agents */
        for(String nomAgent : listprix.keySet()){
        	Double prixcourant = (listprix.get(nomAgent)).doubleValue();
            if(prixcourant < prixMin && prixcourant > 0 && ! ClientAgent.deja_achete_dvds.contains(nomAgent)) {
                prixMin = prixcourant;
                agentMin = nomAgent;
            }
        }
        ClientAgent.log.addText("			[CLIENT] Min des prix DVD : " + prixMin + " pour l'agent '" + agentMin + "'");
		ClientAgent.commercialElu = agentMin;
        ClientAgent.deja_achete_dvds.add(agentMin);
	}

	@Override
	public boolean done() {
		return true;
	}

}
