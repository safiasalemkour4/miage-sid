package agentExtern;

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

import protege.Disque;
import protege.ReponseDisponibilite;
import agentFreedom.ClientAgent;
import agentIntern.StrategyBehaviour;

public class BusinessReceptionStock extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);


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

						BusinessAgent.log.addText(this.myAgent.getLocalName()+" a recu une réponse de stock");
						double prix = ((ReponseDisponibilite)ce).getPrix();
						Disque disc = ((ReponseDisponibilite)ce).getDisque();

						ACLMessage msgRepStock = new ACLMessage(ACLMessage.INFORM);
						msgRepStock.setLanguage(BusinessBehaviour.codec.getName());
						msgRepStock.setOntology(BusinessBehaviour.onto.getName());

						ReponseDisponibilite repDispo = new ReponseDisponibilite();
						repDispo.setDisque(disc);
						repDispo.setPrix(prix);
		

						// On recherche l'agent client parmi notre annuaire
						DFAgentDescription dfd = new DFAgentDescription();
						DFAgentDescription[] result = null;
						try {
							result = DFService.search(this.myAgent, dfd);
						} catch (FIPAException e) {
							e.printStackTrace();
						}
						
						msgRepStock.addReceiver(new AID("Client",AID.ISLOCALNAME));
	
						BusinessBehaviour.manager.fillContent(msgRepStock, repDispo);

						myAgent.send(msgRepStock);
					}
					else{
						this.action();
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

	@Override
	public boolean done() {
		
		return true;
	}
}
