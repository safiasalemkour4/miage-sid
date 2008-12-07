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

import protege.CD;
import protege.Disponible;
import protege.Disque;
import protege.NouvellePhase;
import protege.OK;
import protege.StopEverybody;

public class ProduceDisc extends SimpleBehaviour {


	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);


	public ProduceDisc(Agent a) {
		super(a);

	}

	public void action() {

		//System.out.println(myAgent.getName()+ " est en attente de recevoir un message de phase");
		ACLMessage msg = this.myAgent.blockingReceive(mt);

		if (msg != null) {

			ContentElement ce;

			try {
				ce = BusinessBehaviour.manager.extractContent(msg);
				if (ce instanceof Disponible) {

					ClientAgent.log.addText(this.myAgent.getName()+ " a recu l'ordre de produire des disques !");

					int nbDisc = ((Disponible)ce).getQte();
					Disque disc = ((Disponible)ce).getDisque();

					if (disc instanceof CD) {

						StockManagerAgent.nosStockCD += nbDisc;
						
						if (nbDisc<100) {

							ClientAgent.log.addText(this.myAgent.getName()+ " produit "+nbDisc+" CDs a "+ProducerAgent.CD_HIGHT_PRICE+" euro piece.");
							
							BankerAgent.money -= ProducerAgent.CD_HIGHT_PRICE;

						} else if (nbDisc>=100 & nbDisc<1000) {

							ClientAgent.log.addText(this.myAgent.getName()+ " produit "+nbDisc+" CDs a "+ProducerAgent.CD_MEDIUM_PRICE+" euro piece.");
							BankerAgent.money -= ProducerAgent.CD_MEDIUM_PRICE;

						} else if (nbDisc>=1000) {

							ClientAgent.log.addText(this.myAgent.getName()+ " produit "+nbDisc+" CDs a "+ProducerAgent.CD_LOW_PRICE+" euro piece.");
							BankerAgent.money -= ProducerAgent.CD_LOW_PRICE;
						}
					} 

					else {

						
						StockManagerAgent.nosStockDVD += nbDisc;
						
						if (nbDisc<100) {

							ClientAgent.log.addText(this.myAgent.getName()+ " produit "+nbDisc+" DVDs a "+ProducerAgent.DVD_HIGHT_PRICE+" euro piece.");
							BankerAgent.money -= ProducerAgent.DVD_HIGHT_PRICE;

						} else if (nbDisc>=100 & nbDisc<1000) {

							ClientAgent.log.addText(this.myAgent.getName()+ " produit "+nbDisc+" DVDs a "+ProducerAgent.DVD_MEDIUM_PRICE+" euro piece.");
							BankerAgent.money -= ProducerAgent.DVD_MEDIUM_PRICE;

						} else if (nbDisc>=1000) {
							ClientAgent.log.addText(this.myAgent.getName()+ " produit "+nbDisc+" DVDs a "+ProducerAgent.DVD_LOW_PRICE+" euro piece.");
							BankerAgent.money -= ProducerAgent.DVD_LOW_PRICE;
						}
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
