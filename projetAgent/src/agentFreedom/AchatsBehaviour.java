package agentFreedom;

import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import protege.CD;
import protege.DVD;
import protege.Disponible;
import protege.Disque;

/**
 * Comportement d'achat. Va envoyer une demande de prix à chacun des commerciaux connus
 * par le client
 *
 */

@SuppressWarnings("serial")
public class AchatsBehaviour extends SimpleBehaviour {
	private Disque disque;
	private int quantite;


	/**
	 * @param a L'agent exécutant ce comportement
	 */
	public AchatsBehaviour(Agent a, Disque disque, int quantite) {
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
	
		if(disque instanceof CD){
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
					ClientAgent.log.addText("			[CLIENT] Envoi de la demande de prix a " 
							+ vendeur + " pour une quantite de " + quantite + " CDs.");
					myAgent.send(msg);
				} catch (CodecException e) {
					e.printStackTrace();
				} catch (OntologyException e) {
					e.printStackTrace();
				}
			}
		}else  if(disque instanceof DVD){
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
					ClientAgent.log.addText("			[CLIENT] Envoi de la demande de prix a " 
							+ vendeur + " pour une quantite de " + quantite + " DVDs.");
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
