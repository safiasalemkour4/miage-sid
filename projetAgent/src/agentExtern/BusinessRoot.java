package agentExtern;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

public class BusinessRoot extends SequentialBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessRoot(Agent a) {
		super(a);
		
		for(int i = 0; i<15;i++){
			this.addSubBehaviour(new BusinessBehaviour(this.myAgent));
		
		}

	}

}
