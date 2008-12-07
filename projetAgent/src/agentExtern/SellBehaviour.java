package agentExtern;


import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		SellBehaviour.java														 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Comportement Vente														 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

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

		// Cr�er une instance du message et le recevoir
		ACLMessage msg = myAgent.blockingReceive(mt); 

		if (msg != null) {

			ContentElement ce;

			/* On essaye de recuperer notre predicat */
			try {
				
				ce = this.myAgent.getContentManager().extractContent(msg);

				/*if (ce instanceof VendreCD) {
					
					VendreCD myPredicat = ( VendreCD ) ce;

					System.out.println("Le titre du cd est  : "+myPredicat.getCd().getTitle());
				}*/

			} catch (UngroundedException e) {

				e.printStackTrace();
			} catch (CodecException e) {
				e.printStackTrace();
			} catch (OntologyException e) {

				e.printStackTrace();
			}

		}

	}

	public boolean done() {

		return true;
	}

}
