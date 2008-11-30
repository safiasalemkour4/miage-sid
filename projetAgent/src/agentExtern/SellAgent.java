package agentExtern;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import protege.OntoCDOntology;

/*****************************************************************************************************
 *   					           ~ Projet Agent Powered Miage SID ~			         			 *
 *****************************************************************************************************
 *    CLASS  	******		SellAgent.java															 *
 *****************************************************************************************************
 *    			******															 					 *
 * DESCRIPTION  ******		Agent Vendeur (CDs et DVDs)												 *
 * 				******																				 *
 *****************************************************************************************************
 * 	 @author 	******   	Simon Hasne, Arnaud Knobloch, Florian Collignon							 *										 *
 * ***************************************************************************************************
 *   @version 	******  	1.0																		 *
 *****************************************************************************************************/

/**
 * Agent qui recoit des requetes venant des acheteurs des autres entrepises
 * Il fournira deux services : venteCD et venteDVD
 * Protocole (un service est bloqué tout au long du protocole, 
 * une seule requete Achat à la fois): 
 * 
 * Lorsque recoit disponible(Disque,qté):
 * 1) envoie reponseDispo avec le prix du produit
 * 2) si il recoit une validerAchat true -> demande à agentBDD et agentStock de MAJ
 * 		sinon timeout 5 s et se débloquer (attendre d'autres requete)
 *
 *Rq: le prix de vente peut etre différent selon les demandeDispo
 *	mais une fois qu'on a envoyé le prix on est obligé de le vendre
 *	au prix indiqué
 *
 * Stratégie : 
 * - à quel prix vendre ? 
 * - doit-on varier le prix selon la commande ? (selon la qté demandé? selon nos stock? nos sous?)
 * - quel qté minimum une commande doit avoir ?
 */

public class SellAgent extends Agent {

	private ContentManager manager = (ContentManager)getContentManager();
	private Codec codec = new SLCodec();
	private OntoCDOntology ontology = (OntoCDOntology) OntoCDOntology.getInstance();

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */

	public void setup() {

		manager.registerLanguage(codec);
		manager.registerOntology(ontology);

		/* Ajout du comportement de vente */
		this.addBehaviour(new SellBehaviour(this));
		this.addBehaviour(new RecevoirStop(this));
		
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		
		/* Creation d'une decription du service : Production de Cds */
		ServiceDescription sdSellCd = new ServiceDescription();
		sdSellCd.setType("VenteCD");
		sdSellCd.setName("Vente de CDs aux concurrents");
		sdSellCd.setOwnership(this.getName());
		
		/* Creation d'une decription du service : Production de Dvds */
		ServiceDescription sdSellDvd = new ServiceDescription();
		sdSellDvd.setType("VenteDVD");
		sdSellDvd.setName("vente de DVDs aux concurrents");
		sdSellDvd.setOwnership(this.getName());
		
		/* Enregistrement des services aupres du DF Agent */
		dfd.addServices(sdSellCd);
		dfd.addServices(sdSellDvd);
		
		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
	}

}
