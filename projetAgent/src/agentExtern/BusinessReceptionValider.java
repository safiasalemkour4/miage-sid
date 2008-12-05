package agentExtern;

import protege.StopEverybody;
import protege.ValiderAchat;
import agentFreedom.ClientAgent;
import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class BusinessReceptionValider extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
	

	public BusinessReceptionValider(Agent a) {
		super(a);
		
	}

	@Override
	public void action() {
		
		ClientAgent.log.addText("avant blocking");
		ACLMessage msg = this.myAgent.blockingReceive(mt);
		ClientAgent.log.addText("apres blocking");
		ContentManager manager = myAgent.getContentManager();
		if (msg != null) {

			ContentElement ce;


			try {
				ce = manager.extractContent(msg);
				ClientAgent.log.addText("ce: "+ce);
				if (ce instanceof ValiderAchat) {

					ValiderAchat val = (ValiderAchat)ce;
					if(val.getReponse()){
						ClientAgent.log.addText(this.myAgent.getName()+ " a recu une reponse positive du client");
					}
					else{
						ClientAgent.log.addText(this.myAgent.getName()+ " a recu une reponse négative du client");

					}
					
					// mettre la variable arret a true
				}
			} catch (CodecException e) {
				e.printStackTrace();
			} catch (OntologyException e) {
				e.printStackTrace();
			}


		}

	}

	@Override
	public boolean done() {
		
		return true;
	}

}
