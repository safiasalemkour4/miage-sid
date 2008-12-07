package agentIntern;


import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
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

public class StrategyStart extends CyclicBehaviour {


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

					/* Demande d'informations sur les stocks */
					int stockCD = StockManagerAgent.nosStockCD;
					int stockDVD = StockManagerAgent.nosStockDVD;

					int nbCDForClient = ClientAgent.quantiteMap.get(0)[0]; //5000
					int nbDVDForClient = ClientAgent.quantiteMap.get(0)[1]; //4000

					/************************************** ZONE CD ***************************************/

					/* Le nombre de Cds que lon peut produire (au maximu et au pire) */
					int nbCDCanWeProduce = (int) (BankerAgent.getMoney() / ProducerAgent.CD_HIGHT_PRICE);
					
					ACLMessage msgProduceCD = new ACLMessage(ACLMessage.INFORM);
					msgProduceCD.setLanguage(StrategyBehaviour.codec.getName());
					msgProduceCD.setOntology(StrategyBehaviour.onto.getName());

					Disponible dispoCD = new Disponible();
					Boolean produceCD = true;

					// Si on a pas assez de CD
					if (stockCD<nbCDForClient) {

						if (nbCDForClient-stockCD+1000>nbCDCanWeProduce) {

							/* On produit moins que prévu (a cause de la banque) */
							dispoCD.setDisque(new CD());
							dispoCD.setQte(nbCDCanWeProduce);

						} else {

							/* On produit */
							dispoCD.setDisque(new CD());
							dispoCD.setQte(nbCDForClient-stockCD+1000);
						}


					} 

					// Si on a tout juste ce qu'il faut 
					else if (stockCD>=nbCDForClient && stockCD<nbCDForClient+1000){

						if (1000>nbCDCanWeProduce) {

							/* On produit moins que prévu (a cause de la banque) */
							dispoCD.setDisque(new CD());
							dispoCD.setQte(nbCDCanWeProduce);

						} else {

							/* On produit 1000 */
							dispoCD.setDisque(new CD());
							dispoCD.setQte(1000);
						}

					}

					// Sinon on ne fait rien
					else {
						produceCD = false;
					}

					
					if (produceCD) {

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
									msgProduceCD.addReceiver(new AID(sd.getOwnership(),AID.ISGUID));
									ClientAgent.log.addText(this.myAgent.getLocalName()+" envoie une demande de production de CD a "+sd.getOwnership());
								}

							}
						}

						StrategyBehaviour.manager.fillContent(msgProduceCD, dispoCD);

						myAgent.send(msgProduceCD);
					}

					
					/* On attend la mis a jour de la banque */
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					/************************************** ZONE DVD ***************************************/

					/* Le nombre de Cds que lon peut produire (au maximum et au pire) */
					int nbDVDCanWeProduce = (int) (BankerAgent.getMoney() / ProducerAgent.DVD_HIGHT_PRICE);

					ACLMessage msgProduceDVD = new ACLMessage(ACLMessage.INFORM);
					msgProduceDVD.setLanguage(StrategyBehaviour.codec.getName());
					msgProduceDVD.setOntology(StrategyBehaviour.onto.getName());

					Disponible dispoDVD = new Disponible();
					Boolean produceDVD = true;

					// Si on a pas assez de DVD
					if (stockDVD<nbDVDForClient) {

						if (nbDVDForClient-stockDVD+1000>nbDVDCanWeProduce) {

							/* On produit moins que prévu (a cause de la banque) */
							dispoDVD.setDisque(new DVD());
							dispoDVD.setQte(nbDVDCanWeProduce);

						} else {

							/* On produit */
							dispoDVD.setDisque(new DVD());
							dispoDVD.setQte(nbDVDForClient-stockDVD+1000);
						}
					} 

					// Si on a tout juste ce qui faut 
					else if (stockDVD>=nbDVDForClient && stockDVD<nbDVDForClient+1000){
						System.out.println("4");
						
						if (1000>nbDVDCanWeProduce) {

							/* On produit moins que prévu (a cause de la banque) */
							dispoDVD.setDisque(new DVD());
							dispoDVD.setQte(nbDVDCanWeProduce);

						} else {

							/* On produit 1000 */
							dispoDVD.setDisque(new DVD());
							dispoDVD.setQte(1000);
						}
					}

					// Sinon on ne fait rien
					else {
						produceDVD = false;
					}

					if (produceDVD) {
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

								if(sd.getType().equals("HCK_ProductionDVD")){

									msgProduceDVD.addReceiver(new AID(sd.getOwnership(),AID.ISGUID));
									ClientAgent.log.addText(this.myAgent.getLocalName()+" envoie une demande de production de DVD a "+sd.getOwnership());
								}

							}
						}

						StrategyBehaviour.manager.fillContent(msgProduceDVD, dispoDVD);

						myAgent.send(msgProduceDVD);
					}

					/* Important : On met a jour le  round */
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

	


}
