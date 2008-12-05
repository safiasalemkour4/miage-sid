package agentFreedom;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import protege.ValiderAchat;

public class ClientEnvoiValider extends SimpleBehaviour{

	
	
	
	public ClientEnvoiValider(Agent a) {
		super(a);
	
	}

	@Override
	public void action() {
		
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setLanguage(ClientBehaviour.codec.getName());
		msg.setOntology(ClientBehaviour.onto.getName());
		ClientAgent.log.addText("Le client va envoyer une validation a "+ClientAgent.commercialElu);
		
		msg.addReceiver(new AID(ClientAgent.commercialElu,AID.ISGUID));
		ValiderAchat val = new ValiderAchat();
		val.setReponse(true);
		this.myAgent.send(msg);
		
	}

	@Override
	public boolean done() {


		return true;
	}
	
	

}
