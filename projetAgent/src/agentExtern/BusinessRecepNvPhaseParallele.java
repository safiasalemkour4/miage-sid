package agentExtern;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;

public class BusinessRecepNvPhaseParallele extends ParallelBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	// On démarre 2 ReceptionNvPhase en parrallele pour recevoir les
	// 2 messages de phase du client
	public BusinessRecepNvPhaseParallele(Agent a) {
		
		super(a,ParallelBehaviour.WHEN_ALL);
		this.addSubBehaviour(new RecevoirNvPhase(a));
		this.addSubBehaviour(new RecevoirNvPhase(a));
		
	}

}
