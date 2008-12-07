package agentIntern;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import protege.OntoCDOntology;

/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		ProducerBehaviour.java													 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Comportement Producteur													 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class ProducerBehaviour extends ParallelBehaviour {

	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

	private static final long serialVersionUID = 1L;
	public static ContentManager manager;
	public static Codec codec;
	public static OntoCDOntology onto;

	/** Serial par defaut */

	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Achat
	 */

	public ProducerBehaviour(Agent agent) {

		super(agent, ParallelBehaviour.WHEN_ALL);
		
		manager = myAgent.getContentManager();
		codec = new SLCodec();
		onto = (OntoCDOntology)OntoCDOntology.getInstance();
		manager.registerLanguage(codec);
	    manager.registerOntology(onto);
		
		this.addSubBehaviour(new ProduceDisc(this.myAgent));
		this.addSubBehaviour(new ProduceDisc(this.myAgent));
	}


}
