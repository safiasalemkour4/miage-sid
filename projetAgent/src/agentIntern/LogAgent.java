package agentIntern;

import agentExtern.BuyBehaviour;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;




public class LogAgent extends Agent {

	/** Serial par defaut */
	private static final long serialVersionUID = 1L;
	
	public int stockCD = 0;
	public int stockDVD = 0;

	public String log = "";
	
	private ContentManager manager = (ContentManager)this.getContentManager();

	/**
	 * Methode setup
	 * Utile et necessaire pour l'initialisation de l'agent
	 */
	
	public void setup() {
	    
		/* Ajout du comportement d'achat */
		this.addBehaviour(new BuyBehaviour(this));
	    
		/* Creation d'une description du DF Agent */
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());

		/* Creation d'une decription du service */
		ServiceDescription sdLog = new ServiceDescription();
		sdLog.setType("HCK_Log");
		sdLog.setName("Log des actions (stock et banque)");
		sdLog.setOwnership(this.getName());
		
		/* Enregistrement du service aupres du DF Agent */
		dfd.addServices(sdLog);

		try {

			DFService.register(this, dfd);

		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
	}

	public int getStockCD() {
		return stockCD;
	}

	public int getStockDVD() {
		return stockDVD;
	}
	
	public String getLog() {
		return log;
	}
	
	public void setStockCD(int stockCD) {
		this.stockCD = stockCD;
	}

	public void setStockDVD(int stockDVD) {
		this.stockDVD = stockDVD;
	}

	public void addLog(String newLog) {
		this.log += "\n"+log;
	}
	
	public void displayLog() {
		System.out.println("LOG : \n\n"+log);
	}
	
	
}
