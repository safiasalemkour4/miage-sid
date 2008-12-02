package agentFreedom;

import java.util.ArrayList;
import java.util.HashMap;

import protege.CD;
import protege.DVD;
import protege.Disponible;
import protege.NouvellePhase;
import protege.OntoCDOntology;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
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
		System.out.println("action de achat behaviour");
		CD mon_cd = new CD();
		DVD mon_dvd = new DVD();
		
		((ClientAgent)myAgent).emptyPrixCd();
		((ClientAgent)myAgent).emptyPrixDvd();
		
		ContentManager manager = myAgent.getContentManager();
		Codec codec = new SLCodec();
		OntoCDOntology onto = (OntoCDOntology)OntoCDOntology.getInstance();
		manager.registerLanguage(codec);
	    manager.registerOntology(onto);
	    
		ArrayList<String> liste_vendeurs_cd = ((ClientAgent)this.myAgent).getListe_vendeursCD();
		ArrayList<String> liste_vendeurs_dvd = ((ClientAgent)this.myAgent).getListe_vendeursDVD();
		/* On crÃ©Ã© une HashMap avec les numÃ©ros de phase et le tableau de quantitÃ©s de CDs et DVDs Ã  acheter correspondant */
		HashMap<Integer, Integer[]> quantiteMap = new HashMap<Integer, Integer[]>(); 
		Integer[] achatUn = { 5000, 4000 };
		quantiteMap.put(0, achatUn);
		Integer[] achatDeux = { 2000, 1500 };
		quantiteMap.put(1, achatDeux);
		Integer[] achatTrois = { 1000, 800 };
		quantiteMap.put(2, achatTrois);

		for (int i = 0; i < quantiteMap.size(); i++) {
			/* --- phase d'achat --- */
			/* Envoyer Ã  chaque commercial de la liste des vendeurs CD */
			for(String vendeur : ((ClientAgent)this.myAgent).getListe_vendeursCD()){
				try {
					ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
					msg.setLanguage(codec.getName());
					msg.setOntology(onto.getName());
					msg.addReceiver(new AID(vendeur, AID.ISLOCALNAME));
					Disponible dispo = new Disponible();
					dispo.setQte(quantiteMap.get(i)[0]);
					dispo.setDisque(mon_cd);
					manager.fillContent(msg, dispo);
					myAgent.send(msg);
					//this.myAgent.addBehaviour(new ReceptionPrixBehaviour(this.myAgent));
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
