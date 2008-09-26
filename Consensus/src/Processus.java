import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;


public class Processus extends Thread {

		private SystemProcess system;
		
		private ArrayList<Integer> listNumber;
		
		private String id;

		/**
		 * Constructeur
		 */
		
		public Processus(String id, SystemProcess system) {

			this.id = id;
			this.system=system;

			this.listNumber = new ArrayList<Integer>();
			
			this.listNumber.add(new Integer((int) (Math.random()*10)));

		}
		
		/**
		 * Methode Run
		 */

		public void run() {

			while (true) {

				try {
					// Escequ'il va cracher ?
					// Si oui, il envoit une partie a des proc alea ?
					
					this.system.getBarrier().await();
					
					sleep(10);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					
				} catch (BrokenBarrierException e) {

					e.printStackTrace();
				}
			}
		}
		
		public void sendFirstNumber() {
			
			int firstNumber = this.listNumber.get(0);
			System.out.println("Le processus "+this.getId()+" envoit "+firstNumber+" a tout le monde");
			
			ArrayList<Processus> listProc = this.system.getOthersProc(this);
			
			for (Processus p : listProc) {
				
				p.receiveNumber(firstNumber);
			}
			
		}
		
		public void sendNumber(int i) {
			
			System.out.println("Le processus "+this.getId()+" envoit "+i+" a tout le monde");
			
			ArrayList<Processus> listProc = this.system.getOthersProc(this);
			
			for (Processus p : listProc) {
				
				p.receiveNumber(i);
			}
		}
		
		// Si chiffre rien
		//sinon add tab + renvoyer au autre
		
		public void receiveNumber(int number) {
		
			System.out.println("Le processus "+this.getId()+" reçoit "+number);
			
			// Pas possible de contains car nouvelle objet 
			boolean addNumber = true;
			
			for (Integer i : listNumber) {
				
				if (i.intValue() == number) {
					
					addNumber = false;
				}
			}
			
			if (addNumber) {
				
				this.listNumber.add(new Integer(number));
				
				this.sendNumber(number);
			}
		}


	}