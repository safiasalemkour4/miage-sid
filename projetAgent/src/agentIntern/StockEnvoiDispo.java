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
import agentExtern.BusinessBehaviour;

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

			int notrePrix = StrategyAgent.prixCD;

			try {

				ce = BusinessBehaviour.manager.extractContent(msg);
				if (ce instanceof Disponible) {
					System.out.println("Le stock manager a recu une dmd de dispo");
					int qté = ((Disponible)ce).getQte();
					Disque disc = ((Disponible)ce).getDisque();

					if(disc instanceof CD){
						int nosStockCD = ((StockManagerAgent)this.myAgent).getNosStockCD();
						if(nosStockCD < qté){
							notrePrix = -1;

						}
						else {

							notrePrix = StrategyAgent.prixCD;
						}
					}
					else{
						int nosStockDVD = ((StockManagerAgent)this.myAgent).getNosStockDVD();
						if(nosStockDVD < qté){

							notrePrix = -1;
						}
						else {

							notrePrix = StrategyAgent.prixDVD;
						}
					}


					ACLMessage msgRepStock = new ACLMessage(ACLMessage.INFORM);
					msgRepStock.setLanguage(BusinessBehaviour.codec.getName());
					msgRepStock.setOntology(BusinessBehaviour.onto.getName());

					ReponseDisponibilite repDispo = new ReponseDisponibilite();
					repDispo.setDisque(disc);
					repDispo.setPrix(notrePrix);

					msgRepStock.addReceiver(new AID(msg.getSender().getName(),AID.ISGUID));
					System.out.println("Le stock envoi réponse a "+msg.getSender().getName());


					BusinessBehaviour.manager.fillContent(msgRepStock, repDispo);

					myAgent.send(msgRepStock);
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

}
