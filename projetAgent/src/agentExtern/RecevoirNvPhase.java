package agentExtern;


import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

import protege.NouvellePhase;
import protege.OK;
import protege.StopEverybody;

public class RecevoirNvPhase extends SimpleBehaviour {


	private static final long serialVersionUID = 1L;
	private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);


	public RecevoirNvPhase(Agent a) {
		super(a);

	}



	@Override
	public void action() {

		//System.out.println(myAgent.getName()+ " est en attente de recevoir un message de phase");
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
						System.out.println(this.myAgent.getName()+ " a recu l'ordre de demarrer phase 2");
						// Nous devons envoyer a tous nos agent qu'ils doivent s'arreter
						ACLMessage msgArret = new ACLMessage(ACLMessage.INFORM);
						msgArret.setLanguage(BusinessBehaviour.codec.getName());
						msgArret.setOntology(BusinessBehaviour.onto.getName());
						StopEverybody stop = new StopEverybody();

						ArrayList<String> listAgent = ((BusinessAgent)this.myAgent).getListeNosAgents();

						for(int i = 0;i<listAgent.size();i++){
							if(!listAgent.get(i).contains("hck_business_agent") && !listAgent.get(i).contains("client") ){
								System.out.println("Envoi de l'ordre de s'arreter à "+listAgent.get(i));
								msgArret.addReceiver(new AID(listAgent.get(i),AID.ISGUID));
							}
						}

						try {
							BusinessBehaviour.manager.fillContent(msgArret, stop);
							myAgent.send(msgArret);
						} catch (CodecException e) {
							e.printStackTrace();
						} catch (OntologyException e) {
							e.printStackTrace();
						}

						ACLMessage msgOK = new ACLMessage(ACLMessage.INFORM);
						msgOK.setLanguage(BusinessBehaviour.codec.getName());
						msgOK.setOntology(BusinessBehaviour.onto.getName());
						OK ok = new OK();
						BusinessBehaviour.manager.fillContent(msgOK, ok);
						msgOK.addReceiver(new AID(msg.getSender().getName(),AID.ISGUID));
						myAgent.send(msgOK);
						System.out.println("Le commerciale a envoyé OK a "+msg.getSender().getName());

					}
					else{
						System.out.println(this.myAgent.getName()+ " a recu l'ordre de demarrer phase 1");
						//TODO démarrer le travail des agent. Peut etre avec une variable
						//boolean dont chaque agent vérifirai sa valeur avant de se lancer dans
						//un cycle de travail. Pour les stoper on pourra mettre à false. Cela laissera
						//le temps a l'agent de finir son travail et de s'arreter puis d'envoyer STOP

					}


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
		return true;
	}



}
