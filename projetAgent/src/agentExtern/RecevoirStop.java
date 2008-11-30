package agentExtern;

import java.util.ArrayList;

import protege.NouvellePhase;
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
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RecevoirStop extends CyclicBehaviour {

	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
	ContentManager manager = myAgent.getContentManager();
	Codec codec = new SLCodec();
	OntoCDOntology onto = (OntoCDOntology)OntoCDOntology.getInstance();


	public RecevoirStop(Agent a) {
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

				if (ce instanceof StopEverybody) {

					System.out.println(this.myAgent.getName()+ " a recu l'ordre s'arreter");
					
				}
			} catch (CodecException e) {
				e.printStackTrace();
			} catch (OntologyException e) {
				e.printStackTrace();
			}


		}








	}





}

