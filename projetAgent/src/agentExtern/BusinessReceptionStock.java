package agentExtern;

import java.util.ArrayList;

import protege.CD;
import protege.Disponible;
import protege.Disque;
import protege.ReponseDisponibilite;
import agentFreedom.ClientAgent;
import agentIntern.StockManagerAgent;
import agentIntern.StrategyAgent;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class BusinessReceptionStock extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);


	public BusinessReceptionStock(Agent a) {
		super(a);

	}

	@Override
	public void action() {
		
		ACLMessage msg = this.myAgent.blockingReceive(mt);
		
		if (msg != null) {

			ContentElement ce;

			
			try {
				
				ce = BusinessBehaviour.manager.extractContent(msg);
				
				if (ce instanceof ReponseDisponibilite) {
					
					ClientAgent.log.addText("Le commercial a recu une réponse de stock");
					int prix = ((ReponseDisponibilite)ce).getPrix();
					Disque disc = ((ReponseDisponibilite)ce).getDisque();

					ACLMessage msgRepStock = new ACLMessage(ACLMessage.INFORM);
					msgRepStock.setLanguage(BusinessBehaviour.codec.getName());
					msgRepStock.setOntology(BusinessBehaviour.onto.getName());

					ReponseDisponibilite repDispo = new ReponseDisponibilite();
					repDispo.setDisque(disc);
					repDispo.setPrix(prix);
					ArrayList<String> listeAgent = ((BusinessAgent)this.myAgent).getListeNosAgents();
					String nomClient ="";
					
					// On recherche l'agent client parmi notre annuaire
					for(String agent : listeAgent){
						System.out.println(agent);
						if(agent.contains("client")){
							nomClient = agent;
						}
					}
					
					msgRepStock.addReceiver(new AID(nomClient,AID.ISGUID));
					ClientAgent.log.addText("Le commerciale envoi le prix a "+nomClient);


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

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}



	
}