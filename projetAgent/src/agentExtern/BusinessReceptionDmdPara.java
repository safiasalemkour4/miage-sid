package agentExtern;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;

public class BusinessReceptionDmdPara extends ParallelBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public BusinessReceptionDmdPara(Agent a) {

		super(a,ParallelBehaviour.WHEN_ALL);
		
		// En parallele on attend :
		
		// La reception de la demande de dispo du client et l'envoi de la requete
		// de stock
		this.addSubBehaviour(new BusinessReceptionDmdDispo(a));
		// La reception de la réponse sur les stocks
		this.addSubBehaviour(new BusinessReceptionStock(a));
		

	}

}
