package agentFreedom;

import protege.NouvellePhase;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Comportement Envoyant l'avis de phase 1 aux commerciaux
 * @author Simon
 *
 */
@SuppressWarnings("serial")
public class ClientEnvoiPhase1 extends SimpleBehaviour{

	public ClientEnvoiPhase1(Agent myAgent) {
		super(myAgent);
	}

	@Override
	public void action() {
		/* Envoi d'un message Ã  tous les commerciaux pour leur signaler le dÃ©but de la phase de production */
		for(String s : ClientAgent.commerciaux){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setLanguage(ClientBehaviour.codec.getName());
			msg.setOntology(ClientBehaviour.onto.getName());
			try {
				ClientAgent.log.addText("			[CLIENT] Le client va envoyer un mesg de phase 1 a "+s);
				msg.addReceiver(new AID(s,AID.ISGUID));
				NouvellePhase nph = new NouvellePhase();
				nph.setNumeroPhase(NouvellePhase.PHASE_PROD);

				ClientBehaviour.manager.fillContent(msg, nph);
				myAgent.send(msg);

			} catch (OntologyException e) {
				e.printStackTrace();
			} catch (jade.content.lang.Codec.CodecException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
