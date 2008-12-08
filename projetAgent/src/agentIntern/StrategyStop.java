package agentIntern;


import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import protege.StopEverybody;
import agentExtern.BusinessBehaviour;
import agentFreedom.ClientAgent;

public class StrategyStop extends CyclicBehaviour {


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
						
						/************************************** ZONE CD ***************************************/
						
						// Si on a reussit a vendre a ce prix alors on continu
						if (StrategyAgent.currentRound == StrategyAgent.lastRoundWinForCD) {
							
						} 
						// Si ca fait 1 rounds qu'on a rien vendu alors on baisse le prix (un peu : -10%)
						else if (StrategyAgent.currentRound > StrategyAgent.lastRoundWinForCD && StrategyAgent.currentRound < StrategyAgent.lastRoundWinForCD + 5){
							
							StrategyAgent.prixCD -= StrategyAgent.prixCD*0.1;
							
						}
						// Si ca fait 5 rounds qu'on a rien vendu alors on baisse le prix (moyennement : -25%)
						else if (StrategyAgent.currentRound > StrategyAgent.lastRoundWinForCD +5 && StrategyAgent.currentRound < StrategyAgent.lastRoundWinForCD + 10){
							StrategyAgent.prixCD -= StrategyAgent.prixCD*0.25;
						} 
						// Si ca fait plus de 10 round qu'on a rien vendu alors on baisse le prix (fortement : -50%)
						else if (StrategyAgent.currentRound > StrategyAgent.lastRoundWinForCD + 10) {
							StrategyAgent.prixCD -= StrategyAgent.prixCD*0.50;
						}
						
						// Permet de ne pas vendre a perte
						if (StrategyAgent.prixCD<=ProducerAgent.CD_HIGHT_PRICE) {
							StrategyAgent.prixCD = ProducerAgent.CD_HIGHT_PRICE + 1.0;
						}
						
						/************************************** ZONE DVD ***************************************/
						
						// Si on a reussit a vendre a ce prix alors on continu
						if (StrategyAgent.currentRound == StrategyAgent.lastRoundWinForDVD) {
							
						} 
						// Si ca fait 1 rounds qu'on a rien vendu alors on baisse le prix (un peu : -10%)
						else if (StrategyAgent.currentRound > StrategyAgent.lastRoundWinForDVD && StrategyAgent.currentRound < StrategyAgent.lastRoundWinForDVD + 5){
							
							StrategyAgent.prixDVD -= StrategyAgent.prixDVD*0.1;
							
						}
						// Si ca fait 10 rounds qu'on a rien vendu alors on baisse le prix (moyennement : -25%)
						else if (StrategyAgent.currentRound > StrategyAgent.lastRoundWinForDVD +5 && StrategyAgent.currentRound < StrategyAgent.lastRoundWinForDVD + 10){
							StrategyAgent.prixDVD -= StrategyAgent.prixDVD*0.25;
						} 
						// Si ca fait plus de 10 round qu'on a rien vendu alors on baisse le prix (fortement : -50%)
						else if (StrategyAgent.currentRound > StrategyAgent.lastRoundWinForDVD + 10) {
							StrategyAgent.prixDVD -= StrategyAgent.prixDVD*0.50;
						}
						
						// Permet de ne pas vendre a perte
						if (StrategyAgent.prixDVD<=ProducerAgent.DVD_HIGHT_PRICE) {
							StrategyAgent.prixDVD = ProducerAgent.DVD_HIGHT_PRICE + 1.0;
						}
						
						ClientAgent.log.addText("Nos prix finaux sont desormais de : "+StrategyAgent.prixCD+"e pr les CDs & "+StrategyAgent.prixDVD+"e pr les DVDs.\n"+
								"Et nous possedons "+BankerAgent.getMoney()+" en banque.");
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
