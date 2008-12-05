package agentIntern;


import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

import agentExtern.BusinessBehaviour;
import agentFreedom.ClientAgent;

import protege.NouvellePhase;
import protege.OK;
import protege.StopEverybody;

public class StrategyStart extends SimpleBehaviour {


	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);


	public StrategyStart(Agent a) {
		super(a);

	}

	public void action() {

		//System.out.println(myAgent.getName()+ " est en attente de recevoir un message de phase");
		ACLMessage msg = this.myAgent.blockingReceive(mt);

		System.out.println("ok");
		
		if (msg != null) {

			ContentElement ce;

			System.out.println("ok 2");
			
				try {
					ce = BusinessBehaviour.manager.extractContent(msg);
					if (ce instanceof OK) {
						ClientAgent.log.addText(this.myAgent.getName()+ " a recu l'ordre de demarrer la strategie !");
					}
				} catch (UngroundedException e) {
					e.printStackTrace();
				} catch (CodecException e) {
					e.printStackTrace();
				} catch (OntologyException e) {

					e.printStackTrace();
				}
		}

	}

	public boolean done() {

		return true;
	}


}
