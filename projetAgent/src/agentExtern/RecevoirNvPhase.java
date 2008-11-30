package agentExtern;


import protege.NouvellePhase;
import protege.OntoCDOntology;
import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RecevoirNvPhase extends CyclicBehaviour {

	
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
	ContentManager manager = myAgent.getContentManager();
	Codec codec = new SLCodec();
	OntoCDOntology onto = (OntoCDOntology)OntoCDOntology.getInstance();
	

	
	
	
	public RecevoirNvPhase(Agent a) {
		super(a);
		System.out.println("Demarrage du behaviour RecevoirNvPhase par "+this.myAgent.getName());
		manager.registerLanguage(codec);
	    manager.registerOntology(onto);
		
	}

	
	
	@Override
	public void action() {
		
		
		ACLMessage msg = this.myAgent.blockingReceive(mt);
		
		ContentManager manager = myAgent.getContentManager();
		if (msg != null) {

			ContentElement ce;
			try {
				
				ce = manager.extractContent(msg);
				if (ce instanceof NouvellePhase) {
					int numPhase = ((NouvellePhase)ce).getNumeroPhase();
					System.out.println("J'ai recu l'ordre de demarrer phase "+numPhase);
					
					if(numPhase == 1){
						
					}
					else{
						
					}
					
					
				}
			} catch (UngroundedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CodecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OntologyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		
		
	}

	

}
