package agentIntern;
import agentExtern.RecevoirStop;
import jade.content.ContentManager;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		BankerAgent.java														 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Agent Banquier															 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

/**
 * Agent qui va recevoir les requetes venant des agents
 * vendeur, acheteur, producteur et commercial et va mettre
 * � jour la BDD en fonction des requetes 
 */

/**
 * Recoit les requetes de MAJ de la part des agent producteur, commercial
 * vendeur et acheteur et MAJ variable "caisse"
 * 
 */
public class BankerAgent extends Agent {

	private ContentManager manager = (ContentManager)this.getContentManager();
	private boolean arret;
	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new BankerBehaviour(this));
		this.addBehaviour(new RecevoirStop(this));
		arret = true;
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		/* Creation d'une decription du service */
		ServiceDescription sdBank = new ServiceDescription();
		sdBank.setType("HCK_Banque");
		sdBank.setName("Gestion de la Banque");
		sdBank.setOwnership(this.getName());
		
		/* Enregistrement du service aupres du DF Agent */
		dfd.addServices(sdBank);

		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
	}

	public boolean isArret() {
		return arret;
	}

	public void setArret(boolean arret) {
		this.arret = arret;
	}
	
	
	
}
