package agentFreedom;
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
 * Un tour de jeu a 2 phases : 
 * 1) client envoi "nouvellePhase(1)" au commerciale
 * 		et client se met en attente (dort pdt 10s)
 * 
 * 2) l'entreprise produit, achete et vend aux autres boites
 * 
 * 4) client sort du cycle d'attente
 *  	et il envoi nouvellePhase(2) au commerciale
 * 5) commerciale demande a tout le monde de s'arreter, 
 * 		quand tout le monde est arreté, envoi OK au client
 * 6) quand client a recu 4 OK
 *  - envoi disponibilité
 *  - recoit réponseDispo
 *  - compare prix
 *  - envoi validerAchat a la boite elu
 * 
 * fin phase 2 
 * 7) envoi de nouvellePhase(1)...
 * 
 */


public class ClientAgent extends Agent {

	private ContentManager manager = (ContentManager)this.getContentManager();

	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new ClientBehaviour(this));
	    
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());

	}
}
