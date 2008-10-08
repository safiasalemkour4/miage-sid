import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;


public class Processus extends Thread {

	private SystemProcess system;

	private ArrayList<Number> listNumber;

	private ArrayList<Number> listNumberReceive;

	private String name;

	private boolean isCrashed;

	/**
	 * Constructeur
	 */

	public Processus(String name, SystemProcess system) {

		this.name = name;
		this.system=system;
		this.isCrashed = false;

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

			/* While phase k < Nb Process */ 
			while(!this.system.itIsTheEnd()) {
				
				/* We wait other process */
				this.system.getBarrier().await();
	
				/* If the thread is alive */
				if (!isCrashed) {

					while (!this.system.itIsMyTurn(this)) { // TODO
						//System.out.println("(1)");
						Thread.yield();
						Thread.sleep(10);
					}

					sendNumber();
					
				} else {

				}
				System.out.println("Je suis avant la barriere de phase : "+this.name);
				
				/* We wait other process for the end oh the phase */
				this.system.getBarrierPhase().await();
				
// TODO LE BUG C VERS ICI
				
				System.out.println("Je suis apres la barriere de phase : "+this.name);
				
				// Permet de laisser lecontrol au system
				Thread.sleep(10);
				
				this.system.getBarrierPhase().await();
				
			}

		} catch (InterruptedException e) {

			e.printStackTrace();

		} catch (BrokenBarrierException e) {

			e.printStackTrace();
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

					// Si oui, il envoit une partie a des proc alea ?

					// Si il tombe en panne, chque msg a 20% de chance d'etre send

					/* Si le nombre n'a pas deja ete envoye */
					if (!n.isSended()) {
						sendList.add(n);
						n.setSended();
						message += +n.getValue()+", ";
					}
				}
			}

			message = message.substring(0, message.length()-2);

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
		//System.out.println("(2)");
		/* on finit son tour, sil il est crasher alors on le vire de la liste */
		system.finishMyTurn(this); // TODO

		/* On attend que tous les processus aient envoyer leurs nombres */
		/*try {
			this.system.getBarrier().await();
		} catch (BrokenBarrierException e1) {
			e1.printStackTrace();
		}*/

		/* Si tous les processus ont envoyer leurs nombres */

		
		/* On envoit la liste de nombre aux processus
		 *
		 * Correspond a :
		 * " 1. Envoyer toutes les valeurs de Vi qui n’ont pas encore été envoyées par pi à tous les 
		 * autres processus "
		 */
		for (Processus p : this.system.getOthersProc(this)) {

			/* Si le processus n'a pas crasher alros il recoit des nombres */
			if (!p.isCrashed()) {
				p.receiveNumber(sendList);
			}

		}

		/* On attend que tous les processus aient recut leurs nombres */
		/*try {
			this.system.getBarrier().await();
		} catch (BrokenBarrierException e1) {
			e1.printStackTrace();
		}*/

		/* Permet d'afficher ds l'ordre ce que les processus ont recut */
		if (!this.isCrashed) {
			while (!this.system.itIsMyTurn(this)) {
				//System.out.println("(3)");
				Thread.yield();
				Thread.sleep(10);
			}
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

		/* Seul les process non crash (qui font encore parti de la list) peuvent finir leur tour */
		if (!this.isCrashed) {
			//System.out.println("(4)");
			system.finishMyTurn(this); //TODO
		}
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

	public String getMyName() {
		return name;
	}
}