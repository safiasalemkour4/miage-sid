package agentIntern;


import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.Iterator;

import agentExtern.BusinessAgent;
import agentExtern.BusinessBehaviour;
import agentFreedom.ClientAgent;

import protege.Disponible;
import protege.Disque;
import protege.NouvellePhase;
import protege.OK;
import protege.StopEverybody;

public class StrategyStop extends SimpleBehaviour {


	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);


	public StrategyStop(Agent a) {
		super(a);

	}

	public void action() {

		//System.out.println(myAgent.getName()+ " est en attente de recevoir un message de phase");
		ACLMessage msg = this.myAgent.blockingReceive(mt);
		
		if (msg != null) {

			ContentElement ce;

				try {
					
					ce = BusinessBehaviour.manager.extractContent(msg);
					
					if (ce instanceof StopEverybody) {
						ClientAgent.log.addText(this.myAgent.getName()+ " a recu l'ordre de stoper la strategie !");
				
					
					}
					
					/*
					•	La production d’un CD coute :
						o	3€ pour moins de 100 unités.
						o	2,5€ pour moins de 1000.
						o	2€ pour 1000 et plus.
						
						•	La production d’un DVD coute 
						o	6€ pour moins de 100 unités.
						o	5€ pour moins de 1000.
						o	4€ pour 1000 et plus.
*/
				
					StrategyAgent.currentRound++;
					
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
