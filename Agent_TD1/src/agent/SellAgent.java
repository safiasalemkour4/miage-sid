package agent;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Class SellAgent
 * @author Arnaud Knobloch
 * Represente un Agent de Vente
 */

public class SellAgent extends Agent {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {

		/* Ajout du comportement de vente */
		this.addBehaviour(new SellBehaviour(this));

		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());

		/* Creation d'une decription du service */
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Vente");
		sd.setName("Vente de CDs");
		sd.setOwnership(this.getName());
		
		/* Enregistrement du service aupr�s du DF Agent */
		dfd.addServices(sd);

		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

}
