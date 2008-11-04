package agent;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Class SellBehaviour
 * @author Arnaud Knobloch
 * Represente un comportement de l'Agent d Vente
 */

public class SellBehaviour extends SimpleBehaviour {
	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
	
	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Sell
	 */
	
	public SellBehaviour(Agent agent) {
	
	    super(agent);
	}
	
	public void action() {
		
		// Créer une instance du message et le recevoir
		ACLMessage msg = myAgent.blockingReceive(mt); 
		
		System.out.println("Message recut : "+msg.getContent());
	}

	public boolean done() {

		return true;
	}

}
