package agentFreedom;

import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import protege.CD;
import protege.DVD;
import protege.Disque;
import protege.ValiderAchat;

public class ClientEnvoiValider extends SimpleBehaviour{

	private int qté;
	private String disc;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientEnvoiValider(Agent a, String disque, int quantite) {
		super(a);
		
		this.disc = disque;
		this.qté = quantite;

	}

	@Override
	public void action() {

		try {
			ACLMessage msgOui = new ACLMessage(ACLMessage.INFORM);
			msgOui.setLanguage(ClientBehaviour.codec.getName());
			msgOui.setOntology(ClientBehaviour.onto.getName());
			ClientAgent.log.addText("			[CLIENT] Le client va acheter a " + ClientAgent.commercialElu);

			msgOui.addReceiver(new AID(ClientAgent.commercialElu,AID.ISGUID));
			ValiderAchat val = new ValiderAchat();
			val.setReponse(true);
			if(this.disc.compareTo("DVD")==0){
				val.setDisc(new DVD());
			}
			else{
				val.setDisc(new CD());
			}
			ClientBehaviour.manager.fillContent(msgOui, val);
			this.myAgent.send(msgOui);
			


			ACLMessage msgNon = new ACLMessage(ACLMessage.INFORM);
			msgNon.setLanguage(ClientBehaviour.codec.getName());
			msgNon.setOntology(ClientBehaviour.onto.getName());

			for(String s : ClientAgent.commerciaux){

				if(!s.equals(ClientAgent.commercialElu)){
					ClientAgent.log.addText("			[CLIENT] Le client n'achete pas a " + s);
					msgNon.addReceiver(new AID(s,AID.ISGUID));
					ValiderAchat val2 = new ValiderAchat();
					val2.setReponse(false);
					if(this.disc.compareTo("DVD")==0){
						val.setDisc(new DVD());
					}
					else{
						val.setDisc(new CD());
					}

					ClientBehaviour.manager.fillContent(msgNon, val2);
				}



			}
			myAgent.send(msgNon);
		} catch (OntologyException e) {
			e.printStackTrace();
		} catch (jade.content.lang.Codec.CodecException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean done() {


		return true;
	}



}
