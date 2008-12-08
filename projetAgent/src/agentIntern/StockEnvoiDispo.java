package agentIntern;

import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import protege.CD;
import protege.Disponible;
import protege.Disque;
import protege.ReponseDisponibilite;
import agentExtern.BusinessAgent;
import agentExtern.BusinessBehaviour;
import agentFreedom.ClientAgent;

public class StockEnvoiDispo extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

	public StockEnvoiDispo(Agent a) {
		super(a);

	}

	@Override
	public void action() {
		
		ACLMessage msg = this.myAgent.blockingReceive(mt);
		
		if (msg != null) {

			ContentElement ce;

			double notrePrix = StrategyAgent.prixCD;

			try {
				
				ce = BusinessBehaviour.manager.extractContent(msg);
				if (ce instanceof Disponible) {
					BusinessAgent.log.addText(this.myAgent.getLocalName()+" a recu une dmd de dispo");
					double qté = ((Disponible)ce).getQte();
					Disque disc = ((Disponible)ce).getDisque();

					if(disc instanceof CD){
						int nosStockCD = StockManagerAgent.nosStockCD;
						if(nosStockCD < qté){
							notrePrix = -1;

						}
						else {

							notrePrix = StrategyAgent.prixCD;
						}
					}
					else{
						int nosStockDVD = StockManagerAgent.nosStockDVD;
						if(nosStockDVD < qté){

							notrePrix = -1;
						}
						else {

							notrePrix = StrategyAgent.prixDVD;
						}
					}


					ACLMessage msgRepStock = new ACLMessage(ACLMessage.REQUEST);
					msgRepStock.setLanguage(StockManagerAgent.codec.getName());
					msgRepStock.setOntology(StockManagerAgent.onto.getName());

					ReponseDisponibilite repDispo = new ReponseDisponibilite();
					repDispo.setDisque(disc);
					repDispo.setPrix(notrePrix);

					msgRepStock.addReceiver(new AID(msg.getSender().getName(),AID.ISGUID));
					BusinessAgent.log.addText(this.myAgent.getLocalName()+" envoi réponse a "+msg.getSender().getLocalName());
					
					
					StockManagerAgent.manager.fillContent(msgRepStock, repDispo);
					
					msgRepStock.setSender(this.myAgent.getAID());
					myAgent.send(msgRepStock);
					
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
