package agentFreedom;

import protege.OntoCDOntology;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

public class ClientBehaviour extends SequentialBehaviour {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	
	public static final int SLEEPING = 100;
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
		
		
		
		this.addSubBehaviour(new ClientEnvoiPhase1(this.myAgent));
		this.addSubBehaviour(new ClientAttente(this.myAgent));
		this.addSubBehaviour(new ClientEnvoiPhase2(this.myAgent));
		this.addSubBehaviour(new ClientAttenteOK(this.myAgent));
		System.out.println("Le client commence ses achats ...");
		
			this.addSubBehaviour(new AchatsBehaviour(this.myAgent));
		
		this.addSubBehaviour(new ReceptionPrixBehaviour(this.myAgent));
	}

	
	
	
}
