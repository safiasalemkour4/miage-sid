package agentFreedom;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
/**
 * Comportement d'attente pour laisser le temps de produire
 * @author Simon
 *
 */
public class ClientAttente extends SimpleBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientAttente(Agent myAgent) {
		super(myAgent);
	}

	@Override
	public void action() {
		/* Le client attend pendant la phase de production */
		try {
			Thread.sleep(ClientBehaviour.SLEEPING);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
