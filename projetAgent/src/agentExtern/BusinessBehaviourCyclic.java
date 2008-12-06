package agentExtern;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class BusinessBehaviourCyclic extends CyclicBehaviour{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessBehaviourCyclic(Agent a) {
		super(a);
		
	}

	@Override
	public void action() {
		
		this.myAgent.addBehaviour(new BusinessBehaviour(this.myAgent));
		
	}

}
