package agentFreedom;

import protege.CD;
import protege.DVD;
import protege.Disque;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

@SuppressWarnings("serial")
public class Achat extends SequentialBehaviour {
	/**
	 * Constructeur
	 * @param a agent
	 */
	public Achat(Agent a, Disque disque, int quantite){
		super(a);
		
		// On commence le processus d'achat
		this.addSubBehaviour(new AchatsBehaviour(this.myAgent, disque, quantite));
		// On recoit les prix envoyés par les commerciaux		
		this.addSubBehaviour(new ReceptionPrixBehaviour(this.myAgent));
        if (disque instanceof CD) {
            // On compare les prix des CDs
            this.addSubBehaviour(new ClientComparatifCD(this.myAgent));
        }
        else if (disque instanceof DVD) {
            // On compare les prix des DVDs
            this.addSubBehaviour(new ClientComparatifDVD(this.myAgent));
        }
		// On envoi valider au commercial élu
		this.addSubBehaviour(new ClientEnvoiValider(this.myAgent, disque, quantite));

	}

}
