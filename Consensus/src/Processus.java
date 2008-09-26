import java.util.ArrayList;
import java.util.Date;


public class Processus extends Thread {

		private System system;
		
		private ArrayList<Integer> listNumber;

		/**
		 * Constructeur
		 */
		
		public Processus(System system) {

			this.system=system;
			
			this.listNumber = new ArrayList<Integer>();
			
			this.listNumber.add(new Integer((int) (Math.random()*10)));
			
			this.start();
		}

		/**
		 * Methode Run
		 */

		public void run() {

			while (true) {

				try {


				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		public void sendNumber() {
			
			ArrayList<Processus> listProc = this.system.getOthersProc(this);
			
			for (Processus p : listProc) {
				
				p.
			}
		}
		
		// Si chiffre rien
		//sinon add tab + renvoyer au autre
		
		public void receiveNumber() {
			
			
		}
	}