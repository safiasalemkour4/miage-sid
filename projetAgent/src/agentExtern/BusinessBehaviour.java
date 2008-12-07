package agentExtern;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import protege.OntoCDOntology;

public class BusinessBehaviour extends SequentialBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ContentManager manager;
	public static Codec codec;
	public static OntoCDOntology onto;


	public BusinessBehaviour(Agent a) {
		super(a);

		manager = myAgent.getContentManager();
		codec = new SLCodec();
		onto = (OntoCDOntology)OntoCDOntology.getInstance();
		manager.registerLanguage(codec);
		manager.registerOntology(onto);
		
		// Démarrage du processus qui démarre de attente de nouvelle phase
		// en parallele
		this.addSubBehaviour(new BusinessRecepNvPhaseParallele(this.myAgent));
		
		// Vente de 5000 CD
		this.addSubBehaviour(new BusinessVente(this.myAgent));
		// Vente de 2000 CD
		this.addSubBehaviour(new BusinessVente(this.myAgent));
		// Vente de 1000 CD
		this.addSubBehaviour(new BusinessVente(this.myAgent));
		// Vente de 5000 DVD
		this.addSubBehaviour(new BusinessVente(this.myAgent));
		// Vente de 2000 DVD
		this.addSubBehaviour(new BusinessVente(this.myAgent));
		// Vente de 1000 DVD
		this.addSubBehaviour(new BusinessVente(this.myAgent));
		
			
			
			
		


	}






}
