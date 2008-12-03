package agentExtern;

import protege.OntoCDOntology;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

public class BusinessBehaviour extends SequentialBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ContentManager manager;
	static Codec codec;
	static OntoCDOntology onto;
	
	
	public BusinessBehaviour(Agent a) {
		super(a);
		
		manager = myAgent.getContentManager();
		codec = new SLCodec();
		onto = (OntoCDOntology)OntoCDOntology.getInstance();
		manager.registerLanguage(codec);
	    manager.registerOntology(onto);
		
		this.addSubBehaviour(new BusinessDecouvreService(this.myAgent));
		this.addSubBehaviour(new RecevoirNvPhase(this.myAgent));
		this.addSubBehaviour(new RecevoirNvPhase(this.myAgent));
		this.addSubBehaviour(new BusinessReceptionDisponibilite(this.myAgent));
		
		
	}
	
	
	
	
	

}
