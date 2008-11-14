package agentCompany;
import jade.core.Agent;

/**
 * Class InfosAgent
 * @author Arnaud Knobloch
 * Represente un Agent d'Information / de Recherche
 */

public class InfosAgent extends Agent {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;

	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {

		/* Ajout du comportement information */
		this.addBehaviour(new InfosBehaviour(this));
	}

}
