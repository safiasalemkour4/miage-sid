package agentCompany;
import jade.content.ContentManager;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Class BuyAgent
 * @author Arnaud Knobloch
 * Represente un Agent d'Achat
 */

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

public class CommercialAgent extends Agent {

	private ContentManager manager = (ContentManager)this.getContentManager();

	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new CommercialBehaviour(this));

		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		/* Creation d'une decription du service : Production de Cds */
		ServiceDescription sdBusiness = new ServiceDescription();
		sdBusiness.setType("HCK_Business");
		sdBusiness.setName("Business de CDs et de Dvds aupres du client");
		sdBusiness.setOwnership(this.getName());
		
		/* Enregistrement du service aupres du DF Agent */
		dfd.addServices(sdBusiness);
		
		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
		
	}
}
