package agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Class BuyBehaviour
 * @author Arnaud Knobloch
 * Represente un comportement de l'Agent d'Achat
 */

public class BuyBehaviour extends SimpleBehaviour {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Achat
	 */
	
	public BuyBehaviour(Agent agent) {
	
	    super(agent);
	}
	
	public void action() {
		
		//Créer une instance de la classe ACLMessage avec un template (Inform, Query,…) 
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		//Remplir l’ensemble des agents receveurs dans l’instance ACLMessage. 
		msg.addReceiver(new AID("sell_agent", AID.ISLOCALNAME));
		
		//Remplir le texte du contenu du message
		
		msg.setContent("Salut ça va ?");
		
		//Envoyer le message au receveur avec
		this.myAgent.send(msg);
		
	}

	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
