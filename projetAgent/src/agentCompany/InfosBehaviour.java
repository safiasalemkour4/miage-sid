package agentCompany;

import java.util.Iterator;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Class InfosBehaviour
 * @author Arnaud Knobloch
 * Represente un comportement de l'Agent d'Information
 */

public class InfosBehaviour extends SimpleBehaviour {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Information
	 */
	
	public InfosBehaviour(Agent agent) {

		super(agent);
	}

	public void action() {

		//Creer une description du DF Agent 
		DFAgentDescription dfd = new DFAgentDescription();
		
		//Retrouver les agents dans un tableau
		DFAgentDescription[] result = null;
		
		try {
			result = DFService.search(this.myAgent, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		
		//Parcourir le tableau pour retrouver les agents
		
		for (int i=0; i<result.length; i++) {
			
			Iterator<?> iter = result[i].getAllServices();
			
			//On récupere toutes les descriptions de service
			while (iter.hasNext()) {
				
				ServiceDescription sd =(ServiceDescription)iter.next();
				
				if (sd.getType().compareTo("Vente")==0) {
					
					System.out.println("Le service vente est proposé par l'agent "+sd.getOwnership());
					
				} else {
					
					System.out.println("L'agent "+sd.getOwnership()+" propose le service "+sd.getName());
				}
			}
		}

	}

	public boolean done() {
		
		return true;
	}

}
