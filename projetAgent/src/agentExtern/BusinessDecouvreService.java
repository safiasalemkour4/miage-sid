package agentExtern;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.ArrayList;
import java.util.Iterator;

/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		BusinessBehaviour.java													 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Comportement Commercial													 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class BusinessDecouvreService extends SimpleBehaviour {

	

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Achat
	 */

	public BusinessDecouvreService(Agent agent) {

		super(agent);
	}

	public void action() {
		/**
		 * Recoit "nouvellePhase" venant du client.
		 * Demande a tous les agents de finir leur action en cours et de s'arreter
		 * Renvoi OK au client quand tout le monde est arreter
		 * Recoit une demande de dispo de CD de la part du client
		 * Renvoit ses disponibilite (le prix)
		 * Si il recoit une validationAchat du client - > met a jour agentBDD
		 * 
		 * 
		 */

		//Creer une description du DF Agent 
		DFAgentDescription dfd = new DFAgentDescription();

		//Retrouver les agents dans un tableau
		DFAgentDescription[] result = null;

		try {
			result = DFService.search(this.myAgent, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}

		//Parcourir le tableau pour retrouver les agents

		for (int i=0; i<result.length; i++) {

			Iterator<?> iter = result[i].getAllServices();

			//On recupere toutes les descriptions de service
			// On devrai stocker les agents dans un tableau pour pouvoir ensuite
			// leur parler (pour leur dire stop à la fin d'une phase)
			while (iter.hasNext()) {

				ServiceDescription sd =(ServiceDescription)iter.next();
				// On stocke les agents découvert par l'agent commerciale
				ArrayList<String> listAgent = ((BusinessAgent)this.myAgent).getListeNosAgents();
				if(!listAgent.contains(sd.getOwnership())){
					listAgent.add(sd.getOwnership());
				}
				
				/*
				 * Les services :
				HCK_AchatCD
				HCK_AchatDVD
				HCK_ProductionCD
				HCK_ProductionDVD
				HCK_VenteCD
				HCK_VenteDVD
				HCK_Strategie
				HCK_Banque
				HCK_Stock
				HCK_LOG
				 */
				
				if (sd.getType().equals("HCK_AchatCD")) {
					
					//System.out.println("Le service achat cd est propose par l'agent "+sd.getOwnership());

				} else if (sd.getType().equals("HCK_AchatDVD")) {
					
					//System.out.println("Le service achat dvd est propose par l'agent "+sd.getOwnership());

				} else if (sd.getType().equals("HCK_ProductionCD")) {
					
					//System.out.println("Le service prod cd est propose par l'agent "+sd.getOwnership());

				} else if (sd.getType().equals("HCK_ProductionDVD")) {
					
					//System.out.println("Le service prod dvd est propose par l'agent "+sd.getOwnership());

				} else if (sd.getType().equals("HCK_VenteCD")) {
					
					//System.out.println("Le service vente cd est propose par l'agent "+sd.getOwnership());

				} else if (sd.getType().equals("HCK_VenteDVD")) {
					
					//System.out.println("Le service vente dvd est propose par l'agent "+sd.getOwnership());

				} else if (sd.getType().equals("HCK_Strategie")) {
					
					//System.out.println("Le service strategie est propose par l'agent "+sd.getOwnership());

				} else if (sd.getType().equals("HCK_Banque")) {
					
					//System.out.println("Le service banque est propose par l'agent "+sd.getOwnership());

				}else if (sd.getType().equals("HCK_Stock")) {
					
					//System.out.println("Le service stock est propose par l'agent "+sd.getOwnership());

				}else if (sd.getType().equals("HCK_LOG")) {
					
					//System.out.println("Le service log est propose par l'agent "+sd.getOwnership());

				}else {
					
					//System.out.println("L'agent "+sd.getOwnership()+" propose le service "+sd.getName());
				}
			}
		}
		
		System.out.println("Liste des agents que le commercial connait : ");
		for (int i = 0; i < ((BusinessAgent)this.myAgent).getListeNosAgents().size(); i++) {
			String ag = ((BusinessAgent)this.myAgent).getListeNosAgents().get(i);
			System.out.println(ag);
		}
		System.out.println("");
	}

	public boolean done() {
		return true;
	}

}
