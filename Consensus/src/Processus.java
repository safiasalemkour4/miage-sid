import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;

/**
 * Processus class
 * Represent one process
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 */

public class Processus extends Thread {

	/** The system */
	private SystemProcess system;

	/** The list of the number */
	private ArrayList<Number> listNumber;

	/** The list of the number receive */
	private ArrayList<Number> listNumberReceive;

	/** The name of the process (thread) */
	private String name;

	/** Boolean indicate if the processus is alive or not */
	private boolean isCrashed;

	/**
	 * Constructeur of a process
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

		try {

			/* While phase k < Nb Process crashed + 1 */ 
			while(!this.system.itIsTheEnd()) {

				/* We wait other process */
				this.system.getBarrier().await();

				/* If the thread is alive */
				if (!isCrashed) {

					/* The process wait for his turn */
					while (!this.system.itIsMyTurn(this)) {
						Thread.yield();
						Thread.sleep(10);
					}

					/* The process send numbers */
					sendNumber();

				} 

				/* We wait other process for the end oh the phase */
				this.system.getBarrierPhase().await();

				/* Give the hand o the system (to close the barrier and so the turn) */
				Thread.sleep(10);
				this.system.getBarrierPhase().await();

			}

		} catch (InterruptedException e) {
		} catch (BrokenBarrierException e) {}
	}

	/**
	 * Method sendNumber
	 * Send the number to orher process of the system
	 * @throws InterruptedException
	 */

	public void sendNumber() throws InterruptedException {

		/* The message */
		String message = "Le processus "+this.name+" envoit ";

		/* The crash message */
		String crash = "";

		/* The list of a number it process will have to send */
		ArrayList<Number> sendList = new ArrayList<Number>();

		/* We look if the process crashed */

		/* if the process crashed */
		if (willICrashed()) {

			this.isCrashed = true;
			crash = " et il se crash !";

			/* We build the list of number we have to send */
			for (Number n : listNumber) {

				/* If the message wiil crash */
				if (this.willThisMessageCrash()) {} 

				/* else */
				else {

					/* If the message was not send before */
					if (!n.isSended()) {
						
						sendList.add(n);
						n.setSended();
						message += +n.getValue()+", ";
					}
				}
			}

			message = message.substring(0, message.length()-2);
		} 

		/* If the process is still alive */
		else {

			/* We update the message */

			for (Number n : listNumber) {

				/* If the message was not send before */
				if (!n.isSended()) {
					
					sendList.add(n);
					n.setSended();
					message += +n.getValue()+", ";
				}
			}

			if (sendList.isEmpty()) {

				message += "rien de nouveau  ";
			}
			
			message = message.substring(0, message.length()-2);
			message += " a tout le monde";
		}


		
		/* We display the message */
		system.displayInfos(message+crash);

		/* It's the end of the turn of the process */
		system.finishMyTurn(this);

		/* We send the numbers to other process
		 *
		 * Correspond to :
		 * " 1. Envoyer toutes les valeurs de Vi qui n’ont pas encore été envoyées par pi à tous les autres processus "
		 */

		for (Processus p : this.system.getOthersProc(this)) {

			/* If the process is still alive */
			if (!p.isCrashed()) {
				p.receiveNumber(sendList);
			}
		}


		if (!this.isCrashed) {

			/* The process wait for is turn */
			while (!this.system.itIsMyTurn(this)) {

				Thread.yield();
				Thread.sleep(10);
			}


			/* We display what the process receive */

			message = "Le processus "+this.name+" reçoit ";

			for (Number n : listNumberReceive) {

				message += n.getValue()+", ";
				this.addNumber(n.getValue());
			}

			if (!this.listNumberReceive.isEmpty()) {

				message = message.substring(0, message.length()-2);

			} else {

				message += "rien de nouveau par rapport a la phase precedente";
			}

			/* We display the message */
			system.displayInfos(message);

		}

		/* We clear the list */
		this.listNumberReceive.clear();

		/* Only process alive can finish the turn */

		if (!this.isCrashed) {

			system.finishMyTurn(this);
		}
	}

	/**
	 * Method receiveNumber
	 * @param sendList the list of number
	 */

	public void receiveNumber(ArrayList<Number> sendList) {

		/* We can't use the method contains because it's not the same references */

		boolean addNumber = true;

		for (Number sended : sendList) {

			for (Number n : listNumberReceive) {

				if (n.getValue() == sended.getValue()) {

					addNumber = false;
				}
			}

			/* If the number is not in the list of number receive (int this turn) by other process */
			if (addNumber) {

				this.listNumberReceive.add(sended);

			}
		}
	}

	/**
	 * Method addNumber
	 * @param numberValue the value of he number
	 */

	public void addNumber(int numberValue) {

		boolean addNumber = true;


		for (Number n : listNumber) {

			if (n.getValue() == numberValue) {

				addNumber = false;
			}
		}

		/* If the process list number not contains this number */
		if (addNumber) {

			this.listNumber.add(new Number(numberValue));

		}
	}

	/**
	 * Method getMinNumber
	 * @return the minimum value of the list of number
	 */

	public int getMinNumber() {

		int res = listNumber.get(0).getValue();

		for (Number n : listNumber) {

			if (n.getValue() < res) {

				res = n.getValue();
			}
		}

		return res;
	}

	/**
	 * Method willICrashed
	 * @return true if the process will crash, else false
	 */

	public boolean willICrashed() {

		if (Math.random()<0.2) {

			/* If the is less than 20% process crashed in the system */
			if (system.checkCrashPourcent()<0.2) {

				return true;

			} else {

				return false;
			}

		} else {

			return false;
		}
	}

	/**
	 * Method willThisMessageCrash
	 * @return true if the message will crash, else false
	 */

	private boolean willThisMessageCrash() {

		if (Math.random()<0.2) {

			return true;

		} else {

			return false;
		}
	}

	/**
	 * Getter Name
	 * @return the name of the process
	 */

	public String getMyName() {

		return name;
	}

	/**
	 * Getter Crashed
	 * @return true if the process is crashed
	 */

	public boolean isCrashed() {

		return isCrashed;
	}

	/**
	 * Setter Crashed
	 * @param isCrashed the state of the process
	 */
	public void setCrashed(boolean isCrashed) {

		this.isCrashed = isCrashed;
	}
}