package agentFreedom;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
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
import jade.lang.acl.ACLCodec.CodecException;
import protege.NouvellePhase;
import protege.OK;
import protege.OntoCDOntology;

/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		ClientBehaviour.java													 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Comportement Client														 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

public class ClientBehaviour extends SimpleBehaviour {

	

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	public static final int SLEEPING = 10000;
	/**
	 * Construteur
	 * @param agent l'agent lie a ce comportement
	 * Construit le comportement Achat
	 */

	public ClientBehaviour(Agent agent) {

		super(agent);
	}

	
	/**
	 * Action du comportement
	 */
	public void action() {
		int num_phase = 0;
		JOptionPane.showConfirmDialog(null, "Debuter ?", "Debut", JOptionPane.OK_CANCEL_OPTION);
		

		System.out.println("Demarrage du client ... ");
		
		ContentManager manager = myAgent.getContentManager();
		Codec codec = new SLCodec();
		
		OntoCDOntology onto = (OntoCDOntology)OntoCDOntology.getInstance();

		manager.registerLanguage(codec);
	    manager.registerOntology(onto);
	    
		/* Demander le nombre d'agents et leur nom au DF */
		DFAgentDescription dfd = new DFAgentDescription();

		/* Retrouver les agents dans un tableau */
		DFAgentDescription[] result = null;
		
		System.out.println("Recherche des services proposes par les differents agents ... ");
		try {
			result = DFService.search(this.myAgent, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}

		ArrayList<String> vendeurs_cd = new ArrayList<String>();
		ArrayList<String> vendeurs_dvd = new ArrayList<String>();
		ArrayList<String> commerciaux = new ArrayList<String>();

		for (int i = 0; i < result.length; i++) {
			Iterator<?> iter = result[i].getAllServices();
			/* On rÃ©cupÃ¨re toutes les descriptions de service */
			while (iter.hasNext()) {
				ServiceDescription sd = (ServiceDescription) iter.next();
				if (sd.getType().compareTo(ClientAgent.SERVICE_VENTE_CD_CLIENT) == 0) {
					System.out.println("Le service vente de CDs est propose par l'agent " + sd.getOwnership());
					vendeurs_cd.add(sd.getOwnership());
				} else if (sd.getType().compareTo(ClientAgent.SERVICE_VENTE_DVD_CLIENT) == 0){
					System.out.println("Le service vente de DVDs est propose par l'agent " + sd.getOwnership());
					vendeurs_dvd.add(sd.getOwnership());
				} else if (sd.getType().compareTo(ClientAgent.SERVICE_START) == 0){
					System.out.println("Le service de demarrage est propose par l'agent " + sd.getOwnership());
					commerciaux.add(sd.getOwnership());
				} else {
					//System.out.println("L'agent " + sd.getOwnership() + " propose le service " + sd.getName());
				}
			}
		}
		/* Stockage de la liste du cÃ´tÃ© du client */
		((ClientAgent)(this.myAgent)).setListe_vendeursCD(vendeurs_cd);
		((ClientAgent)(this.myAgent)).setListe_vendeursDVD(vendeurs_dvd);
		
		while (JOptionPane.showConfirmDialog(null, "Voulez-vous faire un tour supplementaire ?", "Fin du tour", JOptionPane.OK_CANCEL_OPTION) == 0){
			num_phase++;
			/* Envoi d'un message Ã  tous les commerciaux pour leur signaler le dÃ©but de la phase de production */
			for(String s : commerciaux){
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.setLanguage(codec.getName());
				msg.setOntology(onto.getName());
				try {
					System.out.println("Le client va envoyer un mesg a "+s);
					msg.addReceiver(new AID(s,AID.ISGUID));
					NouvellePhase nph = new NouvellePhase();
					nph.setNumeroPhase(NouvellePhase.PHASE_PROD);
					
					manager.fillContent(msg, nph);
					myAgent.send(msg);
				} catch (OntologyException e) {
					e.printStackTrace();
				} catch (jade.content.lang.Codec.CodecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/* Le client attend pendant la phase de production */
			try {
				Thread.sleep(ClientBehaviour.SLEEPING);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			/* Envoi Ã  tous les commerciaux du message leur indiquant d'arrÃªter leur production et leurs achats */
			for(String com : commerciaux){
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.setLanguage(codec.getName());
				msg.setOntology(onto.getName());
				try {
					
					msg.addReceiver(new AID(com, AID.ISLOCALNAME));
					NouvellePhase nph = new NouvellePhase();
					nph.setNumeroPhase(NouvellePhase.PHASE_ACHAT);
					
					manager.fillContent(msg, nph);
					myAgent.send(msg);
				} catch (OntologyException e) {
					e.printStackTrace();
				} catch (jade.content.lang.Codec.CodecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}

			/* RÃ©ception tous les messages "OK" */
			System.out.println("Attente des messages 'OK' des commerciaux ...");
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
			ContentElement ce;
			int ok_recus = 0;
			while(ok_recus < commerciaux.size()){
				try {
					/* Si aucun message n'est reÃ§u, le client est bloquÃ© ici */
					ACLMessage msg_recu = myAgent.blockingReceive(mt);
					ce = manager.extractContent(msg_recu);
					if(ce instanceof OK){
						ok_recus++;
					}
				} catch (UngroundedException e) {
					e.printStackTrace();
				} catch (jade.content.lang.Codec.CodecException e) {
					e.printStackTrace();
				} catch (OntologyException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("Le client commence ses achats ...");
			this.myAgent.addBehaviour(new AchatsBehaviour(this.myAgent));
			
			System.out.println("Le client a terminÃ© ses achats pour la phase " + num_phase);
		}
		
		/* Fin */
		System.out.println("Le client a terminÃ© ses achats ! (il y a eu " + num_phase + " phases)");

	}


	public boolean done() {
		return true;
	}


}
