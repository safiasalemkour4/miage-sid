package agentFreedom;

import protege.CD;
import protege.VendreCD;
import protege.VentecdOntology;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.lang.acl.ACLMessage;

/**
 * Class BuyBehaviour
 * @author Arnaud Knobloch
 * Represente un comportement de l'Agent d'Achat
 */

public class ClientBehaviour extends SimpleBehaviour {

	private Codec codec = new SLCodec();
	private VentecdOntology ontology = (VentecdOntology) VentecdOntology.getInstance();

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Achat
	 */

	public ClientBehaviour(Agent agent) {

		super(agent);
	}

	public void action() {


	}

	public boolean done() {
		return true;
	}

}