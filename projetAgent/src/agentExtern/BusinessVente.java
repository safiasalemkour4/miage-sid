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
		
		//du processus de reception de demande de dispo et de requete au stock
		this.addSubBehaviour(new BusinessReceptionDmdPara(this.myAgent));
		// du processus de reception de la validation
		this.addSubBehaviour(new BusinessReceptionValider(this.myAgent));
		
	}
	
	

}
