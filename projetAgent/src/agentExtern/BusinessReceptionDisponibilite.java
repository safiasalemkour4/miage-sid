package agentExtern;

import java.util.ArrayList;

import agentIntern.StrategyAgent;

import protege.Disponible;
import protege.NouvellePhase;
import protege.StopEverybody;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class BusinessReceptionDisponibilite extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

	public BusinessReceptionDisponibilite(Agent myAgent) {
		super(myAgent);
	}

	@Override
	public void action() {

		ACLMessage msg = this.myAgent.blockingReceive(mt);


		if (msg != null) {

			ContentElement ce;
			try {

				ce = BusinessBehaviour.manager.extractContent(msg);
				if (ce instanceof Disponible) {

					int qté = ((Disponible)ce).getQte();
					

					System.out.println(this.myAgent.getName()+ " a recu l'ordre de demarrer phase 2");
					// Nous devons envoyer a tous nos agent qu'ils doivent s'arreter
					ACLMessage msgArret = new ACLMessage(ACLMessage.INFORM);
					msgArret.setLanguage(BusinessBehaviour.codec.getName());
					msgArret.setOntology(BusinessBehaviour.onto.getName());
					StopEverybody stop = new StopEverybody();

					ArrayList<String> listAgent = ((BusinessAgent)this.myAgent).getListeNosAgents();

					for(int i = 0;i<listAgent.size();i++){
						System.out.println("Envoi de l'ordre de s'arreter à "+listAgent.get(i));
						msgArret.addReceiver(new AID(listAgent.get(i),AID.ISGUID));
					}

					try {
						BusinessBehaviour.manager.fillContent(msgArret, stop);
						myAgent.send(msgArret);
					} catch (CodecException e) {
						e.printStackTrace();
					} catch (OntologyException e) {
						e.printStackTrace();
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






@Override
public boolean done() {

	return true;
}

}
