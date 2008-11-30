package agentFreedom;

import protege.CD;
import protege.DVD;
import protege.Disponible;
import protege.OK;
import protege.OntoCDOntology;
import protege.ReponseDisponibilite;
import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * @author Maxime HOEFFEL
 *
 */
@SuppressWarnings("serial")
public class ReceptionPrixBehaviour extends OneShotBehaviour {

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
		ContentManager manager = myAgent.getContentManager();
		Codec codec = new SLCodec();
		OntoCDOntology onto = (OntoCDOntology)OntoCDOntology.getInstance();
		
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ContentElement ce;
		
		/* Si aucun message n'est reÃ§u en 300ms, le client est bloquÃ© ici */
		try {
			ACLMessage msg_recu = myAgent.blockingReceive(mt, 300);
			ce = manager.extractContent(msg_recu);
			if(ce != null){
				if(ce instanceof ReponseDisponibilite){
					if(((ReponseDisponibilite)ce).getDisque() instanceof CD){
						int prix = ((ReponseDisponibilite)ce).getPrix();
						((ClientAgent)myAgent).addPrixCd(msg_recu.getSender().getName(), prix);
					}else if(((ReponseDisponibilite)ce).getDisque() instanceof DVD){
						int prix = ((ReponseDisponibilite)ce).getPrix();
						((ClientAgent)myAgent).addPrixDvd(msg_recu.getSender().getName(), prix);
					}
				}
			}else{
				((ClientAgent)myAgent).addPrixDvd(msg_recu.getSender().getName(), -1);
			}
		} catch (UngroundedException e) {
			e.printStackTrace();
		} catch (CodecException e) {
			e.printStackTrace();
		} catch (OntologyException e) {
			e.printStackTrace();
		}
	}

}
