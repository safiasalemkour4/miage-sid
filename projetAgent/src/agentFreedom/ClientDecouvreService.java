package agentFreedom;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.util.ArrayList;
import java.util.Iterator;

import protege.OntoCDOntology;

public class ClientDecouvreService extends SimpleBehaviour{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientDecouvreService(Agent myAgent) {
		super(myAgent);
	}

	public void action(){
		
		/* Demander le nombre d'agents et leur nom au DF */
		DFAgentDescription dfd = new DFAgentDescription();
		/* Retrouver les agents dans un tableau */
		DFAgentDescription[] result = null;
		
		ClientAgent.log.addText("Recherche des services proposes par les differents agents ... ");
		try {
			result = DFService.search(this.myAgent, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		ArrayList<String> vendeurs_cd = new ArrayList<String>();
		ArrayList<String> vendeurs_dvd = new ArrayList<String>();
		

		for (int i = 0; i < result.length; i++) {
			Iterator<?> iter = result[i].getAllServices();
			/* On rÃ©cupÃ¨re toutes les descriptions de service */
			while (iter.hasNext()) {
				ServiceDescription sd = (ServiceDescription) iter.next();
				if (sd.getType().compareTo(OntoCDOntology.SERVICE_VENTE_CD_CLIENT) == 0) {
					ClientAgent.log.addText("Le service vente de CDs est propose par l'agent " + sd.getOwnership());
					vendeurs_cd.add(sd.getOwnership());
				} else if (sd.getType().compareTo(OntoCDOntology.SERVICE_VENTE_DVD_CLIENT) == 0){
					ClientAgent.log.addText("Le service vente de DVDs est propose par l'agent " + sd.getOwnership());
					vendeurs_dvd.add(sd.getOwnership());
				} else if (sd.getType().compareTo(OntoCDOntology.SERVICE_START) == 0){
					ClientAgent.log.addText("Le service de demarrage est propose par l'agent " + sd.getOwnership());
					ClientAgent.commerciaux.add(sd.getOwnership());
				} else {
					//System.out.println("L'agent " + sd.getOwnership() + " propose le service " + sd.getName());
				}
			}
		}
		/* Stockage de la liste du cÃ´tÃ© du client */
		((ClientAgent)(this.myAgent)).setListe_vendeursCD(vendeurs_cd);
		((ClientAgent)(this.myAgent)).setListe_vendeursDVD(vendeurs_dvd);
		
		
	}

	@Override
	public boolean done() {
		
		return true;
	}
	
}
