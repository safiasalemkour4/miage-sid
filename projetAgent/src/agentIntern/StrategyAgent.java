package agentIntern;
import jade.content.ContentManager;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import agentExtern.BuyBehaviour;
import agentExtern.RecevoirStop;



/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		StrategyAgent.java														 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Agent Strategie															 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class StrategyAgent extends Agent {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	
	public static double prixCD = ProducerAgent.CD_HIGHT_PRICE + ((int)(Math.random()*10));
	public static double prixDVD = ProducerAgent.DVD_HIGHT_PRICE + ((int)(Math.random()*10));
	public static int currentRound = 0;

	/* SIMON : met a jour ici qd on a remporter la vente avec un lastroundWin = currentRound */
	/*C'est fait, voir classe BusinessReceptionValider*/
	public static int lastRoundWinForCD = -1;
	public static int lastRoundWinForDVD = -1;
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		

		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());

		/* Creation d'une decription du service */
		ServiceDescription sdStrategy = new ServiceDescription();
		sdStrategy.setType("HCK_Strategie");
		sdStrategy.setName("Strategie sur le business de Cds et de Dvds");
		sdStrategy.setOwnership(this.getName());
		
		/* Enregistrement du service aupres du DF Agent */
		dfd.addServices(sdStrategy);

		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
		/* Ajout du comportement d'achat */
		this.addBehaviour(new StrategyBehaviour(this));
		this.addBehaviour(new StrategyStop(this));
	}
	
	

	
	
}
