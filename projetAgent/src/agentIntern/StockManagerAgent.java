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
 *    CLASS  	******		StockManagerAgent.java													 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Agent Gestionnaire de Stock (CDs et DVDs)								 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

/**
 * Recoit les requetes de MAJ de la part des agent producteur, commercial
 * vendeur et acheteur et MAJ variable "stock"
 * 
 */

public class StockManagerAgent extends Agent {

	private ContentManager manager = (ContentManager)this.getContentManager();

	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	
	private int nosStockCD;
	private int nosStockDVD;
	  
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new StockEnvoiDispo(this));
		this.addBehaviour(new RecevoirStop(this));
		
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		/* Creation d'une decription du service */
		ServiceDescription sdStock = new ServiceDescription();
		sdStock.setType("HCK_Stock");
		sdStock.setName("Gestion des Stocks");
		sdStock.setOwnership(this.getName());
		
		/* Enregistrement du service aupres du DF Agent */
		dfd.addServices(sdStock);
		
		nosStockCD = 1000;
		nosStockDVD = 1000;

		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
	}

	protected int getNosStockDVD() {
		return nosStockDVD;
	}
	
	protected int getNosStockCD() {
		return nosStockCD;
	}
}
