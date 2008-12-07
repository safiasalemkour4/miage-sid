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
        if (disque.compareTo("CD")==0) {
            // On compare les prix des CDs
            this.addSubBehaviour(new ClientComparatifCD(this.myAgent));
        }
        else if (disque.compareTo("DVD")==0) {
            // On compare les prix des DVDs
            this.addSubBehaviour(new ClientComparatifDVD(this.myAgent));
        }
		// On envoi valider au commercial élu
		this.addSubBehaviour(new ClientEnvoiValider(this.myAgent, disque, quantite));

	}

}
