package agentFreedom;

import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.HashMap;

import protege.CD;
import protege.DVD;
import protege.Disponible;

/**
 * Comportement d'achat. Va envoyer une demande de prix à chacun des commerciaux connus
 * par le client
 * @author Maxime HOEFFEL
 *
 */
@SuppressWarnings("serial")
public class AchatsBehaviour extends SimpleBehaviour {

	private String disque;
	private int quantite;


	/**
	 * @param a L'agent executant ce comportement
	 */
	public AchatsBehaviour(Agent a, String disque, int quantite) {
		super(a);
		this.disque = disque;
		this.quantite = quantite;		
	}



	@Override
	public void action() {

		CD mon_cd = new CD();
		DVD mon_dvd = new DVD();

		((ClientAgent)myAgent).emptyPrixCd();
		((ClientAgent)myAgent).emptyPrixDvd();


		ArrayList<String> liste_vendeurs_cd = ((ClientAgent)this.myAgent).getListe_vendeursCD();
		ArrayList<String> liste_vendeurs_dvd = ((ClientAgent)this.myAgent).getListe_vendeursDVD();
		/* On cree une HashMap avec les numeros de phase et le tableau de quantites de CDs et DVDs a acheter correspondant */		

		if(disque.equals("CD")){

			/* --- phase d'achat --- */
			/* Envoyer a chaque commercial de la liste des vendeurs CD */
			for(String vendeur : ((ClientAgent)this.myAgent).getListe_vendeursCD()){
				try {
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.setLanguage(ClientBehaviour.codec.getName());
					msg.setOntology(ClientBehaviour.onto.getName());
					msg.addReceiver(new AID(vendeur, AID.ISGUID));
					Disponible dispo = new Disponible();
					dispo.setQte(quantite);
					dispo.setDisque(mon_cd);
					ClientBehaviour.manager.fillContent(msg, dispo);
					ClientAgent.log.addText("			[CLIENT] Envoi de la demande de prix a " + vendeur + " pour une quantite de " + quantite + " "+disque+"s.");
					myAgent.send(msg);

				} catch (CodecException e) {
					e.printStackTrace();
				} catch (OntologyException e) {
					e.printStackTrace();
				}
			}
		}

		if(disque.equals("DVD")){

			/* --- phase d'achat --- */
			/* Envoyer a chaque commercial de la liste des vendeurs CD */
			for(String vendeur : ((ClientAgent)this.myAgent).getListe_vendeursDVD()){
				try {
					ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
					msg.setLanguage(ClientBehaviour.codec.getName());
					msg.setOntology(ClientBehaviour.onto.getName());
					msg.addReceiver(new AID(vendeur, AID.ISGUID));
					Disponible dispo = new Disponible();
					dispo.setQte(quantite);
					dispo.setDisque(mon_dvd);
					ClientBehaviour.manager.fillContent(msg, dispo);
					ClientAgent.log.addText("			[CLIENT] Envoi de la demande de prix a " + vendeur + " pour une quantite de " + quantite + " "+disque+"s.");
					myAgent.send(msg);

				} catch (CodecException e) {
					e.printStackTrace();
				} catch (OntologyException e) {
					e.printStackTrace();
				}
			}
		}


	}

	@Override
	public boolean done() {
		return true;
	}

}
