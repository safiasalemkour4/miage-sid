package agentFreedom;

import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import protege.CD;
import protege.DVD;
import protege.ReponseDisponibilite;

/**
 * @author Maxime HOEFFEL
 *
 */
@SuppressWarnings("serial")
public class ReceptionPrixBehaviour extends SimpleBehaviour {

	/**
	 * @param a L'agent exÃ©cutant ce comportement
	 */
	public ReceptionPrixBehaviour(Agent a) {
		super(a);
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {


		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ContentElement ce;

		/* Si aucun message n'est recu en 300ms, le client est bloquÃ© ici */
		try {

			int prix_recus = 0;
			while(prix_recus < ClientAgent.commerciaux.size()){
				
				ACLMessage msg_recu = myAgent.blockingReceive(mt);
				
				ce = ClientBehaviour.manager.extractContent(msg_recu);
				
				if(ce != null){
					if(ce instanceof ReponseDisponibilite){
						prix_recus++;

						if(((ReponseDisponibilite)ce).getDisque() instanceof CD){
							int prix = ((ReponseDisponibilite)ce).getPrix();
							ClientAgent.log.addText("Le client a recu le prix "+prix+" venant de "+msg_recu.getSender().getName());
							((ClientAgent)myAgent).addPrixCd(msg_recu.getSender().getName(), prix);
						}else if(((ReponseDisponibilite)ce).getDisque() instanceof DVD){
							int prix = ((ReponseDisponibilite)ce).getPrix();
							ClientAgent.log.addText("Le client a recu le prix "+prix+" venant de "+msg_recu.getSender().getName());
							((ClientAgent)myAgent).addPrixDvd(msg_recu.getSender().getName(), prix);
						}
					}
				}else{
					((ClientAgent)myAgent).addPrixDvd(msg_recu.getSender().getName(), -1);
				}
			}
			ClientAgent.log.addText("Le client a recu tous les prix");
		} catch (UngroundedException e) {
			e.printStackTrace();
		} catch (CodecException e) {
			e.printStackTrace();
		} catch (OntologyException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean done() {
		
		return true;
	}

}
