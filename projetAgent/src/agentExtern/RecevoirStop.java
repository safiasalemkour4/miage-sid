package agentExtern;

import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import protege.OntoCDOntology;
import protege.StopEverybody;
import agentFreedom.ClientAgent;

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

					ClientAgent.log.addText(this.myAgent.getName()+ " a recu l'ordre de s'arreter");
					// mettre la variable arret a true
				}
			} catch (CodecException e) {
				e.printStackTrace();
			} catch (OntologyException e) {
				e.printStackTrace();
			}
		}

	}

}

