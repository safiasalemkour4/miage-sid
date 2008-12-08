package agentIntern;
import jade.content.ContentManager;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		ProducerAgent.java														 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Agent Producteur (CDs et DVDs)											 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

/**
 * Agent qui va envoyer des requetes à agentBDD et agentStock
 * pour mettre à jour selon la stratégie de production 
 * 
 * Stratégie : 
 * - quand produire ? (en fonction du stock ? periodiquement ? en fonction des sous ?) 
 * - quel qté produire ? (en fonction du stock ?) 
 * 
 * Peut etre sous les ordres d'un super-agent décidant de la strat?
 *
 */

public class ProducerAgent extends Agent {

		
	public static final double CD_HIGHT_PRICE = 3.0, CD_MEDIUM_PRICE = 2.5, CD_LOW_PRICE = 2.0;

	public static final double DVD_HIGHT_PRICE = 6.0, DVD_MEDIUM_PRICE = 5.0, DVD_LOW_PRICE = 4.0;

	
	private ContentManager manager = (ContentManager)this.getContentManager();

	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new ProducerBehaviour(this));
		this.addBehaviour(new ProducerBehaviour(this));
			
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		/* Creation d'une decription du service : Production de Cds */
		ServiceDescription sdProdCd = new ServiceDescription();
		sdProdCd.setType("HCK_ProductionCD");
		sdProdCd.setName("Production de CDs");
		sdProdCd.setOwnership(this.getName());
		
		/* Creation d'une decription du service : Production de Dvds */
		ServiceDescription sdProdDvd = new ServiceDescription();
		sdProdDvd.setType("HCK_ProductionDVD");
		sdProdDvd.setName("Production de DVDs");
		sdProdDvd.setOwnership(this.getName());
		
		/* Enregistrement des services aupres du DF Agent */
		dfd.addServices(sdProdCd);
		dfd.addServices(sdProdDvd);
		
		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}

	}
}
