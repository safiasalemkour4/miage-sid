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

import java.util.Iterator;

import protege.CD;
import protege.DVD;
import protege.Disponible;
import protege.OK;
import agentFreedom.ClientAgent;

public class StrategyStart extends SimpleBehaviour {


	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);


	public StrategyStart(Agent a) {
		super(a);

	}

	public void action() {

		ACLMessage msg = this.myAgent.blockingReceive(mt);

		if (msg != null) {

			ContentElement ce;

			try {

				ce = StrategyBehaviour.manager.extractContent(msg);

				if (ce instanceof OK) {

					ClientAgent.log.addText(this.myAgent.getName()+ " a recu l'ordre de demarrer la strategie !");

					/* TODO : Pardon pr ca = msg demande informations */
					int stockCD = StockManagerAgent.nosStockCD;
					int stockDVD = StockManagerAgent.nosStockDVD;

					int nbCDForClient = ClientAgent.quantiteMap.get(0)[0];
					int nbDVDForClient = 0; // TODO ClientAgent.quantiteMap.get(0)[0];

					ACLMessage msgProduce = new ACLMessage(ACLMessage.INFORM);
					msgProduce.setLanguage(StrategyBehaviour.codec.getName());
					msgProduce.setOntology(StrategyBehaviour.onto.getName());

					Disponible dispo = new Disponible();
					Boolean produce = true;

					// Si on a pas assez de CD
					if (stockCD<nbCDForClient) {

						// Send msg to produceur pour nbCDForClient-stockCD + 1000
						dispo.setDisque(new CD());
						dispo.setQte(nbCDForClient-stockCD+1000);
					} 

					// Si on a tout juste ce qui faut 
					else if (stockCD>=nbCDForClient && stockCD<nbCDForClient+1000){

						// Send msg to produceur pr 1000
						dispo.setDisque(new CD());
						dispo.setQte(1000);
					}

					// Sinon on ne fait rien
					else {
						produce = false;
					}

					// Si on a pas assez de DVD
					if (stockDVD<nbDVDForClient) {

						// Send msg to produceur pour nbCDForClient-stockDVD + 1000
						dispo.setDisque(new DVD());
						dispo.setQte(nbDVDForClient-stockDVD+1000);
					} 

					// Si on a tout juste ce qui faut 
					else if (stockCD>=nbDVDForClient && stockDVD<nbCDForClient+1000){

						// Send msg to produceur pr 1000
						dispo.setDisque(new DVD());
						dispo.setQte(1000);
					}

					// Sinon on ne fait rien
					else {
						produce = false;
					}

					if (produce) {
						/* On recherche l'agent producteur et on lui envoit le message */


						DFAgentDescription dfd = new DFAgentDescription();
						DFAgentDescription[] result = null;
						try {
							result = DFService.search(this.myAgent, dfd);
						} catch (FIPAException e) {
							e.printStackTrace();
						}
						for (int i = 0; i<result.length; i++) {

							Iterator<Object> iter = result[i].getAllServices();

							while (iter.hasNext()) {

								ServiceDescription sd =(ServiceDescription)iter.next();

								if(sd.getType().equals("HCK_ProductionCD")){

									System.out.println("a");
									msgProduce.addReceiver(new AID(sd.getOwnership(),AID.ISGUID));
									ClientAgent.log.addText(this.myAgent.getLocalName()+" envoie une demande de production a "+sd.getOwnership());
								}

							}
						}
						
						StrategyBehaviour.manager.fillContent(msgProduce, dispo);

						myAgent.send(msgProduce);
					}

					StrategyAgent.currentRound++;
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
