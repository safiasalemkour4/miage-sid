package agentFreedom;

import protege.OntoCDOntology;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;


/**
 * Comportement du Client
 * Suite de plusieurs comportements
 * @author Simon
 *
 */
public class ClientBehaviour extends SequentialBehaviour {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	
	public static final int SLEEPING = 1000;
	public static ContentManager manager;
	public static Codec codec;
	public static OntoCDOntology onto;
	
	
	public ClientBehaviour(Agent a) {
		
		super(a);
		manager = myAgent.getContentManager();
		codec = new SLCodec();
		onto = (OntoCDOntology)OntoCDOntology.getInstance();
		manager.registerLanguage(codec);
	    manager.registerOntology(onto);
		
		
		// On envoi l'avis de phase 1 au commerciaux
		this.addSubBehaviour(new ClientEnvoiPhase1(this.myAgent));
		// On attend un peu pour que les entreprises produisent
		this.addSubBehaviour(new ClientAttente(this.myAgent));
		// On envoi l'avis de phase 2
		this.addSubBehaviour(new ClientEnvoiPhase2(this.myAgent));
		// On attend les messages de OK des commerciaux
		this.addSubBehaviour(new ClientAttenteOK(this.myAgent));
		
		// Achat de 5000 CDs
		this.addSubBehaviour(new Achat(this.myAgent, "CD", 5000));		
		// Achat de 2000 CDs
		//this.addSubBehaviour(new Achat(this.myAgent,"CD", 2000));
		// Achat de 1000 CDs
		//this.addSubBehaviour(new Achat(this.myAgent,"CD", 1000));
		
		// Achat de 4000 DVDs
		//this.addSubBehaviour(new Achat(this.myAgent, "DVD", 4000));		
		// Achat de 1500 DVDs
		//this.addSubBehaviour(new Achat(this.myAgent,"DVD", 1500));
		// Achat de 800 DVDs
		//this.addSubBehaviour(new Achat(this.myAgent,"DVD", 800));
		
	}

	
	
	
}
