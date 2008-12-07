package agentIntern;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		LogBehaviour.java														 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Comportement Logguage													 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class LogBehaviour extends SimpleBehaviour {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Sell
	 */

	public LogBehaviour(Agent agent) {

		super(agent);
	}

	public void action() {

	}

	public boolean done() {

		return true;
	}

}
