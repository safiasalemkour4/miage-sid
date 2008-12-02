package agentFreedom;

import jade.content.ContentElement;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ClientAttenteOK extends SimpleBehaviour{

	public ClientAttenteOK(Agent myAgent) {
		super(myAgent);
	}

	

	@Override
	public void action() {


		/* RÃ©ception tous les messages "OK" */
		System.out.println("Attente des messages 'OK' des commerciaux ...");
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ContentElement ce;
		int ok_recus = 0;
		while(ok_recus < ClientAgent.commerciaux.size()){
			ok_recus++;
//			try {
//				/* Si aucun message n'est recu, le client est bloque ici */
//				ACLMessage msg_recu = myAgent.receive(mt);
//				
//				ce = manager.extractContent(msg_recu);
//				if(ce instanceof OK){
//					ok_recus++;
//				}
//			} catch (UngroundedException e) {
//				e.printStackTrace();
//			} catch (jade.content.lang.Codec.CodecException e) {
//				e.printStackTrace();
//			} catch (OntologyException e) {
//				e.printStackTrace();
//			}
		}
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
