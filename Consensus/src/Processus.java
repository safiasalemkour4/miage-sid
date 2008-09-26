import java.util.Date;


public class Processus extends Thread {

	

		/**
		 * Constructeur
		 */
		
		public Processus() {

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
	}