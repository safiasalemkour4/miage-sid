package agentFreedom;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;


/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		ClientAgent.java														 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Agent Client (CDs et DVDs)												 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

/**
 * Un tour de jeu a 2 phases : 
 * 1) client envoi "nouvellePhase(1)" au commerciale
 * 		et client se met en attente (dort pdt 10s)
 * 
 * 2) l'entreprise produit, achete et vend aux autres boites
 * 
 * 4) client sort du cycle d'attente
 *  	et il envoi nouvellePhase(2) au commerciale
 * 5) commerciale demande a tout le monde de s'arreter, 
 * 		quand tout le monde est arreté, envoi OK au client
 * 6) quand client a recu 4 OK
 *  - envoi disponibilité
 *  - recoit réponseDispo
 *  - compare prix
 *  - envoi validerAchat a la boite elu
 * 
 * fin phase 2 
 * 7) envoi de nouvellePhase(1)...
 * 
 */


public class ClientAgent extends Agent {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
	
	
	private ArrayList<String> liste_vendeursCD = new ArrayList<String>();
	private ArrayList<String> liste_vendeursDVD = new ArrayList<String>();
	
	private HashMap<String, Integer> prix_cds = new HashMap<String, Integer>();
	private HashMap<String, Integer> prix_dvds = new HashMap<String, Integer>();

	
	
	static ArrayList<String> commerciaux = new ArrayList<String>();
	
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		int num_phase = 0;
		JOptionPane.showConfirmDialog(null, "Debuter ?", "Debut", JOptionPane.OK_CANCEL_OPTION);
		this.addBehaviour(new ClientDecouvreService(this));

		System.out.println("Demarrage du client ... ");
		
		// A chaque fin de tour on demande si on veut en faire un autre
		while (JOptionPane.showConfirmDialog(null, "Voulez-vous faire un tour supplementaire ?", "Fin du tour", JOptionPane.OK_CANCEL_OPTION) == 0){
			num_phase++;
			// On ajoute le SequentialBehaviour qui va effectuer toutes les phases du tour
			this.addBehaviour(new ClientBehaviour(this));
						
			
			System.out.println("Le client a termine ses achats pour la phase " + num_phase);
		}
		/* Fin */
		System.out.println("Le client a termine ses achats ! (il y a eu " + num_phase + " phases)");
	    
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		ServiceDescription sdClient = new ServiceDescription();
		sdClient.setType("Client");
		sdClient.setName("Client");
		sdClient.setOwnership(this.getName());
		
		/* Enregistrement des services aupres du DF Agent */
		dfd.addServices(sdClient);


	}
	
	public ArrayList<String> getListe_vendeursCD() {
		return liste_vendeursCD;
	}

	public void setListe_vendeursCD(ArrayList<String> liste_vendeursCD) {
		this.liste_vendeursCD = liste_vendeursCD;
	}

	public ArrayList<String> getListe_vendeursDVD() {
		return liste_vendeursDVD;
	}

	public void setListe_vendeursDVD(ArrayList<String> liste_vendeursDVD) {
		this.liste_vendeursDVD = liste_vendeursDVD;
	}
	
	public synchronized void addPrixCd(String commercial, int prix){
		this.prix_cds.put(commercial, prix);
	}
	
	public synchronized void addPrixDvd(String commercial, int prix){
		this.prix_dvds.put(commercial, prix);
	}
	
	public void emptyPrixCd(){
		this.prix_cds.clear();
	}
	
	public void emptyPrixDvd(){
		this.prix_dvds.clear();
	}


	public HashMap<String, Integer> getPrix_cds() {
		return prix_cds;
	}


	public HashMap<String, Integer> getPrix_dvds() {
		return prix_dvds;
	}

}
