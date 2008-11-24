package agentIntern;


import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Class SellBehaviour
 * @author Arnaud Knobloch
 * Represente un comportement de l'Agent d Vente
 */

public class StrategyBehaviour extends SimpleBehaviour {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Sell
	 */

	public StrategyBehaviour(Agent agent) {

		super(agent);
	}

	public void action() {

		
	}

	public boolean done() {

		return true;
	}

}
