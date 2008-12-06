package agentExtern;

import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import protege.ValiderAchat;
import agentFreedom.ClientAgent;

public class BusinessReceptionValider extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
	

	public BusinessReceptionValider(Agent a) {
		super(a);
		
	}

	@Override
	public void action() {
		
		
		ACLMessage msg = this.myAgent.blockingReceive(mt);
		
		
		if (msg != null) {

			ContentElement ce;
			

			try {
				
				ce = BusinessBehaviour.manager.extractContent(msg);
				
				if (ce instanceof ValiderAchat) {

					ValiderAchat val = (ValiderAchat)ce;
					if(val.getReponse()){
						ClientAgent.log.addText(this.myAgent.getName()+ " a recu une reponse positive du client");
					}
					else{
						ClientAgent.log.addText(this.myAgent.getName()+ " a recu une reponse négative du client");

					}
					
					
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
