package agentCompany;
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
	    
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());

	}
}
