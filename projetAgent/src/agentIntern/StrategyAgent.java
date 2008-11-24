package agentIntern;
import agentExtern.BuyBehaviour;
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




public class StrategyAgent extends Agent {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	
	private ContentManager manager = (ContentManager)this.getContentManager();

	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new BuyBehaviour(this));
	    
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
	}

	
	
}
