package agentExtern;
import protege.OntoCDOntology;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
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
 * Agent qui va envoyer des requetes d'achat aux vendeur des autres
 * entreprises. 
 * 1) envoie de Disponible (Disque, Qté)
 * 2) recoit réponseDispo
 * 3) si sup 0 : envoyer validerAchat(true/false)
 * 4) demander à agentBDD et agentStock de mettre à jour
 * 
 * Stratégie : 
 * - quel quantité acheté ? (en fonction du niveau de notre stock?)
 * - quel prix maximum on veut mettre ?
 * 
 */

public class BuyAgent extends Agent {

	private ContentManager manager = (ContentManager)this.getContentManager();

	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	/*************************************************
	 * -gui -host localhost –container hck_business_agent:agentExtern.CommercialAgent hck_buy_agent:agentExtern.BuyAgent hck_producer_agent:agentIntern.ProducerAgent	hck_sell_agent:agentExtern.SellAgent hck_strategy_agent:agentIntern.StrategyAgent	hck_banker_agent:agentIntern.BankerAgent	hck_stockmanager_agent:agentIntern.StockManagerAgent	hck_log_agent:agentIntern.LogAgent
				
	 */
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new BuyBehaviour(this));

		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		/* Creation d'une decription du service : Production de Cds */
		ServiceDescription sdBuyCd = new ServiceDescription();
		sdBuyCd.setType("AchatCD");
		sdBuyCd.setName("Achat de CDs aux concurrents");
		sdBuyCd.setOwnership(this.getName());
		
		/* Creation d'une decription du service : Production de Dvds */
		ServiceDescription sdBuyDvd = new ServiceDescription();
		sdBuyDvd.setType("AchatDVD");
		sdBuyDvd.setName("Achat de DVDs aux concurrents");
		sdBuyDvd.setOwnership(this.getName());
		
		/* Enregistrement des services aupres du DF Agent */
		dfd.addServices(sdBuyCd);
		dfd.addServices(sdBuyDvd);
		
		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
	}
}
