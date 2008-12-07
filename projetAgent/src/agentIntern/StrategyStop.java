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
						
						// Si on a reussit a vendre a ce prix alors on continu
						if (StrategyAgent.currentRound == StrategyAgent.lastRoundWin) {
							
						} 
						// Si ca fait 5 rounds qu'on a rien vendu alors on baisse le prix (un peu : -10%)
						else if (StrategyAgent.currentRound > StrategyAgent.lastRoundWin && StrategyAgent.currentRound < StrategyAgent.lastRoundWin + 5){
							
							StrategyAgent.prixCD -= StrategyAgent.prixCD*0.1;
							
						}
						// Si ca fait 10 rounds qu'on a rien vendu alors on baisse le prix (moyennement : -25%)
						else if (StrategyAgent.currentRound > StrategyAgent.lastRoundWin && StrategyAgent.currentRound < StrategyAgent.lastRoundWin + 10){
							StrategyAgent.prixCD -= StrategyAgent.prixCD*0.25;
						} 
						// Si ca fait plus de 10 round qu'on a rien vendu alors on baisse le prix (fortement : -50%)
						else {
							StrategyAgent.prixCD -= StrategyAgent.prixCD*0.50;
						}
						
						// permet de ne pas vendre a perte
						if (StrategyAgent.prixCD<=ProducerAgent.CD_HIGHT_PRICE) {
							StrategyAgent.prixCD = ProducerAgent.CD_HIGHT_PRICE + 1.0;
						}
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
