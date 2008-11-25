package agentFreedom;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import protege.OntoCDOntology;

/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		ClientBehaviour.java													 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Comportement Client														 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class ClientBehaviour extends SimpleBehaviour {

	private Codec codec = new SLCodec();
	private OntoCDOntology ontology = (OntoCDOntology) OntoCDOntology.getInstance();

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
