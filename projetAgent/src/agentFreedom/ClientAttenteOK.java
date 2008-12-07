package agentFreedom;

import protege.OK;
import jade.content.ContentElement;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Comportement qui attend de recevoir tous les messages OK venant des commerciaux
 * @author Simon
 *
 */
@SuppressWarnings("serial")
public class ClientAttenteOK extends SimpleBehaviour{

	public ClientAttenteOK(Agent myAgent) {
		super(myAgent);
	}

	@Override
	public void action() {


		/* Reception tous les messages "OK" */
		ClientAgent.log.addText("			[CLIENT] Attente des messages 'OK' des commerciaux ...");
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ContentElement ce;
		int ok_recus = 0;
		while(ok_recus < ClientAgent.commerciaux.size()){
			ok_recus++;
			try {
				/* Si aucun message n'est recu, le client est bloque ici */
				ACLMessage msg_recu = myAgent.blockingReceive(mt);
				
				ce = ClientBehaviour.manager.extractContent(msg_recu);
				
				if(ce instanceof OK){
					ClientAgent.log.addText("			[CLIENT] Le client a recu un OK");
					ok_recus++;
				}
			} catch (UngroundedException e) {
				e.printStackTrace();
			} catch (jade.content.lang.Codec.CodecException e) {
				e.printStackTrace();
			} catch (OntologyException e) {
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
