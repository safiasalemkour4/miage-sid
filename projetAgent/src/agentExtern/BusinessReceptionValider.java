package agentExtern;

import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import protege.CD;
import protege.ValiderAchat;
import agentFreedom.ClientAgent;
import agentIntern.BankerAgent;
import agentIntern.StockManagerAgent;
import agentIntern.StrategyAgent;

public class BusinessReceptionValider extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
	

	public BusinessReceptionValider(Agent a) {
		super(a);
		
	}

	@Override
	public void action() {
		
		ACLMessage msg = this.myAgent.blockingReceive(mt);
		
		if (msg != null) {

			ContentElement ce;
			
			try {
				
				ce = BusinessBehaviour.manager.extractContent(msg);
				
				if (ce instanceof ValiderAchat) {

					ValiderAchat val = (ValiderAchat)ce;
					if(val.getReponse()){
						
						if(val.getDisc() instanceof CD){
							BusinessAgent.log.addText(this.myAgent.getName()+ " a recu une reponse positive du client pour les CD");
							StrategyAgent.lastRoundWinForCD = StrategyAgent.currentRound;
							
							BankerAgent.setMoney(BankerAgent.getMoney() + val.getQté()*StrategyAgent.prixCD);
							StockManagerAgent.nosStockCD -= val.getQté();
						}
						else{
							BusinessAgent.log.addText(this.myAgent.getName()+ " a recu une reponse positive du client pour les DVD");
							StrategyAgent.lastRoundWinForDVD = StrategyAgent.currentRound;
							
							BankerAgent.setMoney(BankerAgent.getMoney() + val.getQté()*StrategyAgent.prixDVD);
							StockManagerAgent.nosStockDVD -= val.getQté();
						}

					}
					else{
						BusinessAgent.log.addText(this.myAgent.getName()+ " a recu une reponse négative du client");
						
					}
					BusinessAgent.log.addText("---------------------------------------------------------------");
					BusinessAgent.log.addText("------------------------Apres la vente-------------------------");
					BusinessAgent.log.addText("---------------------------------------------------------------");
					BusinessAgent.log.addText("--> Nos prix sont desormais de : "+StrategyAgent.prixCD+"e pr les CDs & "+StrategyAgent.prixDVD+"e pr les DVDs.\n");
					BusinessAgent.log.addText("--> Nos stock sont desormais de : "+StockManagerAgent.nosStockCD+" CDs & "+StockManagerAgent.nosStockDVD+" DVDs.\n");
					BusinessAgent.log.addText("--> Notre argent en banque est desormais de : "+BankerAgent.getMoney());
					BusinessAgent.log.addText("---------------------------------------------------------------");
				}
			} catch (CodecException e) {
				e.printStackTrace();
			} catch (OntologyException e) {
				e.printStackTrace();
			}

		}
		
	}

	@Override
	public boolean done() {
		
		return true;
	}

}
