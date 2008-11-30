package agentExtern;


import java.util.ArrayList;


import protege.NouvellePhase;
import protege.OK;
import protege.OntoCDOntology;
import protege.StopEverybody;
import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.AID;
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
					System.out.println(this.myAgent.getName()+ " a recu l'ordre de demarrer phase "+numPhase);
					
					if(numPhase == 2){
						
						// Nous devons envoyer a tous nos agent qu'ils doivent s'arreter
						ACLMessage msgArret = new ACLMessage(ACLMessage.INFORM);
						msgArret.setLanguage(codec.getName());
						msgArret.setOntology(onto.getName());
						StopEverybody stop = new StopEverybody();

						ArrayList<String> listAgent = ((BusinessAgent)this.myAgent).getListeNosAgents();
						System.out.println("listeAgent size : "+listAgent.size());
						for(int i = 0;i<listAgent.size();i++){
							System.out.println("Envoi de l'ordre de s'arreter à "+listAgent.get(i));
							msgArret.addReceiver(new AID(listAgent.get(i),AID.ISGUID));
						}
						
						try {
							manager.fillContent(msgArret, stop);
							myAgent.send(msgArret);
						} catch (CodecException e) {
							e.printStackTrace();
						} catch (OntologyException e) {
							e.printStackTrace();
						}
						
											
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
