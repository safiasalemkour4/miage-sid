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

	/**
	 * @param a L'agent exÃ©cutant ce comportement
	 */
	public AchatsBehaviour(Agent a) {
		super(a);
	}
	
	

	@Override
	public void action() {
		
		CD mon_cd = new CD();
		DVD mon_dvd = new DVD();
		
		((ClientAgent)myAgent).emptyPrixCd();
		((ClientAgent)myAgent).emptyPrixDvd();
		
		
		
	    
		ArrayList<String> liste_vendeurs_cd = ((ClientAgent)this.myAgent).getListe_vendeursCD();
		ArrayList<String> liste_vendeurs_dvd = ((ClientAgent)this.myAgent).getListe_vendeursDVD();
		/* On crÃ©Ã© une HashMap avec les numÃ©ros de phase et le tableau de quantitÃ©s de CDs et DVDs Ã  acheter correspondant */
		
		

		for (int i = 0; i < ClientAgent.quantiteMap.size(); i++) {
			/* --- phase d'achat --- */
			/* Envoyer Ã  chaque commercial de la liste des vendeurs CD */
			for(String vendeur : ((ClientAgent)this.myAgent).getListe_vendeursCD()){
				try {
					ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
					msg.setLanguage(ClientBehaviour.codec.getName());
					msg.setOntology(ClientBehaviour.onto.getName());
					msg.addReceiver(new AID(vendeur, AID.ISGUID));
					Disponible dispo = new Disponible();
					dispo.setQte(ClientAgent.quantiteMap.get(i)[0]);
					dispo.setDisque(mon_cd);
					ClientBehaviour.manager.fillContent(msg, dispo);
					ClientAgent.log.addText("			[CLIENT] Envoi de la demande de prix a "+vendeur);
					myAgent.send(msg);
					
				} catch (CodecException e) {
					e.printStackTrace();
				} catch (OntologyException e) {
					e.printStackTrace();
				}
			}
			
//			attendre le "reponseDisponibilite" des commerciaux pour les
//			CD
//			au bout de 5 sec (timeout)
//			il choisit le meilleur => validerAchat(100);
//			liste_vendeurs_cd.add(meilleur);
//
//
//			attendre le "reponseDisponbilite" des commerciaux pour les
//			DVD
//			au bout de 5 sec (timeout)
//			il choisit le meilleur => validerAchat(70);
//			liste_acheteur_dvd.add(meilleur);

		}
	}

	@Override
	public boolean done() {
		return true;
	}

}
