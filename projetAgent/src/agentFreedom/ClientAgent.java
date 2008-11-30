package agentFreedom;
import java.util.ArrayList;
import java.util.HashMap;

import jade.content.ContentManager;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;


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

	private ContentManager manager = (ContentManager)this.getContentManager();

	
	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	  
	public static final String SERVICE_VENTE_CD_CLIENT = "VenteCD_Client";
	public static final String SERVICE_VENTE_DVD_CLIENT = "VenteDVD_Client";
	public static final String SERVICE_START = "Start";
	public static final String SERVICE_VENTE_CD_ENT = "VenteDVD_Ent";
	public static final String SERVICE_VENTE_DVD_ENT = "VenteCD_Ent";
	
	private ArrayList<String> liste_vendeursCD = new ArrayList<String>();
	private ArrayList<String> liste_vendeursDVD = new ArrayList<String>();
	
	private HashMap<String, Integer> prix_cds = new HashMap<String, Integer>();
	private HashMap<String, Integer> prix_dvds = new HashMap<String, Integer>();

	
	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new ClientBehaviour(this));
	    
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		;


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
