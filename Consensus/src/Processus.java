import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;


public class Processus extends Thread {

	private boolean endOfMyPhase = false;

	private SystemProcess system;

	private ArrayList<Number> listNumber;

	private ArrayList<Number> listNumberReceive;

	private String name;

	private boolean isCrashed;

	private boolean msgSended;

	private int currentPhase;

	/**
	 * Constructeur
	 */

	public Processus(String name, SystemProcess system) {

		this.name = name;
		this.system=system;
		this.currentPhase = 0;
		this.isCrashed = false;
		this.msgSended = false;
		this.listNumber = new ArrayList<Number>();
		this.listNumberReceive = new ArrayList<Number>();

		this.listNumber.add(new Number((int) (Math.random()*10)));

	}

	/**
	 * Methode Run
	 */

	public void run() {

		// Faire k phases avec k = nb proc crashé + 1

		try {

			//System.out.println("END ?"+this.system.itisTheEnd());
			while(!this.system.itIsTheEnd()) {

				/* On attend que les autres processus soit RUN */
				this.system.getBarrier().await();

				this.currentPhase++;
				//if (this.currentPhase == this.system.getCurrentPhase()) {

				/* On envoit et on recoit */
				if (!isCrashed) {

					while (!this.system.itIsMyTurn(this)) {
						Thread.sleep(1);
					}

					sendNumber();


				}

				/* Sinon on attend */
				//} else {

				//Thread.sleep(10);
				//}
				this.system.getBarrierPhase().await();

			}

			//System.out.println(" lol : "+this.system.getBarrier().getNumberWaiting());
			// Escequ'il va cracher ?
			// Si oui, il envoit une partie a des proc alea ?

			// Si il tombe en panne, chque msg a 20% de chance d'etre send

		} catch (InterruptedException e) {

			e.printStackTrace();

		} catch (BrokenBarrierException e) {

			e.printStackTrace();
		}

		System.out.println("Je ("+this.name+") decide "+this.getMinNumber());
		if (this.system.itIsMyTurn(this)) {
			this.system.finishMyTurn(this);
		}
		System.out.println("le proc "+this.name+" a finit");


	}

	public void sendNumber() throws InterruptedException {

		/* Le message a afficher */
		String message = "Le processus "+this.name+" envoit ";

		/* Le message si il y a un crash du processus */
		String crash = "";

		/* La liste de nombre de le processus devra envoyer */
		ArrayList<Number> sendList = new ArrayList<Number>();

		/* On regarde si le processus va se crasher */

		/* Si le processus se crash */
		if (willICrashed()) {

			this.isCrashed = true;
			crash = " et il se crash !";

			/* On construit la liste de nombre a envoyer */
			for (Number n : listNumber) {

				/* Si l'envoit du message se crash */
				if (this.willThisMessageCrash()) {

				} 

				/* Si l'envoit du message fonctionne */
				else {

					/* Si le nombre n'a pas deja ete envoye */
					if (!n.isSended()) {
						sendList.add(n);
						n.setSended();
						message += +n.getValue()+", ";
					}
				}
			}

			message = message.substring(0, message.length()-2);
			
			if (this.system.itIsMyTurn(this)) {
				this.system.finishMyTurn(this);
			}
		} 

		/* Si le processus ne se crash pas */
		else {

			/* On met a jour le message */

			for (Number n : listNumber) {

				/* Si le nombre n'a pas deja ete envoye */
				if (!n.isSended()) {
					sendList.add(n);
					n.setSended();
					message += +n.getValue()+", ";
				}
			}

			message = message.substring(0, message.length()-2);
			message += " a tout le monde";
		}

		/* On affiche le message d'envoit */
		//System.out.println(message+crash);
		system.displayInfos(message+crash);

		this.msgSended = true;

		system.finishMyTurn(this);

		/* On attend que tous les processus aient envoyer leurs nombres */
		try {
			this.system.getBarrier().await();
		} catch (BrokenBarrierException e1) {
			e1.printStackTrace();
		}

		/* Si tous les processus ont envoyer leurs nombres */



		/* On envoit la liste de nombre aux processus
		 *
		 * Correspond a :
		 * " 1. Envoyer toutes les valeurs de Vi qui n’ont pas encore été envoyées par pi à tous les 
		 * autres processus "
		 */
		for (Processus p : this.system.getOthersProc(this)) {
			p.receiveNumber(sendList);
		}

		/* On attend que tous les processus aient recut leurs nombres */
		try {
			this.system.getBarrier().await();
		} catch (BrokenBarrierException e1) {
			e1.printStackTrace();
		}

		while (!this.system.itIsMyTurn(this)) {
			Thread.sleep(1);
		}

		message = "Le processus "+this.name+" reçoit ";
		for (Number n : listNumberReceive) {

			message += n.getValue()+", ";
			this.addNumber(n.getValue());
		}

		if (!this.listNumberReceive.isEmpty()) {
			message = message.substring(0, message.length()-2);
		} else {
			message += "empty";
		}

		system.displayInfos(message);


		this.listNumberReceive.clear();
		system.finishMyTurn(this);
	}

	// Si chiffre rien
	//sinon add tab + renvoyer au autre

	private boolean willThisMessageCrash() {

		if (Math.random()<0.2) {

			return true;

		} else {

			return false;
		}
	}

	public void receiveNumber(ArrayList<Number> sendList) {

		this.msgSended = false;

		// Pas possible de contains car nouvelle objet 
		boolean addNumber = true;

		for (Number sended : sendList) {

			for (Number n : listNumber) {

				if (n.getValue() == sended.getValue()) {

					//addNumber = false;
				}
			}

			// On peu recevoir 2x le m'm nombre
			/*for (Number n : listNumberReceive) {

				if (n.getValue() == sended.getValue()) {

					addNumber = false;
				}
			}*/

			if (addNumber) {

				this.listNumberReceive.add(sended);

			}
		}


	}

	public void addNumber(int numberValue) {

		this.msgSended = false;

		// Pas possible de contains car nouvelle objet 
		boolean addNumber = true;

		for (Number n : listNumber) {

			if (n.getValue() == numberValue) {

				addNumber = false;
			}
		}

		// On peu recevoir 2x le m'm nombre
		/*for (Number n : listNumberReceive) {

				if (n.getValue() == sended.getValue()) {

					addNumber = false;
				}
			}*/

		if (addNumber) {

			this.listNumber.add(new Number(numberValue));

		}



	}

	public int getMinNumber() {
		
		int res = listNumber.get(0).getValue();
		
		for (Number n : listNumber) {

			if (n.getValue() < res) {

				res = n.getValue();
			}
		}
		
		return res;
	}
	
	public boolean willICrashed() {

		if (Math.random()<0.2) {
			// Si il y a moins de 20% de crash ds le system
			if (system.checkCrashPourcent()<0.2) {
				return true;
			} else {
				return false;
			}

		} else {

			return false;
		}

	}

	public boolean isCrashed() {
		return isCrashed;
	}

	public void setCrashed(boolean isCrashed) {
		this.isCrashed = isCrashed;
	}


	public boolean isMsgSended() {
		return msgSended;
	}

	public void setMsgSended(boolean msgSended) {
		this.msgSended = msgSended;
	}

	public boolean isEndOfMyPhase() {
		return endOfMyPhase;
	}

	public void setEndOfMyPhase(boolean endOfMyPhase) {
		this.endOfMyPhase = endOfMyPhase;
	}

	public String getMyName() {
		return name;
	}



}