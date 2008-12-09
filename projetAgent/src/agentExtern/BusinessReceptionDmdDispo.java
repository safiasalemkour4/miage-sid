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

import protege.Disponible;
import protege.Disque;

public class BusinessReceptionDmdDispo extends SimpleBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

	public BusinessReceptionDmdDispo(Agent myAgent) {
		super(myAgent);
	}


	public void action() {

			
			ACLMessage msg = this.myAgent.blockingReceive(mt);
			
			if (msg != null) {

				ContentElement ce;
				try {
	
					ce = BusinessBehaviour.manager.extractContent(msg);
					if (ce instanceof Disponible) {
			
						BusinessAgent.log.addText(this.myAgent.getLocalName()+" a recu une dmd de dispo");
						int qté = ((Disponible)ce).getQte();
						Disque disc = ((Disponible)ce).getDisque();


						ACLMessage msgDmdStock = new ACLMessage(ACLMessage.REQUEST);
						msgDmdStock.setLanguage(BusinessBehaviour.codec.getName());
						msgDmdStock.setOntology(BusinessBehaviour.onto.getName());

						Disponible dispo = new Disponible();
						dispo.setDisque(disc);
						dispo.setQte(qté);
						BusinessAgent.discCourant = disc;
						BusinessAgent.qteCourante = qté;
						ArrayList<String> listAgent = ((BusinessAgent)this.myAgent).getListeNosAgents();


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

								if(sd.getType().equals("HCK_Stock")){

									msgDmdStock.addReceiver(new AID(sd.getOwnership(),AID.ISGUID));
									BusinessAgent.log.addText(this.myAgent.getLocalName()+" envoi dispo? a "+sd.getOwnership());
								}
							}
						}

						BusinessBehaviour.manager.fillContent(msgDmdStock, dispo);
						myAgent.send(msgDmdStock);
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
