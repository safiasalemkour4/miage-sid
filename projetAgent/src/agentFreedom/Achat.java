package agentFreedom;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

public class Achat extends SequentialBehaviour {

	private static final long serialVersionUID = 5638433854439123305L;

	/**
	 * Constructeur
	 * @param a agent
	 */
	public Achat(Agent a, String disque, int quantite){
		super(a);
		
		
		// On commence le processus d'achat
		this.addSubBehaviour(new AchatsBehaviour(this.myAgent, disque, quantite));
		// On recoit les prix envoyés par les commerciaux		
		this.addSubBehaviour(new ReceptionPrixBehaviour(this.myAgent));
		// On compare les prix
		this.addSubBehaviour(new ClientComparatif(this.myAgent));
		// On envoi valider au commercial élu
		this.addSubBehaviour(new ClientEnvoiValider(this.myAgent));

	}

}
