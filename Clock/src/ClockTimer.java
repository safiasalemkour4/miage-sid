import java.util.Calendar;
import java.util.Observable;

/**
 * Class ClockTimer
 * Etend la class Observable
 * @author Arnaud Knobloch
 */

public class ClockTimer extends Observable {

	/* Les attributs de notre timer */
	private int hour, minute, second;

	/**
	 * Constructeur
	 */
	
	public ClockTimer() {

		/* On cree l'objet Calendar permettant de recuperer proprement les informations dont on a besoin */

		Calendar calendar = Calendar.getInstance();
		 
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
	}

	/**
	 * Methode start
	 * Permet de demarrer notre timer
	 */
	
	public void start() {

		new Timer(this);
	}

	/**
	 * Getter Hour
	 * @return hour
	 */
	
	public int getHour() {
		
		return hour;
	}

	/**
	 * Getter Minute
	 * @return minute
	 */
	
	public int getMinute() {
		
		return minute;
	}

	/**
	 * Getter Second
	 * @return second
	 */
	
	public int getSecond() {
		
		return second;
	}
	
	/**
	 * Setter Hour
	 * @param hour
	 */
	
	public void setHour(int hour) {
		
		this.hour = hour;
	}

	/**
	 * Setter Minute
	 * @param minute
	 */
	
	public void setMinute(int minute) {
		
		this.minute = minute;
	}

	/**
	 * Setter Second
	 * @param second
	 */

	public void setSecond(int second) {
		
		this.second = second;
	}

	/**
	 * Class interne
	 * Etend la class Thread
	 * @author Arnaud Knobloch
	 */
	
	public class Timer extends Thread {

		/* Instance de notre class sujet (Observable) */
		private ClockTimer clockTimer;

		/**
		 * Constructeur
		 * @param clockTimer
		 */
		
		public Timer(ClockTimer clockTimer) {

			this.clockTimer = clockTimer;
			this.start();
		}

		/**
		 * Methode Run
		 */

		public void run() {

			while (true) {

				try {

					/* On specifie que l'objet a changer */
					setChanged();

					/* On attend 1s */
					Thread.sleep(1000);

					/* On notifie les Observers */
					this.clockTimer.notifyObservers();

					/* MAJ des attributs de notre Timer */
					
					if (this.clockTimer.getSecond()<59) {

						this.clockTimer.setSecond(this.clockTimer.getSecond()+1);

					} else {

						this.clockTimer.setSecond(0);

						if (this.clockTimer.getMinute()<59) {

							this.clockTimer.setMinute(this.clockTimer.getMinute()+1);

						} else {

							this.clockTimer.setMinute(0);

							if (this.clockTimer.getHour()<23) {

								this.clockTimer.setHour(this.clockTimer.getHour()+1);

							} else {

								this.clockTimer.setHour(0);
							}
						}
					}

				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
}
