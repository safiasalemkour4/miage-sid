package agentFreedom;
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

	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new BankerBehaviour(this));
	    
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
	}
}