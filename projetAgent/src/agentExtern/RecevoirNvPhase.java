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

import agentIntern.BankerAgent;
import agentIntern.StockManagerAgent;
import agentIntern.StrategyAgent;

import protege.NouvellePhase;
import protege.OK;

public class RecevoirNvPhase extends SimpleBehaviour {


	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);


	public RecevoirNvPhase(Agent a) {
		super(a);

	}

	public void action() {
		
		ACLMessage msg = this.myAgent.blockingReceive(mt);
		
		if (msg != null) {

			ContentElement ce;
			try {

				ce = BusinessBehaviour.manager.extractContent(msg);
				
				if (ce instanceof NouvellePhase) {
					int numPhase = ((NouvellePhase)ce).getNumeroPhase();
					// On ajoute le client a notre annuaire si il n'y est pas deja
					ArrayList<String> listagent = ((BusinessAgent)this.myAgent).getListeNosAgents();
					boolean clientpresent = false;
					for (String agentconnu : listagent){
						if(agentconnu.contains("client")){
							
							clientpresent = true;
						}
					}
					if(!clientpresent) listagent.add(msg.getSender().getName());
					
					if(numPhase == 2){
						BusinessAgent.log.addText("------------> Phase 2 <------------");
						BusinessAgent.log.addText(this.myAgent.getName()+ " a recu l'ordre de demarrer phase 2");
			
						ACLMessage msgOK = new ACLMessage(ACLMessage.INFORM);
						msgOK.setLanguage(BusinessBehaviour.codec.getName());
						msgOK.setOntology(BusinessBehaviour.onto.getName());
						OK ok = new OK();
						BusinessBehaviour.manager.fillContent(msgOK, ok);
						msgOK.addReceiver(new AID(msg.getSender().getName(),AID.ISGUID));
						myAgent.send(msgOK);
						BusinessAgent.log.addText("Le commerciale a envoyé OK a "+msg.getSender().getName());

					}
					else{
						
						BusinessAgent.log.addText("---------------------------------------------------------------");
						BusinessAgent.log.addText("------------------------Avant la vente-------------------------");
						BusinessAgent.log.addText("---------------------------------------------------------------");
						BusinessAgent.log.addText("--> Nos prix sont desormais de : "+StrategyAgent.prixCD+"e pr les CDs & "+StrategyAgent.prixDVD+"e pr les DVDs.\n");
						BusinessAgent.log.addText("--> Nos stock sont desormais de : "+StockManagerAgent.nosStockCD+" CDs & "+StockManagerAgent.nosStockDVD+" DVDs.\n");
						BusinessAgent.log.addText("--> Notre argent en banque est desormais de : "+BankerAgent.getMoney());
						BusinessAgent.log.addText("---------------------------------------------------------------");
						BusinessAgent.infos.update();
						
						BusinessAgent.log.addText("------------> Phase 1 <------------");
						/* Si numPhase = 1 */
						BusinessAgent.log.addText(this.myAgent.getName()+ " a recu l'ordre de demarrer phase 1");

						ACLMessage msgOK = new ACLMessage(ACLMessage.INFORM);
						msgOK.setLanguage(BusinessBehaviour.codec.getName());
						msgOK.setOntology(BusinessBehaviour.onto.getName());
						OK ok = new OK();
						
						BusinessBehaviour.manager.fillContent(msgOK, ok);
												
						// On recherche l'agent strategy parmi notre annuaire
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

								if(sd.getType().equals("HCK_Strategie")){

									msgOK.addReceiver(new AID(sd.getOwnership(),AID.ISGUID));
									BusinessAgent.log.addText("Le commerciale a envoyé OK a "+sd.getOwnership());
								}
							}
						}
						myAgent.send(msgOK);
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

	@Override
	public boolean done() {

		return true;
	}
}
