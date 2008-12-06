package agentFreedom;

import protege.NouvellePhase;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;


/**
 * Comportement Envoyant l'avis de phase 2 aux commerciaux
 * @author Simon
 * *
 */
public class ClientEnvoiPhase2 extends SimpleBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientEnvoiPhase2(Agent myAgent) {
		super(myAgent);
	}

	@Override
	public void action() {
		
		/* Envoi Ã  tous les commerciaux du message leur indiquant d'arreter leur production et leurs achats */
		for(String com : ClientAgent.commerciaux){
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setLanguage(ClientBehaviour.codec.getName());
			msg.setOntology(ClientBehaviour.onto.getName());
			try {
				ClientAgent.log.addText("			[CLIENT] Le client va envoyer un mesg de phase 2 a "+com);
				msg.addReceiver(new AID(com, AID.ISGUID));
				NouvellePhase nph = new NouvellePhase();
				nph.setNumeroPhase(NouvellePhase.PHASE_ACHAT);
				
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
