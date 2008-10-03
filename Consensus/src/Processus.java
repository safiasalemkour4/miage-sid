import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;


public class Processus extends Thread {


	// SAUVEGARDER SI LE CHIFFRE A ETE SEND !!!!!
	private SystemProcess system;

	private ArrayList<Number> listNumber;

	private String id;

	private boolean isCrashed;

	private boolean msgSended;

	/**
	 * Constructeur
	 */

	public Processus(String id, SystemProcess system) {

		this.id = id;
		this.system=system;
this.isCrashed = false;
this.msgSended = false;
		this.listNumber = new ArrayList<Number>();

		this.listNumber.add(new Number((int) (Math.random()*10)));

	}

	/**
	 * Methode Run
	 */

	public void run() {

		// Faire k phases avec k = nb proc crashé + 1

		try {

			this.system.getBarrier().await();
			
			//System.out.println(" lol : "+this.system.getBarrier().getNumberWaiting());
			// Escequ'il va cracher ?
			// Si oui, il envoit une partie a des proc alea ?

			// Si il tombe en panne, chque msg a 20% de chance d'etre send
			if (!isCrashed) {

				sendNumber();
			}



		} catch (InterruptedException e) {

			e.printStackTrace();

		} catch (BrokenBarrierException e) {

			e.printStackTrace();
		}
		System.out.println("le proc "+this.id+" a finit");

	}

	public void sendNumber() throws InterruptedException {

		/* Le message a afficher */
		String message = "Le processus "+this.id+" envoit ";
		
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
						message += +n.getNumber()+", ";
					}
				}
			}
		} 

		/* Si le processus ne se crash pas */
		else {

			for (Number n : listNumber) {

				/* Si le nombre n'a pas deja ete envoye */
				if (!n.isSended()) {
					sendList.add(n);
					n.setSended();
					message += +n.getNumber()+", ";
				}

			}
		}
		
		/* On affiche le message d'envoit */
		System.out.println(message+crash);
		
		this.msgSended = true;
		
		/* On attend que tous les processus aient envoyer leurs nombres */
		while (!system.isAllMsgSended()) {
			Thread.sleep(10);
		}
		
		/* Si tous les processus ont envoyer leurs nombres */
		if (system.isAllMsgSended()) {
			
			try {
				this.system.getBarrier().await();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/* On envoit la liste de nombre aux processus
			 *
			 * Correspond a :
			 * " 1. Envoyer toutes les valeurs de Vi qui n’ont pas encore été envoyées par pi à tous les 
			 * autres processus "
			 */
			for (Processus p : this.system.getOthersProc(this)) {
				p.receiveNumber(sendList);
			}
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

		this.msgSended = false;
		
		String message = "Le processus "+this.id+" reçoit ";

		// Pas possible de contains car nouvelle objet 
		boolean addNumber = true;

		for (Number sended : sendList) {

			message += sended.getNumber()+", ";

			for (Number n : listNumber) {

				if (n.getNumber() == sended.getNumber()) {

					addNumber = false;
				}
			}

			if (addNumber) {

				this.listNumber.add(sended);

			}
		}

		System.out.println(message);

	}

	public boolean willICrashed() {

		if (Math.random()<0.2) {

			return true;

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

	

}