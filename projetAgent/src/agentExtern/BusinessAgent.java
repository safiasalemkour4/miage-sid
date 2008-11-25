package agentExtern;
import jade.content.ContentManager;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		BusinessAgent.java														 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Agent Commercial (CDs et DVDs)											 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

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

public class BusinessAgent extends Agent {

	private ContentManager manager = (ContentManager)this.getContentManager();

	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new BusinessBehaviour(this));

		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());

		/* Creation d'une decription du service : Production de Cds */
		ServiceDescription sdStart = new ServiceDescription();
		sdStart.setType("Start");
		sdStart.setName("Start du Business");
		sdStart.setOwnership(this.getName());
		
		/* Creation d'une decription du service : Production de Cds */
		ServiceDescription sdSellCDToClient = new ServiceDescription();
		sdSellCDToClient.setType("VenteCD_Client");
		sdSellCDToClient.setName("VenteCD_Client Business");
		sdSellCDToClient.setOwnership(this.getName());
		
		/* Creation d'une decription du service : Production de Cds */
		ServiceDescription sdSellDVDToClient = new ServiceDescription();
		sdSellDVDToClient.setType("VenteDVD_Client");
		sdSellDVDToClient.setName("VenteDVD Client Business");
		sdSellDVDToClient.setOwnership(this.getName());
		
		/* Enregistrement des services aupres du DF Agent */
		dfd.addServices(sdStart);
		dfd.addServices(sdSellCDToClient);
		dfd.addServices(sdSellDVDToClient);
		
		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
		
	}
}
