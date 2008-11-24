package agentCompany;

import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import protege.CD;
import protege.OntoCDOntology;

/**
 * Class BuyBehaviour
 * @author Arnaud Knobloch
 * Represente un comportement de l'Agent d'Achat
 */

public class BuyBehaviour extends SimpleBehaviour {

	private Codec codec = new SLCodec();
	private OntoCDOntology ontology = (OntoCDOntology) OntoCDOntology.getInstance();

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Achat
	 */

	public BuyBehaviour(Agent agent) {

		super(agent);
	}

	public void action() {

		//Cr�er une instance de la classe ACLMessage avec un template (Inform, Query,�) 
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

		//Remplir l�ensemble des agents receveurs dans l�instance ACLMessage. 
		msg.addReceiver(new AID("sell_agent", AID.ISLOCALNAME));

		//Remplir le texte du contenu du message

		//msg.setContent("Salut �a va ?");

		msg.setLanguage(codec.getName());
		msg.setOntology(ontology.getName());

		// Register the SL content language
		this.myAgent.getContentManager().registerLanguage(new SLCodec());

		// Register the mobility ontology
		this.myAgent.getContentManager().registerOntology(ontology);


		// On cree le cd
		//CD cd = new CD();
		//cd.setTitle("test titre");

		// On cree le predicat
		//VendreCD venteCD = new VendreCD();
		//venteCD.setCd(cd);

		// On ajoute au content manager
		/*try {
			this.myAgent.getContentManager().fillContent(msg,venteCD);
		} catch (CodecException e) {
			e.printStackTrace();
		} catch (OntologyException e) {
			e.printStackTrace();
		}*/

		//Envoyer le message au receveur avec
		this.myAgent.send(msg);

	}

	public boolean done() {
		return true;
	}

}
