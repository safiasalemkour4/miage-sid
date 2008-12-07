package agentExtern;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

public class BusinessVente extends SequentialBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessVente(Agent a) {
		super(a);
		
		// La vente est composée:
		
		// De la reception de demande de dispo et de requete au stock
		this.addSubBehaviour(new BusinessReceptionDmdDispo(a));
		// La reception de la réponse sur les stocks
		this.addSubBehaviour(new BusinessReceptionStock(a));
		// Du processus de reception de la validation
		this.addSubBehaviour(new BusinessReceptionValider(this.myAgent));
		
	}
}
