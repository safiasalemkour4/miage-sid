package agent;
import protege.VentecdOntology;
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

public class BuyAgent extends Agent {

	private ContentManager manager = (ContentManager)this.getContentManager();

	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
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
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Achat");
		sd.setName("Achat de CDs");
		sd.setOwnership(this.getName());
		
		/* Enregistrement du service auprès du DF Agent */
		dfd.addServices(sd);

		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
	}
}
